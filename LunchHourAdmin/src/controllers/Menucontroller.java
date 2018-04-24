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
import java.util.Optional;
import java.util.ResourceBundle;

import Models.Menu;
import connection.Lunchhourdb;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    	menu1.setText(menu.getMenu1());
    	menu2.setText(menu.getMenu2());
    	menu3.setText(menu.getMenu3());
    	add1.setText(menu.getAdd1());
    	add2.setText(menu.getAdd2());
    	add3.setText(menu.getAdd3());
    	m1price.setText(menu.getMenu1price());
    	m2price.setText(menu.getMenu2price());
    	m3price.setText(menu.getMenu3price());
    	add1price.setText(menu.getAdd1price());
    	add2price.setText(menu.getAdd2price());
    	add3price.setText(menu.getAdd3price());
    	menu1des.setText(menu.getMenu1des());
    	menu2des.setText(menu.getMenu2des());
    	menu3des.setText(menu.getMenu3des());
    	add1des.setText(menu.getAdd1des());
    	add2des.setText(menu.getAdd2des());
    	add3des.setText(menu.getAdd3des());
    	
    	enddate.setText(dateformatter.format(menu.getEnd().toLocalDate()));
	}
    void setdate(java.sql.Date date) {
    	this.startingdate.setValue(date.toLocalDate());
    }
    private String adminuser;
    public void setAdminuser(String user) {
		this.adminuser = user;
		
	}
    private Menu oldmenu = new Menu();
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
    void editadd1(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/application/Edititem.fxml"));
    	Parent Menupage = loader.load();
    	Edititemcontroller controller = loader.getController();
        controller.setMenu(menu);
        controller.setNewmenu(newmenu);
        controller.setChanged(changed);
        controller.setAdminuser(adminuser);
        controller.setoldmenu(oldmenu);
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
        controller.setNewmenu(newmenu);
        controller.setChanged(changed);
        controller.setoldmenu(oldmenu);
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
        controller.setNewmenu(newmenu);
        controller.setChanged(changed);
        controller.setoldmenu(oldmenu);
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
        controller.setNewmenu(newmenu);
        controller.setChanged(changed);
        controller.setoldmenu(oldmenu);
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
        controller.setoldmenu(oldmenu);
        controller.setNewmenu(newmenu);
        controller.setChanged(changed);
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
        controller.setoldmenu(oldmenu);
        controller.setAdminuser(adminuser);
        controller.setNewmenu(newmenu);
        controller.setChanged(changed);
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
    void submit(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Would you like to submit the menu?" , ButtonType.YES, ButtonType.CANCEL);
	   	 alert.showAndWait();

	   	 if (alert.getResult() == ButtonType.YES) {
	   		 if(changed && newmenu) {
	   			 
	   			try {
	   				String sql = "Insert into Menu (Menu_Start, Menu_End, `Menu 1 Item`, `Menu 2 Item`, `Menu 3 Item`, `Additional 1`, `Additional 2`, `Additional 3`,"
		   					+ " `Menu 1 Price`, `Menu 2 Price`, `Menu 3 Price`, `Add 1 Price`, `Add 2 Price`, `Add 3 Price`, Revision) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
					conn = Lunchhourdb.get();
					ps = conn.prepareStatement(sql);
					ps.setDate(1, menu.getStart());
					ps.setDate(2, menu.getEnd());
					ps.setString(3, menu.getMenu1());
					ps.setString(4, menu.getMenu2());
					ps.setString(5, menu.getMenu3());
					ps.setString(6, menu.getAdd1());
					ps.setString(7, menu.getAdd2());
					ps.setString(8, menu.getAdd3());
					ps.setDouble(9, Double.parseDouble(menu.getMenu1price()));
					ps.setDouble(10, Double.parseDouble(menu.getMenu2price()));
					ps.setDouble(11, Double.parseDouble(menu.getMenu3price()));
					ps.setDouble(12, Double.parseDouble(menu.getAdd1price()));
					ps.setDouble(13, Double.parseDouble(menu.getAdd2price()));
					ps.setDouble(14, Double.parseDouble(menu.getAdd3price()));
					ps.setInt(15, menu.getMenuid());
					ps.execute();
					sql = "Insert into Menutool (Menu1des,Menu2des,Menu3des,add1des,add2des,add3des) Values(?, ?, ?, ?, ?, ?)";
					ps = conn.prepareStatement(sql);
					ps.setString(1, menu.getMenu1des());
					ps.setString(2, menu.getMenu2des());
					ps.setString(3, menu.getMenu3des());
					ps.setString(4, menu.getAdd1des());
					ps.setString(5, menu.getAdd2des());
					ps.setString(6, menu.getAdd3des());
					ps.execute();
					Alert Done = new Alert(AlertType.INFORMATION);
			   		Done.setTitle("Success!");
			   		Done.setHeaderText("Menu submission Successful!");
			   		Done.setContentText("The Menu has been created!");
			   		
			   		Done.showAndWait();
			   		changed = false;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	   			
	   		 }else if (changed && !newmenu) {
	   			 
	   			 
	   			try {
	   				String sql = "Update Menu SET `Menu 1 Item` = ?,`Menu 2 Item` = ?,`Menu 3 Item` = ?, `Additional 1` = ?,`Additional 2` = ?,`Additional 3` = ?, "
		   					+ "`Menu 1 Price` = ?,`Menu 2 Price` = ?,`Menu 3 Price` = ?, `Add 1 Price` = ?,`Add 2 Price` = ?,`Add 3 Price` = ?, Revision = ?+1 Where menuid = ?;";
					conn = Lunchhourdb.get();
					ps = conn.prepareStatement(sql);
					ps.setString(1, menu.getMenu1());
					ps.setString(2, menu.getMenu2());
					ps.setString(3, menu.getMenu3());
					ps.setString(4, menu.getAdd1());
					ps.setString(5, menu.getAdd2());
					ps.setString(6, menu.getAdd3());
					ps.setDouble(7, Double.parseDouble(menu.getMenu1price()));
					ps.setDouble(8, Double.parseDouble(menu.getMenu2price()));
					ps.setDouble(9, Double.parseDouble(menu.getMenu3price()));
					ps.setDouble(10, Double.parseDouble(menu.getAdd1price()));
					ps.setDouble(11, Double.parseDouble(menu.getAdd2price()));
					ps.setDouble(12, Double.parseDouble(menu.getAdd3price()));
					ps.setInt(13, menu.getMenuid());
					ps.setInt(14, menu.getId());
					ps.execute();
					sql = "Update Menutool SET Menu1des=? ,Menu2des=? ,Menu3des=? ,add1des=? ,add2des=? ,add3des=? Where menuidtool =?";
					ps = conn.prepareStatement(sql);
					ps.setString(1, menu.getMenu1des());
					ps.setString(2, menu.getMenu2des());
					ps.setString(3, menu.getMenu3des());
					ps.setString(4, menu.getAdd1des());
					ps.setString(5, menu.getAdd2des());
					ps.setString(6, menu.getAdd3des());
					ps.setInt(7, menu.getMenutoolid());
					ps.execute();
					Alert Done = new Alert(AlertType.INFORMATION);
			   		Done.setTitle("Success!");
			   		Done.setHeaderText("Menu submission Successful!");
			   		Done.setContentText("The Menu has been updated!");

			   		Done.showAndWait();
			   		changed = false;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	   			
	   			
	   			
	   		 }else if(!changed && newmenu) {
	   			Alert Done = new Alert(AlertType.ERROR);
		   		Done.setTitle("No changes found");
		   		Done.setHeaderText("No changes found");
		   		Done.setContentText("No changes were found in the menu. Please change an item and try again.");

		   		Done.showAndWait();
	   		 }
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
	
			@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
				 checkbox1.selectedProperty().addListener(new ChangeListener<Boolean>() {

				        @Override
				        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				          
				            if(newValue){
				            	oldmenu.setMenu1(menu.getMenu1());
				            	oldmenu.setMenu1price(menu.getMenu1price());
				            	oldmenu.setMenu1des(menu.getMenu1des());
				            		menu.setMenu1("No Item");
				        			menu.setMenu1price("0.0");
				        			menu.setMenu1des("No Item");
				        			changed = true;
				            	}else {
				            		menu.setMenu1(oldmenu.getMenu1());
				        			menu.setMenu1price(oldmenu.getMenu1price());
				        			menu.setMenu1des(oldmenu.getMenu1des());
				            	}
				            	setitems();
					        }
				    });
				 checkbox2.selectedProperty().addListener(new ChangeListener<Boolean>() {

				        @Override
				        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				            if(newValue){
				            	oldmenu.setMenu2(menu.getMenu2());
				            	oldmenu.setMenu2price(menu.getMenu2price());
				            	oldmenu.setMenu2des(menu.getMenu2des());
				            	menu.setMenu2("No Item");
				    			menu.setMenu2price("0.0");
				    			menu.setMenu2des("No Item");
				    			changed = true;
				            }else{
				            	menu.setMenu2(oldmenu.getMenu2());
				    			menu.setMenu2price(oldmenu.getMenu2price());
				    			menu.setMenu2des(oldmenu.getMenu2des());
				        	}
				        	setitems(); 
				        }
				    });
				 checkbox3.selectedProperty().addListener(new ChangeListener<Boolean>() {
					 
				        @Override
				        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				       
				            if(newValue){
				            	oldmenu.setMenu3(menu.getMenu3());
				            	oldmenu.setMenu3price(menu.getMenu3price());
				            	oldmenu.setMenu3des(menu.getMenu3des());
				            	menu.setMenu3("No Item");
				    			menu.setMenu3price("0.0");
				    			menu.setMenu3des("No Item");
				    			changed = true;
				            }else{
				            	menu.setMenu3(oldmenu.getMenu3());
				    			menu.setMenu3price(oldmenu.getMenu3price());
				    			menu.setMenu3des(oldmenu.getMenu3des());
				            }
				            setitems();
				        }
				    });
				 checkbox4.selectedProperty().addListener(new ChangeListener<Boolean>() {

				        @Override
				        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				           
				            if(newValue){
				            	oldmenu.setAdd1(menu.getAdd1());
				            	oldmenu.setAdd1price(menu.getAdd1price());
				            	oldmenu.setAdd1des(menu.getAdd1des());
				            	menu.setAdd1("No Item");
				    			menu.setAdd1price("0.0");
				    			menu.setAdd1des("No Item");
				    			changed = true;
				        	}else {
				        		menu.setAdd1(oldmenu.getAdd1());
				    			menu.setAdd1price(oldmenu.getAdd1price());
				    			menu.setAdd1des(oldmenu.getAdd1des());
				        	}
				        	setitems();
				        }
				    });
				 checkbox5.selectedProperty().addListener(new ChangeListener<Boolean>() {

				        @Override
				        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				           
				            if(newValue){
				            	oldmenu.setAdd2(menu.getAdd2());
				            	oldmenu.setAdd2price(menu.getAdd2price());
				            	oldmenu.setAdd2des(menu.getAdd2des());
				            	menu.setAdd2("No Item");
				    			menu.setAdd2price("0.0");
				    			menu.setAdd2des("No Item");
				    			changed = true;
				        	}else {
				        		menu.setAdd2(oldmenu.getAdd2());
				    			menu.setAdd2price(oldmenu.getAdd2price());
				    			menu.setAdd2des(oldmenu.getAdd2des());
				        	}
				        	setitems();
				        }
				    });
				 checkbox6.selectedProperty().addListener(new ChangeListener<Boolean>() {

				        @Override
				        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				            
				            if(newValue){
				            	oldmenu.setAdd3(menu.getAdd3());
				            	oldmenu.setAdd3price(menu.getAdd3price());
				            	oldmenu.setAdd3des(menu.getAdd3des());
				            	menu.setAdd3("No Item");
				    			menu.setAdd3price("0.0");
				    			menu.setAdd3des("No Item");
				    			changed = true;
				        	}else {
				        		menu.setAdd3(oldmenu.getAdd3());
				    			menu.setAdd3price(oldmenu.getAdd3price());
				    			menu.setAdd3des(oldmenu.getAdd3des());
				        	}
				        	setitems();
				        }
				    });
	}
			void firststart() {
					 changed = false;
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
					
				}
			boolean newmenu;
			boolean changed;
			public void setNewmenu(boolean newmenu) {
				this.newmenu = newmenu;
			}
			public void setChanged(boolean changed) {
				this.changed = changed;
			}
			void PullMenu(LocalDate first) {

				String SQL = "SELECT * from Menu INNER JOIN Menutool ON Menu.menuid=Menutool.menuidtool Where Menu_Start = ?";
				try {
					conn = Lunchhourdb.get();
					ps = conn.prepareStatement(SQL);
					System.out.println(first);
					ps.setDate(1, java.sql.Date.valueOf(first));
					rs = ps.executeQuery();
					if(rs.next()) {
						menu = new Menu();
						menu.setId(rs.getInt("menuid"));
						menu.setMenutoolid(rs.getInt("menuidtool"));
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
						newmenu = false;
						setitems();
					}else {
						newmenu = true;
						menu = new Menu();
						menu.setMenuid(1);
						menu.setMenu1("No Item");
						menu.setMenu2("No Item");
						menu.setMenu3("No Item");
						menu.setMenu1price("0.0");
						menu.setMenu2price("0.0");
						menu.setMenu3price("0.0");
						menu.setStart(Date.valueOf(first));
						menu.setEnd(Date.valueOf(first.plusDays(4)));
						menu.setAdd1("No Item");
						menu.setAdd2("No Item");
						menu.setAdd3("No Item");
						menu.setAdd1price("0.0");
						menu.setAdd2price("0.0");
						menu.setAdd3price("0.0");
						menu.setMenu1des("No Item");
						menu.setMenu2des("No Item");
						menu.setMenu3des("No Item");
						menu.setAdd1des("No Item");
						menu.setAdd2des("No Item");
						menu.setAdd3des("No Item");
						setitems();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			void orderdate() {
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
		                            if(item.isBefore(now) && now.isAfter(now.with(DayOfWeek.FRIDAY)) || ( item.isBefore(now) && now.isAfter(now.with(DayOfWeek.THURSDAY)) && date1.isAfter(date2))  ) {
		                            	setDisable(true);
	                                    setStyle("-fx-background-color: #ffc0cb;");
		       					 }
		                            if (day != DayOfWeek.MONDAY || item.isBefore(now.with(DayOfWeek.MONDAY)) || (day == DayOfWeek.FRIDAY  && date1.isAfter(date2))  ){
		                                    setDisable(true);
		                                    setStyle("-fx-background-color: #ffc0cb;");
		                            }

		                    }
		                };
		            }
			};
			startingdate.setDayCellFactory(dayCellFactory);
			 
			 startingdate.valueProperty().addListener((ov, oldValue, newValue) -> {
					 if (!dialogOpen) {
						 if(changed) {
							 dialogOpen = true;
							 Alert alert = new Alert(AlertType.CONFIRMATION);
							 alert.setTitle("Confirmation");
							 alert.setHeaderText("Changed items will be deleted...");
							 alert.setContentText("Are you ok with this?");
							 
							 Optional<ButtonType> result = alert.showAndWait();
							 	if (result.get() == ButtonType.OK){
							 		 startingdate.setValue(newValue);
							 		PullMenu(newValue);
							 		changed = false;
							 		 dialogOpen = false;
							 		 
					 
							 	}
							 	 dialogOpen = false;
						 	}
						 else {
								PullMenu(newValue);
								} 
						 }
					 
		        });
			}
			private boolean dialogOpen = false;
			public void setoldmenu(Menu oldmenu2) {
				this.oldmenu = oldmenu2;
				
			}
}
