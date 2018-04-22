package controllers;


import java.io.File;

import java.io.FileNotFoundException;

import java.io.IOException;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.javafx.scene.control.skin.DatePickerSkin;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
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
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeItem;
import javafx.util.Callback;
import connection.Lunchhourdb;
import entities.Menu;
import entities.Parentinfo;
import entities.Studentinfo;
import entities.Extras;

public class Menupagecontroller implements Initializable {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	final ToggleGroup group = new ToggleGroup();
	Studentinfo Student;
	String totaladd;
	TreeItem<Cart> root;
	ObservableList<Studentinfo> items = FXCollections.observableArrayList();
	public void setItems(ObservableList<Studentinfo> items) {
		this.items = items;
	}
	public TreeItem<Cart> getRoot() {
		return root;
	}

	public void setRoot(TreeItem<Cart> root) {
		this.root = root;
	}
	private int studentid_;
	RadioButton selectedradio;
	private ObservableList<Extras> extras  = FXCollections.observableArrayList();
	NumberFormat formatter = new DecimalFormat("#0.00");
	DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	 DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	@FXML
    private Tooltip menu1des;
	@FXML
    private Tooltip menu2des;
	 @FXML
	    private Tooltip menu3des;
	 @FXML
	    private Tooltip add1des;
	 @FXML
	    private Tooltip add2des;
	 @FXML
	    private Tooltip add3des;
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
    private DatePicker orderdate;

    @FXML
    private Label menudaylabel;
    
    @FXML
    void backtoorder(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/application/OrderPage.fxml"));
    	Parent Orderpage = loader.load();
    	 Orderpagecontroller controller = loader.getController();
    	 controller.setparent(parent);
    	 controller.setItems(items);
    	 controller.setRoot(root);
    	 controller.setInfo();
        Scene Order = new Scene(Orderpage);
        Stage window = (Stage)((Node) (event.getSource())).getScene().getWindow();
        window.setScene(Order);
    }
    
    @FXML
    void addtocart(ActionEvent event) throws IOException {
    	String menuitem = null;
    	if(Menu1.isSelected()) {
			menuitem = Menu1.getText();
		}else if(Menu2.isSelected()) {
			menuitem = Menu2.getText();
		}else {
			menuitem = Menu3.getText();
		}
    	
    	Cart order = new Cart(Studentname.getText(), menuitem, orderdate.getValue().format(dateformatter) , totaladd , totalextra(), Double.parseDouble(total_txt.getText()),menudate.getText(), studentid_);
    	TreeItem<Cart> order1 = new TreeItem<Cart>(order);
    	order.setMenuorderdate(java.sql.Date.valueOf(orderdate.getValue()));
    	
    			if(add1qty.getValue() > 0) {
    		Cart extra = new Cart(null, null, null,Add1.getText() + " -" + add1qty.getValue() , null,  add1qty.getValue() * Double.parseDouble(add1price.getText()),null,0);
        	order1.getChildren().add(new TreeItem<Cart> (extra));
    	} if(add2qty.getValue() > 0) {
    		Cart extra = new Cart(null, null, null,Add2.getText()+ " -" + add2qty.getValue() , null,  add2qty.getValue() * Double.parseDouble(add2price.getText()),null,0);
        	order1.getChildren().add(new TreeItem<Cart> (extra));
    	} if(add3qty.getValue()>0) {
    		Cart extra = new Cart(null, null, null,Add3.getText()+ " -" + add3qty.getValue() , null,  add3qty.getValue() * Double.parseDouble(add3price.getText()),null,0);
        	order1.getChildren().add(new TreeItem<Cart> (extra));
    	}
    	for(int i=0; i < extratable.getItems().size();i++) {
	    	Extras tableRow = extratable.getItems().get(i);
	    int qty = tableRow.getItemCount();
	    if(qty >= 1) {
	    Cart extra = new Cart(null, null, null,null , tableRow.getExtraName() + " -" + qty , tableRow.getExtraPrice() * qty,null,0);
    	order1.getChildren().add(new TreeItem<Cart> (extra));
	    }
    	}
    	root.getChildren().add(order1);
    	
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/application/OrderPage.fxml"));
    	Parent Orderpage = loader.load();
    	 Orderpagecontroller controller = loader.getController();
    	 controller.setRoot(root);
    	 controller.setItems(items);
    	 controller.setparent(parent);
    	 controller.setInfo();
        Scene Order = new Scene(Orderpage);
        Stage window = (Stage)((Node) (event.getSource())).getScene().getWindow();
        window.setScene(Order);
    }

