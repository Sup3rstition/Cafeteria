package controllers;

import javafx.beans.property.SimpleStringProperty;

public class Cart {
	private String Grade;
	private String Section;
	private int orderid;
	private int add_1;
	private int add_2;
	private int add_3;
	private SimpleStringProperty fullname;
	private SimpleStringProperty menuitem;
	private SimpleStringProperty add;
	private SimpleStringProperty extra;
	private SimpleStringProperty total;
	private SimpleStringProperty Day;
	private SimpleStringProperty menuweek;

		public Cart(String name, String menu, String day, String add, String extra, String total ,String menuweek ) {
			this.fullname = new SimpleStringProperty(name);
			this.Day = new SimpleStringProperty(day);
			this.menuitem = new SimpleStringProperty(menu);
			this.add = new SimpleStringProperty(add);
			this.extra = new SimpleStringProperty(extra);
			this.total = new SimpleStringProperty(total);
			this.menuweek = new SimpleStringProperty(menuweek);
		}
		public String getMenuweek() {
		return menuweek.get();
	}

	public void setMenuweek(String menuweek) {
		this.menuweek.set(menuweek);
	}
	public String getFullname() {
		return fullname.get();
	}
	public void setFullname(String first, String second) {
		this.fullname.set(first + second);
	}
	public String getAdd() {
		return add.get();
	}
	public void setAdd(String add) {
		this.add.set(add);
	}
	public String getTotal() {
		return total.get();
	}
	public void setTotal(String total) {
		this.total.set(total);
	}
	public String getDay() {
		return Day.get();
	}
	public void setDay(String day) {
		Day.set(day);
	}
	public String getGrade() {
		return Grade;
	}
	public void setGrade(String grade) {
		Grade=grade;
	}
	public String getSection() {
		return Section;
	}
	public void setSection(String section) {
		Section=section;
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public String getMenuitem() {
		return menuitem.get();
	}
	public void setMenuitem(String menuitem) {
		this.menuitem.set(menuitem);
	}
	public int getAdd_1() {
		return add_1;
	}
	public void setAdd_1(int add_1) {
		this.add_1 = add_1;
	}
	public int getAdd_2() {
		return add_2;
	}
	public void setAdd_2(int add_2) {
		this.add_2 = add_2;
	}
	public int getAdd_3() {
		return add_3;
	}
	public void setAdd_3(int add_3) {
		this.add_3 = add_3;
	}
	public String getExtra() {
		return extra.get();
	}
	public void setExtra(String extra) {
		this.extra.set(extra);
	}
}
