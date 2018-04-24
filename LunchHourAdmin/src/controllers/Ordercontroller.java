package controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.ResourceBundle;

import Models.Account2;
import Models.AdminOrders;
import connection.Lunchhourdb;

public class Ordercontroller implements Initializable {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
    @FXML
    private DatePicker starting;
    ObservableList<AdminOrders> items = FXCollections.observableArrayList();
    @FXML
    private Button savebtn;

    @FXML
    private Button Backbtn;

    @FXML
    private Button searchbtn;

    @FXML
    private TableView<AdminOrders> orderhistory;

    @FXML
    private TableColumn<AdminOrders, Integer> idc;

    @FXML
    private TableColumn<AdminOrders, java.sql.Date> orderc;

    @FXML
    private TableColumn<AdminOrders, String> dayc;

    @FXML
    private TableColumn<AdminOrders, String> namec;

    @FXML
    private TableColumn<AdminOrders, String> gradec;

    @FXML
    private TableColumn<AdminOrders, String> sectionc;

    @FXML
    private TableColumn<AdminOrders, String> menuitemc;

    @FXML
    private TableColumn<AdminOrders, String> addc;

    @FXML
    private TableColumn<AdminOrders, String> extrac;

    @FXML
    private TableColumn<AdminOrders, Double> totalc;

    @FXML
    private TextField filter;

    @FXML
    private DatePicker ending;
    public String getAdminuser() {
		return adminuser;
	}

	public void setAdminuser(String adminuser) {
		this.adminuser = adminuser;
	}

