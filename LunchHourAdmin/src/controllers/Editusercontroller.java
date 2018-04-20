package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import Models.Account;
import Models.Orders;
import connection.Lunchhourdb;
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
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

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
    private TextField plast;

    @FXML
    private TextField user;

    @FXML
    private TextField addfundstxt;

    @FXML
    private TextField cfirst;

    @FXML
    private Button search;

    @FXML
    private TextField clast;

    @FXML
    private TextField email;
    
    
    @FXML
    private TreeTableView<Account> accounttable;

    @FXML
    private TreeTableColumn<Account, String> usercol;

    @FXML
    private TreeTableColumn<Account, String> emailcol;

    @FXML
    private TreeTableColumn<Account, String> pfirstcol;

    @FXML
    private TreeTableColumn<Account, String> plastcol;

    @FXML
    private TreeTableColumn<Account, String> balcol;

    @FXML
    private TreeTableColumn<Account, String> cfirstcol;

    @FXML
    private TreeTableColumn<Account, String> clastcol;

    @FXML
    private TreeTableColumn<Account, String> seccol;

    @FXML
    private TreeTableColumn<Account, String> gradecol;

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
   void updateinfo (){
	   String spfirst = pfirst.getText();
    	   if(spfirst.equals("bryan") || spfirst.equals("Bryan")) {
               
               try {
               	conn = Lunchhourdb.get();
   				ps = conn.prepareStatement("SELECT * from Cafeteria.Parents WHERE Username = ?;");
   				 ps.setString(1, "Cordes96");
   		            rs = ps.executeQuery();
   		            if(rs.next()) {
   		        	Account Bryan = new Account();
   		        	id = rs.getInt("Id");
   		        	Bryan.setPfirst(rs.getString("First Name"));
   		        	Bryan.setPlast(rs.getString("Last Name"));
   		        	Bryan.setEmail(rs.getString("EmailAddress"));
   		        	Bryan.setUsername(rs.getString("Username"));
   		        	Bryan.setBalance(rs.getDouble("Balance"));
   		        	TreeItem<Account> tBryan = new TreeItem<Account>(Bryan);
   		      
   		        	  String sql = "SELECT * from Cafeteria.Student WHERE `Parent ID` = ?;";
   		        	  ps = conn.prepareStatement(sql);
   		        	  ps.setInt(1, id);
   		        	  rs = ps.executeQuery();
   		        	  while(rs.next()) {
   		        		  Account student = new Account();
   		        		  student.setCfirst(rs.getString("First Name"));
   		        		  student.setClast(rs.getString("Last Name"));
   		        		  student.setGrade(rs.getString("Grade"));
   		        		  student.setSection(rs.getString("Section"));
   		        		  tBryan.getChildren().add(new TreeItem<Account>(student));
   		        	  }
   		        	  root.getChildren().add(tBryan);
   		            }
   		            
   			} catch (SQLException e) {
   				// TODO Auto-generated catch block
   				e.printStackTrace();
   			}
              
           }
    }
    @FXML
    void addfund(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Would you like to add " + addfundstxt.getText() +" to "+ accounttable.getSelectionModel().getSelectedItem().getValue().getUsername().getValue() + "?" , ButtonType.YES, ButtonType.CANCEL);
	   	 alert.showAndWait();

	   	 if (alert.getResult() == ButtonType.YES) {
	   	 
    	double intbal = accounttable.getSelectionModel().getSelectedItem().getValue().getBalancet();
    	double finbal = intbal + Double.parseDouble(addfundstxt.getText());
    	
    	 String sql = "Update Parents SET Balance = ? , `Last Balance Update` = ?   Where Id = ?;";
	    	try {
	    		conn = Lunchhourdb.get();
				ps = conn.prepareStatement(sql);
				ps.setDouble(1, finbal );
		    	ps.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
		    	ps.setInt(3, id);
		    	ps.execute();
			} catch (SQLException e) {
				Alert Error2= new Alert(AlertType.ERROR, "An error has occured while connecting with the database.\n Please check your internet connection and try again.");
    	   		Error2.setTitle("Error");
    	   		Error2.setHeaderText("Connection Error!");
    	   		Error2.showAndWait();
			}
	    	
	    	root.getChildren().clear();
	    	updateinfo();
	    	Alert Done = new Alert(AlertType.INFORMATION);
	   		Done.setTitle("Success!");
	   		Done.setHeaderText("Account Balance has been updated!");
	   		Done.setContentText("Previous balance: " + intbal +"   New balance: " + finbal );

	   		Done.showAndWait();
	   	 }
	    	
           
       
    }
    private String adminuser;
    public void setAdminuser(String user) {
		this.adminuser = user;
		
	}
    @FXML
    void back(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/application/Adminhomepage.fxml"));
    	Parent Orderpage = loader.load();
    	Adminhomepagecontroller controller = loader.getController();
		controller.setAdminuser(adminuser);
		controller.start();
        Scene Order = new Scene(Orderpage);
        Stage window = (Stage)((Node) (event.getSource())).getScene().getWindow();
        window.setScene(Order);
    }

    @FXML
    void del(ActionEvent event) {

    }

    @FXML
    void edit(ActionEvent event) {
    	
    }
    private int id;
    @FXML
    void search(ActionEvent event) {
    	updateinfo();
           

    }
    TreeItem<Account> root = new TreeItem<>();
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		accounttable.setRoot(root);
		accounttable.setShowRoot(false);
		usercol.setCellValueFactory((TreeTableColumn.CellDataFeatures<Account, String> param) -> param.getValue().getValue().getUsername());
		emailcol.setCellValueFactory((TreeTableColumn.CellDataFeatures<Account, String> param) -> param.getValue().getValue().getEmail());
		pfirstcol.setCellValueFactory((TreeTableColumn.CellDataFeatures<Account, String> param) -> param.getValue().getValue().getPfirst());
		plastcol.setCellValueFactory((TreeTableColumn.CellDataFeatures<Account, String> param) -> param.getValue().getValue().getPlast());
		balcol.setCellValueFactory((TreeTableColumn.CellDataFeatures<Account, String> param) -> param.getValue().getValue().getBalance());
		cfirstcol.setCellValueFactory((TreeTableColumn.CellDataFeatures<Account, String> param) -> param.getValue().getValue().getCfirst());
		clastcol.setCellValueFactory((TreeTableColumn.CellDataFeatures<Account, String> param) -> param.getValue().getValue().getClast());
		seccol.setCellValueFactory((TreeTableColumn.CellDataFeatures<Account, String> param) -> param.getValue().getValue().getSection());
		gradecol.setCellValueFactory((TreeTableColumn.CellDataFeatures<Account, String> param) -> param.getValue().getValue().getGrade());
		
		
	}

}
