package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
     	/*
    	FXMLLoader loader = new FXMLLoader();
     	
    	loader.setLocation(getClass().getClassLoader().getResource("application/OrderPage.fxml"));
        Parent OrderPage = loader.load();
        Scene Orderpage = new Scene(OrderPage);
          Orderpagecontroller secController = loader.getController();
          secController.setUsername(username.getText());
        Stage window = (Stage) Backbtn.getScene().getWindow();
        
        window.setScene(Orderpage);
        window.show();
        */
     	((Node)(event.getSource())).getScene().getWindow().hide();
    }
    public void setUsername_(String user) {
    	this.username.setText(user);
    	
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		order.setCellValueFactory(new PropertyValueFactory<Cart,String>("menuweek"));
		name.setCellValueFactory(new PropertyValueFactory<Cart, String>("fullname"));
		menu.setCellValueFactory(new PropertyValueFactory<Cart,String>("menuweek"));
		menuitem.setCellValueFactory(new PropertyValueFactory<Cart, String>("menuitem"));
		add.setCellValueFactory(new PropertyValueFactory<Cart, String>("add"));
		extra.setCellValueFactory(new PropertyValueFactory<Cart, String>("extra"));
		total.setCellValueFactory(new PropertyValueFactory<Cart, String>("total"));
	}
    @FXML
    void print(ActionEvent event) {
    	Alert a = new Alert(AlertType.INFORMATION);
    	a.setContentText("Sent to printer");
    	a.showAndWait();
    }

    @FXML
    void save(ActionEvent event) {
    	Alert a = new Alert(AlertType.INFORMATION);
    	a.setContentText("Saved under documents");
    	a.showAndWait();
    }

    @FXML
    private TableView<Cart> orderhistory;

    @FXML
    private TableColumn<Cart, String> order;

    @FXML
    private TableColumn<Cart, String> name;

    @FXML
    private TableColumn<Cart, String> menu;

    @FXML
    private TableColumn<Cart, String> menuitem;

    @FXML
    private TableColumn<Cart, String> add;

    @FXML
    private TableColumn<Cart, String> extra;

    @FXML
    private TableColumn<Cart, String> total;

    @FXML
    void search(ActionEvent event) {
    }

}
