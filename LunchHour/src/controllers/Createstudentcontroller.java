package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import entities.Student;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
    @FXML
    private Label first;
    @FXML
    private Label second;
    @FXML
    private Label grade;
    @FXML
    private Label section;
	private Stage stage;
    @FXML
    void addstudent(ActionEvent event) { 
    	// gets the values and text of the answers the user supplies and inputs them into the table on createAccount
    	if(Studentfirst_txt.getText() != null && Studentlast_txt.getText() != null && Grade.getValue() !=null && Section.getValue() != null ) {
    	student.add(new Student(Studentfirst_txt.getText(),Studentlast_txt.getText(),Grade.getValue(),
    			Section.getValue()));
    	//Clears the values in the fields
    	Studentfirst_txt.clear();
    	Studentlast_txt.clear();
    	Grade.getSelectionModel().clearSelection();
    	Section.getSelectionModel().clearSelection();
    	((Node)(event.getSource())).getScene().getWindow().hide();
    }else {
    	first.setVisible(true);
    	second.setVisible(true);
    	grade.setVisible(true);
    	section.setVisible(true);
    }
    	
    }

    @FXML
    void backtocreateaccount(ActionEvent event) {
    	//Clears everything and closes the stage.
     Studentfirst_txt.clear();
    	Studentlast_txt.clear();
    	Grade.getSelectionModel().clearSelection();
    	Section.getSelectionModel().clearSelection();
    	((Node)(event.getSource())).getScene().getWindow().hide();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//sets the stage and also fills the comboboxs
		setStage(stage);
		Grade.getItems().addAll("Pre-K","K","1","2","3","4","5","6","7","8","9","10","11");
		Section.getItems().addAll("A","B","C");
		
	}
	public void setStage(Stage stage){
	    this.stage = stage;
	  }
    
}