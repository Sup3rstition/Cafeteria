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
import java.util.Calendar;
import java.util.ResourceBundle;


import connection.Lunchhourdb;
import entities.Parentinfo;
import entities.Studentinfo;
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
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.Callback;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
public class Orderpagecontroller implements Initializable {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	ObservableList<Studentinfo> items = FXCollections.observableArrayList();
    public ObservableList<Studentinfo> getItems() {
		return items;
	}

	public void setItems(ObservableList<Studentinfo> items) {
		this.items = items;
	}
	NumberFormat formatter = new DecimalFormat("#0.00");
    DecimalFormat df = new DecimalFormat("#0.00");
    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    private Parentinfo parent = null;
    private TreeItem<Cart>root = new TreeItem<>();
	
    @FXML
    private Label parentsname;

    @FXML
    private Button close;
    
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
    private Button menubtn;

    @FXML
    private Button orderbtn;

    @FXML
    private Button clearbtn;
    @FXML
    private  Label Accountinfo;

    @FXML
    private Label LastOrder;

    @FXML
    private Label LastUpdate;
    
    @FXML
    private TextField Cart_txt;
    
    @FXML
	private Button remove;
    
    @FXML
    private TextField balancerem_txt;
    @FXML
    private TreeTableView<Cart> tree;

    @FXML
    private TreeTableColumn<Cart, String> nametree;

    @FXML
    private TreeTableColumn<Cart, String> daytree;

    @FXML
    private TreeTableColumn<Cart, String> menutree;

    @FXML
    private TreeTableColumn<Cart, String> addtree;

    @FXML
    private TreeTableColumn<Cart, String> extratree;

    @FXML
    private TreeTableColumn<Cart, Double> totaltree;
    
    @FXML
    void checkhistory(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/application/CheckHistoryPage.fxml"));
    	Parent CheckHistory = loader.load();
        Scene Check = new Scene(CheckHistory);
        Stage stage = new Stage();
        CheckHistoryController control = loader.<CheckHistoryController>getController();
        control.setparentid(parent.getParentId());
        control.setList(items);
        stage.setScene(Check);
        stage.getIcons().add(new Image(("file:icon.png")));
        stage.showAndWait();
    }
    
    @FXML
    void clearcart(ActionEvent event) {
    	root.getChildren().clear();
    	tree.getSelectionModel().clearSelection();
    	orderprice();
    }

    public TreeItem<Cart> getRoot() {
		return root;
	}

	public void setRoot(TreeItem<Cart> root) {
		this.root = root;
	}

	@FXML
    void logout(ActionEvent event) throws IOException {
    	parent = null;
    	root.getChildren().clear();
    	tree.getSelectionModel().clearSelection();
    	Parent Loginpage = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
        Scene Login = new Scene(Loginpage);
        Stage window = (Stage) logutbtn.getScene().getWindow();
        window.getIcons().add(new Image(("file:icon.png")));
        window.setScene(Login);
        window.show();
    }