    @FXML
    void clearorder(ActionEvent event) {
    	if(Menu1.getText().equals("No Item")) {
    		Menu1.setSelected(false);
    		Menu2.setSelected(true);
    	}if(Menu2.getText().equals("No Item") && Menu1.getText().equals("No Item")) {
    		Menu2.setSelected(false);
    		Menu3.setSelected(true);
    	}else {
    		Menu2.setSelected(false);
    		Menu3.setSelected(false);
    		Menu1.setSelected(true);
    	}
    	add1qty.getValueFactory().setValue(0);
    	add2qty.getValueFactory().setValue(0);
    	add3qty.getValueFactory().setValue(0);
    	for(int i=0; i < extratable.getItems().size();i++) {
	    	Extras tableRow = extratable.getItems().get(i);
	    tableRow.setItemCount(0);
		}
    	Totalchange();
    	}
    

    
	public void setinfo(Studentinfo studentinfo) {
		Studentname.setText(studentinfo.getFirstname() + " " + studentinfo.getLastname());
		studentgrade.setText(studentinfo.getGrade());
		studentsection.setText(studentinfo.getSection());
		studentid_ = studentinfo.getId();
	}
	private Parentinfo parent;
	public Parentinfo getParent() {
		return parent;
	}

	public void setParent(Parentinfo parent) {
		this.parent = parent;
	}
	public void start() {
		try {
			BuildExtraTable();
			//Menu building method
			orderdate();
			BuildMenu();
			//Sets the price for the text box
			setprops();
			Totalchange();
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	private void orderdate() {
        final Callback<DatePicker, DateCell> dayCellFactory = 
            new Callback<DatePicker, DateCell>() {
                @Override
                public DateCell call(final DatePicker datePicker) {
                    return new DateCell() {
                        @Override
                        public void updateItem(LocalDate item, boolean empty) {
                            super.updateItem(item, empty);
                            LocalDate now = LocalDate.now();
                            LocalDateTime date1 = LocalDateTime.now();
                            LocalDateTime date2 = now.atStartOfDay().plusHours(7);
                            DayOfWeek day = DayOfWeek.from(item);
                            if (item.isAfter(now.with(TemporalAdjusters.next(DayOfWeek.FRIDAY)).plusDays(7)) || day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY || item.isBefore(now)
                            		|| (date1.isAfter(date2) && item.isBefore(now.plusDays(1)))
                                ) {
                                    setDisable(true);
                                    setStyle("-fx-background-color: #ffc0cb;");
                            }
                            if(now.getDayOfWeek() == DayOfWeek.FRIDAY && date1.isAfter(date2) && item.compareTo(now) == 0) {
                            	setDisable(true);
                                setStyle("-fx-background-color: #ffc0cb;");
                            }
                            if(now.getDayOfWeek() == DayOfWeek.FRIDAY && item.isAfter(now.with(TemporalAdjusters.next(DayOfWeek.FRIDAY))) && date1.isBefore(date2)) {
                            	setDisable(true);
                                setStyle("-fx-background-color: #ffc0cb;");
                            }

                    }
                };
            }
	};
	 LocalDate now = LocalDate.now();
	 DayOfWeek day = DayOfWeek.from(now);
     LocalDateTime date1 = LocalDateTime.now();
     LocalDateTime date2 = now.atStartOfDay().plusHours(7);
	 if(day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY || (day == DayOfWeek.FRIDAY  && date1.isAfter(date2) )) {
		 orderdate.setValue(now.with(TemporalAdjusters.next(DayOfWeek.MONDAY)));
	 }else if(date1.isAfter(date2)) {
		 orderdate.setValue(now.plusDays(1));
	 }
	 else {
		 orderdate.setValue(now);
	 }
	 orderdate.setDayCellFactory(dayCellFactory);
	 menudaylabel.setText(orderdate.getValue().getDayOfWeek().toString());
		orderdate.valueProperty().addListener((ov, oldValue, newValue) -> {
			if(newValue.isAfter(menu.getEnd().toLocalDate())) {
				try {
					BuildNextMenu();
				} catch (JsonParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(newValue.isBefore(menu.getStart().toLocalDate())) {
				try {
					BuildMenu();
				} catch (JsonParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        });
	}
	void setprops() {
		IntegerSpinnerValueFactory add1fac = new IntegerSpinnerValueFactory(0, 99);
		IntegerSpinnerValueFactory add2fac = new IntegerSpinnerValueFactory(0, 99);
		IntegerSpinnerValueFactory add3fac = new IntegerSpinnerValueFactory(0, 99);
		add1qty.setValueFactory(add1fac);
		add2qty.setValueFactory(add2fac);
		add3qty.setValueFactory(add3fac);
		Menu1.setToggleGroup(group);
		Menu2.setToggleGroup(group);
		Menu3.setToggleGroup(group);
		group.selectToggle(Menu1);;
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
@FXML
void changeday(ActionEvent event) {
	menudaylabel.setText(orderdate.getValue().getDayOfWeek().toString());
}
void setmenus() {
	if(!menu.getMenu1().equals("No Item")) {
		Menu1.setDisable(false);
		Menu1.setText(menu.getMenu1());
		menu1price.setText(menu.getMenu1price());
		menu1des.setText(menu.getMenu1des());
	}else {
		Menu1.setText("No Item");
		menu1price.setText("0");
		Menu1.setDisable(true);
	}
	
	if(!menu.getMenu2().equals("No Item")) {
		Menu2.setDisable(false);
		Menu2.setText(menu.getMenu2());
		menu2price.setText(menu.getMenu2price());
		menu2des.setText(menu.getMenu2des());
		}else {
		Menu2.setText("No Item");
		menu2price.setText("0");
		Menu2.setDisable(true);
	}
	
	if(!menu.getMenu3().equals("No Item")) {
		Menu3.setDisable(false);
		Menu3.setText(menu.getMenu3());
		menu3price.setText(menu.getMenu3price());
		menu3des.setText(menu.getMenu3des());
		
	}else {
		Menu3.setText("No Item");
		menu3price.setText("0");
		Menu3.setDisable(true);
	}
	
	if(!menu.getAdd1().equals("No Item")) {
		add1qty.setDisable(false);
		Add1.setDisable(false);
		Add1.setText(menu.getAdd1());
		add1price.setText(menu.getAdd1price());
		add1des.setText(menu.getAdd1des());
		
		
	}else {
		Add1.setText("No Item");
		add1price.setText("0");
		add1qty.setDisable(true);
		Add1.setDisable(true);
	}
	
	if(!menu.getAdd2().equals("No Item")) {
		Add2.setDisable(false);
		add2qty.setDisable(false);
		Add2.setText(menu.getAdd2());
		add2price.setText(menu.getAdd2price());
		add2des.setText(menu.getAdd2des());
		
	}else {
		Add2.setText("No Item");
		add2price.setText("0");
		add2qty.setDisable(true);
		Add2.setDisable(true);
	}
	
	if(!menu.getAdd3().equals("No Item")) {
		Add3.setDisable(false);
		add3qty.setDisable(false);
		Add3.setText(menu.getAdd3());
		add3price.setText(menu.getAdd3price());
		add3des.setText(menu.getAdd3des());
	}else {
		Add3.setText("No Item");
		add3price.setText("0");
		add3qty.setDisable(true);
		Add3.setDisable(true);
	}
	
	
	menudate.setText(dateFormat.format(menu.getStart().getTime()));
	Menu1.setSelected(true);
	if(Menu1.getText().equals("No Item")) {
		Menu1.setSelected(false);
		Menu2.setSelected(true);
	}if(Menu2.getText().equals("No Item") && Menu1.getText().equals("No Item")) {
		Menu2.setSelected(false);
		Menu3.setSelected(true);
	}if(Menu3.getText().equals("No Item") && Menu2.getText().equals("No Item")) {
		Menu3.setSelected(false);
		Menu1.setSelected(true);
	}
}
	//Menu building method
	Menu menu;
	private void BuildMenu() throws JsonParseException, JsonMappingException, IOException {
		java.sql.Date first = java.sql.Date.valueOf(LocalDate.now().with(DayOfWeek.MONDAY));
		 LocalDate now = LocalDate.now();
		 DayOfWeek day = DayOfWeek.from(now);
	     LocalDateTime date1 = LocalDateTime.now();
	     LocalDateTime date2 = now.atStartOfDay().plusHours(7);
		 if(day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY || (date1.isAfter(date2)&& day == DayOfWeek.FRIDAY) ) {
			  first = java.sql.Date.valueOf(now.with(TemporalAdjusters.next(DayOfWeek.MONDAY)));
		 }
		try {
			ObjectMapper mapper = new ObjectMapper(); 
			menu = mapper.readValue(new File("Menu.json"), Menu.class);
			checkdate(first);
			setmenus();
		}catch(FileNotFoundException f) {
		createMenuFile(first);
		}
	}
	private void BuildNextMenu() throws JsonParseException, JsonMappingException, IOException {
		java.sql.Date first = java.sql.Date.valueOf(LocalDate.now().plusDays(7).with(DayOfWeek.MONDAY));
		try {
			ObjectMapper mapper = new ObjectMapper(); 
			menu = mapper.readValue(new File("Next_Menu.json"), Menu.class);
			checkdate(first);
			setmenus(); 
		}catch(FileNotFoundException f) {
		createMenuFile(first);
		}
	}
	private void checkdate(java.sql.Date first) throws JsonParseException, JsonMappingException, IOException {
		String SQL = "SELECT Menu_Start,Revision from Menu Where Menu_Start = ?;";
		try {
			conn = Lunchhourdb.get();
			ps = conn.prepareStatement(SQL);
			ps.setDate(1,first);
			rs = ps.executeQuery();
			if(rs.next()) {
			if(menu.getStart().compareTo(rs.getDate("Menu_Start")) < 0 || menu.getMenuid() < rs.getInt("Revision")) {
				if(menu.getStart().compareTo(Date.valueOf(LocalDate.now().with(DayOfWeek.MONDAY))) > 0) {
				createMenuFile(first); 
				}
			}
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
			Alert Error2= new Alert(AlertType.ERROR, "An error has occured while connecting with the database.\n Please check your internet connection and try again.");
	   		Error2.setTitle("Error");
	   		Error2.setHeaderText("Connection Error!");
	   		Error2.showAndWait();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	// Builds the extra table
	private void BuildExtraTable() throws JsonParseException, JsonMappingException, IOException, ParseException{
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
		                    	SimpleIntegerProperty i = new SimpleIntegerProperty(99) ;
		                        valueFactory.maxProperty().bind(i);
		                        valueFactory.setValue(item.getItemCount());
		                        item.itemCountProperty().addListener(valueChangeListener);
		                        count.setVisible(true);
		                    }

		                }
		            };

		            return cell;
		        }
		    });
			 try {
				 ObjectMapper mapper = new ObjectMapper();
					mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
					Extras objArray[] = mapper.readValue(new File("Extra.json"), Extras[].class);
					extras.addAll(Arrays.asList(objArray));
					extratable.setItems(extras);
		        
			 
		 }catch(FileNotFoundException f) {
			 try{ 
				 conn = Lunchhourdb.get();
				 ObjectMapper mapper = new ObjectMapper();
			        String Sql = "Select * from Cafeteria.Extras";            
			        ps = conn.prepareStatement(Sql);
					rs = ps.executeQuery();
					List<Extras> array = new ArrayList<Extras>();
			        while(rs.next()){
			        	
			        		Extras item_ = new Extras ();
			        		item_.setExtraName(rs.getString("Extra_Name"));
			        		item_.setExtraPrice(rs.getDouble("Extra_Price"));
			        		array.add(item_);
			             
			        }
			        conn.close();
			        mapper.writeValue(new File("Extra.json"), array);
			        BuildExtraTable();
			        //extratable.setItems(extras);
			    }
			    catch(Exception e){
			          e.printStackTrace();
			          System.out.println("Error on Building Data");            
			    }
		 }
		
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
	//Method to change the Total for the menu total price
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

	void createMenuFile(java.sql.Date first) {

	String SQL = "SELECT * from Menu INNER JOIN Menutool ON Menu.menuid=Menutool.menuidtool Where Menu_Start = ?";
	try {
		conn = Lunchhourdb.get();
		ps = conn.prepareStatement(SQL);
		System.out.println(first);
		ps.setDate(1, first);
		rs = ps.executeQuery();
		if(rs.next()) {
			menu = new Menu();
			menu.setMenuid(rs.getInt("Revision"));
			menu.setMenu1(rs.getString("Menu 1 item"));
			menu.setMenu2(rs.getString("Menu 2 item"));
			menu.setMenu3(rs.getString("Menu 3 item"));
			menu.setMenu1price(rs.getString("Menu 1 Price"));
			menu.setMenu2price(rs.getString("Menu 2 Price"));
			menu.setMenu3price(rs.getString("Menu 3 Price"));
			menu.setStart(rs.getDate("Menu_Start"));
			menu.setEnd(rs.getDate("Menu_End"));
			menu.setAdd1(rs.getString("Additional 1"));
			menu.setAdd2(rs.getString("Additional 2"));
			menu.setAdd3(rs.getString("Additional 3"));
			menu.setAdd1price(rs.getString("Add 1 Price"));
			menu.setAdd2price(rs.getString("Add 2 Price"));
			menu.setAdd3price(rs.getString("Add 3 Price"));
			menu.setMenu1des(rs.getString("Menu1des"));
			menu.setMenu2des(rs.getString("Menu2des"));
			menu.setMenu3des(rs.getString("Menu3des"));
			menu.setAdd1des(rs.getString("add1des"));
			menu.setAdd2des(rs.getString("add2des"));
			menu.setAdd3des(rs.getString("add3des"));
			setmenus();
			try {
				ObjectMapper mapper = new ObjectMapper();
				if(menu.getStart().compareTo(Date.valueOf(LocalDate.now().with(DayOfWeek.MONDAY))) > 0) {
				mapper.writeValue(new File("Next_Menu.json"), menu);
				}else {
					mapper.writeValue(new File("Menu.json"), menu);
				}
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	} catch (SQLException e) {
		Alert Error2= new Alert(AlertType.ERROR, "An error has occured while connecting with the database.\n Please check your internet connection and try again.");
   		Error2.setTitle("Error");
   		Error2.setHeaderText("Connection Error!");
   		Error2.showAndWait();
	}finally {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

}