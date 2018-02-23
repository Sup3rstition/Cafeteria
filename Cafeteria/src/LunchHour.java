import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JSplitPane;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.awt.TextField;
import javax.swing.JSeparator;
import net.miginfocom.swing.MigLayout;
import java.awt.Dimension;
import javax.swing.SwingConstants;

public class LunchHour {
	private JFrame frame1;
	private JPanel contentPane;
	private JTextField TotalTxtOrder;
	private JTextField BalanceRemainingTxt;
	private final ButtonGroup OrderButtonGroup = new ButtonGroup();
	private String[] Order_column_headers = {"Extras","#"};
	private String[][] Order_extras = {
	{"Extra","1"},{"Extra","2"},{"Extra","3"},{"Extra","4"},{"Extra","5"},{"Extra","6"},{"Extra","7"},{"Extra","8"},{"Extra","9"},{"Extra","10"},
	{"Extra","11"},{"Extra","12"},{"Extra","13"},{"Extra","14"},{"Extra","15"},{"Extra","16"},{"Extra","17"},{"Extra","18"},{"Extra","19"},{"Extra","20"},
	{"Extra","21"},{"Extra","22"},{"Extra","23"},{"Extra","24"},{"Extra","25"},{"Extra","26"},{"Extra","27"},{"Extra","28"},{"Extra","29"},{"Extra","30"},
	{"Extra","31"},{"Extra","32"},{"Extra","33"},{"Extra","34"},{"Extra","35"},{"Extra","36"},{"Extra","37"},{"Extra","38"},{"Extra","39"},{"Extra","40"}};
	private JTable table;
	private JFrame frame;
	private JPanel ConfirmPanel;
	private JTable Confirmtable;
	private String[] Confirm_column_headers = {"Student" ,"Date","Menu Item","Addtitional item","extras", "Student Total"};
	private String[][] Confirm_Order = {{"Bryan Cordes","??/??/????", "Menu 1", "Add 1 and Add 2", "3 Extras", "$999.99"}};
	private JTextField ConfirmBalanceTxtfield;
	private JTextField ConfirmOrderTotalTxtField;
	private JPanel CheckHistoryPanel;
	private JTextField ChkHistorytextField;
	private JTextField ChkHistorytextField_1;
	private JTextField Usernametxt;
	private JPasswordField pwdPassword;

