package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
    
    @FXML
    private Label Invalidinfo;
    private String userName;
    public String getUsername() {
		return userName;
	}

    @FXML
    void performLoginAction(ActionEvent event)  {
    	 	 userName = Username_txt.getText().trim().toUpperCase();
    	    String password = Password_txt.getText().trim();
    	    if(userName.length() >=6 && password.length() >= 3) {
    		
					
    	    	Invalidinfo.setVisible(false);
    	    String sql = "SELECT * from Cafeteria.Administrators WHERE Username = ? AND Password = ?;";
    	    try {
    	    	conn = Lunchhourdb.get();
    	        ps = conn.prepareStatement(sql);
    	        ps.setString(1, userName);
    	        ps.setString(2, password);
    	        rs = ps.executeQuery();
    	        
    	        if (rs.next()) {
    	        	Wronginfo.setVisible(false);
    	        	FXMLLoader loader = new FXMLLoader();
    	        	loader.setLocation(getClass().getResource("/application/Adminhomepage.fxml"));

    	        	loader.load();
    	        	Adminhomepagecontroller controller = loader.getController();
   	             controller.setAdminuser(userName);
   	             controller.start();
    	        	Parent order = loader.getRoot();

    	             // Display popup
    	             Stage stage = new Stage();

    	             stage.setScene(new Scene(order));
    	             stage.show();
    	             conn.close();
    	           	((Node)(event.getSource())).getScene().getWindow().hide();
    	            
    	        } else {
    	            Wronginfo.setVisible(true);
    	        }
    	        conn.close();
    	    } catch (Exception e) {
    	    	Alert Error2= new Alert(AlertType.ERROR, "An error has occured while connecting with the database.\n Please check your internet connection and try again.");
    	   		Error2.setTitle("Error");
    	   		Error2.setHeaderText("Connection Error!");
    	   		Error2.showAndWait();
    	    }
    	    }else {
    	    	Invalidinfo.setVisible(true);
    	    }
    }
   
    public void exitBtnAction(ActionEvent event) {
        Stage stage = (Stage) exitBtn.getScene().getWindow();
        stage.close();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
}