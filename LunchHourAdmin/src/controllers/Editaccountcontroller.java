package controllers;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Models.Account;
import Models.Account2;
import Models.Parentinfo;
import Models.Student;
import connection.Lunchhourdb;
import Models.Studentinfo;
import helpers.BCrypt;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Editaccountcontroller implements Initializable {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;	
	 private String adminuser;
	    public void setAdminuser(String user) {
			this.adminuser = user;
			
		}
	    @FXML
	    private TableView<Studentinfo> studenttable;

	    @FXML
	    private TableColumn<Studentinfo, String> firstc;

	    @FXML
	    private TableColumn<Studentinfo, String> lastc;

	    @FXML
	    private TableColumn<Studentinfo, String> secc;

	    @FXML
	    private TableColumn<Studentinfo, String> gradec;
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
      //   controller.setItems(studenttable.getItems());

         // Display popup
         Stage stage = new Stage();
         stage.setScene(new Scene(popup));
         //This displays the stage and waits for the input
         stage.showAndWait();
        // studenttable.refresh();
         
    }

    @FXML
    void backtologin(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/application/EditUserPage.fxml"));
    	Parent page = loader.load();
    	Editusercontroller controller = loader.getController();
		controller.setAdminuser(adminuser);
		controller.setAdminList(items2);
		controller.secondstart();
        Scene newscene = new Scene(page);
        Stage stage = new Stage();
        stage.setScene(newscene);
        stage.show();


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
    void createanaccount(ActionEvent event) throws  IOException {
    	try {
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
		    String sql = "SELECT * from Cafeteria.Parents WHERE username = ?;";
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
			            /* for(int i=0; i < studenttable.getItems().size();i++) {
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
				             }*/
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
		} catch (SQLException e) {
			Alert Error2= new Alert(AlertType.ERROR, "An error has occured while connecting with the database.\n Please check your internet connection and try again.");
	   		Error2.setTitle("Error");
	   		Error2.setHeaderText("Connection Error!");
	   		Error2.showAndWait();
		}

    	
    }

    @FXML
    void removeselected(ActionEvent event) {

    	Alert alert = new Alert(AlertType.CONFIRMATION, "Remove " + studenttable.getSelectionModel().getSelectedItem().getFirstname() +" "+studenttable.getSelectionModel().getSelectedItem().getLastname() +" from Student table?" , ButtonType.YES, ButtonType.CANCEL);
      	 alert.showAndWait();

      	 if (alert.getResult() == ButtonType.YES) {
      		Studentinfo selectedItem = studenttable.getSelectionModel().getSelectedItem();
           studenttable.getItems().remove(selectedItem);
      	 }
    }
    @FXML
    private TextField balancetxt;
    void start() {
    	Username_txt.setText(Selectedparent.getUsername());
    	Emailaddress_txt.setText(Selectedparent.getEmail());
    	Firstnametxt.setText(Selectedparent.getPfirst());
    	Lastnametxt.setText(Selectedparent.getPlast());
    	balancetxt.setText(String.valueOf(Selectedparent.getBalance()));
    	searchkids();
    }
    ObservableList<Models.Studentinfo> items = FXCollections.observableArrayList();
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//this creates a reference to student to get the information from the other controller.
		firstc.setCellValueFactory(new PropertyValueFactory<Studentinfo, String>("firstName"));
		lastc.setCellValueFactory(new PropertyValueFactory<Studentinfo, String>("lastName"));
		gradec.setCellValueFactory(new PropertyValueFactory<Studentinfo, String>("Grade"));
		secc.setCellValueFactory(new PropertyValueFactory<Studentinfo, String>("Section"));
	}
	ArrayList<Studentinfo> students = new ArrayList<Studentinfo>();
	void searchkids(){
	      String sql = "SELECT * from Cafeteria.Student Where `Parent ID` = ?;";
	  	  try {
	  		  conn = Lunchhourdb.get();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(Selectedparent.getParentId()));
		  	  rs = ps.executeQuery();
		  	  while(rs.next()) {
		  		Studentinfo student = new Studentinfo();
		  		  student.setParentID(rs.getInt("Parent ID"));
		  		  student.setFirstname(rs.getString("First Name").toLowerCase());
		  		  student.setLastname(rs.getString("Last Name").toLowerCase());
		  		  student.setGrade(rs.getString("Grade").toLowerCase());
		  		  student.setSection(rs.getString("Section").toLowerCase());
		  		  list.add(student);
		  		  studenttable.setItems(list);
		  	  }} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	  	  }
	  
	}
		private Account2 Selectedparent;
	  public Account2 getSelectedparent() {
			return Selectedparent;
		}
	  ObservableList<Studentinfo> list = FXCollections.observableArrayList();
		public void setSelectedparent(Account2 selectedparent) {
			Selectedparent = selectedparent;
		}
	private ObservableList<Account2>items2;
			public void setAdminlist(ObservableList<Account2> items2) {
				// TODO Auto-generated method stub
				
			}
}
	