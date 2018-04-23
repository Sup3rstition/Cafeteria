package controllers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javax.swing.text.NumberFormatter;

import connection.Lunchhourdb;
import entities.Studentinfo;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;

public class CheckHistoryController implements Initializable {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
    @FXML
    private Button savebtn;


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
    private Label actionStatus;

    @FXML
    private ComboBox<Studentinfo> Selectedstudent;
    private ArrayList<Studentinfo> stdlist = new ArrayList<Studentinfo>();

    @FXML
    void backtoorder(ActionEvent event) throws IOException {
    	list.clear();
     	((Node)(event.getSource())).getScene().getWindow().hide();
    }
    void setList(ObservableList<Studentinfo> item) {
    	Studentinfo all = new Studentinfo();
    	all.setFirstname("All");
    	all.setLastname("Students");
    	all.setSection("all");
  		stdlist.add(all);
    	stdlist.addAll(item);
    	Selectedstudent.setItems(FXCollections.observableArrayList(stdlist));
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
		orderdate(EndingDate);
		Selectedstudent.setItems(FXCollections.observableList(stdlist));
	        searchid.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
		
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
	
    private void saveFileRoutine(File file)
			throws IOException{
		// Creates a new file and writes the txtArea contents into it
    	TextArea texts = new TextArea();
  	  String Titles = "Order ID,Order Date,Name,Menu Item,Additionals,Extra Items,Total\n";
  	  texts.appendText(Titles);
  	  orderhistory.getItems().stream().forEach((o)
                -> {
              	  String text = orderIdcol.getCellData(o)+ "," +order.getCellData(o)+ "," +name.getCellData(o)+ "," +menuitem.getCellData(o)+ "," +add.getCellData(o).replaceAll(",", ". ")+ "," +extra.getCellData(o).replaceAll(",", ". ")+ "," +total.getCellData(o) + "\n";
              	  texts.appendText(text);
                });
                ObservableList<CharSequence> paragraph = texts.getParagraphs();
                Iterator<CharSequence>  iter = paragraph.iterator();
                try
                {
                    BufferedWriter bf = new BufferedWriter(new FileWriter(file));
                    while(iter.hasNext())
                    {
                        CharSequence seq = iter.next();
                        bf.append(seq);
                        bf.newLine();
                    }
                    bf.flush();
                    bf.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
		file.createNewFile();
	}
    @FXML
    void save(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Save file");
    	fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Csv", "*.Csv"));
    	fileChooser.setInitialFileName("Orders_" + LocalDate.now().toString() + ".Csv");
    	File savedFile = fileChooser.showSaveDialog(((Node)(event.getSource())).getScene().getWindow());

    	if (savedFile != null) {

    	    try {
    	        saveFileRoutine(savedFile);
    	    }
    	    catch(IOException e) {
    		
    	        e.printStackTrace();
    	        Alert alert = new Alert(AlertType.ERROR);
    	        alert.setTitle("Error");
    	        alert.setHeaderText("Saving Error...");
    	        alert.setContentText("An error has occured while trying to save file! Please try again!");

    	        alert.showAndWait();
    	        return;
    	    }
    	    Alert alert = new Alert(AlertType.INFORMATION);
    	    alert.setTitle("File Saved");
    	    alert.setHeaderText(null);
    	    alert.setContentText("File saved: " + savedFile.toString());

    	    alert.showAndWait();
    	}
    	else {
    		 Alert alert = new Alert(AlertType.INFORMATION);
     	    alert.setTitle("File Save canceled");
     	    alert.setHeaderText(null);
     	    alert.setContentText("Saving Cancelled");
    	}
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
	void searchbydates() {
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
    				Alert Error2= new Alert(AlertType.ERROR, "An error has occured while connecting with the database.\n Please check your internet connection and try again.");
        	   		Error2.setTitle("Error");
        	   		Error2.setHeaderText("Connection Error!");
        	   		Error2.showAndWait();
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
		    		ps.setDate(3, Date.valueOf(today.plusDays(6)));
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
					Alert Error2= new Alert(AlertType.ERROR, "An error has occured while connecting with the database.\n Please check your internet connection and try again.");
	    	   		Error2.setTitle("Error");
	    	   		Error2.setHeaderText("Connection Error!");
	    	   		Error2.showAndWait();
				}
	    	} 
	}
	void searchbyid() {
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
					Alert Error2= new Alert(AlertType.ERROR, "An error has occured while connecting with the database.\n Please check your internet connection and try again.");
	    	   		Error2.setTitle("Error");
	    	   		Error2.setHeaderText("Connection Error!");
	    	   		Error2.showAndWait();
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
					Alert Error2= new Alert(AlertType.ERROR, "An error has occured while connecting with the database.\n Please check your internet connection and try again.");
	    	   		Error2.setTitle("Error");
	    	   		Error2.setHeaderText("Connection Error!");
	    	   		Error2.showAndWait();
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
					Alert Error2= new Alert(AlertType.ERROR, "An error has occured while connecting with the database.\n Please check your internet connection and try again.");
	    	   		Error2.setTitle("Error");
	    	   		Error2.setHeaderText("Connection Error!");
	    	   		Error2.showAndWait();
				}
	    	}
	}
	void searchbystudent(String searchorderid) {
		//checks if search is filled then searchs if the starting dates are filled in or just the search id.
		if(!VALID_WORD.matcher(searchorderid).matches() && !searchorderid.equals("") && !searchorderid.contains(" ")){
    		//checks if the dates are filled in
    		 if(StartingDate.getValue() != null && EndingDate.getValue() !=null) {
    	    		try {    		
    	    			conn = Lunchhourdb.get();
    	    			System.out.println(parentid);
    	    			String sql = "Select * from Cafeteria.Order Where `Parent ID` = ? AND ID = ? AND `Student ID` = ?  AND `Order_Date` BETWEEN ?  AND ? ";
    					ps = conn.prepareStatement(sql);
    					ps.setInt(1, parentid);
    					ps.setInt(2, Integer.parseInt(searchid.getText().trim()));
    					ps.setInt(3, Selectedstudent.getValue().getId());
    		    		ps.setDate(4,Date.valueOf(StartingDate.getValue()));
    		    		ps.setDate(5, Date.valueOf(EndingDate.getValue()));
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
    					Alert Error2= new Alert(AlertType.ERROR, "An error has occured while connecting with the database.\n Please check your internet connection and try again.");
    	    	   		Error2.setTitle("Error");
    	    	   		Error2.setHeaderText("Connection Error!");
    	    	   		Error2.showAndWait();
    				}
    	    		//checks to see if only starting date is filled
    	    	}else if(EndingDate.getValue() != null && StartingDate.getValue() == null) { //checks to see if only the ending date is filled in
       			 incorrectlabel.setVisible(true);											// sets incorrect day error.
    	    	}
    		 else if(StartingDate.getValue() != null) {
    	    		try {    		
    	    			conn = Lunchhourdb.get();
    	    			System.out.println(parentid);
    	    			String sql = "Select * from Cafeteria.Order Where `Parent ID` = ? AND ID = ? AND `Order_Date` BETWEEN ?  AND ?  AND `Student ID` = ? ";
    					ps = conn.prepareStatement(sql);
    					ps.setInt(1, parentid);
    					ps.setInt(2, Integer.parseInt(searchid.getText().trim()));
    		    		ps.setDate(3,Date.valueOf(StartingDate.getValue()));
    		    		ps.setDate(4, Date.valueOf(today.plusDays(7)));
    		    		ps.setInt(5, Selectedstudent.getValue().getId());
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
    					Alert Error2= new Alert(AlertType.ERROR, "An error has occured while connecting with the database.\n Please check your internet connection and try again.");
    	    	   		Error2.setTitle("Error");
    	    	   		Error2.setHeaderText("Connection Error!");
    	    	   		Error2.showAndWait();
    				}
    	    	}
    		 //if only orderid is filled in
    	    	else {
    	    		try {    		
    	    			conn = Lunchhourdb.get();
    	    			System.out.println(parentid);
    	    			String sql = "Select * from Cafeteria.Order Where `Parent ID` = ? AND `ID` = ?  AND `Student ID` = ? ";
    					ps = conn.prepareStatement(sql);
    					ps.setInt(1, parentid);
    					ps.setInt(2, Integer.parseInt(searchid.getText()));
    					ps.setInt(3, Selectedstudent.getValue().getId());
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
    					Alert Error2= new Alert(AlertType.ERROR, "An error has occured while connecting with the database.\n Please check your internet connection and try again.");
    	    	   		Error2.setTitle("Error");
    	    	   		Error2.setHeaderText("Connection Error!");
    	    	   		Error2.showAndWait();
    				}
    	    	}
	}else if(StartingDate.getValue() != null || EndingDate.getValue() !=null) {
		if(StartingDate.getValue() != null && EndingDate.getValue() != null) {
			if(!StartingDate.getValue().isAfter(EndingDate.getValue())){
    			try {    		
        			conn = Lunchhourdb.get();
        			System.out.println(parentid);
        			String sql = "Select * from Cafeteria.Order Where `Parent ID` = ? AND `Order_Date` BETWEEN ?  AND ?  AND `Student ID` = ?  ";
    				ps = conn.prepareStatement(sql);
    				ps.setInt(1, parentid);
    	    		ps.setDate(2,Date.valueOf(StartingDate.getValue()));
    	    		ps.setDate(3, Date.valueOf(EndingDate.getValue()));
    	    		ps.setInt(4, Selectedstudent.getValue().getId());
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
    				Alert Error2= new Alert(AlertType.ERROR, "An error has occured while connecting with the database.\n Please check your internet connection and try again.");
        	   		Error2.setTitle("Error");
        	   		Error2.setHeaderText("Connection Error!");
        	   		Error2.showAndWait();
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
	    			String sql = "Select * from Cafeteria.Order Where `Parent ID` = ? AND `Order_Date` BETWEEN ?  AND ?  AND `Student ID` = ?  ";
					ps = conn.prepareStatement(sql);
					ps.setInt(1, parentid);
		    		ps.setDate(2,Date.valueOf(StartingDate.getValue()));
		    		ps.setDate(3, Date.valueOf(today.plusDays(7)));
		    		ps.setInt(4, Selectedstudent.getValue().getId());
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
					Alert Error2= new Alert(AlertType.ERROR, "An error has occured while connecting with the database.\n Please check your internet connection and try again.");
	    	   		Error2.setTitle("Error");
	    	   		Error2.setHeaderText("Connection Error!");
	    	   		Error2.showAndWait();
				}
	    	} 
	} else {
		try {
			conn = Lunchhourdb.get();
			System.out.println(parentid);
			String sql = "Select * from Cafeteria.Order Where `Parent ID` = ?  AND `Student ID` = ? ";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, parentid);
			ps.setInt(2, Selectedstudent.getValue().getId());
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
			Alert Error2= new Alert(AlertType.ERROR, "An error has occured while connecting with the database.\n Please check your internet connection and try again.");
	   		Error2.setTitle("Error");
	   		Error2.setHeaderText("Connection Error!");
	   		Error2.showAndWait();
		}
	}
		
	}
	void searchall() {
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
			Alert Error2= new Alert(AlertType.ERROR, "An error has occured while connecting with the database.\n Please check your internet connection and try again.");
	   		Error2.setTitle("Error");
	   		Error2.setHeaderText("Connection Error!");
	   		Error2.showAndWait();
		}
	}
	LocalDate today = LocalDate.now();
	@FXML
    void search(ActionEvent event) {
    	list.clear();
		incorrectlabel.setVisible(false);
		String searchorderid = searchid.getText();
		//if specific student is selected it will search by them first.
		if(!Selectedstudent.getValue().getSection().equals("all")) {
			searchbystudent(searchorderid);
		}
		//Search id Search
		//checks to see if the searchid is blank
		else if(!VALID_WORD.matcher(searchorderid).matches() && !searchorderid.equals("") && !searchorderid.contains(" ")){
			searchbyid();
    	}
    	
    	// next type of search dates
    	//checks to see if only the starting date is filled in
    	else if(StartingDate.getValue() != null || EndingDate.getValue() !=null) {
    		searchbydates();
    	
    }else {
    		searchall();
    	}
    }

}
