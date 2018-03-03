package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import connection.Lunchhourdb;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import helpers.BCrypt;

public class Logincontroller implements Initializable{
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
    @FXML
    private Button LoginBtn;

    @FXML
    private TextField Username_txt;

    @FXML
    private PasswordField Password_txt;

    @FXML
    private CheckBox Remember_Chck;

    @FXML
    private Label Createaccount;

    @FXML
    private Label Wronginfo;
    
    @FXML
    private Button exitBtn;
    
    @FXML
    void opencreateaccount(MouseEvent event) {
    	 Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getClassLoader().getResource("application/CreateAccountPage.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         Stage stage = new Stage();
         stage.setScene(new Scene(root));
         stage.setTitle("Elevel Cafeteria");
         stage.show();
         Stage login = (Stage) exitBtn.getScene().getWindow();
         login.close();
    }
    @FXML
    private Label Invalidinfo;
    /*
     * The action for the login button to create a connection to the database to search for the user name and the password.
     */
    @FXML
    void performLoginAction(ActionEvent event) {
    	 	String userName = Username_txt.getText().trim().toUpperCase(); // removes any space after the username and capitlize it to make a make sure username isn't case-sensitive.
    	    String password = Password_txt.getText().trim();
    	    if(userName.length() >=6 && password.length() >= 3) {
    	    	Invalidinfo.setVisible(false);
    	    	/*
    	    	 * creates a sql command to search the parents for the username and password.
    	    	 */
    	    String sql = "SELECT * from Cafeteria.Parents WHERE userName = ? AND password = ?;";
    	    try {
    	    	// Creates the prepared statement to search the database with.
    	        ps = conn.prepareStatement(sql);
    	        ps.setString(1, userName);
    	        ps.setString(2, password);
    	        rs = ps.executeQuery();		// this is the result from the query and will be check to see if any result came back.
    	        
    	        if (rs.next()) {		// if there is a result from the serach in the database this changes the stage to the order page.
    	        	Wronginfo.setVisible(false);
    	            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("application/OrderPage.fxml"));
    	            Stage stage = new Stage();
    	            stage.setScene(new Scene(root));
    	            stage.setTitle("Elevel Cafeteria");
    	            stage.show(); 
    	            Stage login = (Stage) exitBtn.getScene().getWindow();
    	            login.close();
    	            
    	        } else {
    	            Wronginfo.setVisible(true);		// if the search fails it sets the wrong info label to visible and once it becomes true
    	            									// it'll set the wronginfo to false.
    	        }
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    }
    	    }else {
    	    	Invalidinfo.setVisible(true);
    	    }
    }
   
    public void exitBtnAction(ActionEvent event) {
        Stage stage = (Stage) exitBtn.getScene().getWindow();
        stage.close();
    }

    /*
     * (non-Javadoc)
     * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
     * This uses a connection class that has the database connection already setup to easy connection creation rather then typing the
     *  jdbc out every time. under the connection package is the connection class. With a hibernation configuration file to keep all the 
     *  information in.
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		conn = Lunchhourdb.get();
		
	}
}