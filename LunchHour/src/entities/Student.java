package entities;

import javafx.beans.property.SimpleStringProperty;

public class Student {
	private SimpleStringProperty firstName;
	private SimpleStringProperty lastName;
	private SimpleStringProperty Grade;
	private SimpleStringProperty Section;
	public Student(String firstname, String lastname, String grade, String section) {
		this.firstName = new SimpleStringProperty(firstname);
		this.lastName = new SimpleStringProperty(lastname	);
		this.Grade = new SimpleStringProperty(grade);
		this.Section = new SimpleStringProperty(section);

	}
	public String getFirstName() {
		return firstName.get();
	}
	public void setFirstName(String firstname) {
		this.firstName.set(firstname);
	}
	public String getLastName() {
		return lastName.get();
	}
	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}
	public String getGrade() {
		return Grade.get();
	}
	public void setGrade(String grade) {
		Grade.set(grade);
	}
	public String getSection() {
		return Section.get();
	}
	public void setSection(String section) {
		Section.set(section);
	}
}