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
    @FXML
    void performLoginAction(ActionEvent event) {
    	 	String userName = Username_txt.getText().trim().toUpperCase();
    	    String password = Password_txt.getText().trim();
    	    if(userName.length() >=6 && password.length() >= 3) {
    	    	Invalidinfo.setVisible(false);
    	    String sql = "SELECT * from Cafeteria.Parents WHERE userName = ? AND password = ?;";
    	    try {
    	        ps = conn.prepareStatement(sql);
    	        ps.setString(1, userName);
    	        ps.setString(2, password);
    	        rs = ps.executeQuery();
    	        
    	        if (rs.next()) {
    	        	Wronginfo.setVisible(false);
    	            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("application/OrderPage.fxml"));
    	            Stage stage = new Stage();
    	            stage.setScene(new Scene(root));
    	            stage.setTitle("Elevel Cafeteria");
    	            stage.show();
    	            
    	            Stage login = (Stage) exitBtn.getScene().getWindow();
    	            login.close();
    	            
    	        } else {
    	            Wronginfo.setVisible(true);
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		conn = Lunchhourdb.get();
		
	}
}