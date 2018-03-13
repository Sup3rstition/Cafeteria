package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import application.Student.Studentinfo;

public class Menupagecontroller {
	ObservableList<Cart> cart = FXCollections.observableArrayList();
	final ToggleGroup group = new ToggleGroup();
    @FXML
    private RadioButton Menu1;

    @FXML
    private RadioButton Menu2;

    @FXML
    private RadioButton Menu3;

    @FXML
    private Label Add1;

    @FXML
    private Label Add2;

    @FXML
    private Label Add3;

    @FXML
    private TextField total_txt;

    @FXML
    private Button clearbtn;

    @FXML
    private Button addcartbtn;

    @FXML
    private Spinner<?> add1qty;

    @FXML
    private Spinner<?> add2qty;

    @FXML
    private Spinner<?> add3qty;

    @FXML
    private Label mneu1price;

    @FXML
    private Label menu2price;

    @FXML
    private Label menu3price;

    @FXML
    private Label add1price;

    @FXML
    private Label add2price;

    @FXML
    private Label addprice3;

    @FXML
    private TableColumn<?, ?> extracol;

    @FXML
    private TableColumn<?, ?> extrapricecol;

    @FXML
    private TableColumn<?, ?> extraamountcol;

    @FXML
    private Label Studentname;

    @FXML
    private Label menudate;

    @FXML
    private ComboBox<String> menuday;

    @FXML
    private Label studentgrade;

    @FXML
    private Label studentsection;

    @FXML
    private Button backtoOrder;

    @FXML
    private Button nextweek;

    @FXML
    private Label usern;
    
    @FXML
    private Label studentid;
    @FXML
    void Nextweek(ActionEvent event) {

    }
    Studentinfo Student;
    @FXML
    void addtocart(ActionEvent event) throws IOException, SQLException {
    	String menuitem = null;
    	if(Menu1.isSelected()) {
			menuitem = "Menu 1 - Meatloaf";
		}else if(Menu2.isSelected()) {
			menuitem = "Menu 2";
		}else {
			menuitem = "Menu 3";
		}
    	cart.add(new Cart(Studentname.getText(), menuitem, menuday.getValue(), "0", "0", "9.09","03/12/2018"));
    	((Node)(event.getSource())).getScene().getWindow().hide();
    	/*
    	FXMLLoader loader = new FXMLLoader();
     	
    	loader.setLocation(getClass().getClassLoader().getResource("application/OrderPage.fxml"));
        Parent OrderPage = loader.load();
        Scene Orderpage = new Scene(OrderPage);
          Orderpagecontroller secController = loader.getController();
          secController.setUsername(usern.getText());
        Stage window = (Stage) backtoOrder.getScene().getWindow();
        
        window.setScene(Orderpage);
        window.show();
        */
    }

    @FXML
    void backtoorder(ActionEvent event) throws SQLException, IOException {
    	((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void clearorder(ActionEvent event) {

    }

    @FXML
    void selectday(ActionEvent event) {

    }

	public void setinfo(String username, Studentinfo studentinfo) {
		this.usern.setText(username);
		Studentname.setText(studentinfo.getFirstname() + " " + studentinfo.getLastname());
		studentgrade.setText(studentinfo.getGrade());
		studentsection.setText(studentinfo.getSection());
		Menu1.setToggleGroup(group);
		Menu2.setToggleGroup(group);
		Menu3.setToggleGroup(group);
		Menu1.setSelected(true);
		menuday.getItems().addAll("Monday","Tuesday","Wednesday","Thrusday","Friday");
		menuday.getSelectionModel().selectFirst();
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Calendar cal = Calendar.getInstance();
        menudate.setText(dateFormat.format(cal.getTime()));
		
	}

	public void setItems(ObservableList<Cart> items) {
		this.cart = items;
		
	}
	
}
