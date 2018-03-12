package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class Menupagecontroller {

    @FXML
    private RadioButton Menu1;

    @FXML
    private RadioButton Menu2;

    @FXML
    private RadioButton Menu3;

    @FXML
    private Label Add1;

    @FXML
    private Label Add2;

    @FXML
    private Label Add3;

    @FXML
    private TextField total_txt;

    @FXML
    private Button clearbtn;

    @FXML
    private Button addcartbtn;

    @FXML
    private Spinner<?> add1qty;

    @FXML
    private Spinner<?> add2qty;

    @FXML
    private Spinner<?> add3qty;

    @FXML
    private Label mneu1price;

    @FXML
    private Label menu2price;

    @FXML
    private Label menu3price;

    @FXML
    private Label add1price;

    @FXML
    private Label add2price;

    @FXML
    private Label addprice3;

    @FXML
    private TableColumn<?, ?> extracol;

    @FXML
    private TableColumn<?, ?> extrapricecol;

    @FXML
    private TableColumn<?, ?> extraamountcol;

    @FXML
    private Label Studentname;

    @FXML
    private Label menudate;

    @FXML
    private ComboBox<?> menuday;

    @FXML
    private Label studentgrade;

    @FXML
    private Label studentsection;

    @FXML
    private Button backtologin;

    @FXML
    private Button nextweek;

    @FXML
    void Nextweek(ActionEvent event) {

    }

    @FXML
    void selectday(ActionEvent event) {

    }

}
