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
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableCell;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.util.Callback;
import application.Student.Studentinfo;
import connection.Lunchhourdb;

public class Menupagecontroller implements Initializable {
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
    private TableColumn<Extras, Extras> extraamountcol;

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
    String totaladd;
    @FXML
    void Nextweek(ActionEvent event) {

    }
    Studentinfo Student;
    TreeItem<Cart> root = Cart.getTreeRoot();
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
    	
    	Cart order = new Cart(Studentname.getText(), menuitem, menuday.getValue(), totaladd , totalextra(), Double.parseDouble(total_txt.getText()),menudate.getText(), studentid_);
    	TreeItem<Cart> order1 = new TreeItem<Cart>(order);
    	
    			if(add1qty.getValue() > 0) {
    		Cart extra = new Cart(null, null, null,Add1.getText() + " x" + add1qty.getValue() , null,  add1qty.getValue() * Double.parseDouble(add1price.getText()),null,0);
        	order1.getChildren().add(new TreeItem<Cart> (extra));
    	} if(add2qty.getValue() > 0) {
    		Cart extra = new Cart(null, null, null,Add2.getText()+ " x" + add2qty.getValue() , null,  add2qty.getValue() * Double.parseDouble(add2price.getText()),null,0);
        	order1.getChildren().add(new TreeItem<Cart> (extra));
    	} if(add3qty.getValue()>0) {
    		Cart extra = new Cart(null, null, null,Add3.getText()+ " x" + add3qty.getValue() , null,  add3qty.getValue() * Double.parseDouble(add3price.getText()),null,0);
        	order1.getChildren().add(new TreeItem<Cart> (extra));
    	}
    	for(int i=0; i < extratable.getItems().size();i++) {
	    	Extras tableRow = extratable.getItems().get(i);
	    int qty = tableRow.getItemCount();
	    if(qty >= 1) {
	    Cart extra = new Cart(null, null, null,null , tableRow.getExtraName() + " x" + qty , tableRow.getExtraPrice() * qty,null,0);
    	order1.getChildren().add(new TreeItem<Cart> (extra));
	    }
    	}
    	root.getChildren().add(order1);
    	
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/application/OrderPage.fxml"));
    	Parent Orderpage = loader.load();
        Scene Order = new Scene(Orderpage);
        Stage window = (Stage)((Node) (event.getSource())).getScene().getWindow();
        window.setScene(Order);
    }

    @FXML
    void backtoorder(ActionEvent event) throws SQLException, IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/application/OrderPage.fxml"));
    	Parent Orderpage = loader.load();
        Scene Order = new Scene(Orderpage);
        Stage window = (Stage)((Node) (event.getSource())).getScene().getWindow();
        window.setScene(Order);
    }

    @FXML
    void clearorder(ActionEvent event) {
    	Menu1.setSelected(true);
    	add1qty.getValueFactory().setValue(0);
    	add2qty.getValueFactory().setValue(0);
    	add3qty.getValueFactory().setValue(0);
    	for(int i=0; i < extratable.getItems().size();i++) {
	    	Extras tableRow = extratable.getItems().get(i);
	    tableRow.setItemCount(0);
		}
    	Totalchange();
    	}
    private String totalextra() {
    	int totalextra=0;	
    	for(int i=0; i < extratable.getItems().size();i++) {
	    	Extras tableRow = extratable.getItems().get(i);
	    int qty = tableRow.getItemCount();
	    totalextra = totalextra + qty;
		}
		return Integer.toString(totalextra);
    }
    

    @FXML
    void selectday(ActionEvent event) {

    }
