package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import connection.Lunchhourdb;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Orderpagecontroller implements Initializable {

	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
    @FXML
    private Label parentsname;

    @FXML
    private Label Balanceamountlabel;

    @FXML
    private Label currentdatelabel;

    @FXML
    private Button Checkhistorybtn;

    @FXML
    private Button logutbtn;

    @FXML
    private ChoiceBox<String> studentcombox;

    @FXML
    private TableView<?> cartable;

    @FXML
    private Button menubtn;

    @FXML
    private Button orderbtn;

    @FXML
    private Button clearbtn;
    @FXML
    private  Label Accountinfo;

    @FXML
    private Label Lastupdate;

    @FXML
    private Label Balancelast;
    
    @FXML
    private TextField Cart_txt;

    @FXML
    private TextField balancerem_txt;
    
    @FXML
    void checkhistory(ActionEvent event) throws IOException {
    	Parent Loginpage = FXMLLoader.load(getClass().getResource("/application/CheckHistoryPage.fxml"));
        Scene Login = new Scene(Loginpage);
        Stage window = (Stage) logutbtn.getScene().getWindow();
        window.setScene(Login);
        window.show();
    }

    @FXML
    void clearcart(ActionEvent event) {

    }

    @FXML
    void logout(ActionEvent event) throws IOException {
    	Parent Loginpage = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
        Scene Login = new Scene(Loginpage);
        Stage window = (Stage) logutbtn.getScene().getWindow();
        window.setScene(Login);
        window.show();
    }

    @FXML
    void submitorder(ActionEvent event) {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		conn = Lunchhourdb.get();
		Cart_txt.setText("0.00");
		balancerem_txt.setText("0.00");
		
	}	
	public void setUsername(String user) throws SQLException{
		parentsname.setText(user);
		String username = parentsname.getText();
		Setinfo(username);
	}
	private int parentid;
	private void Setinfo(String user) throws SQLException {
		 String sql = "SELECT * from Cafeteria.Parents WHERE userName = ?";
		 ps = conn.prepareStatement(sql);
	        ps.setString(1, user);
	        rs = ps.executeQuery();
	        if(rs.next()) {
	        parentsname.setText(	rs.getString("First Name") + " " + rs.getString("Last Name"));
	        DecimalFormat df = new DecimalFormat("#.00");
	        Balanceamountlabel.setText(df.format(rs.getDouble("Balance")));
	        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	        Calendar cal = Calendar.getInstance();
	        currentdatelabel.setText(dateFormat.format(cal.getTime()));
	        java.sql.Date dbSqlLast = rs.getDate("Last Balance Update");
	        java.sql.Date dbSqlBalance = rs.getDate("Last_Purchase");
	        java.util.Date Last = new java.util.Date(dbSqlLast.getTime());
	        java.util.Date dBalance = new java.util.Date(dbSqlBalance.getTime());
	        Balancelast.setText(dateFormat.format(Last.getTime()));
	        Lastupdate.setText(dateFormat.format(dBalance.getTime()));
	        parentid = rs.getInt("Id"); 
	        addstudent(parentid);
	        }
	        
	        
	}
	private String selectedstudentname;
	private void addstudent(int parentid) throws SQLException {
		String sql = "SELECT * from Cafeteria.Student WHERE `Parent ID` = ?";
		 ps = conn.prepareStatement(sql);
	        ps.setInt(1, parentid);
	        rs = ps.executeQuery();
	        	while(rs.next()) {
	        		studentcombox.getItems().add(rs.getString("First Name") + " " + rs.getString("Last Name"));
	        		
	        	
	        }
		
	}
    @FXML
    void menuopen(ActionEvent event) {

    }
}