	/**
	 * Launch the application.
	 */
	//new comment
	public static void main(String[] args) throws Exception{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LunchHour window = new LunchHour();
					window.frame1.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws ClassNotFoundException 
	 */
	public LunchHour() throws Exception {
		initialize();
	}
	private static Connection getRemoteConnection() {
		Connection con = null;
		// TODO Auto-generated method stub
		try {
		    System.out.println("Loading driver...");
		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Driver loaded!");
		  } catch (ClassNotFoundException e) {
		    throw new RuntimeException("Cannot find the driver in the classpath!", e);
		  }
		
		      try {
		      Class.forName("com.mysql.jdbc.Driver");
		      String dbName = "Cafeteria";
		      String userName = "root";
		      String password = "root";
		      String hostname = "lunchhourdb.codmmpb86f3e.us-east-1.rds.amazonaws.com";
		      String port = "3306";
		      String jdbcUrl = "jdbc:mysql://" + hostname + ":" +
		    		    port + "/" + dbName + "?user=" + userName + "&password=" + password;
		      System.out.println(jdbcUrl);
		      con = DriverManager.getConnection(jdbcUrl,userName,password);
		      System.out.println("Success on conncetion");
		      return con;
		    }
		    catch (ClassNotFoundException e) {System.out.print("Driver Connection problem"); }
		    catch (SQLException ex) {
		        // Handle any errors
		        System.out.println("SQLException: " + ex.getMessage());
		        System.out.println("SQLState: " + ex.getSQLState());
		        System.out.println("VendorError: " + ex.getErrorCode());
		      }
	    return null;
	  }
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws Exception {
		frame1 = new JFrame();
		frame1.setForeground(new Color(255, 255, 255));
		frame1.setBackground(new Color(255, 255, 255));
		frame1.setTitle("Login \n");
		frame1.setResizable(false);
		frame1.setBounds(100, 100, 628,505);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.getContentPane().setLayout(new CardLayout(0, 0));
		Connection con = getRemoteConnection();
		/*
		 * Login Panel Begins
		 */
		JPanel LoginPanel = new JPanel();
		frame1.getContentPane().add(LoginPanel, "name_272179477667435");
		LoginPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel LoginPanel_1 = new JPanel();
		LoginPanel_1.setBackground(new Color(236, 240, 241));
		LoginPanel.add(LoginPanel_1, BorderLayout.EAST);
		
		JLabel lblUsernameemail = new JLabel("Username/Email:");
		lblUsernameemail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		Usernametxt = new JTextField();
		Usernametxt.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Usernametxt.setBorder(null);
		Usernametxt.setText("Username");
		Usernametxt.setBackground(new Color(236, 240, 241));
		Usernametxt.setColumns(10);
		
		JLabel lblPassowrd = new JLabel("Password:");
		lblPassowrd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		pwdPassword = new JPasswordField();
		pwdPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pwdPassword.setText("Password");
		pwdPassword.setBackground(new Color(236, 240, 241));
		pwdPassword.setBorder(null);
		
		JCheckBox chckbxRememberMe = new JCheckBox("Remember Me");
		chckbxRememberMe.setBackground(new Color(236, 240, 241));
		
		JLabel lblForogtPassord = new JLabel("Forgot Password?");
		lblForogtPassord.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setForeground(Color.BLACK);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBackground(Color.BLACK);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(145,180,150));
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBackground(new Color(145,180,150));
		btnLogin.setMinimumSize(new Dimension(60, 23));
		btnLogin.setMaximumSize(new Dimension(250, 100));
		LoginPanel_1.setLayout(new MigLayout("", "[290px][130px][48px][136px]", "[152px][10px][16px][8px][1px][10px][16px][11px][16px][8px][1px][13px][23px][11px][23px][129px][14px]"));
		LoginPanel_1.add(chckbxRememberMe, "cell 1 12,alignx right,aligny top");
		LoginPanel_1.add(lblForogtPassord, "cell 3 12,alignx left,aligny center");
		LoginPanel_1.add(lblPassowrd, "cell 1 6,alignx left,aligny top");
		LoginPanel_1.add(lblUsernameemail, "cell 1 0,alignx left,aligny bottom");
		LoginPanel_1.add(Usernametxt, "cell 1 2 3 1,growx,aligny top");
		LoginPanel_1.add(pwdPassword, "cell 1 8 3 1,growx,aligny top");
		LoginPanel_1.add(separator_1, "cell 1 9 3 2,grow");
		LoginPanel_1.add(separator, "cell 1 3 3 2,grow");
		
		JLabel lblCreateAnAccount = new JLabel("Create an Account");
		lblCreateAnAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		lblCreateAnAccount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		LoginPanel_1.add(lblCreateAnAccount, "cell 1 14,alignx right,aligny top");
		LoginPanel_1.add(btnLogin, "cell 3 14,grow");
		LoginPanel_1.add(panel, "cell 0 0 1 17,grow");
		
		JLabel lblCreatedByLunchhour = new JLabel("Created By LunchHour");
		LoginPanel_1.add(lblCreatedByLunchhour, "cell 3 16,alignx right");
		
				/*Login Panel Ends
				 * 
				 * 
				 * OrderPanel Begins
				 */
		JPanel OrderPanel = new JPanel();
		frame1.getContentPane().add(OrderPanel, "name_272189214436444");
		
		JPanel ParentAccountPanel = 
				new JPanel();
		ParentAccountPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JLabel AccountLabel = new JLabel("Account:");
		AccountLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel BalanceLabel = new JLabel("Balance:");
		BalanceLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblCurrentDateLabel = new JLabel("Current Date:");
		lblCurrentDateLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel CurrentDateLabel = new JLabel("??/??/????");
		CurrentDateLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
		
		JLabel lblparentnameLabel = new JLabel("(ParentName)");
		lblparentnameLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
		
		JLabel BalanceAmount = new JLabel("??.??");
		BalanceAmount.setFont(new Font("Dialog", Font.PLAIN, 14));
		
		JPanel Menu_Panel = new JPanel();
		Menu_Panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JLabel TotalOrder = new JLabel("Total;");
		
		TotalTxtOrder = new JTextField();
		TotalTxtOrder.setText("$0.00");
		TotalTxtOrder.setColumns(10);
		
		JLabel OrderBalanceRemaingLabel = new JLabel("Balance remaining:");
		
		BalanceRemainingTxt = new JTextField();
		BalanceRemainingTxt.setText("$0.00");
		BalanceRemainingTxt.setColumns(10);
		
		JButton OrderbtnOrder = new JButton("Order");
		
		JButton OrderBtnClear = new JButton("Clear");
		
		JLabel lblOrderProblemsContactUsLabel = new JLabel("Problems? Contact the school.");
		
		JList OrderList = new JList();
		OrderList.setBorder(new LineBorder(new Color(0, 0, 0)));
		OrderList.setModel(new AbstractListModel() {
			String[] values = new String[] {"Cordes,Bryan", "Cordes,Bryan", "Cordes,Bryan", "Cordes,Bryan", "Cordes,Bryan", "Cordes,Bryan", "Cordes,Bryan", "Cordes,Bryan", "Cordes,Bryan", ""};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		
		JLabel lblStudentsOrderLabel = new JLabel("Students");
		lblStudentsOrderLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JPanel ExtraPanel = new JPanel();
		
		JPanel PickItemPanel = new JPanel();
		PickItemPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		ParentAccountPanel.setLayout(new MigLayout("", "[67px][6px][26px][][6px][71px]", "[14px][14px][14px][23px][]"));
		
		JButton CheckHistoryBtn = new JButton("Check History");
		CheckHistoryBtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		ParentAccountPanel.add(CheckHistoryBtn, "cell 0 3,alignx left,aligny top");
		CheckHistoryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderPanel.setVisible(false);
				frame1.setSize(360, 493);
				CheckHistoryPanel.setVisible(true);
			}
		});
		ParentAccountPanel.add(AccountLabel, "cell 0 0,alignx left,aligny top");
		ParentAccountPanel.add(BalanceLabel, "cell 0 1,alignx left,aligny top");
		ParentAccountPanel.add(lblCurrentDateLabel, "cell 0 2,alignx left,aligny top");
		ParentAccountPanel.add(BalanceAmount, "cell 2 1,alignx left,aligny top");
		ParentAccountPanel.add(CurrentDateLabel, "cell 2 2 4 1,alignx left,aligny top");
		ParentAccountPanel.add(lblparentnameLabel, "cell 2 0 4 1,alignx left,aligny top");
		
