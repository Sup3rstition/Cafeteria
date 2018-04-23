package Models;

import javafx.beans.property.SimpleStringProperty;

public class Student {
	private SimpleStringProperty firstName= new SimpleStringProperty();
	private SimpleStringProperty lastName =new SimpleStringProperty();
	private SimpleStringProperty Grade =new SimpleStringProperty();
	private SimpleStringProperty Section =new SimpleStringProperty();
	private SimpleStringProperty parentid = new SimpleStringProperty();
	public SimpleStringProperty getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid.set(parentid);
	}
	public void setFirstName(String firstname) {
		this.firstName.set(firstname);
	}
	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}
	public void setGrade(String grade) {
		Grade.set(grade);
	}
	public SimpleStringProperty getFirstNamet() {
		return firstName;
	}
	public String getFirstName() {
		return firstName.get();
	}
	public void setFirstName(SimpleStringProperty firstName) {
		this.firstName = firstName;
	}
	public SimpleStringProperty getLastNamet() {
		return lastName;
	}
	public String getLastName() {
		return lastName.get();
	}
	public void setLastName(SimpleStringProperty lastName) {
		this.lastName = lastName;
	}
	public SimpleStringProperty getGradet() {
		return Grade;
	}
	public String getGrade() {
		return Grade.get();
	}
	public void setGrade(SimpleStringProperty grade) {
		Grade = grade;
	}
	public SimpleStringProperty getSectiont() {
		return Section;
	}
	public String getSection() {
		return Section.get();
	}
	public void setSection(SimpleStringProperty section) {
		Section = section;
	}
	public void setSection(String section) {
		Section.set(section);
	}
}