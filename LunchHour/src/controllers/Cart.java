package controllers;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

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
	private SimpleDoubleProperty total;
	private SimpleStringProperty Day;
	private SimpleStringProperty menuweek;
	
	private static TreeItem<Cart>treeroot = new TreeItem<>(new Cart("Name","Menu","Day","add1","extra",0.0,"week"));
	public static TreeItem<Cart> getTreeRoot() {
		return treeroot;
	}
	private static ObservableList<Cart> list= FXCollections.observableArrayList();
	public Cart(String extra) {
		this.extra = new SimpleStringProperty(extra);
	}
		public Cart(String name, String menu, String day, String add1, String extra, double total ,String menuweek ) {
			this.fullname = new SimpleStringProperty(name);
			this.Day = new SimpleStringProperty(day);
			this.menuitem = new SimpleStringProperty(menu);
			this.add = new SimpleStringProperty(add1);
			this.extra = new SimpleStringProperty(extra);
			this.total = new SimpleDoubleProperty(total);
			this.menuweek = new SimpleStringProperty(menuweek);
		}
		public String getMenuweek() {
		return menuweek.get();
	}

	public static ObservableList<Cart> getList() {
		return list;
	}
		
	public void setMenuweek(String menuweek) {
		this.menuweek.set(menuweek);
	}
	public String getFullname() {
		return fullname.get();
	}
	public SimpleStringProperty getFullnames() {
		return fullname;
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
	public double getTotal() {
		return total.get();
	}
	public void setTotal(double total) {
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
	
	//treeview
	public SimpleStringProperty getMenuweekt() {
	return menuweek;
}
	
public SimpleStringProperty getAddt() {
	return add;
}
public ObjectProperty<Double> getTotalt() {
	return total.asObject();
}
public SimpleStringProperty getDayt() {
	return Day;
}

/*public int getOrderid() {
	return orderid;
}
public void setOrderid(int orderid) {
	this.orderid = orderid;
}
*/
public SimpleStringProperty getMenuitemt() {
	return menuitem;
}
public SimpleStringProperty getExtrat() {
	return extra;
}
}