		JButton OrderLogoutBtn = new JButton("Log Out");
		OrderLogoutBtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		ParentAccountPanel.add(OrderLogoutBtn, "cell 3 3,alignx left,aligny top");
		PickItemPanel.setLayout(new MigLayout("", "[41px][6px][3px][6px][61px]", "[31px][16px][1px][6px][16px][16px][16px][16px][16px]"));
		
		JLabel lblMenu_1Label = new JLabel("Menu 1:");
		PickItemPanel.add(lblMenu_1Label, "cell 0 2 3 1,alignx left,growy");
		
		JLabel lblMenu_3Label = new JLabel("Menu 2:");
		PickItemPanel.add(lblMenu_3Label, "cell 0 4 3 1,alignx left,aligny top");
		
		JLabel lblMenu_4Label = new JLabel("Menu 3:");
		PickItemPanel.add(lblMenu_4Label, "cell 0 5 3 1,alignx left,aligny top");
		
		JLabel lblAddLabel = new JLabel("Add 1:");
		PickItemPanel.add(lblAddLabel, "cell 0 6,alignx left,aligny top");
		
		JLabel lblAdd_1Label = new JLabel("Add 2:");
		PickItemPanel.add(lblAdd_1Label, "cell 0 7,alignx left,aligny top");
		
		JLabel lblAdd_2Label = new JLabel("Add 3:");
		PickItemPanel.add(lblAdd_2Label, "cell 0 8,alignx left,aligny top");
		
