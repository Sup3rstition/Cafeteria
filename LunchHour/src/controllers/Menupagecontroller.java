package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import application.Student.Studentinfo;
import connection.Lunchhourdb;

public class Menupagecontroller implements Initializable {
	ObservableList<Cart> cart = FXCollections.observableArrayList();
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
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
    private Spinner<Integer> add1qty;

    @FXML
    private Spinner<Integer> add2qty;

    @FXML
    private Spinner<Integer> add3qty;

    @FXML
    private Label menu1price;

    @FXML
    private Label menu2price;

    @FXML
    private Label menu3price;

    @FXML
    private Label add1price;

    @FXML
    private Label add2price;

    @FXML
    private Label add3price;

    @FXML
    private TableView<Extras> extratable;
    @FXML
    private TableColumn<Extras, String> extracol;

    @FXML
    private TableColumn<Extras, Double> extrapricecol;

    @FXML
    private TableColumn<Extras, Integer> extraamountcol;

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
    private int old1;
    private int old2;
    private int old3;
    
    
    @FXML
    private Label studentid;
    String totaladd;
    @FXML
    void Nextweek(ActionEvent event) {

    }
    Studentinfo Student;
    @FXML
    void addtocart(ActionEvent event) throws IOException, SQLException {
    	String menuitem = null;
    	if(Menu1.isSelected()) {
			menuitem = Menu1.getText();
		}else if(Menu2.isSelected()) {
			menuitem = Menu2.getText();
		}else {
			menuitem = Menu3.getText();
		}

    	cart.add(new Cart(Studentname.getText(), menuitem, menuday.getValue(), totaladd , "0", total_txt.getText(),menudate.getText()));
    	((Node)(event.getSource())).getScene().getWindow().hide();
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
	}

	public void setItems(ObservableList<Cart> items) {
		this.cart = items;
		
	}
	 RadioButton selectedradio;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		extracol.setCellValueFactory(new PropertyValueFactory<Extras, String>("ExtraName"));
		extrapricecol.setCellValueFactory(new PropertyValueFactory<Extras, Double>("ExtraPrice"));
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		conn = Lunchhourdb.get();
		IntegerSpinnerValueFactory add1fac = new IntegerSpinnerValueFactory(0, 99);
		IntegerSpinnerValueFactory add2fac = new IntegerSpinnerValueFactory(0, 99);
		IntegerSpinnerValueFactory add3fac = new IntegerSpinnerValueFactory(0, 99);
		add1qty.setValueFactory(add1fac);
		add2qty.setValueFactory(add2fac);
		add3qty.setValueFactory(add3fac);
		Menu1.setToggleGroup(group);
		Menu2.setToggleGroup(group);
		Menu3.setToggleGroup(group);
		group.selectToggle(Menu1);
		menuday.getItems().addAll("Monday","Tuesday","Wednesday","Thrusday","Friday");
		menuday.getSelectionModel().selectFirst();
		String SQL = "SELECT * from Menu";
		try {
			ps = conn.prepareStatement(SQL);
			rs = ps.executeQuery();
			if(rs.next()) {
				java.sql.Date dbSqlMenudate = rs.getDate("Menu_Start");
		        java.util.Date Menudate = new java.util.Date(dbSqlMenudate.getTime());
				menudate.setText(dateFormat.format(Menudate.getTime()));
				Menu1.setText(rs.getString("Menu 1 item"));
				Menu2.setText(rs.getString("Menu 2 item"));
				Menu3.setText(rs.getString("Menu 3 item"));
				menu1price.setText(rs.getString("Menu 1 Price"));
				menu2price.setText(rs.getString("Menu 2 Price"));
				menu3price.setText(rs.getString("Menu 3 Price"));
				Add1.setText(rs.getString("Additional 1"));
				Add2.setText(rs.getString("Additional 2"));
				Add3.setText(rs.getString("Additional 3"));
				add1price.setText(rs.getString("Add 1 Price"));
				add2price.setText(rs.getString("Add 2 Price"));
				add3price.setText(rs.getString("Add 3 Price"));
				buildData();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
		    public void changed(ObservableValue<? extends Toggle> ov,
		        Toggle old_toggle, Toggle new_toggle) {
		    		selectedradio = (RadioButton)group.getSelectedToggle();
		            		  Totalchange();
		            	  }
		              });
		add1qty.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
			 Totalchange();
	    });
		add2qty.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
			 Totalchange();
	    });
		add3qty.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
			 Totalchange();
	    });
		Totalchange();
	}
	
	private ObservableList<Extras> data;

	public void buildData(){        
	    data = FXCollections.observableArrayList();
	    try{      
	        String SQL = "Select * from Cafeteria.Extras;";            
	        ps = conn.prepareStatement(SQL);
			rs = ps.executeQuery();
	        while(rs.next()){
	        		Extras ex = new Extras();
	            ex.ExtraName.set(rs.getString("Extra_Name"));                       
	            ex.ExtraPrice.set(rs.getDouble("Extra_Price"));
	            data.add(ex);                  
	        }
	        extratable.setItems(data);
	    }
	    catch(Exception e){
	          e.printStackTrace();
	          System.out.println("Error on Building Data");            
	    }
	}
	
	
	private void Totalchange() {
		double Menuprice = 0.00;
		Double Total = 0.00;
		if(selectedradio == Menu3) {
			Menuprice = Double.parseDouble(menu3price.getText());
		}else if(selectedradio == Menu2) {
			Menuprice = Double.parseDouble(menu2price.getText());
		}else {
			Menuprice = Double.parseDouble(menu1price.getText());
		}
		NumberFormat formatter = new DecimalFormat("#0.00");
	
		double Totaladd1 = add1qty.getValue() * Double.parseDouble(add1price.getText());
		double Totaladd2 = add2qty.getValue() * Double.parseDouble(add2price.getText());
		double Totaladd3 = add3qty.getValue() * Double.parseDouble(add3price.getText());
		totaladd = Integer.toString(add1qty.getValue() +add2qty.getValue()+add3qty.getValue());
		Total = Total+Menuprice+Totaladd1+Totaladd2+Totaladd3;
		total_txt.setText(formatter.format(Total));
	}
}

class Extras {
	public SimpleStringProperty ExtraName = new SimpleStringProperty();
	public SimpleDoubleProperty ExtraPrice = new SimpleDoubleProperty();
	public int Extraqty;
	
	   public String getExtraName() {
		return ExtraName.get();
	}
	public void setExtraName(String extraName) {
		ExtraName.set(extraName);
	}
	public Double getExtraPrice() {
		return ExtraPrice.get();
	}
	public void setExtraPrice(Double extraPrice) {
		ExtraPrice.set(extraPrice);
	}
	public int getExtraqty() {
		return Extraqty;
	}
	public void setExtraqty(int extraqty) {
		Extraqty = extraqty;
	}
	   
}