    @FXML
    void submitorder(ActionEvent event) {
    	
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Would you like to submit your order?" , ButtonType.YES, ButtonType.CANCEL);
	   	 alert.showAndWait();

	   	 if (alert.getResult() == ButtonType.YES) {
	   		 if(!root.getChildren().isEmpty()) {
	   		 if(Double.parseDouble(balancerem_txt.getText()) > 0) {
	   			 
	 		try {
				conn = Lunchhourdb.get();
				Calendar cal = Calendar.getInstance();
		        java.sql.Date startDate = new java.sql.Date(cal.getTime().getTime());
		   		 String sql = "Insert into Cafeteria.Order (`Parent Id`, `Student ID`, `Menu Item`, Additional, Extra, Total, `Order Date`, Studentname, Order_Date) Values ( ?, ?, ?, ?, ?, ?, ?, ?, ?);"; 
				 ps = conn.prepareStatement(sql);
				 for(int i=0; i < root.getChildren().size();i++) {
				    	TreeItem<Cart> tableRow = root.getChildren().get(i);
				    	ps.setInt(1, parent.getParentId());
				    	ps.setInt(2, tableRow.getValue().getStudentid());
				    	ps.setString(3, tableRow.getValue().getMenuitem());
				    	String add = "";
				    	String extras_ = "";
				    	for(int j =0; j< tableRow.getChildren().size(); j++) {
				    		TreeItem<Cart> childRow = tableRow.getChildren().get(j);
				    		if (childRow.getValue().getAdd() != null){
				    		add = add + childRow.getValue().getAdd() + "/";
				    		}
				    		if(childRow.getValue().getExtra() != null) {
					    		extras_ = extras_ + childRow.getValue().getExtra() + "/";
				    		}
				    	}
				    	if(add == "") {
				    		add = null;
				    	}
				    	if (extras_ == ""){
				    			extras_ = null;
				    	}
				    	ps.setString(4, add);
				    	ps.setString(5,extras_);
				    	ps.setDouble(6,Double.parseDouble(Cart_txt.getText()));
				    	ps.setDate(7,startDate);
				    	ps.setString(8, tableRow.getValue().getFullname());
				    	ps.setDate(9, tableRow.getValue().getMenuorderdate());
				    	ps.execute();
					}
				 sql = "Update Parents SET Balance = ? , Last_Purchase = ?   Where Id = ?;";
					    	ps = conn.prepareStatement(sql);
					    	ps.setDouble(1,Double.parseDouble(balancerem_txt.getText()));
					    	ps.setDate(2, startDate);
					    	ps.setInt(3,parent.getParentId());
					    	ps.execute();
				 
		   		 root.getChildren().clear();
		   		updateinfo();
		        orderprice();
		    	tree.getSelectionModel().clearSelection();
		    	conn.close();
		   		Alert Done = new Alert(AlertType.INFORMATION);
		   		Done.setTitle("Success!");
		   		Done.setHeaderText("Order(s) have been submitted.");
		   		Done.setContentText("Please Check email for Confirmation Order number");

		   		Done.showAndWait();
				
			} catch (SQLException e) {
				Alert Error2= new Alert(AlertType.ERROR, "An error has occured while connecting with the database.\n Please check your internet connection and try again.");
		   		Error2.setTitle("Error");
		   		Error2.setHeaderText("Connection Error!");
		   		Error2.showAndWait();
			}
	   		 
	   	 }else {
	   		Alert Error= new Alert(AlertType.ERROR, "Please add funds and try again.");
	   		Error.setTitle("Error");
	   		Error.setHeaderText("Error Submitting Order");
	   		Error.showAndWait();
	   	 }
	   	 }
	   	 else {
	   		Alert Error2= new Alert(AlertType.ERROR, "Cart is Empty");
	   		Error2.setTitle("Error");
	   		Error2.setHeaderText("Error Submitting Order");
	   		Error2.showAndWait();
	   	 }
	   	 }
    }
    
	
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	
	@FXML
	void removeselected(ActionEvent event) {
		TreeItem<Cart> selectedItem = tree.getSelectionModel().getSelectedItem();
		if(selectedItem != null) {
			if(selectedItem.getParent() != tree.getRoot()) {
				if (selectedItem.isLeaf()) {
				selectedItem = selectedItem.getParent();
			}
			}
		Alert alert = new Alert(AlertType.CONFIRMATION, "Remove " + selectedItem.getValue().getFullname() + " " + selectedItem.getValue().getMenuitem()
				+" Order from cart?" , ButtonType.YES, ButtonType.CANCEL);
	   	 alert.showAndWait();

	   	 if (alert.getResult() == ButtonType.YES) {
	   		TreeItem<Cart> c = (TreeItem<Cart>)tree.getSelectionModel().getSelectedItem();
	   		if (c.isLeaf() && c.getParent() !=tree.getRoot()) {
				c = c.getParent();
	   		}
	   		c.getParent().getChildren().remove(c);
	   		orderprice();
	   	 }
		}
		
	}
   
	@FXML   
	void menuopen(ActionEvent event) throws IOException {
		if(studentcombox.getValue() !=null) {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/application/MenuPage.fxml"));
    	Parent Menupage = loader.load();
        Scene Menu = new Scene(Menupage);
        Menupagecontroller controller = loader.getController();
        Studentinfo studentid = studentcombox.getValue();
        controller.setParent(parent);
        controller.setinfo(studentid);
        controller.setItems(items);
        controller.start();
        controller.setRoot(root);
        Stage window = (Stage)((Node) (event.getSource())).getScene().getWindow();
        window.setScene(Menu);
    }else {
    	Alert Error= new Alert(AlertType.ERROR, "There was a selecting Student. Please contact the administrators to add children to your account");
    	Error.setTitle("Error");
   		Error.setHeaderText("Error on Selecting Student");
   		Error.showAndWait();
	   	
    }
	}
   
	@FXML
    void closewindow(ActionEvent event) throws IOException{
    	((Node)(event.getSource())).getScene().getWindow().hide();
    
    }

	private double totalprice() {
	    	double totalprice=0;	
	    	for(int i=0; i < root.getChildren().size();i++) {
		    	TreeItem<Cart> tableRow = root.getChildren().get(i);
		    Cart price = tableRow.getValue();
		    totalprice = totalprice + price.getTotal();
			}
			return totalprice;
	    }
	