		JLabel lblMenuBoxLabel = new JLabel("Menu");
		lblMenuBoxLabel.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		PickItemPanel.add(lblMenuBoxLabel, "cell 0 0 5 1,alignx center,growy");
		
		JLabel lblMenu_2WeekLabel = new JLabel("??/??/????");
		PickItemPanel.add(lblMenu_2WeekLabel, "cell 0 1 5 1,alignx center,aligny top");
		
		JLabel Menu_1ItemLabel = new JLabel("New label");
		PickItemPanel.add(Menu_1ItemLabel, "cell 4 2 1 3,alignx left,aligny top");
		
		JLabel Menu_2ItemLabel = new JLabel("New label");
		PickItemPanel.add(Menu_2ItemLabel, "cell 4 4,alignx left,aligny top");
		
		JLabel Menu_3ItemLabel = new JLabel("New label");
		PickItemPanel.add(Menu_3ItemLabel, "cell 4 5,alignx left,aligny top");
		
		JLabel Add_1ItemLabel = new JLabel("New label");
		PickItemPanel.add(Add_1ItemLabel, "cell 2 6 3 1,alignx left,aligny top");
		
		JLabel Add_2itemLabel = new JLabel("New label");
		PickItemPanel.add(Add_2itemLabel, "cell 2 7 3 1,alignx left,aligny top");
		
		JLabel Add_3itemLabel = new JLabel("New label");
		PickItemPanel.add(Add_3itemLabel, "cell 2 8 3 1,alignx left,aligny top");
		
		JLabel lblOrderExtrasLabel = new JLabel("Extras");
		lblOrderExtrasLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		DefaultTableModel Order_model = new DefaultTableModel(Order_extras,Order_column_headers); 
		ExtraPanel.setLayout(new MigLayout("", "[157px]", "[14px][424px]"));
		table = new JTable(Order_model);
		JScrollPane OrderscrollPane = new JScrollPane(table);
		ExtraPanel.add(OrderscrollPane, "cell 0 1,grow");
		ExtraPanel.add(lblOrderExtrasLabel, "cell 0 0,alignx center,aligny top");
		
		JLabel lblDayLabel = new JLabel("Day:");
		
		JRadioButton Menu1Radio = new JRadioButton("Menu 1");
		OrderButtonGroup.add(Menu1Radio);
		
		JRadioButton rdbtnMenu_2 = new JRadioButton("Menu 2");
		OrderButtonGroup.add(rdbtnMenu_2);
		
		JRadioButton rdbtnMenu_3 = new JRadioButton("Menu 3");
		OrderButtonGroup.add(rdbtnMenu_3);
		
		JSpinner OrderSpinner = new JSpinner();
		
		JSpinner OrderSpinner_1 = new JSpinner();
		
		JSpinner OrderSpinner_2 = new JSpinner();
		Menu_Panel.setLayout(new MigLayout("", "[78px][][74px][][][6px][59px]", "[14px][20px][14px][14px][23px][24px][24px]"));
		Menu_Panel.add(lblDayLabel, "cell 0 1,alignx center,aligny center");
		
