package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Student.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class Orderpagecontroller implements Initializable {

    @FXML
    private Label parentsname;

    @FXML
    private Label Balanceamountlabel;

    @FXML
    private Label currentdatelabel;

    @FXML
    private Button Checkhistorybtn;

    @FXML
    private Button logutbtn;

    @FXML
    private ChoiceBox<Student> studentcombox;

    @FXML
    private TableView<?> cartable;

    @FXML
    private Button menubtn;

    @FXML
    private Button orderbtn;

    @FXML
    private Button clearbtn;

    @FXML
    void checkhistory(ActionEvent event) {

    }

    @FXML
    void clearcart(ActionEvent event) {

    }

    @FXML
    void logout(ActionEvent event) throws IOException {
    	Parent Loginpage = FXMLLoader.load(getClass().getClassLoader().getResource("application/Login.fxml"));
        Scene Login = new Scene(Loginpage);
        Stage window = (Stage) logutbtn.getScene().getWindow();
        window.setScene(Login);
        window.show();
    }

    @FXML
    void menuopen(ActionEvent event) {

    }

    @FXML
    void submitorder(ActionEvent event) {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