	private double balancechange() {
			double cartbal = Double.parseDouble(Cart_txt.getText());
			double curbal = Double.parseDouble(Balanceamountlabel.getText());
	    	double newbalance = curbal-cartbal;
	    	return newbalance;
	    	
	    }
	public void updateinfo() {
		try {
			conn = Lunchhourdb.get();
			String sql = "SELECT Balance,Last_Purchase from Cafeteria.Parents WHERE Id = ?";
			 ps = conn.prepareStatement(sql);
		        ps.setInt(1,parent.getParentId());
		        rs = ps.executeQuery();
		        if(rs.next()) {
			        parent.setBalance(rs.getDouble("Balance"));
			        parent.setLastOrder(rs.getDate("Last_Purchase"));
			        java.util.Date Lastorder = new java.util.Date(parent.getLastOrder().getTime());
					 LastOrder.setText(dateFormat.format(Lastorder.getTime()));
					 Balanceamountlabel.setText(df.format(parent.getBalance()));
			        
			        
			}
		        conn.close(); 
		        
		} catch (SQLException e) {
			Alert Error2= new Alert(AlertType.ERROR, "An error has occured while connecting with the database.\n Please check your internet connection and try again.");
	   		Error2.setTitle("Error");
	   		Error2.setHeaderText("Connection Error!");
	   		Error2.showAndWait();
		}
		 
	}
	public void setInfo() {
		parentsname.setText(parent.getName());
		  Calendar cal = Calendar.getInstance();
	        currentdatelabel.setText(dateFormat.format(cal.getTime()));
		Balanceamountlabel.setText(df.format(parent.getBalance()));
	
		if(parent.getLastOrder() != null ) {
		 java.util.Date Lastorder = new java.util.Date(parent.getLastOrder().getTime());
		 LastOrder.setText(dateFormat.format(Lastorder.getTime()));
		}
		
		if(parent.getLastupdate() != null) {

			 java.util.Date dBalance = new java.util.Date(parent.getLastupdate().getTime());
				LastUpdate.setText(dateFormat.format(dBalance.getTime()));
		}
		addstudent();
		   maketree();
	     orderprice();
	}
	
	public void Setinfo() {
			 parent = new Parentinfo();
			try {
				conn = Lunchhourdb.get();
				parent.setUsername(Logincontroller.getUsername());
				 String sql = "SELECT * from Cafeteria.Parents WHERE userName = ?";
				 ps = conn.prepareStatement(sql);
			        ps.setString(1, parent.getUsername());
			        rs = ps.executeQuery();
			        if(rs.next()) {
			        parent.setParentId(rs.getInt("Id"));
			        parent.setName(rs.getString("First Name") + " " + rs.getString("Last Name"));
			        parent.setBalance(rs.getDouble("Balance"));
			        Calendar cal = Calendar.getInstance();
			        currentdatelabel.setText(dateFormat.format(cal.getTime()));
			        parent.setLastupdate(rs.getDate("Last Balance Update"));
			        parent.setLastOrder(rs.getDate("Last_Purchase"));
			        sql = "SELECT * from Cafeteria.Student WHERE `Parent ID` = ?";
					 ps = conn.prepareStatement(sql);
				        ps.setInt(1, parent.getParentId());
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
			        conn.close();
			        setInfo();
			        }
			} catch (SQLException e) {
				Alert Error2= new Alert(AlertType.ERROR, "An error has occured while connecting with the database.\n Please check your internet connection and try again.");
    	   		Error2.setTitle("Error");
    	   		Error2.setHeaderText("Connection Error!");
    	   		Error2.showAndWait();
			}
			
		}
	        
	private void addstudent() {
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
	}
	
    private void orderprice() {
    	Cart_txt.setText(formatter.format(totalprice()));
      	 balancerem_txt.setText(formatter.format(balancechange()));
    }

    private void maketree () {
		tree.setRoot(root);
		tree.setShowRoot(false);
		nametree.setCellValueFactory((TreeTableColumn.CellDataFeatures<Cart, String> param) -> param.getValue().getValue().getFullnames());
		daytree.setCellValueFactory((TreeTableColumn.CellDataFeatures<Cart, String> param) -> param.getValue().getValue().getDayt());
		menutree.setCellValueFactory((TreeTableColumn.CellDataFeatures<Cart, String> param) -> param.getValue().getValue().getMenuitemt());
		addtree.setCellValueFactory((TreeTableColumn.CellDataFeatures<Cart, String> param) -> param.getValue().getValue().getAddt());
		extratree.setCellValueFactory((TreeTableColumn.CellDataFeatures<Cart, String> param) -> param.getValue().getValue().getExtrat());
		totaltree.setCellValueFactory((TreeTableColumn.CellDataFeatures<Cart, Double> param) -> param.getValue().getValue().getTotalt());
	}

    public Parentinfo getparentinfo() {
    	return parent;
    }
    public void setparent(Parentinfo parentinfo) {
    	this.parent = parentinfo;
    }
   
  
}