		JComboBox DayComboBox = new JComboBox();
		DayComboBox.setModel(new DefaultComboBoxModel(new String[] {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"}));
		Menu_Panel.add(DayComboBox, "cell 1 1,growx,aligny top");
		
		JLabel StudentNameLabel = new JLabel("First Last");
		Menu_Panel.add(StudentNameLabel, "cell 1 2,alignx center,aligny top");
		
		JLabel OrderMenuLabel = new JLabel("Menu");
		Menu_Panel.add(OrderMenuLabel, "cell 0 3,alignx center,aligny top");
		
		JLabel lblAdditionalItemsLabel = new JLabel("Additional Items:");
		Menu_Panel.add(lblAdditionalItemsLabel, "cell 1 3,alignx center,aligny top");
		Menu_Panel.add(Menu1Radio, "cell 0 4,alignx center,aligny top");
		
		JLabel lblOrderAdd3Label = new JLabel("Add 1:");
		Menu_Panel.add(lblOrderAdd3Label, "cell 1 4,alignx center,aligny center");
		
		JLabel lblOrderAdd2Label = new JLabel("Add 2:");
		Menu_Panel.add(lblOrderAdd2Label, "cell 1 5,alignx center,aligny center");
		Menu_Panel.add(rdbtnMenu_3, "cell 0 6,alignx center,aligny bottom");
		Menu_Panel.add(rdbtnMenu_2, "cell 0 5,alignx center,aligny bottom");
		
		JLabel lblOrderAdd1Label = new JLabel("Add 3:");
		Menu_Panel.add(lblOrderAdd1Label, "cell 1 6,alignx center,aligny center");
		Menu_Panel.add(OrderSpinner_2, "cell 6 6,alignx left,aligny top");
		Menu_Panel.add(OrderSpinner_1, "cell 6 5,alignx left,aligny top");
		Menu_Panel.add(OrderSpinner, "cell 6 4,alignx left,aligny top");
		
		JLabel lblWeekLabel = new JLabel("Week:");
		Menu_Panel.add(lblWeekLabel, "cell 0 0,alignx center,aligny top");
		
		JLabel StudentLabel = new JLabel("Student:");
		Menu_Panel.add(StudentLabel, "cell 0 2,alignx center,aligny top");
		OrderPanel.setLayout(new MigLayout("", "[147px][270px][171px]", "[14px][6px][97px][11px][73px][11px][121px][6px][99px][6px][15px]"));
		OrderPanel.add(lblStudentsOrderLabel, "cell 0 0,alignx center,aligny top");
		OrderPanel.add(OrderList, "cell 0 2 1 3,grow");
		OrderPanel.add(PickItemPanel, "cell 0 6 1 5,grow");
		OrderPanel.add(Menu_Panel, "cell 1 4 1 3,grow");
		OrderPanel.add(layeredPane, "cell 1 8,grow");
		layeredPane.setLayout(new MigLayout("", "[57px][20px][28px][111px]", "[20px][20px][23px]"));
		layeredPane.add(TotalOrder, "cell 2 0,alignx left,aligny center");
		layeredPane.add(TotalTxtOrder, "cell 3 0,growx,aligny top");
		layeredPane.add(OrderBalanceRemaingLabel, "cell 0 1 3 1,alignx right,aligny center");
		layeredPane.add(BalanceRemainingTxt, "cell 3 1,growx,aligny top");
		layeredPane.add(OrderBtnClear, "cell 0 2,alignx left,aligny top");
		layeredPane.add(OrderbtnOrder, "cell 3 2,alignx right,aligny top");
		OrderPanel.add(lblOrderProblemsContactUsLabel, "cell 1 10,growx,aligny top");
		OrderPanel.add(ParentAccountPanel, "cell 1 0 1 3,grow");
		OrderPanel.add(ExtraPanel, "cell 2 0 1 11,alignx left,aligny top");
		
		/*
		 * End of Order Panel
		 * 
		 * 
		 * ConfirmPanel Begins
		 */
		
		JPanel ConfirmPanel = new JPanel();
		ConfirmPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		ConfirmPanel.setLayout(new BorderLayout(0, 0));
		frame1.getContentPane().add(ConfirmPanel, "name_275723673854486");

		JPanel Confirmpanel_1 = new JPanel();
		ConfirmPanel.add(Confirmpanel_1, BorderLayout.CENTER);
		SpringLayout sl_Confirmpanel_1 = new SpringLayout();
		Confirmpanel_1.setLayout(sl_Confirmpanel_1);
		
		JLabel lblOrderConfirmation = new JLabel("Order Confirmation");
		lblOrderConfirmation.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		sl_Confirmpanel_1.putConstraint(SpringLayout.NORTH, lblOrderConfirmation, 10, SpringLayout.NORTH, Confirmpanel_1);
		sl_Confirmpanel_1.putConstraint(SpringLayout.WEST, lblOrderConfirmation, 10, SpringLayout.WEST, Confirmpanel_1);
		Confirmpanel_1.add(lblOrderConfirmation);
		
		DefaultTableModel Confirm_model = new DefaultTableModel(Confirm_Order,Confirm_column_headers);
		Confirmtable = new JTable(Confirm_model);
		Confirmtable.setEnabled(false);
		Confirmtable.setSurrendersFocusOnKeystroke(true);
		JScrollPane ConfirmscrollPane = new JScrollPane(Confirmtable);
		sl_Confirmpanel_1.putConstraint(SpringLayout.NORTH, ConfirmscrollPane, 18, SpringLayout.SOUTH, lblOrderConfirmation);
		sl_Confirmpanel_1.putConstraint(SpringLayout.WEST, ConfirmscrollPane, 10, SpringLayout.WEST, Confirmpanel_1);
		sl_Confirmpanel_1.putConstraint(SpringLayout.SOUTH, ConfirmscrollPane, -29, SpringLayout.SOUTH, Confirmpanel_1);
		sl_Confirmpanel_1.putConstraint(SpringLayout.EAST, ConfirmscrollPane, 609, SpringLayout.WEST, Confirmpanel_1);
		Confirmpanel_1.add(ConfirmscrollPane);
		
		JPanel Confirmpanel_2 = new JPanel();
		ConfirmPanel.add(Confirmpanel_2, BorderLayout.SOUTH);
		
		JButton ConfirmCancelButton = new JButton("Cancel ");
		ConfirmCancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConfirmPanel.setVisible(false);
				frame1.setSize(621,487);
				OrderPanel.setVisible(true);
			}
		});
		
