package Models;

import javafx.beans.property.SimpleStringProperty;

public class Account {
	private SimpleStringProperty pfirst = new SimpleStringProperty();
	private SimpleStringProperty plast = new SimpleStringProperty();
	private SimpleStringProperty Username = new SimpleStringProperty();
	private SimpleStringProperty ParentId = new SimpleStringProperty();
	private SimpleStringProperty Email = new SimpleStringProperty();
	private SimpleStringProperty balance = new SimpleStringProperty();
	private SimpleStringProperty cfirst = new SimpleStringProperty();
	private SimpleStringProperty clast = new SimpleStringProperty();
	private SimpleStringProperty section = new SimpleStringProperty();
	private SimpleStringProperty grade = new SimpleStringProperty();
	public SimpleStringProperty getPfirst() {
		return pfirst;
	}
	public void setPfirst(String pfirst) {
		this.pfirst.set(pfirst);
	}
	public SimpleStringProperty getPlast() {
		return plast;
	}
	public void setPlast(String plast) {
		this.plast.set(plast);
	}
	public SimpleStringProperty getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username.set(username);
	}
	public SimpleStringProperty getParentId() {
		return ParentId;
	}
	public void setParentId(int parentId) {
		ParentId.set(Integer.toString(parentId));
	}
	public SimpleStringProperty getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email.set(email);
	}
	public SimpleStringProperty getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance.set(Double.toString(balance));
	}
	public SimpleStringProperty getCfirst() {
		return cfirst;
	}
	public void setCfirst(String cfirst) {
		this.cfirst.set(cfirst);
	}
	public SimpleStringProperty getClast() {
		return clast;
	}
	public void setClast(String clast) {
		this.clast.set(clast);
	}
	public SimpleStringProperty getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section.set(section);
	}
	public SimpleStringProperty getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade.set(grade);
	}
	public double getBalancet() {
		String bal = this.balance.get();
		return Double.parseDouble(bal);
	}
}
