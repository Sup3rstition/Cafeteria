package Models;

import java.time.LocalDate;

public class AdminOrders {

public int getOrderid() {
	return orderid;
}
public void setOrderid(int orderid) {
	this.orderid = orderid;
}
public LocalDate getOrderDate() {
	return OrderDate;
}
public void setOrderDate(LocalDate orderDate) {
	OrderDate = orderDate;
}
public String getDay() {
	return day;
}
public void setDay(String day) {
	this.day = day;
}
public String getFname() {
	return fname;
}
public void setFname(String fname) {
	this.fname = fname;
}
public String getLname() {
	return lname;
}
public void setLname(String lname) {
	this.lname = lname;
}
public String getGrade() {
	return grade;
}
public void setGrade(String grade) {
	this.grade = grade;
}
public String getSection() {
	return section;
}
public void setSection(String section) {
	this.section = section;
}
public String getMenu() {
	return Menu;
}
public void setMenu(String menu) {
	Menu = menu;
}
public String getAdd() {
	return add;
}
public void setAdd(String add) {
	this.add = add;
}
public String getExtra() {
	return extra;
}
public void setExtra(String extra) {
	this.extra = extra;
}
public double getTotal() {
	return total;
}
public void setTotal(double total) {
	this.total = total;
}
private int orderid;
private LocalDate OrderDate;
private String day;
private String fname;
private String lname;
private String grade;
private int StudentID;
public int getStudentID() {
	return StudentID;
}
public void setStudentID(int studentID) {
	StudentID = studentID;
}
public String getFullname() {
	return fullname;
}
private String section;
private String Menu;
private String add;
private String extra;
private double total;
private String fullname;
public void setFullname(String string) {
	this.fullname = string;
	
}
}
