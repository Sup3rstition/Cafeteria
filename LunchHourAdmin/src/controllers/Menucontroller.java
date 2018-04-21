package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Models.Menu;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Menucontroller implements Initializable {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
    @FXML
    private Button menu1btn;

    @FXML
    private Button menu2btn;

    @FXML
    private Button menu3btn;

    @FXML
    private Button add1btn;

    @FXML
    private Button add2btn;

    @FXML
    private CheckBox checkbox1;

    @FXML
    private CheckBox checkbox2;

    @FXML
    private CheckBox checkbox3;

    @FXML
    private CheckBox checkbox4;

    @FXML
    private CheckBox checkbox5;

    @FXML
    private CheckBox checkbox6;

    @FXML
    private Button editextrabtn;

    @FXML
    private Button submitbtn;

    @FXML
    private Button backbtn;

    @FXML
    private Label menu1;

    @FXML
    private Label menu2;

    @FXML
    private Label menu3;

    @FXML
    private Label add1;

    @FXML
    private Label add2;

    @FXML
    private Label add3;
    @FXML
    private Label m1price;

    @FXML
    private Label m2price;

    @FXML
    private Label m3price;

    @FXML
    private Label add1price;

    @FXML
    private Label add2price;

    @FXML
    private Label add3price;
    void setitems() {
		menu1.setText(menu.getMenu1());
		m1price.setText(menu.getMenu1price());
		menu2.setText(menu.getMenu2());
		m2price.setText(menu.getMenu2price());
		menu3.setText(menu.getMenu3());
		m3price.setText(menu.getMenu3price());
		add1.setText(menu.getAdd1());
		add2.setText(menu.getAdd2());
		add3.setText(menu.getAdd3());
		add1price.setText(menu.getAdd1price());
		add2price.setText(menu.getAdd2price());
		add3price.setText(menu.getAdd3price());
	}

    @FXML
    private ToggleButton nextweekbtn;
    private String adminuser;
    public void setAdminuser(String user) {
		this.adminuser = user;
		
	}
    @FXML
    void back(ActionEvent event) throws IOException {
     	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/application/Adminhomepage.fxml"));
    	Parent Orderpage = loader.load();
    	Adminhomepagecontroller controller = loader.getController();
		controller.setAdminuser(adminuser);
		controller.start();
        Scene Order = new Scene(Orderpage);
        Stage window = (Stage)((Node) (event.getSource())).getScene().getWindow();
        window.setScene(Order);
    }

    @FXML
    void check1(ActionEvent event) {

    }

    @FXML
    void check2(ActionEvent event) {

    }

    @FXML
    void check3(ActionEvent event) {

    }

    @FXML
    void check4(ActionEvent event) {

    }

    @FXML
    void check5(ActionEvent event) {

    }

    @FXML
    void check6(ActionEvent event) {

    }

    @FXML
    void editadd1(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/application/Edititem.fxml"));
    	Parent Menupage = loader.load();
    	Edititemcontroller controller = loader.getController();
        controller.setMenu(menu);
        controller.setAdminuser(adminuser);
        controller.setItemnum(3);
        controller.start();
        Scene Menu = new Scene(Menupage);
        Stage window = (Stage)((Node) (event.getSource())).getScene().getWindow();
        window.setScene(Menu);
    }

    @FXML
    void editadd2(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/application/Edititem.fxml"));
    	Parent Menupage = loader.load();
    	Edititemcontroller controller = loader.getController();
        controller.setMenu(menu);
        controller.setAdminuser(adminuser);
        controller.setItemnum(4);
        controller.start();
        Scene Menu = new Scene(Menupage);
        Stage window = (Stage)((Node) (event.getSource())).getScene().getWindow();
        window.setScene(Menu);
    }

    @FXML
    void editadd3(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/application/Edititem.fxml"));
    	Parent Menupage = loader.load();
    	Edititemcontroller controller = loader.getController();
        controller.setMenu(menu);
        controller.setAdminuser(adminuser);
        controller.setItemnum(5);
        controller.start();
        Scene Menu = new Scene(Menupage);
        Stage window = (Stage)((Node) (event.getSource())).getScene().getWindow();
        window.setScene(Menu);
    }

    @FXML
    void editmenu1(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/application/Edititem.fxml"));
    	Parent Menupage = loader.load();
    	Edititemcontroller controller = loader.getController();
        controller.setMenu(menu);
        controller.setAdminuser(adminuser);
        controller.setItemnum(0);
        controller.start();
        Scene Menu = new Scene(Menupage);
        Stage window = (Stage)((Node) (event.getSource())).getScene().getWindow();
        window.setScene(Menu);
    }

    @FXML
    void editmenu2(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/application/Edititem.fxml"));
    	Parent Menupage = loader.load();
    	Edititemcontroller controller = loader.getController();
        controller.setMenu(menu);
        controller.setAdminuser(adminuser);
        controller.setItemnum(1);
        controller.start();
        Scene Menu = new Scene(Menupage);
        Stage window = (Stage)((Node) (event.getSource())).getScene().getWindow();
        window.setScene(Menu);
    }

    @FXML
    void editmenu3(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/application/Edititem.fxml"));
    	Parent Menupage = loader.load();
    	Edititemcontroller controller = loader.getController();
        controller.setMenu(menu);
        controller.setAdminuser(adminuser);
        controller.setItemnum(2);
        controller.start();
        Scene Menu = new Scene(Menupage);
        Stage window = (Stage)((Node) (event.getSource())).getScene().getWindow();
        window.setScene(Menu);
    }

    @FXML
    void exitextra(ActionEvent event) {

    }

    @FXML
    void nextweekbtn(ActionEvent event) {

    }

    @FXML
    void submit(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Would you like to submit the menu?" , ButtonType.YES, ButtonType.CANCEL);
	   	 alert.showAndWait();

	   	 if (alert.getResult() == ButtonType.YES) {
	   		Alert Done = new Alert(AlertType.INFORMATION);
	   		Done.setTitle("Success!");
	   		Done.setHeaderText("Menu submission Successful!");
	   		Done.setContentText("The Menu has been updated!");

	   		Done.showAndWait();
	   	 }
    }

    	Menu menu;
	public Menu getMenu() {
			return menu;
		}

		public void setMenu(Menu menu) {
			this.menu = menu;
		}
			@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(menu == null) {
		
		String SQL = "SELECT * from Menu";
			try {
				conn = Lunchhourdb.get();
				ps = conn.prepareStatement(SQL);
				rs = ps.executeQuery();
				if(rs.next()) {
					Menu menu = new Menu();
					menu.setMenu1(rs.getString("Menu 1 item"));
					menu.setMenu2(rs.getString("Menu 2 item"));
					menu.setMenu3(rs.getString("Menu 3 item"));
					menu.setMenu1price(rs.getString("Menu 1 Price"));
					menu.setMenu2price(rs.getString("Menu 2 Price"));
					menu.setMenu3price(rs.getString("Menu 3 Price"));
					menu.setStart(rs.getDate("Menu_Start"));
					menu.setAdd1(rs.getString("Additional 1"));
					menu.setAdd2(rs.getString("Additional 2"));
					menu.setAdd3(rs.getString("Additional 3"));
					menu.setAdd1price(rs.getString("Add 1 Price"));
					menu.setAdd2price(rs.getString("Add 2 Price"));
					menu.setAdd3price(rs.getString("Add 3 Price"));
					this.menu = menu;
					setitems();
				}
			} catch (SQLException e) {
				Alert Error2= new Alert(AlertType.ERROR, "An error has occured while connecting with the database.\n Please check your internet connection and try again.");
    	   		Error2.setTitle("Error");
    	   		Error2.setHeaderText("Connection Error!");
    	   		Error2.showAndWait();
			}
		}else {
			setitems();
		}
				
		
	}

	
}
