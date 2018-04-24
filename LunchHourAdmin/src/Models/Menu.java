package Models;

import java.sql.Date;

import javafx.beans.property.SimpleStringProperty;

public class Menu {
private java.sql.Date start;
private java.sql.Date end;
private String menu1;
private String menu2;
private String menu3;
private String add1;
private String add2;
private String add3;
private String menu1price;
private String menu2price;
private String menu3price;
private String add1price;
private String add2price;
private String add3price;
private String menu1des;
private String menu2des;
private String menu3des;
private String add1des;
private String add2des;
private String add3des;
private int id;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getMenutoolid() {
	return menutoolid;
}
public void setMenutoolid(int menutoolid) {
	this.menutoolid = menutoolid;
}
private int menutoolid;

public java.sql.Date getEnd() {
	return end;
}
public java.sql.Date getStart() {
	return start;
}
public void setStart(java.sql.Date start) {
	this.start = start;
}
private int Menuid;
public int getMenuid() {
	return Menuid;
}
public void setMenuid(int menuid) {
	Menuid = menuid;
}
public String getMenu1des() {
	return menu1des;
}
public void setMenu1des(String menu1des) {
	this.menu1des = menu1des;
}
public String getMenu2des() {
	return menu2des;
}
public void setMenu2des(String menu2des) {
	this.menu2des = menu2des;
}
public String getMenu3des() {
	return menu3des;
}
public void setMenu3des(String menu3des) {
	this.menu3des = menu3des;
}
public String getAdd1des() {
	return add1des;
}
public void setAdd1des(String add1des) {
	this.add1des = add1des;
}
public String getAdd2des() {
	return add2des;
}
public void setAdd2des(String add2des) {
	this.add2des = add2des;
}
public String getAdd3des() {
	return add3des;
}
public void setAdd3des(String add3des) {
	this.add3des = add3des;
}
public String getMenu1() {
	return menu1;
}
public void setMenu1(String menu1) {
	this.menu1 = menu1;
}
public String getMenu2() {
	return menu2;
}
public void setMenu2(String menu2) {
	this.menu2 = menu2;
}
public String getMenu3() {
	return menu3;
}
public void setMenu3(String menu3) {
	this.menu3 = menu3;
}
public String getAdd1() {
	return add1;
}
public void setAdd1(String add1) {
	this.add1 = add1;
}
public String getAdd2() {
	return add2;
}
public void setAdd2(String add2) {
	this.add2 = add2;
}
public String getAdd3() {
	return add3;
}
public void setAdd3(String add3) {
	this.add3 = add3;
}
public String getMenu1price() {
	return menu1price;
}
public void setMenu1price(String menu1price) {
	this.menu1price = menu1price;
}
public String getMenu2price() {
	return menu2price;
}
public void setMenu2price(String menu2price) {
	this.menu2price = menu2price;
}
public String getMenu3price() {
	return menu3price;
}
public void setMenu3price(String menu3price) {
	this.menu3price = menu3price;
}
public String getAdd1price() {
	return add1price;
}
public void setAdd1price(String add1price) {
	this.add1price = add1price;
}
public String getAdd2price() {
	return add2price;
}
public void setAdd2price(String add2price) {
	this.add2price = add2price;
}
public String getAdd3price() {
	return add3price;
}
public void setAdd3price(String add3price) {
	this.add3price = add3price;
}
public void setEnd(Date date) {
	this.end = date;
	
}
}
