package controllers;
	import java.io.IOException;

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
import javafx.stage.Stage;

	public class Edititemcontroller {
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
	    void save(ActionEvent event) throws IOException {
	    	int i = itemnum; 
			if(i==0) {
				menu.setMenu1(nametxt.getText());
				menu.setMenu1price(pricetxt.getText());
			}
			if(i==1) {
				menu.setMenu2(nametxt.getText());
				menu.setMenu2price(pricetxt.getText());
			}
			if(i==2) {
				menu.setMenu3(nametxt.getText());
				menu.setMenu3price(pricetxt.getText());
			}
			if(i==3) {
				menu.setAdd1(nametxt.getText());
				menu.setAdd1price(pricetxt.getText());
			}
			if(i==4) {
				menu.setAdd2(nametxt.getText());
				menu.setAdd2price(pricetxt.getText());
			} 
			if(i==5) {
				menu.setAdd3(nametxt.getText());
				menu.setAdd3price(pricetxt.getText());
			}
			FXMLLoader loader = new FXMLLoader();
	    	loader.setLocation(getClass().getResource("/application/MenuEditor.fxml"));
	    	Parent Menupage = loader.load();
	    	Menucontroller control = loader.getController();
	    	control.setMenu(menu);
	    	control.setAdminuser(adminuser);
	    	control.setitems();
	        Scene Menu = new Scene(Menupage);
	        Stage window = (Stage)((Node) (event.getSource())).getScene().getWindow();
	        window.setScene(Menu);
	    }

		public void start() {
			int i = itemnum; 
			if(i==0) {
				nametxt.setText(menu.getMenu1());
				pricetxt.setText(menu.getMenu1price());
			}
			if(i==1) {
				nametxt.setText(menu.getMenu2());
				pricetxt.setText(menu.getMenu2price());
			}
			if(i==2) {
				nametxt.setText(menu.getMenu3());
				pricetxt.setText(menu.getMenu3price());
			}
			if(i==3) {
				nametxt.setText(menu.getAdd1());
				pricetxt.setText(menu.getAdd1price());
			}
			if(i==4) {
				nametxt.setText(menu.getAdd2());
				pricetxt.setText(menu.getAdd2price());
			} 
			if(i==5) {
				nametxt.setText(menu.getAdd3());
				pricetxt.setText(menu.getAdd3price());
			}							
		}

	}

