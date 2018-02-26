package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

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
    private ChoiceBox<?> studentcombox;

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
    void logout(ActionEvent event) {

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