		JButton ConfirmOrderMoreBtn = new JButton("Confirm and Order More");
		
		JButton ConfirmandLogoutbtn = new JButton("Confirm and Logout");
		sl_Confirmpanel_1.putConstraint(SpringLayout.NORTH, ConfirmandLogoutbtn, 6, SpringLayout.SOUTH, ConfirmscrollPane);
		sl_Confirmpanel_1.putConstraint(SpringLayout.EAST, ConfirmandLogoutbtn, -10, SpringLayout.EAST, Confirmpanel_1);
		
		JLabel ConfirmOrderTotalLabel = new JLabel("Order's Total:");
		sl_Confirmpanel_1.putConstraint(SpringLayout.NORTH, ConfirmOrderTotalLabel, 6, SpringLayout.SOUTH, ConfirmscrollPane);
		Confirmpanel_1.add(ConfirmOrderTotalLabel);
		
		ConfirmBalanceTxtfield = new JTextField();
		sl_Confirmpanel_1.putConstraint(SpringLayout.WEST, ConfirmBalanceTxtfield, 342, SpringLayout.WEST, Confirmpanel_1);
		ConfirmBalanceTxtfield.setEditable(false);
		sl_Confirmpanel_1.putConstraint(SpringLayout.NORTH, ConfirmBalanceTxtfield, 1, SpringLayout.SOUTH, ConfirmscrollPane);
		Confirmpanel_1.add(ConfirmBalanceTxtfield);
		ConfirmBalanceTxtfield.setColumns(10);
		
		JLabel lblConfirmRemainingLabel = new JLabel("Remaining Balance:");
		sl_Confirmpanel_1.putConstraint(SpringLayout.NORTH, lblConfirmRemainingLabel, 0, SpringLayout.NORTH, ConfirmOrderTotalLabel);
		sl_Confirmpanel_1.putConstraint(SpringLayout.EAST, lblConfirmRemainingLabel, -6, SpringLayout.WEST, ConfirmBalanceTxtfield);
		Confirmpanel_1.add(lblConfirmRemainingLabel);
		