	private String adminuser;
    @FXML
    void backtoorder(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/application/Adminhomepage.fxml"));
    	Parent page = loader.load();
    	Adminhomepagecontroller controller = loader.getController();
		controller.setAdminuser(adminuser);
		controller.start();
        Scene Order = new Scene(page);
        Stage window = (Stage)((Node) (event.getSource())).getScene().getWindow();
        window.setScene(Order);
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
    private void saveFileRoutine(File file)
			throws IOException{
		// Creates a new file and writes the txtArea contents into it
    	TextArea texts = new TextArea();
  	  String Titles = "Order Number,Order Date,Day,Student Name,Grade,Section,Menu Item,Additionals,Extra Items,Total\n";
  	  texts.appendText(Titles);
  	  orderhistory.getItems().stream().forEach((o)
                -> {
              	  String text = idc.getCellData(o)+ "," +orderc.getCellData(o)+"," +dayc.getCellData(o)+ "," +namec.getCellData(o)+"," +gradec.getCellData(o)+ "," +sectionc.getCellData(o)+"," +menuitemc.getCellData(o)+ "," +addc.getCellData(o).replaceAll(",", ". ")+ "," +extrac.getCellData(o).replaceAll(",", ". ")+ "," +totalc.getCellData(o) + "\n";
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
    void searchbydates() {
    	incorrectdates.setVisible(false);
    	items.clear();
		if(starting.getValue() != null && ending.getValue() != null) {
			if(!starting.getValue().isAfter(ending.getValue())){
    			try {    		
        			conn = Lunchhourdb.get();
        			String sql = "Select * from Cafeteria.Order Where `Order_Date` BETWEEN ?  AND ? ";
    				ps = conn.prepareStatement(sql);
    	    		ps.setDate(1,Date.valueOf(starting.getValue()));
    	    		ps.setDate(2, Date.valueOf(ending.getValue()));
    	    		rs = ps.executeQuery();
    	    		while(rs.next()) {
    	    			AdminOrders history = new AdminOrders();
    	    			history.setFullname(rs.getString("Studentname"));
    					history.setMenu(rs.getString("Menu Item"));
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
    					history.setOrderDate((rs.getDate("Order_Date").toLocalDate()));
    					history.setOrderid(rs.getInt("ID"));
    					history.setDay((rs.getDate("Order_Date").toLocalDate().getDayOfWeek().toString()));
    					history.setStudentID(rs.getInt("Student ID"));
    	    			items.add(history);
    	    		}
    	    		conn = Lunchhourdb.get();
        			sql = "Select * from Cafeteria.Student Where ID = ?;";
    	    		for(AdminOrders o : items) {
        				ps = conn.prepareStatement(sql);
        	    		ps.setInt(1,o.getStudentID());
        	    		rs = ps.executeQuery();
        	    		if(rs.next()) {
        	    			o.setGrade(rs.getString("Grade"));
        	    			o.setSection(rs.getString("Section"));
        	    		}
    	    		}
    	    		conn.close();
    	    		orderhistory.setItems(items);
    			} catch (SQLException e) {
    				Alert Error2= new Alert(AlertType.ERROR, "An error has occured while connecting with the database.\n Please check your internet connection and try again.");
        	   		Error2.setTitle("Error");
        	   		Error2.setHeaderText("Connection Error!");
        	   		Error2.showAndWait();
    			}
    		}else {
    			incorrectdates.setVisible(true);
    		}
    	}
		else if(ending.getValue() != null && starting.getValue() == null) { //checks to see if only the ending date is filled in
    			 incorrectdates.setVisible(true);											// sets incorrect day error.
		}
	//runs search for starting date up till today plus 7 days
		
		else if(starting.getValue() !=null) {
	    		try {
	    			conn = Lunchhourdb.get();
	    			String sql = "Select * from Cafeteria.Order Where `Order_Date` BETWEEN ?  AND ? ";
					ps = conn.prepareStatement(sql);
		    		ps.setDate(1,Date.valueOf(starting.getValue()));
		    		ps.setDate(2, Date.valueOf(LocalDate.now().plusDays(6)));
		    		rs = ps.executeQuery();
		    		while(rs.next()) {
		    			AdminOrders history = new AdminOrders();
		    			history.setFullname(rs.getString("Studentname").toLowerCase());
						history.setMenu(rs.getString("Menu Item").toLowerCase());
						if(rs.getString("Additional") != null) {
							history.setAdd(rs.getString("Additional").replaceAll("/", ",").toLowerCase());
							}else {
								history.setAdd("0");
							}
							if(rs.getString("Extra") != null) {
							history.setExtra(rs.getString("Extra").replaceAll("/", ",").toLowerCase());
							}else {
								history.setExtra("0");
							}
						history.setTotal(rs.getDouble("Total"));
						history.setOrderDate((rs.getDate("Order_Date").toLocalDate()));
						history.setOrderid(rs.getInt("ID"));
						history.setDay((rs.getDate("Order_Date").toLocalDate().getDayOfWeek().toString()).toLowerCase());
    					history.setStudentID(rs.getInt("Student ID"));
    	    			items.add(history);
    	    		}
    	    		conn = Lunchhourdb.get();
        			sql = "Select * from Cafeteria.Student Where ID = ?;";
    	    		for(AdminOrders o : items) {
        				ps = conn.prepareStatement(sql);
        	    		ps.setInt(1,o.getStudentID());
        	    		rs = ps.executeQuery();
        	    		if(rs.next()) {
        	    			o.setGrade(rs.getString("Grade").toLowerCase());
        	    			o.setSection(rs.getString("Section").toLowerCase()i );
        	    		}
    	    		}
    	    		conn.close();
		    		orderhistory.setItems(items);
				} catch (SQLException e) {
					Alert Error2= new Alert(AlertType.ERROR, "An error has occured while connecting with the database.\n Please check your internet connection and try again.");
	    	   		Error2.setTitle("Error");
	    	   		Error2.setHeaderText("Connection Error!");
	    	   		Error2.showAndWait();
				}
	    	} 
	}
    void searchall(){
    	incorrectdates.setVisible(false);
    	items.clear();
    			try {    		
        			conn = Lunchhourdb.get();
        			String sql = "Select * from Cafeteria.Order;";
    				ps = conn.prepareStatement(sql);
    	    		rs = ps.executeQuery();
    	    		while(rs.next()) {
    	    			AdminOrders history = new AdminOrders();
    	    			history.setFullname(rs.getString("Studentname"));
    					history.setMenu(rs.getString("Menu Item"));
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
    					history.setOrderDate((rs.getDate("Order_Date").toLocalDate()));
    					history.setOrderid(rs.getInt("ID"));
    					history.setDay((rs.getDate("Order_Date").toLocalDate().getDayOfWeek().toString()));
    					history.setStudentID(rs.getInt("Student ID"));
    	    			items.add(history);
    	    		}
    	    		conn = Lunchhourdb.get();
        			sql = "Select * from Cafeteria.Student Where ID = ?;";
    	    		for(AdminOrders o : items) {
        				ps = conn.prepareStatement(sql);
        	    		ps.setInt(1,o.getStudentID());
        	    		rs = ps.executeQuery();
        	    		if(rs.next()) {
        	    			o.setGrade(rs.getString("Grade"));
        	    			o.setSection(rs.getString("Section"));
        	    		}
    	    		}
    	    		conn.close();
    	    		orderhistory.setItems(items);
    			} catch (SQLException e) {
    				Alert Error2= new Alert(AlertType.ERROR, "An error has occured while connecting with the database.\n Please check your internet connection and try again.");
        	   		Error2.setTitle("Error");
        	   		Error2.setHeaderText("Connection Error!");
        	   		Error2.showAndWait();
    			}
    }
    @FXML
    void search(ActionEvent event) {
    	if(starting!=null || ending !=null) {
    	searchbydates();
    	}else {
    		searchall();
    	}
    }
    @FXML
    private Label incorrectdates;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		orderc.setCellValueFactory(new PropertyValueFactory<>("OrderDate"));
		orderc.setCellFactory(new Ordercontroller.ColumnFormatter<>(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		idc.setCellValueFactory(new PropertyValueFactory<AdminOrders, Integer>("orderid"));
		namec.setCellValueFactory(new PropertyValueFactory<AdminOrders, String>("fullname"));
		menuitemc.setCellValueFactory(new PropertyValueFactory<AdminOrders, String>("Menu"));
		addc.setCellValueFactory(new PropertyValueFactory<AdminOrders, String>("add"));
		extrac.setCellValueFactory(new PropertyValueFactory<AdminOrders, String>("extra"));
		totalc.setCellValueFactory(new PropertyValueFactory<AdminOrders, Double>("total"));
		gradec.setCellValueFactory(new PropertyValueFactory<AdminOrders, String>("grade"));
		sectionc.setCellValueFactory(new PropertyValueFactory<AdminOrders, String>("section"));
		dayc.setCellValueFactory(new PropertyValueFactory<AdminOrders, String>("day"));
		
		FilteredList<AdminOrders> filteredList = new FilteredList<>(items, p -> true);
		filter.setOnKeyReleased(e-> {
		 filter.textProperty().addListener((observable, oldValue, newValue) -> filteredList.setPredicate(newValue.isEmpty()
                 ? null
                 : user -> String.valueOf(user.getOrderid()).contains(newValue.toLowerCase())
                           || user.getAdd().contains(newValue.toLowerCase())
                           || user.getExtra().contains(newValue.toLowerCase())
                           || user.getDay().contains(newValue.toLowerCase())
                           || user.getFullname().contains(newValue.toLowerCase())
                           || user.getGrade().contains(newValue.toLowerCase())
                           || user.getSection().contains(newValue.toLowerCase())
                           || user.getMenu().contains(newValue.toLowerCase())
                           || user.getOrderDate().toString().contains(newValue.toLowerCase())
				 ));
		 				    
		 SortedList<AdminOrders> sortedData = new SortedList<>(filteredList);
		 sortedData.comparatorProperty().bind(orderhistory.comparatorProperty());
		 orderhistory.setItems(sortedData);
		});
		
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
}