private int studentid_;
	public void setinfo(Studentinfo studentinfo) {
		Studentname.setText(studentinfo.getFirstname() + " " + studentinfo.getLastname());
		studentgrade.setText(studentinfo.getGrade());
		studentsection.setText(studentinfo.getSection());
		studentid_ = studentinfo.getId();
	}
	 RadioButton selectedradio;
		private ObservableList<Extras> extras  = FXCollections.observableArrayList();
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Getting connection
		conn = Lunchhourdb.get();
		//extras table Method
		BuildExtraTable();
		//Menu building method
		BuildMenu();
		//Sets the price for the text box
		Totalchange();
	}
	//Menu building method
	private void BuildMenu() {
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
			}
		} catch (SQLException e) {
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
	}
	// Builds the extra table
	private void BuildExtraTable(){
		conn = Lunchhourdb.get();
		extracol.setCellValueFactory(new PropertyValueFactory<Extras, String>("ExtraName"));
		extrapricecol.setCellValueFactory(new PropertyValueFactory<Extras, Double>("ExtraPrice"));
		extraamountcol.setCellValueFactory(cd -> Bindings.createObjectBinding(() -> cd.getValue()));
		   extraamountcol.setCellFactory(new Callback<TableColumn<Extras, Extras>, TableCell<Extras, Extras>>() {
		        @Override
		        public TableCell<Extras, Extras> call(TableColumn<Extras, Extras> param) {
		            TableCell<Extras, Extras> cell = new TableCell<Extras, Extras>() {

		                private final Spinner<Integer> count;

		                private final SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory;
		                private final ChangeListener<Number> valueChangeListener;

		                {
		                    valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 0);
		                    count = new Spinner<>(valueFactory);
		                    count.setVisible(false);
		                    setGraphic(count);
		                    valueChangeListener = (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
		                        valueFactory.setValue(newValue.intValue());
		                        Totalchange();
		                    };
		                    count.valueProperty().addListener((obs, oldValue, newValue) -> {
		                        if (getItem() != null) {
		                            // write new value to table item
		                            getItem().setItemCount(newValue);
		                            
		                        }
		                    });
		                }

		                @Override
		                public void updateItem(Extras item, boolean empty) {

		                    // unbind old values
		                    valueFactory.maxProperty().unbind();
		                    if (getItem() != null) {
		                        getItem().itemCountProperty().removeListener(valueChangeListener);
		                    }

		                    super.updateItem(item, empty);

		                    // update according to new item
		                    if (empty || item == null) {
		                        count.setVisible(false);
		                    } else {
		                        valueFactory.maxProperty().bind(item.itemMaxCountProperty());
		                        valueFactory.setValue(item.getItemCount());
		                        item.itemCountProperty().addListener(valueChangeListener);
		                        count.setVisible(true);
		                    }

		                }
		            };

		            return cell;
		        }
		    });
		 try{      
	        String Sql = "Select * from Cafeteria.Extras";            
	        ps = conn.prepareStatement(Sql);
			rs = ps.executeQuery();
	        while(rs.next()){
	        		extras.add(new Extras (rs.getString("Extra_Name"),rs.getDouble("Extra_Price"), 0, 99));
	        }
	        extratable.setItems(extras);
	    }
	    catch(Exception e){
	          e.printStackTrace();
	          System.out.println("Error on Building Data");            
	    }
		
	}
	
	//Method to change the Total for the menu total price
	NumberFormat formatter = new DecimalFormat("#0.00");
	private void Totalchange() {
		double Menuprice = 0.00;
		Double Total;
		if(selectedradio == Menu3) {
			Menuprice = Double.parseDouble(menu3price.getText());
		}else if(selectedradio == Menu2) {
			Menuprice = Double.parseDouble(menu2price.getText());
		}else {
			Menuprice = Double.parseDouble(menu1price.getText());
		}
		double totalextra=0;	
    	for(int i=0; i < extratable.getItems().size();i++) {
	    Extras tableRow = extratable.getItems().get(i);
	    int qty = tableRow.getItemCount();
	    double extraprice = tableRow.getExtraPrice();
	     totalextra = totalextra + (qty * extraprice);
	     
	     
	     
		}
		double Totaladd1 = add1qty.getValue() * Double.parseDouble(add1price.getText());
		double Totaladd2 = add2qty.getValue() * Double.parseDouble(add2price.getText());
		double Totaladd3 = add3qty.getValue() * Double.parseDouble(add3price.getText());
		totaladd = Integer.toString(add1qty.getValue() +add2qty.getValue()+add3qty.getValue());
		Total = Menuprice+Totaladd1+Totaladd2+Totaladd3 + totalextra;
		total_txt.setText(formatter.format(Total));
	}
	
	
	//Extra class
public static class Extras {
	private SimpleStringProperty ExtraName;
	private SimpleDoubleProperty ExtraPrice;
	 private final SimpleIntegerProperty itemMaxCount;
	 private final SimpleIntegerProperty itemCount;
	    	public final IntegerProperty itemMaxCountProperty() {
	            return this.itemMaxCount;
	        }

	public Extras(String name, double price, int qty, int max) {
		this.ExtraName = new SimpleStringProperty(name);
		this.ExtraPrice = new SimpleDoubleProperty(price);
		this.itemCount = new SimpleIntegerProperty(qty);
		this.itemMaxCount = new SimpleIntegerProperty(max);
	}
	public String getExtraName() {
		return ExtraName.get();
	}
	public void setExtraName(String extraName) {
		ExtraName.set(extraName);
	}
	public double getExtraPrice() {
		return ExtraPrice.get();
	}
	public void setExtraPrice(double extraPrice) {
		ExtraPrice.set(extraPrice);
	}

	    @Override
	    public String toString() {
	        return Integer.toString(getItemCount());
	    }

	   

	    public final int getItemCount() {
	        return this.itemCount.get();
	    }

	    public final void setItemCount(int value) {
	        this.itemCount.set(value);
	    }

	    public final IntegerProperty itemCountProperty() {
	        return this.itemCount;
	    }

}
}