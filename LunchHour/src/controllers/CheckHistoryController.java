package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CheckHistoryController implements Initializable {

    @FXML
    private Button savebtn;

    @FXML
    private Button printbtn;

    @FXML
    private Button Backbtn;

    @FXML
    private Button searchbtn;
    @FXML
    private Label username;
    
    @FXML
    void backtoorder(ActionEvent event) throws IOException, SQLException {
     	FXMLLoader loader = new FXMLLoader();
     	
    	loader.setLocation(getClass().getClassLoader().getResource("application/OrderPage.fxml"));
        Parent OrderPage = loader.load();
        Scene Orderpage = new Scene(OrderPage);
          Orderpagecontroller secController = loader.getController();
          secController.setUsername(username.getText());
        Stage window = (Stage) Backbtn.getScene().getWindow();
        
        window.setScene(Orderpage);
        window.show();
    }
    public void setUsername_(String user) {
    	this.username.setText(user);
    	
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
}
