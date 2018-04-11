package controllers;

import java.sql.Date;
import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class Cart {
	private String Grade;
	private String Section;
	private SimpleIntegerProperty orderid;
	private int studentid;
	private SimpleStringProperty fullname;
	private SimpleStringProperty menuitem;
	private SimpleStringProperty add;
	private SimpleStringProperty extra;
	private SimpleDoubleProperty total;
	private SimpleStringProperty Day;
	private SimpleStringProperty menuweek;
	 public ObjectProperty<LocalDate> menudate = new SimpleObjectProperty<>();
	
	private static TreeItem<Cart>treeroot = new TreeItem<>(new Cart("Name","Menu","Day","add1","extra",0.0,"week", 0));
	public static TreeItem<Cart> getTreeRoot() {
		return treeroot;
	}
	public Cart() {
		this.fullname = new SimpleStringProperty();
		this.menuitem = new SimpleStringProperty();
		this.add = new SimpleStringProperty();
		this.extra = new SimpleStringProperty();
		this.total = new SimpleDoubleProperty();
		this.menudate = new SimpleObjectProperty();
		this.orderid = new SimpleIntegerProperty();
	}
	public Cart(String extra) {
		this.extra = new SimpleStringProperty(extra);
	}
		public Cart(String name, String menu, String day, String add1, String extra, double total ,String menuweek, int studentid) {
			this.fullname = new SimpleStringProperty(name);
			this.Day = new SimpleStringProperty(day);
			this.menuitem = new SimpleStringProperty(menu);
			this.add = new SimpleStringProperty(add1);
			this.extra = new SimpleStringProperty(extra);
			this.total = new SimpleDoubleProperty(total);
			this.menuweek = new SimpleStringProperty(menuweek);
			this.studentid = new Integer(studentid);
		}
		public Cart(String name, String menu, String add1, String extra, double total ,LocalDate menuweek, int orderid) {
			this.fullname = new SimpleStringProperty(name);
			this.menuitem = new SimpleStringProperty(menu);
			this.add = new SimpleStringProperty(add1);
			this.extra = new SimpleStringProperty(extra);
			this.total = new SimpleDoubleProperty(total);
			this.menudate = new SimpleObjectProperty(menuweek);
			this.orderid = new SimpleIntegerProperty(orderid);
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
		return orderid.get();
	}
	public void setOrderid(int orderid) {
		this.orderid.set(orderid);
	}
	public String getMenuitem() {
		return menuitem.get();
	}
	public void setMenuitem(String menuitem) {
		this.menuitem.set(menuitem);
	}
	public String getExtra() {
		return extra.get();
	}
	public void setExtra(String extra) {
		this.extra.set(extra);
	}
	public int getStudentid() {
		return studentid;
	}
	public void setStudentid(int studentid) {
		this.studentid = studentid;
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
public SimpleStringProperty getMenuitemt() {
	return menuitem;
}
public SimpleStringProperty getExtrat() {
	return extra;
}
public Object getMenudate() {
    return menudate.get();
}

public void setMenudate(LocalDate dateofbirth) {
    this.menudate.set(dateofbirth);
}

public ObjectProperty<LocalDate> dateOfBirthProperty() {
    return menudate;
}
public void setFullname(String string) {
	this.fullname.set(string);
	
}
}
