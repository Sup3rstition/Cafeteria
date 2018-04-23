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
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
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
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.Callback;

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
    private Label enddate;
    @FXML
    private Label m2price;

    @FXML
    private Label m3price;

    @FXML
    private Label add1price;

    @FXML
    private Label add2price;
    @FXML
    private DatePicker startingdate;
    @FXML
    private Label add3price;
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
    void setitems() {
    	if(!menu.getMenu1().equals("No Item")) {
    		menu1.setDisable(false);
    		menu1.setText(menu.getMenu1());
    		m1price.setText(menu.getMenu1price());
    		menu1des.setText(menu.getMenu1des());
    	}else {
    		menu1.setText("No Item");
    		m1price.setText("0");
    		menu1.setDisable(true);
    	}
    	
    	if(!menu.getMenu2().equals("No Item")) {
    		menu2.setDisable(false);
    		menu2.setText(menu.getMenu2());
    		m2price.setText(menu.getMenu2price());
    		menu2des.setText(menu.getMenu2des());
    		}else {
    		menu2.setText("No Item");
    		m2price.setText("0");
    		menu2.setDisable(true);
    	}
    	
    	if(!menu.getMenu3().equals("No Item")) {
    		menu3.setDisable(false);
    		menu3.setText(menu.getMenu3());
    		m3price.setText(menu.getMenu3price());
    		menu3des.setText(menu.getMenu3des());
    		
    	}else {
    		menu3.setText("No Item");
    		m3price.setText("0");
    		menu3.setDisable(true);
    	}
    	
    	if(!menu.getAdd1().equals("No Item")) {
    		add1.setDisable(false);
    		add1.setText(menu.getAdd1());
    		add1price.setText(menu.getAdd1price());
    		add1des.setText(menu.getAdd1des());
    		
    		
    	}else {
    		add1.setText("No Item");
    		add1price.setText("0");
    		add1.setDisable(true);
    	}
    	
    	if(!menu.getAdd2().equals("No Item")) {
    		add2.setDisable(false);
    		add2.setText(menu.getAdd2());
    		add2price.setText(menu.getAdd2price());
    		add2des.setText(menu.getAdd2des());
    		
    	}else {
    		add2.setText("No Item");
    		add2price.setText("0");
    		add2.setDisable(true);
    	}
    	
    	if(!menu.getAdd3().equals("No Item")) {
    		add3.setDisable(false);
    		add3.setText(menu.getAdd3());
    		add3price.setText(menu.getAdd3price());
    		add3des.setText(menu.getAdd3des());
    	}else {
    		add3.setText("No Item");
    		add3price.setText("0");
    		add3.setDisable(true);
    	}
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
	DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		public void setMenu(Menu menu) {
			this.menu = menu;
		}
		private java.sql.Date dateMenu(LocalDate date) {
			if(date.compareTo(LocalDate.now().with(DayOfWeek.MONDAY)) == 0) {
			java.sql.Date first = java.sql.Date.valueOf(LocalDate.now().with(DayOfWeek.MONDAY));
			 LocalDate now = LocalDate.now();
			 DayOfWeek day = DayOfWeek.from(now);
		     LocalDateTime date1 = LocalDateTime.now();
		     LocalDateTime date2 = now.atStartOfDay().plusHours(7);
			 if(day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY || (date1.isAfter(date2)&& day == DayOfWeek.FRIDAY) ) {
				  first = java.sql.Date.valueOf(now.with(TemporalAdjusters.next(DayOfWeek.MONDAY)));
			 }
			 startingdate.setValue(first.toLocalDate());
			 enddate.setText(dateformatter.format(first.toLocalDate().with(DayOfWeek.FRIDAY)));
			 return first;
			}else {
				java.sql.Date first = java.sql.Date.valueOf(startingdate.getValue());
				return first;
			}
		}
			@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
			void firststart() {
					 orderdate();
					 LocalDate now = LocalDate.now();
					 DayOfWeek day = DayOfWeek.from(now);
				     LocalDateTime date1 = LocalDateTime.now();
				     LocalDateTime date2 = now.atStartOfDay().plusHours(7);
					 if( day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY || (day == DayOfWeek.FRIDAY  && date1.isAfter(date2) )) {
						 startingdate.setValue(now.with(TemporalAdjusters.next(DayOfWeek.MONDAY)));
					 }else {
						 startingdate.setValue(now.with(DayOfWeek.MONDAY));
					 
					 }
					createMenu(dateMenu(startingdate.getValue()));
					setitems();
				}
			

			void createMenu(java.sql.Date first) {

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
		                            DayOfWeek day = DayOfWeek.from(item);
		                            LocalDateTime date1 = LocalDateTime.now();
		                            LocalDateTime date2 = now.atStartOfDay().plusHours(7);
		                            if (day != DayOfWeek.MONDAY || item.isBefore(now.with(DayOfWeek.MONDAY)) || (day == DayOfWeek.FRIDAY  && date1.isAfter(date2) ) ){
		                                    setDisable(true);
		                                    setStyle("-fx-background-color: #ffc0cb;");
		                            }

		                    }
		                };
		            }
			};
			startingdate.setDayCellFactory(dayCellFactory);
			 
			 startingdate.valueProperty().addListener((ov, oldValue, newValue) -> {
				 if(newValue != oldValue) {
						createMenu(dateMenu(newValue));
						enddate.setText(dateformatter.format((newValue.plusDays(4))));
				 }
		        });
			}
}
