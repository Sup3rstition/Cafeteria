package controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ResourceBundle;

import com.mysql.jdbc.Statement;

import connection.Lunchhourdb;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
    private TableView<?> studenttable;

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
    void Addstudentable(ActionEvent event) {

    }

    @FXML
    void backtologin(ActionEvent event) {

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
    void createanaccount(ActionEvent event) {
    	String userName = Username_txt.getText().toUpperCase();
	    String password = Password_txt1.getText().trim();
	    String Firstname = Firstnametxt.getText().trim();
	    String Lastname = Lastnametxt.getText().trim();
	    String Email = Emailaddress_txt.getText().trim().toUpperCase();
	    if(userName.length() >= 6 && !userName.contains(" ")) {
	    	Uservalid.setVisible(false);
	    	Userlength.setVisible(false);
	    String usernamesql = "SELECT * from Cafeteria.Parents WHERE userName = ?;";
	        try {
	        	conn = Lunchhourdb.get();
				ps = conn.prepareStatement(usernamesql);
				ps.setString(1, userName);
		        rs = ps.executeQuery();
		        if(!rs.next()) {
		        		Usernamelabel.setVisible(false);
		        		if(!Email.contains(" ") && Email.contains("@")) {
		    	        	emailvalid.setVisible(false);
		        	 String emailsql = "SELECT * from Cafeteria.Parents WHERE EmailAddress = ?;";
		        	 ps = conn.prepareStatement(emailsql);
		 	         ps.setString(1, Email);
		 	         rs = ps.executeQuery();
			        if(!rs.next()) {
			        		Emaillabel.setVisible(false);
			        	if(password.length() >= 8 && password.equals(Password_txt2.getText())) {
			        		Passlength.setVisible(false);
			        		Passwordlabel.setVisible(false);
			        	 Calendar calendar = Calendar.getInstance();
			             java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
			             String Insertaccount = "Insert INTO Cafeteria.Parents(Username,EmailAddress,Password,`First Name`,`Last Name`,Balance) "
			             		+ "VAlUES('"+userName+"','"+Email+"','"+password+"','"+Firstname+"','"+Lastname+"',0);";
			             ps = conn.prepareStatement(Insertaccount);
			        	 ps.execute();
			        	 conn.close();
			        	}else if(password.length()< 8){
			        		Passlength.setVisible(true);
			        	}else if(!password.equals(Password_txt2.getText())) {
			        		Passwordlabel.setVisible(true);
			        	}
			        	else {
			        	}
			        	}
			        }else {
			        	Emaillabel.setVisible(true);
			        }
		        		}else {
		    	        	emailvalid.setVisible(true);
		    	        }
		        }else {
		        	Usernamelabel.setVisible(true);
		        }
				
			} catch (SQLException e) {
				Alert Error2= new Alert(AlertType.ERROR, "An error has occured while connecting with the database.\n Please check your internet connection and try again.");
    	   		Error2.setTitle("Error");
    	   		Error2.setHeaderText("Connection Error!");
    	   		Error2.showAndWait();
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
    }

    @FXML
    void removeselected(ActionEvent event) {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	private String adminuser;
    public void setAdminuser(String user) {
		this.adminuser = user;
		
	}

}

