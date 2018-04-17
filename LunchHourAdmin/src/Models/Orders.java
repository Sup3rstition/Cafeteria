package Models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Orders {
	private SimpleStringProperty orderday = new SimpleStringProperty();
	private SimpleStringProperty menuitem = new SimpleStringProperty();
	public SimpleStringProperty getOrderday() {
		return orderday;
	}
	public void setOrderday(String orderday) {
		this.orderday.set(orderday);
	}
	public SimpleStringProperty getMenuitem() {
		return menuitem;
	}
	public void setMenuitem(String string) {
		this.menuitem.set(string);
	}
	public SimpleIntegerProperty getMenuqty() {
		return menuqty;
	}
	public void setMenuqty(SimpleIntegerProperty menuqty) {
		this.menuqty = menuqty;
	}
	public SimpleStringProperty getAdditem() {
		return additem;
	}
	public void setAdditem(String additem) {
		this.additem.set(additem);
	}
	public SimpleIntegerProperty getAddqty() {
		return addqty;
	}
	public void setAddqty(Integer addqty) {
		this.addqty.set(addqty);
	}
	public SimpleStringProperty getExtraitem() {
		return extraitem;
	}
	public void setExtraitem(String extraitem) {
		this.extraitem.set(extraitem);
	}
	public SimpleIntegerProperty getExtraqty() {
		return extraqty;
	}
	public void setExtraqty(Integer extraqty) {
		this.extraqty.set(extraqty);
	}
	private SimpleIntegerProperty menuqty= new SimpleIntegerProperty();
	private SimpleStringProperty additem= new SimpleStringProperty();
	private SimpleIntegerProperty addqty= new SimpleIntegerProperty();
	private SimpleStringProperty extraitem= new SimpleStringProperty();
	private SimpleIntegerProperty extraqty= new SimpleIntegerProperty();
	private int menu1qty = new Integer(0);
	private int menu2qty= new Integer(0);
	private int menu3qty= new Integer(0);
	private int add1qty= new Integer(0);
	private int add2qty= new Integer(0);
	private int add3qty= new Integer(0);
	public int getMenu1qty() {
		return menu1qty;
	}
	public void setMenu1qty(int menu1qty) {
		this.menu1qty = menu1qty;
	}
	public int getMenu2qty() {
		return menu2qty;
	}
	public void setMenu2qty(int menu2qty) {
		this.menu2qty = menu2qty;
	}
	public int getMenu3qty() {
		return menu3qty;
	}
	public void setMenu3qty(int menu3qty) {
		this.menu3qty = menu3qty;
	}
	public int getAdd1qty() {
		return add1qty;
	}
	public void setAdd1qty(int add1qty) {
		this.add1qty = add1qty;
	}
	public int getAdd2qty() {
		return add2qty;
	}
	public void setAdd2qty(int add2qty) {
		this.add2qty = add2qty;
	}
	public int getAdd3qty() {
		return add3qty;
	}
	public void setAdd3qty(int add3qty) {
		this.add3qty = add3qty;
	}
	public void setOrderday(SimpleStringProperty orderday) {
		this.orderday = orderday;
	}
	public void setMenuitem(SimpleStringProperty menuitem) {
		this.menuitem = menuitem;
	}
	public void setAdditem(SimpleStringProperty additem) {
		this.additem = additem;
	}
	public void setAddqty(SimpleIntegerProperty addqty) {
		this.addqty = addqty;
	}
	public void setExtraitem(SimpleStringProperty extraitem) {
		this.extraitem = extraitem;
	}
	public void setExtraqty(SimpleIntegerProperty extraqty) {
		this.extraqty = extraqty;
	}
	
}
