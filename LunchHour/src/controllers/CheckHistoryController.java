package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CheckHistoryController {

    @FXML
    private Button savebtn;

    @FXML
    private Button printbtn;

    @FXML
    private Button Backbtn;

    @FXML
    private Button searchbtn;
    
    @FXML
    void backtoorder(ActionEvent event) throws IOException {
    	Parent OrderPage = FXMLLoader.load(getClass().getClassLoader().getResource("application/OrderPage.fxml"));
        Scene Orderpage = new Scene(OrderPage);
        Stage window = (Stage) OrderPage.getScene().getWindow();
        window.setScene(Orderpage);
        window.show();
    }
}
