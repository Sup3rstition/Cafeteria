package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import connection.Lunchhourdb;
import entities.Studentinfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class CheckHistoryController implements Initializable {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
    @FXML
    private Button savebtn;

    @FXML
    private Button printbtn;

    @FXML
    private Button Backbtn;

    @FXML
    private Button searchbtn;
    
    @FXML
    private DatePicker StartingDate;

    @FXML
    private DatePicker EndingDate;

    @FXML
    private TextField searchid;

    @FXML
    private ComboBox<Studentinfo> Selectedstudent;
    
    @FXML
    void backtoorder(ActionEvent event) throws IOException, SQLException {
    	list.clear();
     	((Node)(event.getSource())).getScene().getWindow().hide();
    }
    ObservableList<Studentinfo> items;
    void setList(ObservableList<Studentinfo> item) {
    	this.items = item;
    	Studentinfo all = new Studentinfo();
    	all.setFirstname("All");
    	all.setLastname("Students");
    	items.add(0, all);
    	Selectedstudent.setItems(items);
    	Selectedstudent.getSelectionModel().selectFirst();
    	Selectedstudent.setCellFactory(new Callback<ListView<Studentinfo>,ListCell<Studentinfo>>(){
    	 public ListCell<Studentinfo> call(ListView<Studentinfo> l) {
         	return new ListCell<Studentinfo>() {

                 @Override
                 protected void updateItem(Studentinfo t, boolean bln) {
                     super.updateItem(t, bln);
                      
                     if(t != null){
                         setText(t.getFirstname() + " " + t.getLastname());
                     }else{
                         setText(null);
                     }
                 }

             };
         }
	 });
	 Selectedstudent.setConverter(new StringConverter<Studentinfo>() {
      @Override
      public String toString(Studentinfo user) {
        if (user == null){
          return null;
        } else {
          return (user.getFirstname() + " " + user.getLastname());
        }
      }

    @Override
    public Studentinfo fromString(String userId) {
        return null;
    }
});
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		orderdate(StartingDate);
		Selectedstudent.setItems(items);
		order.setCellValueFactory(new PropertyValueFactory<>("menudate"));
		order.setCellFactory(new CheckHistoryController.ColumnFormatter<>(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		orderIdcol.setCellValueFactory(new PropertyValueFactory<Cart, Integer>("orderid"));
		name.setCellValueFactory(new PropertyValueFactory<Cart, String>("fullname"));
		menuitem.setCellValueFactory(new PropertyValueFactory<Cart, String>("menuitem"));
		add.setCellValueFactory(new PropertyValueFactory<Cart, String>("add"));
		extra.setCellValueFactory(new PropertyValueFactory<Cart, String>("extra"));
		total.setCellValueFactory(new PropertyValueFactory<Cart, Double>("total"));
	}
	
	private class ColumnFormatter<S, T> implements Callback<TableColumn<S, T>, TableCell<S, T>> {

	    private final DateTimeFormatter format;

	    public ColumnFormatter(DateTimeFormatter format) {
	        super();
	        this.format = format;
	    }

	    @Override
	    public TableCell<S, T> call(TableColumn<S, T> arg0) {
	        return new TableCell<S, T>() {
	            @Override
	            protected void updateItem(T item, boolean empty) {
	                super.updateItem(item, empty);
	                if (item == null || empty) {
	                    setGraphic(null);
	                } else {
	                    LocalDate ld = (LocalDate) item;
	                    String val = ld.format(format);
	                    setGraphic(new Label(val));
	                }
	            }
	        };
	    }
	}
	
    @FXML
    void print(ActionEvent event) {
    	Alert a = new Alert(AlertType.INFORMATION);
    	a.setContentText("Sent to printer");
    	a.showAndWait();
    }

    @FXML
    void save(ActionEvent event) {
    	Alert a = new Alert(AlertType.INFORMATION);
    	a.setContentText("Saved under documents");
    	a.showAndWait();
    }

    @FXML
    private TableView<Cart> orderhistory;

    @FXML
    private TableColumn<Cart, LocalDate> order;
    @FXML
    private TableColumn<Cart, Integer> orderIdcol;
    @FXML
    private TableColumn<Cart, String> name;

    @FXML
    private TableColumn<Cart, String> menuitem;

    @FXML
    private TableColumn<Cart, String> add;

    @FXML
    private TableColumn<Cart, String> extra;

    @FXML
    private TableColumn<Cart, Double> total;
    @FXML
    private Label incorrectlabel;
    @FXML
    private Button Clear;

    @FXML
    void Clearboxes(ActionEvent event) {
    	Selectedstudent.getSelectionModel().selectFirst();
    	StartingDate.setValue(null);
    	EndingDate.setValue(null);
    	searchid.clear();
    	list.clear();
    }
    private int parentid;
    void setparentid(int id) {
    	this.parentid = id;
    }
	private ObservableList<Cart> list = FXCollections.observableArrayList();
	private static final Pattern VALID_WORD = Pattern.compile("^[A-Za-z]*$ ");
	
	private void orderdate(DatePicker date) {
        final Callback<DatePicker, DateCell> dayCellFactory = 
            new Callback<DatePicker, DateCell>() {
                @Override
                public DateCell call(final DatePicker datePicker) {
                    return new DateCell() {
                        @Override
                        public void updateItem(LocalDate item, boolean empty) {
                            super.updateItem(item, empty);
                            LocalDate now = LocalDate.now();
                            DayOfWeek day = DayOfWeek.from(item);
                            if (item.isAfter(now.with(TemporalAdjusters.next(DayOfWeek.FRIDAY))) || day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY
                                ) {
                                    setDisable(true);
                                    setStyle("-fx-background-color: #ffc0cb;");
                            }
                            
                            
                    }
                };
            }
	};
	 date.setDayCellFactory(dayCellFactory);
	}
    @FXML
    void search(ActionEvent event) {
    	list.clear();
		LocalDate today = LocalDate.now();
		incorrectlabel.setVisible(false);
		String searchorderid = searchid.getText();
		//Search id Search
		//checks to see if the searchid is blank
    	if(!VALID_WORD.matcher(searchorderid).matches() && !searchorderid.equals("") && !searchorderid.contains(" ")){
    		//checks if the dates are filled in
    		 if(StartingDate.getValue() != null && EndingDate.getValue() !=null) {
    	    		try {    		
    	    			conn = Lunchhourdb.get();
    	    			System.out.println(parentid);
    	    			String sql = "Select * from Cafeteria.Order Where `Parent ID` = ? AND ID = ? AND `Order_Date` BETWEEN ?  AND ? ";
    					ps = conn.prepareStatement(sql);
    					ps.setInt(1, parentid);
    					ps.setInt(2, Integer.parseInt(searchid.getText().trim()));
    		    		ps.setDate(3,Date.valueOf(StartingDate.getValue()));
    		    		ps.setDate(4, Date.valueOf(EndingDate.getValue()));
    		    		rs = ps.executeQuery();
    		    		while(rs.next()) {
    		    			Cart history = new Cart();
    		    			history.setFullname(rs.getString("Studentname"));
    						history.setMenuitem(rs.getString("Menu Item"));
    						if(rs.getString("Additional") != null) {
    							history.setAdd(rs.getString("Additional").replaceAll("/", ","));
    							}else {
    								history.setAdd("0");
    							}
    							if(rs.getString("Extra") != null) {
    							history.setExtra(rs.getString("Extra").replaceAll("/", ","));
    							}else {
    								history.setExtra("0");
    							}
    						history.setTotal(rs.getDouble("Total"));
    						history.setMenudate((rs.getDate("Order_Date").toLocalDate()));
    						history.setOrderid(rs.getInt("ID"));
    		    			list.add(history);
    		    		}
    		    		orderhistory.setItems(list);
    				} catch (SQLException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    	    		//checks to see if only starting date is filled
    	    	}else if(EndingDate.getValue() != null && StartingDate.getValue() == null) { //checks to see if only the ending date is filled in
       			 incorrectlabel.setVisible(true);											// sets incorrect day error.
    	    	}
    		 else if(StartingDate.getValue() != null) {
    	    		try {    		
    	    			conn = Lunchhourdb.get();
    	    			System.out.println(parentid);
    	    			String sql = "Select * from Cafeteria.Order Where `Parent ID` = ? AND ID = ? AND `Order_Date` BETWEEN ?  AND ? ";
    					ps = conn.prepareStatement(sql);
    					ps.setInt(1, parentid);
    					ps.setInt(2, Integer.parseInt(searchid.getText().trim()));
    		    		ps.setDate(3,Date.valueOf(StartingDate.getValue()));
    		    		ps.setDate(4, Date.valueOf(today.plusDays(7)));
    		    		rs = ps.executeQuery();
    		    		while(rs.next()) {
    		    			Cart history = new Cart();
    		    			history.setFullname(rs.getString("Studentname"));
    						history.setMenuitem(rs.getString("Menu Item"));
    						if(rs.getString("Additional") != null) {
    							history.setAdd(rs.getString("Additional").replaceAll("/", ","));
    							}else {
    								history.setAdd("0");
    							}
    							if(rs.getString("Extra") != null) {
    							history.setExtra(rs.getString("Extra").replaceAll("/", ","));
    							}else {
    								history.setExtra("0");
    							}
    						history.setTotal(rs.getDouble("Total"));
    						history.setMenudate((rs.getDate("Order_Date").toLocalDate()));
    						history.setOrderid(rs.getInt("ID"));
    		    			list.add(history);
    		    		}
    		    		orderhistory.setItems(list);
    				} catch (SQLException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    	    	}
    		 //if only orderid is filled in
    	    	else {
    	    		try {    		
    	    			conn = Lunchhourdb.get();
    	    			System.out.println(parentid);
    	    			String sql = "Select * from Cafeteria.Order Where `Parent ID` = ? AND `ID` = ? ";
    					ps = conn.prepareStatement(sql);
    					ps.setInt(1, parentid);
    					ps.setInt(2, Integer.parseInt(searchid.getText()));
    		    		rs = ps.executeQuery();
    		    		while(rs.next()) {
    		    			Cart history = new Cart();
    		    			history.setFullname(rs.getString("Studentname"));
    						history.setMenuitem(rs.getString("Menu Item"));
    						if(rs.getString("Additional") != null) {
    							history.setAdd(rs.getString("Additional").replaceAll("/", ","));
    							}else {
    								history.setAdd("0");
    							}
    							if(rs.getString("Extra") != null) {
    							history.setExtra(rs.getString("Extra").replaceAll("/", ","));
    							}else {
    								history.setExtra("0");
    							}
    						history.setTotal(rs.getDouble("Total"));
    						history.setMenudate((rs.getDate("Order_Date").toLocalDate()));
    						history.setOrderid(rs.getInt("ID"));
    		    			list.add(history);
    		    		}
    		    		orderhistory.setItems(list);
    				} catch (SQLException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    	    	}
    	}
    	
    	// next type of search dates
    	//checks to see if only the starting date is filled in
    	else if(StartingDate.getValue() != null || EndingDate.getValue() !=null) {
    		if(StartingDate.getValue() != null && EndingDate.getValue() != null) {
    			if(!StartingDate.getValue().isAfter(EndingDate.getValue())){
        			try {    		
            			conn = Lunchhourdb.get();
            			System.out.println(parentid);
            			String sql = "Select * from Cafeteria.Order Where `Parent ID` = ? AND `Order_Date` BETWEEN ?  AND ? ";
        				ps = conn.prepareStatement(sql);
        				ps.setInt(1, parentid);
        	    		ps.setDate(2,Date.valueOf(StartingDate.getValue()));
        	    		ps.setDate(3, Date.valueOf(EndingDate.getValue()));
        	    		rs = ps.executeQuery();
        	    		while(rs.next()) {
        	    			Cart history = new Cart();
        	    			history.setFullname(rs.getString("Studentname"));
        					history.setMenuitem(rs.getString("Menu Item"));
        					if(rs.getString("Additional") != null) {
        						history.setAdd(rs.getString("Additional").replaceAll("/", ","));
        						}else {
        							history.setAdd("0");
        						}
        						if(rs.getString("Extra") != null) {
        						history.setExtra(rs.getString("Extra").replaceAll("/", ","));
        						}else {
        							history.setExtra("0");
        						}
        					history.setTotal(rs.getDouble("Total"));
        					history.setMenudate((rs.getDate("Order_Date").toLocalDate()));
        					history.setOrderid(rs.getInt("ID"));
        	    			list.add(history);
        	    		}
        	    		orderhistory.setItems(list);
        			} catch (SQLException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
        		}else {
        			incorrectlabel.setVisible(true);
        		}
        	}
    		else if(EndingDate.getValue() != null && StartingDate.getValue() == null) { //checks to see if only the ending date is filled in
        			 incorrectlabel.setVisible(true);											// sets incorrect day error.
    		}
    	//runs search for starting date up till today plus 7 days
    		
    		else if(StartingDate.getValue() !=null) {
    	    		try {
    	    			conn = Lunchhourdb.get();
    	    			System.out.println(parentid);
    	    			String sql = "Select * from Cafeteria.Order Where `Parent ID` = ? AND `Order_Date` BETWEEN ?  AND ? ";
    					ps = conn.prepareStatement(sql);
    					ps.setInt(1, parentid);
    		    		ps.setDate(2,Date.valueOf(StartingDate.getValue()));
    		    		ps.setDate(3, Date.valueOf(today.plusDays(7)));
    		    		rs = ps.executeQuery();
    		    		while(rs.next()) {
    		    			Cart history = new Cart();
    		    			history.setFullname(rs.getString("Studentname"));
    						history.setMenuitem(rs.getString("Menu Item"));
    						if(rs.getString("Additional") != null) {
    							history.setAdd(rs.getString("Additional").replaceAll("/", ","));
    							}else {
    								history.setAdd("0");
    							}
    							if(rs.getString("Extra") != null) {
    							history.setExtra(rs.getString("Extra").replaceAll("/", ","));
    							}else {
    								history.setExtra("0");
    							}
    						history.setTotal(rs.getDouble("Total"));
    						history.setMenudate((rs.getDate("Order_Date").toLocalDate()));
    						history.setOrderid(rs.getInt("ID"));
    		    			list.add(history);
    		    		}
    		    		orderhistory.setItems(list);
    				} catch (SQLException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    	    	} 
    	
    }else {
    		try {
    			conn = Lunchhourdb.get();
    			System.out.println(parentid);
    			String sql = "Select * from Cafeteria.Order Where `Parent ID` = ?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, parentid);
	    		rs = ps.executeQuery();
	    		while(rs.next()) {
	    			Cart history = new Cart();
	    			history.setFullname(rs.getString("Studentname"));
					history.setMenuitem(rs.getString("Menu Item"));
					if(rs.getString("Additional") != null) {
					history.setAdd(rs.getString("Additional").replaceAll("/", ","));
					}else {
						history.setAdd("0");
					}
					if(rs.getString("Extra") != null) {
					history.setExtra(rs.getString("Extra").replaceAll("/", ","));
					}else {
						history.setExtra("0");
					}
					history.setTotal(rs.getDouble("Total"));
					history.setMenudate((rs.getDate("Order_Date").toLocalDate()));
					history.setOrderid(rs.getInt("ID"));
	    			list.add(history);
	    		}
	    		orderhistory.setItems(list);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }

}
