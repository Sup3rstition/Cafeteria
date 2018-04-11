package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import connection.Lunchhourdb;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

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
    private ComboBox<?> Selectedstudent;
    
    @FXML
    void backtoorder(ActionEvent event) throws IOException, SQLException {
     	/*
    	FXMLLoader loader = new FXMLLoader();
     	
    	loader.setLocation(getClass().getClassLoader().getResource("application/OrderPage.fxml"));
        Parent OrderPage = loader.load();
        Scene Orderpage = new Scene(OrderPage);
          Orderpagecontroller secController = loader.getController();
          secController.setUsername(username.getText());
        Stage window = (Stage) Backbtn.getScene().getWindow();
        
        window.setScene(Orderpage);
        window.show();
        */
     	((Node)(event.getSource())).getScene().getWindow().hide();
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
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
    	StartingDate.getEditor().clear();
    	EndingDate.getEditor().clear();
    	searchid.clear();
    }
    private int parentid;
    void setparentid(int id) {
    	this.parentid = id;
    }
	private ObservableList<Cart> list= FXCollections.observableArrayList();
	private static final Pattern VALID_WORD = Pattern.compile("^[A-Za-z]*$ ");
    @FXML
    void search(ActionEvent event) {
    	list.clear();
		LocalDate today = LocalDate.now();
		incorrectlabel.setVisible(false);
    	if(!VALID_WORD.matcher(searchid.getText()).matches() && !searchid.getText().contains(" ")
    			&& searchid.getText() != null && searchid.getText() != ("")
    			&& StartingDate.getValue() != null && EndingDate.getValue() !=null) {
    		try {    		
    			conn = Lunchhourdb.get();
    			System.out.println(parentid);
    			String sql = "Select * from Cafeteria.Order Where `Parent ID` = ? AND ID = ? AND `Order Date` BETWEEN ?  AND ? ";
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
					history.setAdd(rs.getString("Additional"));
					history.setExtra(rs.getString("Extra"));
					history.setTotal(rs.getDouble("Total"));
					history.setMenudate((rs.getDate("Order Date").toLocalDate()));
					history.setOrderid(rs.getInt("ID"));
	    			list.add(history);
	    		}
	    		orderhistory.setItems(list);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	else if(!VALID_WORD.matcher(searchid.getText()).matches() && !searchid.getText().contains(" ") && searchid.getText() != null && searchid.getText() != "") {
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
					history.setAdd(rs.getString("Additional"));
					history.setExtra(rs.getString("Extra"));
					history.setTotal(rs.getDouble("Total"));
					history.setMenudate((rs.getDate("Order Date").toLocalDate()));
					history.setOrderid(rs.getInt("ID"));
	    			list.add(history);
	    		}
	    		orderhistory.setItems(list);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	else if(StartingDate.getValue() != null && EndingDate.getValue() != null) {
    		if(!StartingDate.getValue().isAfter(today) && !StartingDate.getValue().isAfter(EndingDate.getValue())){
    			try {    		
        			conn = Lunchhourdb.get();
        			System.out.println(parentid);
        			String sql = "Select * from Cafeteria.Order Where `Parent ID` = ? AND `Order Date` BETWEEN ?  AND ? ";
    				ps = conn.prepareStatement(sql);
    				ps.setInt(1, parentid);
    	    		ps.setDate(2,Date.valueOf(StartingDate.getValue()));
    	    		ps.setDate(3, Date.valueOf(EndingDate.getValue()));
    	    		rs = ps.executeQuery();
    	    		while(rs.next()) {
    	    			Cart history = new Cart();
    	    			history.setFullname(rs.getString("Studentname"));
    					history.setMenuitem(rs.getString("Menu Item"));
    					history.setAdd(rs.getString("Additional"));
    					history.setExtra(rs.getString("Extra"));
    					history.setTotal(rs.getDouble("Total"));
    					history.setMenudate((rs.getDate("Order Date").toLocalDate()));
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
    	}else if(StartingDate.getValue() !=null) {
    		try {
    			conn = Lunchhourdb.get();
    			System.out.println(parentid);
    			String sql = "Select * from Cafeteria.Order Where `Parent ID` = ? AND `Order Date` BETWEEN ?  AND ? ";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, parentid);
	    		ps.setDate(2,Date.valueOf(StartingDate.getValue()));
	    		ps.setDate(3, Date.valueOf(today));
	    		rs = ps.executeQuery();
	    		while(rs.next()) {
	    			Cart history = new Cart();
	    			history.setFullname(rs.getString("Studentname"));
					history.setMenuitem(rs.getString("Menu Item"));
					history.setAdd(rs.getString("Additional"));
					history.setExtra(rs.getString("Extra"));
					history.setTotal(rs.getDouble("Total"));
					history.setMenudate((rs.getDate("Order Date").toLocalDate()));
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
