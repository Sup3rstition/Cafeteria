package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Adminhomepagecontroller implements Initializable{

	private String adminuser;
    public String getAdminuser() {
		return adminuser;
	}

	public void setAdminuser(String adminuser) {
		this.adminuser = adminuser;
	}

	@FXML
    private Button Parentbtn;

    @FXML
    private Button Deliverybtn;

    @FXML
    private Button Orderbtn;

    @FXML
    private Button Menubtn;

    @FXML
    private Button Fdrbtn;

    @FXML
    private Button Logoutbtn;
    @FXML
    private Label Adminname;

    @FXML
    void logout(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/application/Login.fxml"));
    	Parent Login = loader.load();
        Scene login = new Scene(Login);
        Stage window = (Stage)((Node) (event.getSource())).getScene().getWindow();
        window.setScene(login);
    }

    @FXML
    void opendelivery(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/application/GetDelivery.fxml"));
    	Parent delivery = loader.load();
    	Deliverycontroller controller = loader.getController();
		controller.setAdminuser(adminuser);
        Scene Delivery = new Scene(delivery);
        Stage window = (Stage)((Node) (event.getSource())).getScene().getWindow();
        window.setScene(Delivery);
    }

    @FXML
    void openfdr(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/application/getFDR.fxml"));
    	Parent fdr = loader.load();
        Scene Fdr = new Scene(fdr);
        Stage window = (Stage)((Node) (event.getSource())).getScene().getWindow();
        window.setScene(Fdr);
    }

    @FXML
    void openmenu(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/application/MenuEditor.fxml"));
    	Parent Menupage = loader.load();
    	Menucontroller control = loader.getController();
    	control.setAdminuser(adminuser);
    	control.firststart();
        Scene Menu = new Scene(Menupage);
        Stage window = (Stage)((Node) (event.getSource())).getScene().getWindow();
        window.setScene(Menu);
    }

    @FXML
    void openorder(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/application/getOrder.fxml"));
    	Parent orderpage = loader.load();
        Scene order = new Scene(orderpage);
        Stage window = (Stage)((Node) (event.getSource())).getScene().getWindow();
        window.setScene(order);
    }

    @FXML
    void openparent(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/application/EditUserPage.fxml"));
    	Parent parentpage = loader.load();
    	Editusercontroller controller = loader.getController();
		controller.setAdminuser(adminuser);
        Scene Parent = new Scene(parentpage);
        Stage window = (Stage)((Node) (event.getSource())).getScene().getWindow();
        window.setScene(Parent);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	public void start() {
		Adminname.setText("Welcome, " + adminuser);
	}

}
