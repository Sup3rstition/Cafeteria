package controllers;


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

import Models.AdminOrders;
import connection.Lunchhourdb;
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

public class FDRcontroller implements Initializable {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
    ObservableList<AdminOrders> items = FXCollections.observableArrayList();
    @FXML
    private DatePicker starting;

    @FXML
    private Button savebtn;

    @FXML
    private Button Backbtn;

    @FXML
    private Button searchbtn;

    @FXML
    private TextField sectionfil;

    @FXML
    private TextField gradefil;

    @FXML
    private TableView<AdminOrders> fdrtable;

    @FXML
    private TableColumn<AdminOrders,String> namec;

    @FXML
    private TableColumn<AdminOrders,String> gradec;

    @FXML
    private TableColumn<AdminOrders,String> sectionc;

    @FXML
    private TableColumn<AdminOrders,String> menuc;

    @FXML
    private TableColumn<AdminOrders,String> addc;

    @FXML
    private TableColumn<AdminOrders,String> extrac;
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
  	  String Titles = "Order Date,Day,Student Name,Grade,Section,Menu Item,Additionals,Extra Items \n";
  	  texts.appendText(Titles);
  	 fdrtable.getItems().stream().forEach((o)
                -> {
              	  String text = o.getOrderDate()+"," +o.getDay()+ "," +namec.getCellData(o)+"," +gradec.getCellData(o)+ "," +sectionc.getCellData(o)+"," +menuc.getCellData(o)+ "," +addc.getCellData(o).replaceAll(",", ". ")+ "," +extrac.getCellData(o).replaceAll(",", ". ")+ "," + "\n";
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
    void search(ActionEvent event) {
    	items.clear();
    	if(starting.getValue() !=null) {
    		try {
    			conn = Lunchhourdb.get();
    			String sql = "Select * from Cafeteria.Order Where `Order_Date`= ? ";
				ps = conn.prepareStatement(sql);
	    		ps.setDate(1,Date.valueOf(starting.getValue()));
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
    	    			o.setGrade(rs.getString("Grade").toUpperCase());
    	    			o.setSection(rs.getString("Section").toUpperCase());
    	    		}
	    		}
	    		conn.close();
	    	fdrtable.setItems(items);
			} catch (SQLException e) {
				Alert Error2= new Alert(AlertType.ERROR, "An error has occured while connecting with the database.\n Please check your internet connection and try again.");
    	   		Error2.setTitle("Error");
    	   		Error2.setHeaderText("Connection Error!");
    	   		Error2.showAndWait();
			}
    	} 
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		namec.setCellValueFactory(new PropertyValueFactory<AdminOrders, String>("fullname"));
		menuc.setCellValueFactory(new PropertyValueFactory<AdminOrders, String>("Menu"));
		addc.setCellValueFactory(new PropertyValueFactory<AdminOrders, String>("add"));
		extrac.setCellValueFactory(new PropertyValueFactory<AdminOrders, String>("extra"));
		gradec.setCellValueFactory(new PropertyValueFactory<AdminOrders, String>("grade"));
		sectionc.setCellValueFactory(new PropertyValueFactory<AdminOrders, String>("section"));
		
		FilteredList<AdminOrders> sectionfilteredList = new FilteredList<>(items, p -> true);
		sectionfil.setOnKeyReleased(e-> {
			sectionfil.textProperty().addListener((observable, oldValue, newValue) -> sectionfilteredList.setPredicate(newValue.isEmpty()
                 ? null
                 : user -> user.getSection().contains(newValue.toUpperCase())

				 ));
		 				    
		 SortedList<AdminOrders> sortedData = new SortedList<>(sectionfilteredList);
		 sortedData.comparatorProperty().bind(fdrtable.comparatorProperty());
		 fdrtable.setItems(sortedData);
		});
		
		FilteredList<AdminOrders> gradefilteredList = new FilteredList<>(items, p -> true);
		gradefil.setOnKeyReleased(e-> {
			gradefil.textProperty().addListener((observable, oldValue, newValue) -> gradefilteredList.setPredicate(newValue.isEmpty()
                 ? null
                 : user -> user.getGrade().contains(newValue.toUpperCase())

				 ));
		 				    
		 SortedList<AdminOrders> sortedData = new SortedList<>(gradefilteredList);
		 sortedData.comparatorProperty().bind(fdrtable.comparatorProperty());
		 fdrtable.setItems(sortedData);
		});
		
	}
}