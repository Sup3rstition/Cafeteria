package controllers;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.function.UnaryOperator;

import Models.Menu;
import javafx.event.ActionEvent;
	import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
	import javafx.scene.control.TextArea;
	import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

	public class Edititemcontroller {
		private boolean newmenu;
		private boolean changed;
		public void setChanged(boolean changed) {
			this.changed = changed;
		}

		public void setNewmenu(boolean newmenu) {
			this.newmenu = newmenu;
		}
		private int itemnum;
		  public int getItemnum() {
			return itemnum;
		}

		public void setItemnum(int itemnum) {
			this.itemnum = itemnum;
		}
		private String adminuser;
		    public void setAdminuser(String user) {
				this.adminuser = user;
				
			}
		Menu menu;
	    public Menu getMenu() {
			return menu;
		}

		public void setMenu(Menu menu) {
			this.menu = menu;
		}

		@FXML
	    private TextArea description;

	    @FXML
	    private TextField pricetxt;

	    @FXML
	    private TextField nametxt;

	    @FXML
	    private Button savebtn;
	    @FXML
	    private Button backbtn;
	    @FXML
	    void Back(ActionEvent event) throws IOException {
	    	FXMLLoader loader = new FXMLLoader();
	    	loader.setLocation(getClass().getResource("/application/MenuEditor.fxml"));
	    	Parent Menupage = loader.load();
	    	Menucontroller control = loader.getController();
	    	control.setMenu(menu);
	    	control.setNewmenu(newmenu);
	    	control.setChanged(changed);
	    	control.setoldmenu(oldmenu);
	    	control.setAdminuser(adminuser);
	    	control.setdate(menu.getStart());
	    	control.setChanged(false);
	    	control.orderdate();
	    	control.setitems();
	        Scene Menu = new Scene(Menupage);
	        Stage window = (Stage)((Node) (event.getSource())).getScene().getWindow();
	        window.setScene(Menu);
	    }
	    DecimalFormat df = new DecimalFormat("#0.00");
	    @FXML
	    void save(ActionEvent event) throws IOException {
	    	int i = itemnum; 
			if(i==0) {
				if(!nametxt.getText().equals("")) {
				menu.setMenu1(nametxt.getText());
				menu.setMenu1price(df.format(Double.parseDouble(pricetxt.getText())));
				menu.setMenu1des(description.getText());
				}else {
				menu.setMenu1("No Item");
				menu.setMenu1price("0.0");
				menu.setMenu1des("No Item");
			}
			}else if(i==1) {
				if(!nametxt.getText().equals("")) {
				menu.setMenu2(nametxt.getText());
				menu.setMenu2price(df.format(Double.parseDouble(pricetxt.getText())));
				menu.setMenu2des(description.getText());
			}else {
				menu.setMenu2("No Item");
				menu.setMenu2price("0.0");
				menu.setMenu2des("No Item");
			}
			}else if(i==2) {
				if(!nametxt.getText().equals("")) {
				menu.setMenu3(nametxt.getText());
				menu.setMenu3price(df.format(Double.parseDouble(pricetxt.getText())));
				menu.setMenu3des(description.getText());
			}else {
				menu.setMenu3("No Item");
				menu.setMenu3price("0.0");
				menu.setMenu3des("No Item");
			}
			}else if(i==3) {
				if(!nametxt.getText().equals("")) {
				menu.setAdd1(nametxt.getText());
				menu.setAdd1price(df.format(Double.parseDouble(pricetxt.getText())));
				menu.setAdd1des(description.getText());
			}else {
				menu.setAdd1("No Item");
				menu.setAdd1price("0.0");
				menu.setAdd1des("No Item");
			}
			}else if(i==4) {
				if(!nametxt.getText().equals("")) {
				menu.setAdd2(nametxt.getText());
				menu.setAdd2price(df.format(Double.parseDouble(pricetxt.getText())));
				menu.setAdd2des(description.getText());
				
			} else {
				menu.setAdd2("No Item");
				menu.setAdd2price("0.0");
				menu.setAdd2des("No Item");
			}
			}else if(i==5) {
				if(!nametxt.getText().equals("")) {
				menu.setAdd3(nametxt.getText());
				menu.setAdd3price(df.format(Double.parseDouble(pricetxt.getText())));
				menu.setAdd3des(description.getText());
			}else {
				menu.setAdd3("No Item");
				menu.setAdd3price("0.0");
				menu.setAdd3des("No Item");
			}
			}
			FXMLLoader loader = new FXMLLoader();
	    	loader.setLocation(getClass().getResource("/application/MenuEditor.fxml"));
	    	Parent Menupage = loader.load();
	    	Menucontroller control = loader.getController();
	    	control.setMenu(menu);
	    	control.setNewmenu(newmenu);
	    	control.setAdminuser(adminuser);
	    	control.setdate(menu.getStart());
	    	control.setChanged(true);
	    	control.orderdate();
	    	control.setitems();
	        Scene Menu = new Scene(Menupage);
	        Stage window = (Stage)((Node) (event.getSource())).getScene().getWindow();
	        window.setScene(Menu);
	    }
		public void start() {
			UnaryOperator<TextFormatter.Change> textFormatter = new UnaryOperator<TextFormatter.Change>() {

	            @Override
	            public TextFormatter.Change apply(TextFormatter.Change t) {
	            	
	                if (t.isReplaced()) 
	                    if(t.getText().matches("[^a-zA-Z., ]"))
	                        t.setText(t.getControlText().substring(t.getRangeStart(), t.getRangeEnd()));
	                

	                if (t.isAdded()) {
	                        if (t.getText().matches("[^a-zA-Z., ]")) {
	                            t.setText("");
	                        }
	                        if(t.getControlText().length() > 255) {
	                        	t.setText("");
	                        }
	                }

	                return t;
	            }
	        };
	        nametxt.setTextFormatter(new TextFormatter<>(textFormatter));
	        description.setTextFormatter(new TextFormatter<>(textFormatter));
			 UnaryOperator<TextFormatter.Change> filter = new UnaryOperator<TextFormatter.Change>() {

		            @Override
		            public TextFormatter.Change apply(TextFormatter.Change t) {

		                if (t.isReplaced()) 
		                    if(t.getText().matches("[^0-9]"))
		                        t.setText(t.getControlText().substring(t.getRangeStart(), t.getRangeEnd()));
		                

		                if (t.isAdded()) {
		                    if (t.getControlText().contains(".")) {
		                        if (t.getText().matches("[^0-9]")) {
		                            t.setText("");
		                        }
		                    } else if (t.getText().matches("[^0-9.]")) {
		                        t.setText("");
		                    }
		                }

		                return t;
		            }
		        };
		        pricetxt.setTextFormatter(new TextFormatter<>(filter));
			int i = itemnum; 
			if(i==0) {
				nametxt.setText(menu.getMenu1());
				pricetxt.setText(menu.getMenu1price());
				description.setText(menu.getMenu1des());
			}
			if(i==1) {
				nametxt.setText(menu.getMenu2());
				pricetxt.setText(menu.getMenu2price());
				description.setText(menu.getMenu2des());
			}
			if(i==2) {
				nametxt.setText(menu.getMenu3());
				pricetxt.setText(menu.getMenu3price());
				description.setText(menu.getMenu3des());
			}
			if(i==3) {
				nametxt.setText(menu.getAdd1());
				pricetxt.setText(menu.getAdd1price());
				description.setText(menu.getAdd1des());
			}
			if(i==4) {
				nametxt.setText(menu.getAdd2());
				pricetxt.setText(menu.getAdd2price());
				description.setText(menu.getAdd2des());
			} 
			if(i==5) {
				nametxt.setText(menu.getAdd3());
				pricetxt.setText(menu.getAdd3price());
				description.setText(menu.getAdd3des());
			}							
		}
		private Menu oldmenu;
		public void setoldmenu(Menu oldmenu) {
			oldmenu = oldmenu;
			
		}
	}