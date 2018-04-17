package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


import Models.Order;
import Models.Orders;
import Models.treeroot;
import connection.Lunchhourdb;
import Models.Extras;
import Models.Menu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Deliverycontroller implements Initializable{

    @FXML
    private DatePicker startingdate;

    @FXML
    private DatePicker endingdate;

    @FXML
    private Button savebtn;

    @FXML
    private Button printbtn;

    @FXML
    private Button Backbtn;

    @FXML
    private Button searchbtn;

    @FXML
    private TreeTableView<Orders> ordertree;

    @FXML
    private TreeTableColumn<Orders, String> orderday;

    @FXML
    private TreeTableColumn<Orders, String> menuitem;

    @FXML
    private TreeTableColumn<Orders, Integer> menuqty;

    @FXML
    private TreeTableColumn<Orders, String> add;

    @FXML
    private TreeTableColumn<Orders, Integer> addqty;

    @FXML
    private TreeTableColumn<Orders, String> extra;

    @FXML
    private TreeTableColumn<Orders, Integer> extraqty;

    @FXML
    private Label delivery;

    @FXML
    private ComboBox<String> filtercombo;

    @FXML
    private TextField filter;
    private String adminuser;
    @FXML
    void backtoorder(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/application/Adminhomepage.fxml"));
    	Parent Orderpage = loader.load();
    	Adminhomepagecontroller controller = loader.getController();
		controller.setAdminuser(adminuser);
		controller.start();
        Scene Order = new Scene(Orderpage);
        Stage window = (Stage)((Node) (event.getSource())).getScene().getWindow();
        window.setScene(Order);
    }

    @FXML
    void filtercom(ActionEvent event) {

    }

    @FXML
    void print(ActionEvent event) {

    }

    @FXML
    void save(ActionEvent event) {

    }
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
    @FXML
    void search(ActionEvent event) throws SQLException { 
	
    	list.clear();
    	LocalDate today = LocalDate.now();
    	/*
    	if(startingdate.getValue() != null && endingdate.getValue() != null) {
    		if(!startingdate.getValue().isAfter(today) && !startingdate.getValue().isAfter(endingdate.getValue())){
    			try {    		
        			conn = Lunchhourdb.get();
        			String sql = "Select * from Cafeteria.Order Where `Order Date` BETWEEN ?  AND ? ";
    				ps = conn.prepareStatement(sql);
    	    		ps.setDate(2,Date.valueOf(startingdate.getValue()));
    	    		ps.setDate(3, Date.valueOf(endingdate.getValue()));
    	    		rs = ps.executeQuery();
    	    		while(rs.next()) {
    	    			
    	    			Order order = new Order();
    	    			order.setMenuitem(rs.getString("Menu Item"));
    	    			order.setAdditem(rs.getString("Additional"));
    	    			order.setExtraitem(rs.getString("Extra"));
    	    			order.setOrderday(rs.getDate("Order_Date"));
    	    			list.add(order);
    	    		}
    	    		
    	    		
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
    	}else*/ if(startingdate.getValue() !=null) {
    		try {
    			conn = Lunchhourdb.get();
    			String sql = "Select * from Cafeteria.Order Where `Order Date` BETWEEN ?  AND ? ";
				ps = conn.prepareStatement(sql);
	    		ps.setDate(1,Date.valueOf(startingdate.getValue()));
	    		ps.setDate(2, Date.valueOf(today));
	    		rs = ps.executeQuery();
	    		Orders orders = new Orders();
	    		int qty;
	    		List<Order> orderlist = new ArrayList<Order>();
	    		while(rs.next()) {
	    			List<Extras> orderextralist = new ArrayList<Extras>();
	    			Order order = new Order();
	    			order.setOrderday(rs.getDate("Order_Date"));
	    			order.setMenuitem(rs.getString("Menu Item"));
	    			String adds = rs.getString("Additional");
	    			if(adds != null) {
	    				adds = adds.substring(0, adds.length() - 1);
	    				String add[] = adds.split("/");
	    				for(int i = 0; i <add.length;i++) {
	    					String addspilt[] = add[i].split("x");
	    					if(addspilt[0].trim().equals(menu.getAdd1())) {
	    					order.setAdd1qty(addspilt[1]);
	    					}else if(addspilt[0].trim().equals(menu.getAdd2())) {
		    					order.setAdd2qty(addspilt[1]);
	    					}else {
		    					order.setAdd3qty(addspilt[1]);
	    					}
	    				}
	    			}else {
	    				order.setAdd1qty("0");
	    				order.setAdd2qty("0");
	    				order.setAdd3qty("0");
	    			}
	    			String extra_ = rs.getString("Extra");
	    			if(extra_ != null) {
	    				extra_ = extra_.substring(0, extra_.length() - 1);
	    				String extra[] = extra_.split("/");
	    				for(int i = 0; i <extra.length;i++) {
	    					String extrasplit[] = extra[i].split("x");
	    					Extras newextra = new Extras();
	    					newextra.setExtraName(extrasplit[0].trim());
	    					newextra.setItemCount(Integer.parseInt(extrasplit[1].trim()));
	    					orderextralist.add(newextra);
	    			}
	    			order.setExtraitems(orderextralist);
	    			orderlist.add(order);
	    			
	    			
	    		}
	    			
	    			int days=0;
	    			LocalDate startpoint = startingdate.getValue();
	    			while(startpoint.isBefore(today)){
	    				Order mainorder = new Order();
	    				mainorder.setOrderday(java.sql.Date.valueOf(startpoint));
	    				for(Order order1 : orderlist) {
	    					if(order1.getOrderday() == mainorder.getOrderday()) {
	    	    			if(order1.getMenuitem().equals(menu.getMenu1())) {
	    	    				qty = mainorder.getMenu1qty() + 1;
	    	    				mainorder.setMenu1qty(qty);
	    	    			}else if(order1.getMenuitem().equals(menu.getMenu2())) {
	    	    				qty = mainorder.getMenu2qty() + 1;
	    	    				mainorder.setMenu2qty(qty);
	    	    			}else {
	    	    				qty = mainorder.getMenu3qty() + 1;
	    	    				mainorder.setMenu3qty(qty);
	    	    			}
	    	    			mainorder.setAdd1qty(order1.getAdd1qty() + mainorder.getAdd1qty());
	    	    			mainorder.setAdd1qty(order1.getAdd2qty() + mainorder.getAdd2qty());
	    	    			mainorder.setAdd1qty(order1.getAdd3qty() + mainorder.getAdd3qty());
	    					}	
	    	    		}
	    				
	    			days++;
	    			startpoint = startpoint.plusDays(days);
	    			}
	    		}
    		}//
	    		/*
	    			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
	    			Orders order = new Orders();
	    			order.setOrderday(df.format(rs.getDate("Order_Date")));
	    			TreeItem<Orders> mainnode = new TreeItem<>(order);
	    			Orders test = new Orders();
	    			test.setMenuitem(rs.getString("Menu Item"));
	    			mainnode.getChildren().add(new TreeItem<Orders>(test));
	    			if(rs.getString("Additional") != null && rs.getString("Extra") != null) {
	    			String add[] = rs.getString("Additional").split("/");
	    			String Extra[] = rs.getString("Extra").split("/");
	    			if(add.length >= Extra.length) {
	    			for(int i = 0; i< add.length; i++) {
	    				if(i == 0) {
	    				String addqty_[] = add[i].split("x");
	    				String extraqty_[] = Extra[i].split("x");
	    				test.setAdditem(addqty_[0]);
	    				test.setAddqty(Integer.parseInt(addqty_[1].trim()));
	    				test.setExtraitem(extraqty_[0]);
	    				test.setExtraqty(Integer.parseInt(extraqty_[1].trim()));
	    				mainnode.getChildren().add(new TreeItem<Orders>(test));
	    				}else if(i>0 && i<3) {
	    					test.setMenuitem("");
	    					String addqty_[] = add[i].split("x");
		    				String extraqty_[] = Extra[i].split("x");
		    				test.setAdditem(addqty_[0]);
		    				test.setAddqty(Integer.parseInt(addqty_[1].trim()));
		    				test.setExtraitem(extraqty_[0]);
		    				test.setExtraqty(Integer.parseInt(extraqty_[1].trim()));
		    				mainnode.getChildren().add(new TreeItem<Orders>(test));
	    				}else {
	    					
	    				}
	    			}
	    			}else{
	    				for(int i = 0; i< Extra.length; i++) {
	    				if(i == 0) {
	    				String addqty_[] = add[i].split("x");
	    				String extraqty_[] = Extra[i].split("x");
	    				test.setAdditem(addqty_[0]);
	    				test.setAddqty(Integer.parseInt(addqty_[1].trim()));
	    				test.setExtraitem(extraqty_[0]);
	    				test.setExtraqty(Integer.parseInt(extraqty_[1].trim()));
	    				orderroot.getChildren().add(new TreeItem<Orders>(test));
	    				}else if(i>0 && i<3) {
	    					test.setMenuitem("");
	    					String addqty_[] = add[i].split("x");
		    				String extraqty_[] = Extra[i].split("x");
		    				test.setAdditem(addqty_[0]);
		    				test.setAddqty(Integer.parseInt(addqty_[1].trim()));
		    				test.setExtraitem(extraqty_[0]);
		    				test.setExtraqty(Integer.parseInt(extraqty_[1].trim()));
		    				orderroot.getChildren().add(new TreeItem<Orders>(test));
	    				}else {
	    					
	    				}
	    			}
	    				
	    			}
	    			}else {
	    				System.out.println("No items");
	    			}
	    		} */
    		finally{
    			conn.close();
    		}
    	}
    		}
    	
	public void setAdminuser(String user) {
		this.adminuser = user;
		
	}
	private void fillextras()  {
		 String Sql = "Select * from Cafeteria.Extras";            
	        try {
	        	conn = Lunchhourdb.get();
				ps = conn.prepareStatement(Sql);
				rs = ps.executeQuery();
		        while(rs.next()){
		        	
		        		Extras item_ = new Extras ();
		        		item_.setExtraName(rs.getString("Extra_Name"));
		        		item_.setExtraPrice(rs.getDouble("Extra_Price"));
		        		item_.setItemCount(0);
		        		list.add(item_);
		             
		        }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        finally {
	        	try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	}
	private Menu menu;
	private void fillmenu() {
		conn = Lunchhourdb.get();
		String SQL = "SELECT * from Menu";
		
			try {
				ps = conn.prepareStatement(SQL);
				rs = ps.executeQuery();
				if(rs.next()) {
					 menu = new Menu();
					menu.setMenu1(rs.getString("Menu 1 item"));
					menu.setMenu2(rs.getString("Menu 2 item"));
					menu.setMenu3(rs.getString("Menu 3 item"));
					menu.setMenu1price(rs.getString("Menu 1 Price"));
					menu.setMenu2price(rs.getString("Menu 2 Price"));
					menu.setMenu3price(rs.getString("Menu 3 Price"));
					menu.setStart(rs.getDate("Menu_Start"));
					menu.setAdd1(rs.getString("Additional 1"));
					menu.setAdd2(rs.getString("Additional 2"));
					menu.setAdd3(rs.getString("Additional 3"));
					menu.setAdd1price(rs.getString("Add 1 Price"));
					menu.setAdd2price(rs.getString("Add 2 Price"));
					menu.setAdd3price(rs.getString("Add 3 Price"));
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}
	private void orderdate(DatePicker date) {
        final Callback<DatePicker, DateCell> dayCellFactory = 
            new Callback<DatePicker, DateCell>() {
                @Override
                public DateCell call(final DatePicker datePicker) {
                    return new DateCell() {
                        @Override
                        public void updateItem(LocalDate item, boolean empty) {
                            super.updateItem(item, empty);
                            LocalDate now = LocalDate.now();
                            LocalDateTime date1 = LocalDateTime.now();
                            LocalDateTime date2 = now.atStartOfDay().plusHours(7);
                            DayOfWeek day = DayOfWeek.from(item);
                            if (item.isAfter(now.with(TemporalAdjusters.next(DayOfWeek.FRIDAY))) || day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY
                                ) {
                                    setDisable(true);
                                    setStyle("-fx-background-color: #ffc0cb;");
                            }
                            if(date1.isAfter(date2) && item.isBefore(now.plusDays(1)) || item.isBefore(now)) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                            }
                            if(now.getDayOfWeek() == DayOfWeek.FRIDAY && item.isAfter(now.plusDays(1))) {
                            	setDisable(true);
                                setStyle("-fx-background-color: #ffc0cb;");
                            }
                    }
                };
            }
	};
	 LocalDate now = LocalDate.now();
	 DayOfWeek day = DayOfWeek.from(now);
     LocalDateTime date1 = LocalDateTime.now();
     LocalDateTime date2 = now.atStartOfDay().plusHours(7);
	 if(day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY ) {
		 date.setValue(now.with(TemporalAdjusters.next(DayOfWeek.MONDAY)));
	 }else if(date1.isAfter(date2)) {
		 date.setValue(now.plusDays(1));
	 }
	 else {
		 date.setValue(now);
	 }
	 date.setDayCellFactory(dayCellFactory);
	}
	private ObservableList<Extras> list= FXCollections.observableArrayList();
	TreeItem<Orders> orderroot = new TreeItem<Orders>(new Orders());
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		start();
		ordertree.setRoot(orderroot);
		ordertree.setShowRoot(false);
		orderday.setCellValueFactory((TreeTableColumn.CellDataFeatures<Orders, String> param) -> param.getValue().getValue().getOrderday());
		menuitem.setCellValueFactory((TreeTableColumn.CellDataFeatures<Orders, String> param) -> param.getValue().getValue().getMenuitem());
		add.setCellValueFactory((TreeTableColumn.CellDataFeatures<Orders, String> param) -> param.getValue().getValue().getAdditem());
		add.setCellValueFactory((TreeTableColumn.CellDataFeatures<Orders, String> param) -> param.getValue().getValue().getAdditem());
		extra.setCellValueFactory((TreeTableColumn.CellDataFeatures<Orders, String> param) -> param.getValue().getValue().getExtraitem());

	}
public void start() {
	fillextras();
	fillmenu();
}

}
