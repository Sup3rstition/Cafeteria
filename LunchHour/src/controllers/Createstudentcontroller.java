package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.Student.Student;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Createstudentcontroller implements Initializable{
	private ObservableList<Student> student;
	public void setItems(ObservableList<Student> student) {
		this.student = student;
	}

    @FXML
    private TextField Studentfirst_txt;

    @FXML
    private TextField Studentlast_txt;

    @FXML
    private ComboBox<String> Grade;

    @FXML
    private ComboBox<String> Section;

    @FXML
    private Button cancel;

    @FXML
    private Button addbtn;
	private Stage stage;
    @FXML
    void addstudent(ActionEvent event) { 
    	student.add(new Student(Studentfirst_txt.getText(),Studentlast_txt.getText(),Grade.getValue(),
    			Section.getValue()));
    	Studentfirst_txt.clear();
    	Studentlast_txt.clear();
    	Grade.getSelectionModel().clearSelection();
    	Section.getSelectionModel().clearSelection();
    	((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void backtocreateaccount(ActionEvent event) {
     Studentfirst_txt.clear();
    	Studentlast_txt.clear();
    	Grade.getSelectionModel().clearSelection();
    	Section.getSelectionModel().clearSelection();
    	((Node)(event.getSource())).getScene().getWindow().hide();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setStage(stage);
		Grade.getItems().addAll("Pre-K","K","1","2","3","4","5","6","7","8","9","10","11");
		Section.getItems().addAll("A","B","C");
	}
	public void setStage(Stage stage){
	    this.stage = stage;
	  }
    
}