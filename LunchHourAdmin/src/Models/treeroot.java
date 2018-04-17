package Models;

import javafx.scene.control.TreeItem;

public class treeroot {
private TreeItem<Order> orderday;

public TreeItem<Order> getOrderday() {
	return orderday;
}

public void setOrderday(TreeItem<Order> orderday) {
	this.orderday = orderday;
}
public TreeItem<String> getMenu1() {
	return menu1;
}

public void setMenu1(TreeItem<String> menu1) {
	this.menu1 = menu1;
}
private TreeItem<String> menu1;
public TreeItem<String> getMenu2() {
	return menu2;
}

public void setMenu2(TreeItem<String> menu2) {
	this.menu2 = menu2;
}

public TreeItem<String> getMenu3() {
	return menu3;
}

public void setMenu3(TreeItem<String> menu3) {
	this.menu3 = menu3;
}
private TreeItem<String> menu2;
private TreeItem<String> menu3;


}