		ConfirmOrderTotalTxtField = new JTextField();
		sl_Confirmpanel_1.putConstraint(SpringLayout.NORTH, ConfirmOrderTotalTxtField, 1, SpringLayout.SOUTH, ConfirmscrollPane);
		sl_Confirmpanel_1.putConstraint(SpringLayout.EAST, ConfirmOrderTotalLabel, -4, SpringLayout.WEST, ConfirmOrderTotalTxtField);
		sl_Confirmpanel_1.putConstraint(SpringLayout.WEST, ConfirmOrderTotalTxtField, 536, SpringLayout.WEST, Confirmpanel_1);
		sl_Confirmpanel_1.putConstraint(SpringLayout.EAST, ConfirmOrderTotalTxtField, -22, SpringLayout.EAST, Confirmpanel_1);
		sl_Confirmpanel_1.putConstraint(SpringLayout.EAST, ConfirmBalanceTxtfield, -133, SpringLayout.WEST, ConfirmOrderTotalTxtField);
		ConfirmOrderTotalTxtField.setEditable(false);
		Confirmpanel_1.add(ConfirmOrderTotalTxtField);
		ConfirmOrderTotalTxtField.setColumns(10);
		GroupLayout gl_Confirmpanel_2 = new GroupLayout(Confirmpanel_2);
		gl_Confirmpanel_2.setHorizontalGroup(
			gl_Confirmpanel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Confirmpanel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(ConfirmCancelButton)
					.addPreferredGap(ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
					.addComponent(ConfirmandLogoutbtn)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ConfirmOrderMoreBtn)
					.addContainerGap())
		);
		gl_Confirmpanel_2.setVerticalGroup(
			gl_Confirmpanel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_Confirmpanel_2.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_Confirmpanel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(ConfirmCancelButton)
						.addComponent(ConfirmOrderMoreBtn)
						.addComponent(ConfirmandLogoutbtn)))
		);
		Confirmpanel_2.setLayout(gl_Confirmpanel_2);
		/*End of Confirm Panel
		 * 
		 * 
		 * 
		 * Check History Panel Begins
		 */
		JPanel CheckHistoryPanel = new JPanel();
		frame1.getContentPane().add(CheckHistoryPanel, "name_277364005394280");
		CheckHistoryPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		CheckHistoryPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel CheckHistoryPanel_1 = new JPanel();
		CheckHistoryPanel.add(CheckHistoryPanel_1, BorderLayout.NORTH);
		
		JLabel ChkHistoryDateLAbel = new JLabel("Date:");
		
		ChkHistorytextField = new JTextField();
		ChkHistorytextField.setColumns(10);
		
		ChkHistorytextField_1 = new JTextField();
		ChkHistorytextField_1.setColumns(10);
		
		JLabel ChkHistorydashlabel = new JLabel("-");
		ChkHistorydashlabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		
		JButton ChkHistorybtnCheck = new JButton("Check");
		GroupLayout gl_CheckHistoryPanel_1 = new GroupLayout(CheckHistoryPanel_1);
		gl_CheckHistoryPanel_1.setHorizontalGroup(
			gl_CheckHistoryPanel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CheckHistoryPanel_1.createSequentialGroup()
					.addGap(22)
					.addComponent(ChkHistoryDateLAbel, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ChkHistorytextField, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ChkHistorydashlabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(ChkHistorytextField_1, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ChkHistorybtnCheck)
					.addContainerGap(16, Short.MAX_VALUE))
		);
		gl_CheckHistoryPanel_1.setVerticalGroup(
			gl_CheckHistoryPanel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CheckHistoryPanel_1.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_CheckHistoryPanel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(ChkHistoryDateLAbel, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
						.addComponent(ChkHistorytextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(ChkHistorydashlabel)
						.addComponent(ChkHistorytextField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(ChkHistorybtnCheck)))
		);
		CheckHistoryPanel_1.setLayout(gl_CheckHistoryPanel_1);
		
		JPanel CheckHistoryPanel_2 = new JPanel();
		CheckHistoryPanel.add(CheckHistoryPanel_2, BorderLayout.CENTER);
		SpringLayout sl_CheckHistoryPanel_2 = new SpringLayout();
		CheckHistoryPanel_2.setLayout(sl_CheckHistoryPanel_2);
		
		JScrollPane ChkHistoryscrollPane = new JScrollPane();
		sl_CheckHistoryPanel_2.putConstraint(SpringLayout.NORTH, ChkHistoryscrollPane, 10, SpringLayout.NORTH, CheckHistoryPanel_2);
		sl_CheckHistoryPanel_2.putConstraint(SpringLayout.WEST, ChkHistoryscrollPane, 10, SpringLayout.WEST, CheckHistoryPanel_2);
		sl_CheckHistoryPanel_2.putConstraint(SpringLayout.SOUTH, ChkHistoryscrollPane, 391, SpringLayout.NORTH, CheckHistoryPanel_2);
		sl_CheckHistoryPanel_2.putConstraint(SpringLayout.EAST, ChkHistoryscrollPane, 340, SpringLayout.WEST, CheckHistoryPanel_2);
		CheckHistoryPanel_2.add(ChkHistoryscrollPane);
		
		JButton ChkHistorybtnBack = new JButton("Back");
		sl_CheckHistoryPanel_2.putConstraint(SpringLayout.NORTH, ChkHistorybtnBack, 6, SpringLayout.SOUTH, ChkHistoryscrollPane);
		sl_CheckHistoryPanel_2.putConstraint(SpringLayout.WEST, ChkHistorybtnBack, 0, SpringLayout.WEST, CheckHistoryPanel_2);
		CheckHistoryPanel_2.add(ChkHistorybtnBack);
		
		JButton ChkHistorybtnPrint = new JButton("Print");
		sl_CheckHistoryPanel_2.putConstraint(SpringLayout.NORTH, ChkHistorybtnPrint, 6, SpringLayout.SOUTH, ChkHistoryscrollPane);
		CheckHistoryPanel_2.add(ChkHistorybtnPrint);
		
		JButton ChkHistorybtnSave = new JButton("Save");
		sl_CheckHistoryPanel_2.putConstraint(SpringLayout.EAST, ChkHistorybtnPrint, -5, SpringLayout.WEST, ChkHistorybtnSave);
		ChkHistorybtnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		sl_CheckHistoryPanel_2.putConstraint(SpringLayout.NORTH, ChkHistorybtnSave, 6, SpringLayout.SOUTH, ChkHistoryscrollPane);
		sl_CheckHistoryPanel_2.putConstraint(SpringLayout.EAST, ChkHistorybtnSave, 0, SpringLayout.EAST, ChkHistoryscrollPane);
		CheckHistoryPanel_2.add(ChkHistorybtnSave);
		/*
		 * End of check history panel
		 * 
		 * Action listner for buttons begins
		 */
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Username = Usernametxt.getText().toUpperCase();
				String Password = pwdPassword.getText();
				Statement st;
				try {
					st = (Statement) con.createStatement();
					ResultSet rs = st.executeQuery("Select * from Cafeteria. Parents;");
					while (rs.next()){
						if(rs.getString(1).equals(Username) && rs.getString(2).equals(Password)) {
							OrderPanel.setVisible(true);
							frame1.setSize(621,487);
							LoginPanel.setVisible(false);
							PullInfo(lblparentnameLabel,BalanceAmount,con,Username);
							break;
						}
					}
					} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
								
			}

			private void reopen(Connection con) {
				con = getRemoteConnection();
			}
			
		});
		ChkHistorybtnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CheckHistoryPanel.setVisible(false);
				frame1.setSize(621,487);
				OrderPanel.setVisible(true);
				
			}
		});
		OrderbtnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderPanel.setVisible(false);
				frame1.setSize(629, 300);
				ConfirmPanel.setVisible(true);
			}
		});
		ConfirmOrderMoreBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConfirmPanel.setVisible(false);
				frame1.setSize(621,487);
				OrderPanel.setVisible(true);
			}
		});
		ConfirmandLogoutbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConfirmPanel.setVisible(false);
				frame1.setSize(621,487);
				LoginPanel.setVisible(true);
			}
		});
		OrderLogoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usernametxt.setText("Username");
				pwdPassword.setText("Password");
				OrderPanel.setVisible(false);
				frame1.setSize(628,505);
				LoginPanel.setVisible(true);	
			}
		});
	}
	public void PullInfo(JLabel ParentName,JLabel BalanceAmount, Connection con ,String Username) throws SQLException {
		Statement str = (Statement) con.createStatement();
		ResultSet res = str.executeQuery("Select `First Name`,`Last Name`,`Balance` from `Cafeteria`.`Parents`"
				+ "where `Username` = '"+ Username + "';");
		while(res.next()){
		String FirstName = res.getString(1);
		String LastName = res.getString(2);
		String Balance =  res.getString(3);
		ParentName.setText(FirstName +" " + LastName);
		BalanceAmount.setText(Balance);
		
		}
	}
}