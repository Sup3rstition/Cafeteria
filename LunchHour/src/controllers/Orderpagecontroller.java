package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import connection.Lunchhourdb;
import controllers.Menupagecontroller.Extras;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.StringConverter;
import application.Student.Student;
import application.Student.Studentinfo;
import javafx.util.Callback;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
public class Orderpagecontroller implements Initializable {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private ObservableList<Cart> cart = Cart.getList();
	
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
    private ComboBox<Studentinfo> studentcombox;

    @FXML
    private TableView<Cart> cartable;
    @FXML
    private TableColumn<Cart, String> studentcol;

    @FXML
    private TableColumn<Cart, String> daycol;

    @FXML
    private TableColumn<Cart, String> menucol;

    @FXML
    private TableColumn<Cart, String> additionalcol;

    @FXML
    private TableColumn<Cart, String> extracol;

    @FXML
    private TableColumn<Cart, String> totalcol;
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
    NumberFormat formatter = new DecimalFormat("#0.00");
    @FXML
    void checkhistory(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/application/CheckHistoryPage.fxml"));
    	Parent CheckHistory = loader.load();
        Scene Check = new Scene(CheckHistory);
        CheckHistoryController controller = loader.getController();
       // Stage window = (Stage) Checkhistorybtn.getScene().getWindow();
        
       // window.setScene(Check);
       // window.show();
        Stage stage = new Stage();
        stage.setScene(Check);
        stage.showAndWait();
    }

    @FXML
    void clearcart(ActionEvent event) {

    }

    @FXML
    void logout(ActionEvent event) throws IOException, SQLException {
    	Cart.getList().clear();
    	Parent Loginpage = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
        Scene Login = new Scene(Loginpage);
        Stage window = (Stage) logutbtn.getScene().getWindow();
        window.setScene(Login);
        window.show();
    }

    @FXML
    void submitorder(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Would you like to submit your order?" , ButtonType.YES, ButtonType.CANCEL);
	   	 alert.showAndWait();

	   	 if (alert.getResult() == ButtonType.YES) {
	   		cartable.getItems().clear();
	   		cartable.refresh();
	   		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	        Calendar cal = Calendar.getInstance();
	        Lastupdate.setText(dateFormat.format(cal.getTime()));
	   		
	   	 }
    }
    private double totalprice() {
    	double totalprice=0;	
    	for(int i=0; i < cartable.getItems().size();i++) {
	    	Cart tableRow = cartable.getItems().get(i);
	    double price = tableRow.getTotal();
	    totalprice = totalprice + price;
		}
		return totalprice;
    }
    private double balancechange() {
		double cartbal = Double.parseDouble(Cart_txt.getText());
		double curbal = Double.parseDouble(Balanceamountlabel.getText());
    	double newbalance = curbal-cartbal;
    	return newbalance;
    	
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		  
		conn = Lunchhourdb.get();
		studentcol.setCellValueFactory(new PropertyValueFactory<Cart, String>("fullname"));
		daycol.setCellValueFactory(new PropertyValueFactory<Cart, String>("Day"));
		menucol.setCellValueFactory(new PropertyValueFactory<Cart, String>("menuitem"));
		additionalcol.setCellValueFactory(new PropertyValueFactory<Cart, String>("add"));
		extracol.setCellValueFactory(new PropertyValueFactory<Cart, String>("extra"));
		totalcol.setCellValueFactory(new PropertyValueFactory<Cart, String>("total"));
		cartable.setItems(cart);
		try {
			Setinfo();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private int parentid;
	public void Setinfo() throws SQLException {
		String user = Logincontroller.getUsername();
		 String sql = "SELECT * from Cafeteria.Parents WHERE userName = ?";
		 ps = conn.prepareStatement(sql);
	        ps.setString(1, user);
	        rs = ps.executeQuery();
	        if(rs.next()) {
	        parentsname.setText( rs.getString("First Name") + " " + rs.getString("Last Name"));
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
	        Cart_txt.setText(formatter.format(totalprice()));
	        balancerem_txt.setText(formatter.format(balancechange()));
	        }
	        
	        
	}
	ObservableList<Studentinfo> items = FXCollections.observableArrayList();
	private void addstudent(int parentid) throws SQLException {
		String sql = "SELECT * from Cafeteria.Student WHERE `Parent ID` = ?";
		 ps = conn.prepareStatement(sql);
	        ps.setInt(1, parentid);
	        rs = ps.executeQuery();
	        	while(rs.next()) {
	        		Studentinfo Student = new Studentinfo();
	        		Student.setId(rs.getInt("id"));
	        		Student.setFirstname(rs.getString("First Name"));
	        		Student.setLastname(rs.getString("Last Name"));
	        		Student.setGrade(rs.getString("grade"));
	        		Student.setSection(rs.getString("section"));
	        		items.add(Student);
	        }
	        	studentcombox.setItems(items);
		studentcombox.getSelectionModel().selectFirst();
		 studentcombox.setCellFactory(new Callback<ListView<Studentinfo>,ListCell<Studentinfo>>(){
			 
	            public ListCell<Studentinfo> call(ListView<Studentinfo> l) {
	            	return new ListCell<Studentinfo>() {
	 
	                    @Override
	                    protected void updateItem(Studentinfo t, boolean bln) {
	                        super.updateItem(t, bln);
	                         
	                        if(t != null){
	                            setText(t.getFirstname() + " " + t.getLastname());
	                        }else{
	                            setText(null);
	                        }
	                    }
	  
	                };
	            }
		 });
		 studentcombox.setConverter(new StringConverter<Studentinfo>() {
             @Override
             public String toString(Studentinfo user) {
               if (user == null){
                 return null;
               } else {
                 return (user.getFirstname() + " " + user.getLastname());
               }
             }

           @Override
           public Studentinfo fromString(String userId) {
               return null;
           }
       });
		 cartable.refresh();
	}

	@FXML
	private Button remove;
	@FXML
	void removeselected(ActionEvent event) {
		Cart selectedItem = cartable.getSelectionModel().getSelectedItem();
		if(selectedItem != null) {
		Alert alert = new Alert(AlertType.CONFIRMATION, "Remove " + cartable.getSelectionModel().getSelectedItem().getFullname() + " " + cartable.getSelectionModel().getSelectedItem().getMenuitem() 
				+" Order from cart?" , ButtonType.YES, ButtonType.CANCEL);
	   	 alert.showAndWait();

	   	 if (alert.getResult() == ButtonType.YES) {
	        cartable.getItems().remove(selectedItem);
	        
	   	 }
		}
	}
    @FXML
    void menuopen(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/application/MenuPage.fxml"));
    	Parent Menupage = loader.load();
        Scene Menu = new Scene(Menupage);
        Menupagecontroller controller = loader.getController();
        Studentinfo studentid = studentcombox.getValue();
        controller.setinfo(studentid);
        Stage window = (Stage)((Node) (event.getSource())).getScene().getWindow();
        window.setScene(Menu);
        cartable.refresh();
    }
    @FXML
    private Button close;
    @FXML
    void closewindow(ActionEvent event) throws IOException{
    	((Node)(event.getSource())).getScene().getWindow().hide();
    
    }
    
}
