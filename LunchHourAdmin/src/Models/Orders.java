package Models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Orders {
	private SimpleStringProperty orderday = new SimpleStringProperty();
	private SimpleStringProperty menuitem = new SimpleStringProperty();
	public String getOrderdaystring() {
		return orderday.get();
	}
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
	public Integer getMenuqtyi() {
		return menuqty.get();
	}
	public void setMenuqty(Integer menuqty) {
		this.menuqty.set(menuqty);
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
