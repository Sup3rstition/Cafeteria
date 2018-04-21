package Models;

public class Parentinfo {
private String Name;
private String Username;
private int ParentId;
private String Email;
private java.sql.Date Lastupdate;
private java.sql.Date LastOrder;
private Double balance;
public String getName() {
	return Name;
}
public void setName(String name) {
	Name = name;
}
public String getUsername() {
	return Username;
}
public void setUsername(String username) {
	Username = username;
}
public int getParentId() {
	return ParentId;
}
public void setParentId(int parentId) {
	ParentId = parentId;
}
public String getEmail() {
	return Email;
}
public void setEmail(String email) {
	Email = email;
}
public java.sql.Date getLastupdate() {
	return Lastupdate;
}
public void setLastupdate(java.sql.Date lastupdate) {
	Lastupdate = lastupdate;
}
public java.sql.Date getLastOrder() {
	return LastOrder;
}
public void setLastOrder(java.sql.Date Order) {
	LastOrder = Order;
}
public Double getBalance() {
	return balance;
}
public void setBalance(Double balance) {
	this.balance = balance;
}

}
