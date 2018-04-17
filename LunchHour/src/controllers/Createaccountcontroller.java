package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.ResourceBundle;

import application.Student.Student;
import connection.Lunchhourdb;
import helpers.BCrypt;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Createaccountcontroller implements Initializable {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;	
    @FXML
    private TextField Username_txt;

    @FXML
    private TextField Emailaddress_txt;

    @FXML
    private PasswordField Password_txt1;

    @FXML
    private PasswordField Password_txt2;

    @FXML
    private TextField Firstnametxt;

    @FXML
    private TextField Lastnametxt;

    @FXML
    private TableView<Student> studenttable;

    @FXML
    private TableColumn<Student, String> firstNamecol;

    @FXML
    private TableColumn<Student, String> lastnamecol;

    @FXML
    private TableColumn<Student, String> gradecol;

    @FXML
    private TableColumn<Student, String> sectioncol;

    @FXML
    private Button removebtn;

    @FXML
    private Button addbtn;

    @FXML
    private Button backbtn;

    @FXML
    private Button createaccount;

    @FXML
    private Label Passwordlabel;

    @FXML
    private Label Usernamelabel;

    @FXML
    private Label Emaillabel;

    @FXML
    void Addstudentable(ActionEvent event) throws IOException {
    	// opens the add student to table stage instead of changing the scene.
    	 FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/CreateStudent.fxml"));
         loader.load();
         Createstudentcontroller controller = loader.getController();
         Parent popup = loader.getRoot();
         controller.setItems(studenttable.getItems());

         // Display popup
         Stage stage = new Stage();
         stage.setScene(new Scene(popup));
         //This displays the stage and waits for the input
         stage.showAndWait();
         studenttable.refresh();
         
    }

    @FXML
    void backtologin(ActionEvent event) throws IOException, SQLException {
    	Parent Loginpage = FXMLLoader.load(getClass().getClassLoader().getResource("application/Login.fxml"));
        Scene Login = new Scene(Loginpage);
        Stage window = (Stage) backbtn.getScene().getWindow();
        window.setScene(Login);
        window.show();


    }
    @FXML
    private Label Passlength;

    @FXML
    private Label Userlength;

    @FXML
    private Label emailvalid;

    @FXML
    private Label Uservalid;

    @FXML
    void createanaccount(ActionEvent event) throws SQLException, IOException {
    	conn = Lunchhourdb.get();

    	//Text box to variables
    	String userName = Username_txt.getText().toUpperCase().trim();
	    String password = Password_txt1.getText().trim();
	    String Firstname = Firstnametxt.getText().trim();
	    String Lastname = Lastnametxt.getText().trim();
	    String Email = Emailaddress_txt.getText().trim().toUpperCase();
	    //Checks to if the username is long and if it contains any spaces
	    if(userName.length() >= 6 && !userName.contains(" ")) {
	    	Uservalid.setVisible(false);
	    	Userlength.setVisible(false);
	    String sql = "SELECT * from Cafeteria.Parents WHERE userName = ?;";
	    //creates statement for the sql query
	        ps = conn.prepareStatement(sql);
	        ps.setString(1, userName);
	        rs = ps.executeQuery();
	        
	        if(!rs.next()) { // Checks to see if there is a username the same as the one that typed
	        	
	        	System.out.println("username passed");
	        		Usernamelabel.setVisible(false);
	        		if(!Email.contains(" ") && Email.contains("@")) {
	    	        	emailvalid.setVisible(false);
	        	  sql = "SELECT * from Cafeteria.Parents WHERE EmailAddress = ?;";
	        	 ps = conn.prepareStatement(sql);
	 	         ps.setString(1, Email);
	 	         rs = ps.executeQuery();
	 	         
		        if(!rs.next()) {		//checks to see if there is any email in the parent table that is the same
		        	System.out.println("email passed");
		        		Emaillabel.setVisible(false);
		        		
		        	if(password.length() >= 8 && password.equals(Password_txt2.getText())) {		//checks to see if the passwords match and if they are long enough
		        		Passlength.setVisible(false);
		        		Passwordlabel.setVisible(false);
		             Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		             String encryptedpassword = BCrypt.hashpw(password,BCrypt.gensalt());
		             sql = "Insert INTO Cafeteria.Parents(Username,EmailAddress,Password,`First Name`,`Last Name`,Balance, create_time) "
		             		+ "VAlUES(?,?,?,?,?,?,?);";
		             ps = conn.prepareStatement(sql);
		             ps.setString(1, userName);
		             ps.setString(2, Email);
		             ps.setString(3, encryptedpassword);
		             ps.setString(4, Firstname);
		             ps.setString(5, Lastname);
		             ps.setDouble(6, 0);
		             ps.setTimestamp(7, timestamp);
		             ps.execute();
		             int parentid;
		             sql = "Select Id from Cafeteria.Parents where Username = ?";
		             ps = conn.prepareStatement(sql);
		             ps.setString(1, userName);
		             rs = ps.executeQuery();
		             if(rs.next()) {
		             parentid = rs.getInt(1);
		             sql = "Insert Into Cafeteria.Student(`First Name`, `Last Name`, Section, Grade, `Parent ID`) Values(?,?,?,?,?)";
		             ps = conn.prepareStatement(sql);
		             for(int i=0; i < studenttable.getItems().size();i++) {
			             	Student tableRow = studenttable.getItems().get(i);
			             String name = tableRow.getFirstName();
			             String lastname = tableRow.getLastName();
			             String Grade = tableRow.getGrade();
			             String Section = tableRow.getSection();
			             ps.setString(1, name );
			             ps.setString(2,lastname );
			             ps.setString(3, Section );
			             ps.setString(4, Grade );
			             ps.setInt(5, parentid);
			        	 ps.execute();
			             }
		             }
		             
		        	 conn.close();    					// Closes connection
		        	 
		        	 /*
		        	  * Creates an alert to tell the user the account was created.
		        	  */
		        	 Alert alert = new Alert(AlertType.INFORMATION, "Account Created", ButtonType.OK);
		        	 alert.showAndWait();

		        	 if (alert.getResult() == ButtonType.OK) {
		        		 
		        	    	Parent CreateStudent = FXMLLoader.load(getClass().getClassLoader().getResource("application/Login.fxml"));
		        	        Scene Login = new Scene(CreateStudent);
		        	        Stage window = (Stage) backbtn.getScene().getWindow();
		        	        window.setScene(Login);
		        	        window.show();
		        	 }
		        	}else if(password.length()< 8){			// If password isn't long enough shows error
		        		Passlength.setVisible(true);
		        	}else if(!password.equals(Password_txt2.getText())) {			//if the 2 passwords don't match show error.
		        		Passwordlabel.setVisible(true);
		        	}
		        	else {
		        		Alert alert = new Alert(AlertType.ERROR, "Account Failed");
			        	 alert.showAndWait();
		        	}
		        	}
		        else {
		        	Emaillabel.setVisible(true);
		        }
	        		}else {
	    	        	emailvalid.setVisible(true);
	    	        }
	        }else {
	        	Usernamelabel.setVisible(true);
	        }
	        
    }else {
    	if(userName.contains(" ")) {
    		Uservalid.setVisible(true);
    	}else if(userName.length() < 6) {
    		if(!Uservalid.isVisible()) {
    		Userlength.setVisible(true);
    		}
    	}
    }
	    conn.close();
    }

    @FXML
    void removeselected(ActionEvent event) {
    	//Remove alert to make sure you want to remove the student
    	
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Remove " + studenttable.getSelectionModel().getSelectedItem().getFirstName() +" "+studenttable.getSelectionModel().getSelectedItem().getLastName() +" from Student table?" , ButtonType.YES, ButtonType.CANCEL);
   	 alert.showAndWait();

   	 if (alert.getResult() == ButtonType.YES) {
    	Student selectedItem = studenttable.getSelectionModel().getSelectedItem();
        studenttable.getItems().remove(selectedItem);
   	 }

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//this creates a reference to student to get the information from the other controller.
		firstNamecol.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
		lastnamecol.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
		gradecol.setCellValueFactory(new PropertyValueFactory<Student, String>("Grade"));
		sectioncol.setCellValueFactory(new PropertyValueFactory<Student, String>("Section"));
		
	    
	}
}

