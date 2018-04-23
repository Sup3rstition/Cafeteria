package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import Models.Account2;
import Models.Orders;
import Models.Student;
import Models.Studentinfo;
import connection.Lunchhourdb;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class Editusercontroller implements Initializable {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;	
    @FXML
    private Button editbtn;

    @FXML
    private Button addaccbtn;

    @FXML
    private Button delacc;

    @FXML
    private Button addfundbtn;

    @FXML
    private Button backbtn;

    @FXML
    private TextField pfirst;


    @FXML
    private TextField addfundstxt;

    public void setAdminList( ObservableList<Account2> items) {
    	this.items = items;
    }
    ObservableList<Account2> items = FXCollections.observableArrayList();
    @FXML
    private TableView<Account2> accounts;

    @FXML
    private TableColumn<Account2, String> userc;

    @FXML
    private TableColumn<Account2,String> emailc;

    @FXML
    private TableColumn<Account2, String> firstc;

    @FXML
    private TableColumn<Account2,String> lastc;

    @FXML
    private TableColumn<Account2,Double> balancec;
    @FXML
    void addacc(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/application/CreateAccountPage.fxml"));
    	Parent Orderpage = loader.load();
    	Createaccountcontroller controller = loader.getController();
		controller.setAdminuser(adminuser);
        Scene Order = new Scene(Orderpage);
        Stage window = (Stage)((Node) (event.getSource())).getScene().getWindow();
        window.setScene(Order);
    }
    void secondstart() {
    	accounts.setItems(items);
    }
void searchall() {
		items.clear();
	   try {
        	conn = Lunchhourdb.get();
			ps = conn.prepareStatement("SELECT * from Cafeteria.Parents;");
	            rs = ps.executeQuery();
	            while(rs.next()) {
	        	Account2 Account = new Account2();
	        	Account.setParentId(String.valueOf(rs.getInt("Id")));
	        	Account.setPfirst(rs.getString("First Name").toLowerCase());
	        	Account.setPlast(rs.getString("Last Name").toLowerCase());
	        	Account.setEmail(rs.getString("EmailAddress").toLowerCase());
	        	Account.setUsername(rs.getString("Username").toLowerCase());
	        	Account.setBalance(rs.getDouble("Balance"));
	        	items.add(Account);
	            }
	            accounts.setItems(items);
	            
		} catch (SQLException e) {
			Alert Error2= new Alert(AlertType.ERROR, "An error has occured while connecting with the database.\n Please check your internet connection and try again.");
	   		Error2.setTitle("Error");
	   		Error2.setHeaderText("Connection Error!");
	   		Error2.showAndWait();
		}
}
    @FXML
    void addfund(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Would you like to add " + addfundstxt.getText() +" to "+ accounts.getSelectionModel().getSelectedItem().getUsername()+ "?" , ButtonType.YES, ButtonType.CANCEL);
	  	 alert.showAndWait();

	  	 if (alert.getResult() == ButtonType.YES) {
	   	 
    	double intbal = accounts.getSelectionModel().getSelectedItem().getBalance();
    	double finbal = intbal + Double.parseDouble(addfundstxt.getText());
    	
    	 String sql = "Update Parents SET Balance = ? , `Last Balance Update` = ?   Where Id = ?;";
	    	try {
	    		conn = Lunchhourdb.get();
				ps = conn.prepareStatement(sql);
				ps.setDouble(1, finbal );
		    	ps.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
		    	ps.setInt(3, Integer.parseInt(accounts.getSelectionModel().getSelectedItem().getParentId()));
		    	ps.execute();
			} catch (SQLException e) {
				Alert Error2= new Alert(AlertType.ERROR, "An error has occured while connecting with the database.\n Please check your internet connection and try again.");
    	   		Error2.setTitle("Error");
    	   		Error2.setHeaderText("Connection Error!");
    	   		Error2.showAndWait();
			}
	    	
	    	Alert Done = new Alert(AlertType.INFORMATION);
	   		Done.setTitle("Success!");
	   		Done.setHeaderText("Account Balance has been updated!");
	   		Done.setContentText("Previous balance: " + intbal +"   New balance: " + finbal );

	   		Done.showAndWait();
	   	 }
    }
	    	
           
       
  //  }
    private String adminuser;
    public void setAdminuser(String user) {
		this.adminuser = user;
		
	}
    @FXML
    void back(ActionEvent event) throws IOException {
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
    void del(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Would you like to Account "+ accounts.getSelectionModel().getSelectedItem().getUsername() + "?" , ButtonType.YES, ButtonType.CANCEL);
	  	 alert.showAndWait();

	 if (alert.getResult() == ButtonType.YES) {
	   	 searchkids();
   	int parentid = Integer.parseInt(accounts.getSelectionModel().getSelectedItem().getParentId());
   	
   	 String sql = "DELETE FROM `Cafeteria`.`Parents` WHERE `Id`=?;";
	    	try {
	    		conn = Lunchhourdb.get();
				ps = conn.prepareStatement(sql);
				ps.setInt(1, parentid );
		    	ps.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    	
	    	Alert Done = new Alert(AlertType.INFORMATION);
	   		Done.setTitle("Success!");
	   		Done.setHeaderText("Selected Account Status: Deleted");
	   		Done.setContentText("Account has been Deleted");

	   		Done.showAndWait();
	   	 }
    }
    @FXML
    void edit(ActionEvent event) throws IOException {
    	if(accounts.getSelectionModel().getSelectedItem() != null) {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/application/EditParent.fxml"));
    	Parent page = loader.load();
    	Editaccountcontroller controller = loader.getController();
		controller.setAdminuser(adminuser);
		controller.setSelectedparent(accounts.getSelectionModel().getSelectedItem());
		controller.setAdminlist(items);
		controller.start();
        Scene newscene = new Scene(page);
        Stage stage = new Stage();
        stage.setScene(newscene);
        stage.show();
    	}else {
    		
    	}
    }
    ArrayList<Studentinfo> students = new ArrayList<>();
    void searchkids(){
	      String sql = "SELECT * from Cafeteria.Student Where `Parent ID` = ?;";
	  	  try {
	  		  conn = Lunchhourdb.get();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(accounts.getSelectionModel().getSelectedItem().getParentId()));
		  	  rs = ps.executeQuery();
		  	  while(rs.next()) {
		  		Studentinfo student = new Studentinfo();
		  		  student.setParentID(rs.getInt("Parent ID"));
		  		  student.setFirstname(rs.getString("First Name").toLowerCase());
		  		  student.setLastname(rs.getString("Last Name").toLowerCase());
		  		  student.setGrade(rs.getString("Grade").toLowerCase());
		  		  student.setSection(rs.getString("Section").toLowerCase());
		  		  students.add(student);
		  		  
		  	  }} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	  	  }
	  
	}
    void start(){
    	searchall();
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		addfundstxt.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
		firstc.setCellValueFactory(new PropertyValueFactory<Account2, String>("pfirst"));
		lastc.setCellValueFactory(new PropertyValueFactory<Account2, String>("plast"));
		emailc.setCellValueFactory(new PropertyValueFactory<Account2, String>("Email"));
		userc.setCellValueFactory(new PropertyValueFactory<Account2, String>("Username"));
		balancec.setCellValueFactory(new PropertyValueFactory<Account2, Double>("balance"));
		
		FilteredList<Account2> filteredList = new FilteredList<>(items, p -> true);
		pfirst.setOnKeyReleased(e-> {
		 pfirst.textProperty().addListener((observable, oldValue, newValue) -> filteredList.setPredicate(newValue.isEmpty()
                 ? null
                 : user -> user.getPfirst().contains(newValue.toLowerCase())
                           || user.getUsername().contains(newValue.toLowerCase())
                           || user.getPlast().contains(newValue.toLowerCase())
                           || user.getParentId().contains(newValue.toLowerCase())
                           || user.getEmail().contains(newValue.toLowerCase())
                           || user.getEmail().contains(newValue.toLowerCase())
				 ));
		 				    
		 SortedList<Account2> sortedData = new SortedList<>(filteredList);
		 sortedData.comparatorProperty().bind(accounts.comparatorProperty());
		 accounts.setItems(sortedData);
		});
	}
}
