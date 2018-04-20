package Models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Order {
private java.sql.Date orderday;
private String menuitem;
public String getMenuitem() {
	return menuitem;
}
public void setMenuitem(String menuitem) {
	this.menuitem = menuitem;
}
private List<Extras>extraitems = new ArrayList<Extras>();
private Integer add2qty =0 ;
private Integer add3qty =0;
private Integer add1qty =0;

public Integer getAdd2qty() {
	return add2qty;
}
public void setAdd2qty(Integer add2qty) {
	this.add2qty = add2qty;
}
public Integer getAdd3qty() {
	return add3qty;
}
public void setAdd3qty(Integer add3qty) {
	this.add3qty = add3qty;
}
public Integer getAdd1qty() {
	return add1qty;
}
public void setAdd1qty(Integer add1qty) {
	this.add1qty = add1qty;
}
public Date getOrderday() {
	return orderday;
}
public void setOrderday(java.sql.Date orderday) {
	this.orderday = orderday;
}

public List<Extras> getExtraitems() {
	return extraitems;
}
public void setExtraitems(List<Extras> extraitems) {
	this.extraitems = extraitems;
}

}
