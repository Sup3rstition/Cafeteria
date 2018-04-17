package Models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Order {
private java.sql.Date orderday;
private int menu1qty;
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
private int menu2qty;
private int menu3qty;
private List<Extras>extraitems = new ArrayList<Extras>();
private String add2qty;
private String add3qty;
private String add1qty;

public String getAdd2qty() {
	return add2qty;
}
public void setAdd2qty(String add2qty) {
	this.add2qty = add2qty;
}
public String getAdd3qty() {
	return add3qty;
}
public void setAdd3qty(String add3qty) {
	this.add3qty = add3qty;
}
public String getAdd1qty() {
	return add1qty;
}
public void setAdd1qty(String add1qty) {
	this.add1qty = add1qty;
}
public Date getOrderday() {
	return orderday;
}
public void setOrderday(java.sql.Date orderday) {
	this.orderday = orderday;
}
public String getMenuitem() {
	return menuitem;
}
public void setMenuitem(String menuitem) {
	this.menuitem = menuitem;
}
public List<Extras> getExtraitems() {
	return extraitems;
}
public void setExtraitems(List<Extras> extraitems) {
	this.extraitems = extraitems;
}

}
