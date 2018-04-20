package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.ResourceBundle;

import connection.Lunchhourdb;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
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
    private Label Createaccount;

    @FXML
    private Label Wronginfo;
    
    @FXML
    private Button exitBtn;
    
    @FXML	//if the user wants a new account they will click on the create account label.
    void opencreateaccount(MouseEvent event) throws IOException {
        Parent CreateAccount = FXMLLoader.load(getClass().getClassLoader().getResource("application/CreateAccountPage.fxml"));
        Scene CreateAccountPage = new Scene(CreateAccount);
        Stage window = (Stage) Createaccount.getScene().getWindow();
        window.setScene(CreateAccountPage);
        window.show();
    }
    
    /*
     * Scene changer
     */
    @FXML
    private Label Invalidinfo;
    /*
     * The action for  the login button to create a connection to the database to search for the user name and the password.
     */
    private static String username;
    public static String getUsername() {
		return username;
	}

	@FXML
    void performLoginAction(ActionEvent event) {
    	 	 username = Username_txt.getText().trim().toUpperCase(); // removes any space after the username and capitlize it to make a make sure username isn't case-sensitive.
    	    String password = Password_txt.getText().trim();
    	    if(username.length() >=6 && password.length() >= 3) {
    	    	Invalidinfo.setVisible(false);
    	    	/*
    	    	 * creates a sql command to search the parents for the username and password.
    	    	 */
    	    String sql = "SELECT * from Cafeteria.Parents WHERE userName = ?;";
    	    try {
    	    	conn = Lunchhourdb.get();
    	    	// Creates the prepared statement to search the database with.
    	        ps = conn.prepareStatement(sql);
    	        ps.setString(1, username);
    	        rs = ps.executeQuery();		// this is the result from the query and will be check to see if any result came back.
    	        
    	        if (rs.next()) {		// if there is a result from the serach in the database this changes the stage to the order page.
    	        	if(BCrypt.checkpw(password, rs.getString("password"))){
    	        	sql = "Update Cafeteria.Parents Set Last_Sign_in = ? Where username = ? ";
    	        	ps = conn.prepareStatement(sql);
    	        	java.util.Date javaDate = new java.util.Date();
    	        	java.sql.Date now = new java.sql.Date(javaDate.getTime());
    	        	
    	        	ps.setDate(1, now );
    	        	ps.setString(2, username);
    	        	ps.execute();
    	        	Wronginfo.setVisible(false); 
    	        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/OrderPage.fxml"));
    	        	loader.load();
    	        	Parent order = loader.getRoot();
    	        	Orderpagecontroller controller = loader.getController();
    	        	controller.Setinfo();
    	             // Display popup
    	             Stage stage = new Stage();
    	             stage.setScene(new Scene(order));
    	             stage.getIcons().add(new Image(("file:icon.png")));
    	             //This displays the stage and waits for the input
    	             stage.show();
    	             conn.close();
    	           	((Node)(event.getSource())).getScene().getWindow().hide();
    	        	}else {
    	        		Wronginfo.setVisible(true);
    	        	}
    	        		
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
   @FXML
   void close(ActionEvent event) {
	   ((Node)(event.getSource())).getScene().getWindow().hide();
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
	}
}