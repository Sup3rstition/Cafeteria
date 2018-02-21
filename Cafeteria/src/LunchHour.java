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
import java.awt.FlowLayout;

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
	private JTextField txtUsernmewebsitecom;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	 */
	public LunchHour() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame1 = new JFrame();
		frame1.setTitle("Login \n");
		frame1.setResizable(false);
		frame1.setBounds(100, 100, 628,505);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.getContentPane().setLayout(new CardLayout(0, 0));
		
		/*
		 * Login Panel Begins
		 */
		JPanel LoginPanel = new JPanel();
		frame1.getContentPane().add(LoginPanel, "name_272179477667435");
		LoginPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel LoginPanel_1 = new JPanel();
		LoginPanel_1.setBackground(new Color(102, 102, 102));
		LoginPanel.add(LoginPanel_1, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 204, 0));
		
		JLabel lblUsernameemail = new JLabel("Username/Email:");
		
		txtUsernmewebsitecom = new JTextField();
		txtUsernmewebsitecom.setText("Username@website.com");
		txtUsernmewebsitecom.setBackground(new Color(128, 128, 128));
		txtUsernmewebsitecom.setColumns(10);
		
		JLabel lblPassowrd = new JLabel("Password:");
		
		passwordField = new JPasswordField();
		
		JCheckBox chckbxRememberMe = new JCheckBox("Remember Me");
		
		JButton btnLogin = new JButton("Login");
		
		JLabel lblForogtPassord = new JLabel("Forgot Password?");
		GroupLayout gl_LoginPanel_1 = new GroupLayout(LoginPanel_1);
		gl_LoginPanel_1.setHorizontalGroup(
			gl_LoginPanel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_LoginPanel_1.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 303, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_LoginPanel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_LoginPanel_1.createSequentialGroup()
							.addComponent(chckbxRememberMe)
							.addGap(18)
							.addComponent(lblForogtPassord, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_LoginPanel_1.createSequentialGroup()
							.addGroup(gl_LoginPanel_1.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblPassowrd)
								.addComponent(lblUsernameemail))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_LoginPanel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(txtUsernmewebsitecom, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
								.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)))
						.addComponent(btnLogin))
					.addContainerGap())
		);
		gl_LoginPanel_1.setVerticalGroup(
			gl_LoginPanel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_LoginPanel_1.createSequentialGroup()
					.addGroup(gl_LoginPanel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 496, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_LoginPanel_1.createSequentialGroup()
							.addGap(191)
							.addGroup(gl_LoginPanel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblUsernameemail)
								.addComponent(txtUsernmewebsitecom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_LoginPanel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPassowrd)
								.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_LoginPanel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(chckbxRememberMe)
								.addComponent(lblForogtPassord))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnLogin)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		JLabel lblSchoolLogo = new JLabel("School Logo");
		sl_panel.putConstraint(SpringLayout.NORTH, lblSchoolLogo, 231, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, lblSchoolLogo, 106, SpringLayout.WEST, panel);
		panel.add(lblSchoolLogo);
		LoginPanel_1.setLayout(gl_LoginPanel_1);
		
				/*Login Panel Ends
				 * 
				 * 
				 * OrderPanel Begins
				 */
		JPanel OrderPanel = new JPanel();
		frame1.getContentPane().add(OrderPanel, "name_272189214436444");
		
		JPanel ParentAccountPanel = new JPanel();
		ParentAccountPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JLabel AccountLabel = new JLabel("Account:");
		
		JLabel BalanceLabel = new JLabel("Balance:");
		
		JLabel lblCurrentDateLabel = new JLabel("Current Date:");
		
		JLabel CurrentDateLabel = new JLabel("??/??/????");
		
		JLabel lblparentnameLabel = new JLabel("(ParentName)");
		
		JLabel BalanceAmount = new JLabel("??.??");
		
		JButton CheckHistoryBtn = new JButton("Check History");
		
		JButton OrderLogoutBtn = new JButton("Log Out");
		
		GroupLayout gl_ParentAccountPanel = new GroupLayout(ParentAccountPanel);
		gl_ParentAccountPanel.setHorizontalGroup(
			gl_ParentAccountPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ParentAccountPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_ParentAccountPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_ParentAccountPanel.createSequentialGroup()
							.addComponent(CheckHistoryBtn)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(OrderLogoutBtn))
						.addGroup(gl_ParentAccountPanel.createSequentialGroup()
							.addGroup(gl_ParentAccountPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(AccountLabel)
								.addComponent(BalanceLabel)
								.addComponent(lblCurrentDateLabel))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_ParentAccountPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_ParentAccountPanel.createParallelGroup(Alignment.LEADING)
									.addComponent(BalanceAmount)
									.addComponent(CurrentDateLabel))
								.addComponent(lblparentnameLabel))))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		gl_ParentAccountPanel.setVerticalGroup(
			gl_ParentAccountPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ParentAccountPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_ParentAccountPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblparentnameLabel)
						.addComponent(AccountLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_ParentAccountPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(BalanceLabel)
						.addComponent(BalanceAmount))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_ParentAccountPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCurrentDateLabel)
						.addComponent(CurrentDateLabel))
					.addGap(14)
					.addGroup(gl_ParentAccountPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(CheckHistoryBtn)
						.addComponent(OrderLogoutBtn))
					.addGap(17))
		);
		ParentAccountPanel.setLayout(gl_ParentAccountPanel);
		
		JPanel Menu_Panel = new JPanel();
		Menu_Panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JLabel TotalOrder = new JLabel("Total:");
		
		TotalTxtOrder = new JTextField();
		TotalTxtOrder.setText("$0.00");
		TotalTxtOrder.setColumns(10);
		
		JLabel OrderBalanceRemaingLabel = new JLabel("Balance remaining:");
		
		BalanceRemainingTxt = new JTextField();
		BalanceRemainingTxt.setText("$0.00");
		BalanceRemainingTxt.setColumns(10);
		
		JButton OrderbtnOrder = new JButton("Order");
		
		JButton OrderBtnClear = new JButton("Clear");
		GroupLayout gl_layeredPane = new GroupLayout(layeredPane);
		gl_layeredPane.setHorizontalGroup(
			gl_layeredPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_layeredPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_layeredPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_layeredPane.createSequentialGroup()
							.addGroup(gl_layeredPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_layeredPane.createSequentialGroup()
									.addComponent(TotalOrder)
									.addGap(18)
									.addComponent(TotalTxtOrder, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_layeredPane.createSequentialGroup()
									.addComponent(OrderBalanceRemaingLabel)
									.addGap(18)
									.addComponent(BalanceRemainingTxt, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)))
							.addGap(5))
						.addGroup(gl_layeredPane.createSequentialGroup()
							.addComponent(OrderBtnClear)
							.addPreferredGap(ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
							.addComponent(OrderbtnOrder)
							.addContainerGap())))
		);
		gl_layeredPane.setVerticalGroup(
			gl_layeredPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_layeredPane.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_layeredPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(TotalTxtOrder, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(TotalOrder))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_layeredPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(OrderBalanceRemaingLabel)
						.addComponent(BalanceRemainingTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_layeredPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(OrderbtnOrder)
						.addComponent(OrderBtnClear))
					.addGap(37))
		);
		layeredPane.setLayout(gl_layeredPane);
		
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
		
		JPanel ExtraPanel = new JPanel();
		
		JPanel PickItemPanel = new JPanel();
		PickItemPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		
		GroupLayout gl_OrderPanel = new GroupLayout(OrderPanel);
		gl_OrderPanel.setHorizontalGroup(
			gl_OrderPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_OrderPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_OrderPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_OrderPanel.createSequentialGroup()
							.addGap(44)
							.addComponent(lblStudentsOrderLabel))
						.addGroup(gl_OrderPanel.createSequentialGroup()
							.addGap(1)
							.addComponent(OrderList, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE))
						.addComponent(PickItemPanel, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_OrderPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(Menu_Panel, 0, 0, Short.MAX_VALUE)
						.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblOrderProblemsContactUsLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
						.addComponent(ParentAccountPanel, GroupLayout.PREFERRED_SIZE, 268, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ExtraPanel, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
					.addGap(12))
		);
		gl_OrderPanel.setVerticalGroup(
			gl_OrderPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_OrderPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_OrderPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_OrderPanel.createSequentialGroup()
							.addComponent(ExtraPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_OrderPanel.createSequentialGroup()
							.addGroup(gl_OrderPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(Alignment.LEADING, gl_OrderPanel.createSequentialGroup()
									.addComponent(lblStudentsOrderLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(OrderList, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(PickItemPanel, GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
									.addGap(9))
								.addGroup(gl_OrderPanel.createSequentialGroup()
									.addComponent(ParentAccountPanel, GroupLayout.PREFERRED_SIZE, 118, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(Menu_Panel, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblOrderProblemsContactUsLabel)))
							.addGap(12))))
		);
		SpringLayout sl_PickItemPanel = new SpringLayout();
		PickItemPanel.setLayout(sl_PickItemPanel);
		
		JLabel lblMenu_1Label = new JLabel("Menu 1:");
		PickItemPanel.add(lblMenu_1Label);
		
		JLabel lblMenu_3Label = new JLabel("Menu 2:");
		sl_PickItemPanel.putConstraint(SpringLayout.WEST, lblMenu_1Label, 0, SpringLayout.WEST, lblMenu_3Label);
		sl_PickItemPanel.putConstraint(SpringLayout.SOUTH, lblMenu_1Label, -6, SpringLayout.NORTH, lblMenu_3Label);
		sl_PickItemPanel.putConstraint(SpringLayout.NORTH, lblMenu_3Label, 73, SpringLayout.NORTH, PickItemPanel);
		sl_PickItemPanel.putConstraint(SpringLayout.WEST, lblMenu_3Label, 10, SpringLayout.WEST, PickItemPanel);
		PickItemPanel.add(lblMenu_3Label);
		
		JLabel lblMenu_4Label = new JLabel("Menu 3:");
		sl_PickItemPanel.putConstraint(SpringLayout.WEST, lblMenu_4Label, 10, SpringLayout.WEST, PickItemPanel);
		sl_PickItemPanel.putConstraint(SpringLayout.NORTH, lblMenu_4Label, 6, SpringLayout.SOUTH, lblMenu_3Label);
		PickItemPanel.add(lblMenu_4Label);
		
		JLabel lblAddLabel = new JLabel("Add 1:");
		sl_PickItemPanel.putConstraint(SpringLayout.NORTH, lblAddLabel, 6, SpringLayout.SOUTH, lblMenu_4Label);
		sl_PickItemPanel.putConstraint(SpringLayout.WEST, lblAddLabel, 10, SpringLayout.WEST, PickItemPanel);
		PickItemPanel.add(lblAddLabel);
		
		JLabel lblAdd_1Label = new JLabel("Add 2:");
		sl_PickItemPanel.putConstraint(SpringLayout.NORTH, lblAdd_1Label, 6, SpringLayout.SOUTH, lblAddLabel);
		sl_PickItemPanel.putConstraint(SpringLayout.WEST, lblAdd_1Label, 10, SpringLayout.WEST, PickItemPanel);
		PickItemPanel.add(lblAdd_1Label);
		
		JLabel lblAdd_2Label = new JLabel("Add 3:");
		sl_PickItemPanel.putConstraint(SpringLayout.NORTH, lblAdd_2Label, 6, SpringLayout.SOUTH, lblAdd_1Label);
		sl_PickItemPanel.putConstraint(SpringLayout.WEST, lblAdd_2Label, 10, SpringLayout.WEST, PickItemPanel);
		PickItemPanel.add(lblAdd_2Label);
		
		JLabel lblMenuBoxLabel = new JLabel("Menu");
		sl_PickItemPanel.putConstraint(SpringLayout.NORTH, lblMenu_1Label, 25, SpringLayout.SOUTH, lblMenuBoxLabel);
		sl_PickItemPanel.putConstraint(SpringLayout.NORTH, lblMenuBoxLabel, 10, SpringLayout.NORTH, PickItemPanel);
		sl_PickItemPanel.putConstraint(SpringLayout.WEST, lblMenuBoxLabel, 43, SpringLayout.WEST, PickItemPanel);
		sl_PickItemPanel.putConstraint(SpringLayout.SOUTH, lblMenuBoxLabel, -198, SpringLayout.SOUTH, PickItemPanel);
		sl_PickItemPanel.putConstraint(SpringLayout.EAST, lblMenuBoxLabel, 93, SpringLayout.WEST, PickItemPanel);
		lblMenuBoxLabel.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		PickItemPanel.add(lblMenuBoxLabel);
		
		JLabel lblMenu_2WeekLabel = new JLabel("??/??/????");
		sl_PickItemPanel.putConstraint(SpringLayout.SOUTH, lblMenu_2WeekLabel, -6, SpringLayout.NORTH, lblMenu_1Label);
		sl_PickItemPanel.putConstraint(SpringLayout.EAST, lblMenu_2WeekLabel, 0, SpringLayout.EAST, lblMenuBoxLabel);
		PickItemPanel.add(lblMenu_2WeekLabel);
		
		JLabel Menu_1ItemLabel = new JLabel("New label");
		sl_PickItemPanel.putConstraint(SpringLayout.NORTH, Menu_1ItemLabel, 0, SpringLayout.NORTH, lblMenu_1Label);
		sl_PickItemPanel.putConstraint(SpringLayout.WEST, Menu_1ItemLabel, 6, SpringLayout.EAST, lblMenu_1Label);
		PickItemPanel.add(Menu_1ItemLabel);
		
		JLabel Menu_2ItemLabel = new JLabel("New label");
		sl_PickItemPanel.putConstraint(SpringLayout.WEST, Menu_2ItemLabel, 6, SpringLayout.EAST, lblMenu_3Label);
		sl_PickItemPanel.putConstraint(SpringLayout.SOUTH, Menu_2ItemLabel, 0, SpringLayout.SOUTH, lblMenu_3Label);
		PickItemPanel.add(Menu_2ItemLabel);
		
		JLabel Menu_3ItemLabel = new JLabel("New label");
		sl_PickItemPanel.putConstraint(SpringLayout.WEST, Menu_3ItemLabel, 6, SpringLayout.EAST, lblMenu_4Label);
		sl_PickItemPanel.putConstraint(SpringLayout.SOUTH, Menu_3ItemLabel, 0, SpringLayout.SOUTH, lblMenu_4Label);
		PickItemPanel.add(Menu_3ItemLabel);
		
		JLabel Add_1ItemLabel = new JLabel("New label");
		sl_PickItemPanel.putConstraint(SpringLayout.NORTH, Add_1ItemLabel, 6, SpringLayout.SOUTH, lblMenu_4Label);
		sl_PickItemPanel.putConstraint(SpringLayout.WEST, Add_1ItemLabel, 6, SpringLayout.EAST, lblAddLabel);
		PickItemPanel.add(Add_1ItemLabel);
		
		JLabel Add_2itemLabel = new JLabel("New label");
		sl_PickItemPanel.putConstraint(SpringLayout.NORTH, Add_2itemLabel, 6, SpringLayout.SOUTH, lblAddLabel);
		sl_PickItemPanel.putConstraint(SpringLayout.WEST, Add_2itemLabel, 6, SpringLayout.EAST, lblAdd_1Label);
		PickItemPanel.add(Add_2itemLabel);
		
		JLabel Add_3itemLabel = new JLabel("New label");
		sl_PickItemPanel.putConstraint(SpringLayout.NORTH, Add_3itemLabel, 6, SpringLayout.SOUTH, lblAdd_1Label);
		sl_PickItemPanel.putConstraint(SpringLayout.WEST, Add_3itemLabel, 6, SpringLayout.EAST, lblAdd_2Label);
		PickItemPanel.add(Add_3itemLabel);
		
		JLabel lblOrderExtrasLabel = new JLabel("Extras");
		DefaultTableModel Order_model = new DefaultTableModel(Order_extras,Order_column_headers); 
		table = new JTable(Order_model);
		JScrollPane OrderscrollPane = new JScrollPane(table);
		
		GroupLayout gl_ExtraPanel = new GroupLayout(ExtraPanel);
		gl_ExtraPanel.setHorizontalGroup(
			gl_ExtraPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ExtraPanel.createSequentialGroup()
					.addGroup(gl_ExtraPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_ExtraPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(OrderscrollPane, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_ExtraPanel.createSequentialGroup()
							.addGap(66)
							.addComponent(lblOrderExtrasLabel)))
					.addContainerGap(8, Short.MAX_VALUE))
		);
		gl_ExtraPanel.setVerticalGroup(
			gl_ExtraPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ExtraPanel.createSequentialGroup()
					.addComponent(lblOrderExtrasLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(OrderscrollPane, GroupLayout.PREFERRED_SIZE, 424, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		ExtraPanel.setLayout(gl_ExtraPanel);
		
		JLabel lblDayLabel = new JLabel("Day:");
		
		JComboBox DayComboBox = new JComboBox();
		DayComboBox.setModel(new DefaultComboBoxModel(new String[] {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"}));
		
		JLabel OrderMenuLabel = new JLabel("Menu");
		
		JLabel lblAdditionalItemsLabel = new JLabel("Additional Items:");
		
		JRadioButton Menu1Radio = new JRadioButton("Menu 1");
		OrderButtonGroup.add(Menu1Radio);
		
		JRadioButton rdbtnMenu_2 = new JRadioButton("Menu 2");
		OrderButtonGroup.add(rdbtnMenu_2);
		
		JRadioButton rdbtnMenu_3 = new JRadioButton("Menu 3");
		OrderButtonGroup.add(rdbtnMenu_3);
		
		JLabel lblOrderAdd3Label = new JLabel("Add 1:");
		
		JLabel lblOrderAdd2Label = new JLabel("Add 2:");
		
		JLabel lblOrderAdd1Label = new JLabel("Add 3:");
		
		JSpinner OrderSpinner = new JSpinner();
		
		JSpinner OrderSpinner_1 = new JSpinner();
		
		JSpinner OrderSpinner_2 = new JSpinner();
		SpringLayout sl_Menu_Panel = new SpringLayout();
		sl_Menu_Panel.putConstraint(SpringLayout.NORTH, OrderSpinner_2, -1, SpringLayout.NORTH, rdbtnMenu_3);
		sl_Menu_Panel.putConstraint(SpringLayout.EAST, OrderSpinner_2, 0, SpringLayout.EAST, OrderSpinner_1);
		sl_Menu_Panel.putConstraint(SpringLayout.WEST, OrderSpinner, 171, SpringLayout.WEST, Menu_Panel);
		sl_Menu_Panel.putConstraint(SpringLayout.WEST, OrderSpinner_1, 171, SpringLayout.WEST, Menu_Panel);
		sl_Menu_Panel.putConstraint(SpringLayout.SOUTH, OrderSpinner, -3, SpringLayout.NORTH, OrderSpinner_1);
		sl_Menu_Panel.putConstraint(SpringLayout.WEST, lblOrderAdd2Label, 31, SpringLayout.EAST, rdbtnMenu_2);
		sl_Menu_Panel.putConstraint(SpringLayout.EAST, lblOrderAdd2Label, -6, SpringLayout.WEST, OrderSpinner_1);
		sl_Menu_Panel.putConstraint(SpringLayout.NORTH, OrderSpinner_1, -1, SpringLayout.NORTH, rdbtnMenu_2);
		sl_Menu_Panel.putConstraint(SpringLayout.WEST, lblOrderAdd1Label, 31, SpringLayout.EAST, rdbtnMenu_3);
		sl_Menu_Panel.putConstraint(SpringLayout.NORTH, lblOrderAdd2Label, 4, SpringLayout.NORTH, rdbtnMenu_2);
		sl_Menu_Panel.putConstraint(SpringLayout.NORTH, lblOrderAdd1Label, 4, SpringLayout.NORTH, rdbtnMenu_3);
		sl_Menu_Panel.putConstraint(SpringLayout.NORTH, DayComboBox, -4, SpringLayout.NORTH, lblDayLabel);
		sl_Menu_Panel.putConstraint(SpringLayout.WEST, DayComboBox, 91, SpringLayout.WEST, Menu_Panel);
		sl_Menu_Panel.putConstraint(SpringLayout.EAST, DayComboBox, 0, SpringLayout.EAST, lblAdditionalItemsLabel);
		sl_Menu_Panel.putConstraint(SpringLayout.WEST, lblDayLabel, 0, SpringLayout.WEST, OrderMenuLabel);
		sl_Menu_Panel.putConstraint(SpringLayout.EAST, lblDayLabel, 72, SpringLayout.WEST, OrderMenuLabel);
		sl_Menu_Panel.putConstraint(SpringLayout.WEST, rdbtnMenu_2, 8, SpringLayout.WEST, Menu_Panel);
		sl_Menu_Panel.putConstraint(SpringLayout.WEST, rdbtnMenu_3, 8, SpringLayout.WEST, Menu_Panel);
		sl_Menu_Panel.putConstraint(SpringLayout.WEST, Menu1Radio, 8, SpringLayout.WEST, Menu_Panel);
		sl_Menu_Panel.putConstraint(SpringLayout.EAST, lblOrderAdd3Label, -13, SpringLayout.WEST, OrderSpinner);
		sl_Menu_Panel.putConstraint(SpringLayout.NORTH, lblOrderAdd3Label, 4, SpringLayout.NORTH, Menu1Radio);
		sl_Menu_Panel.putConstraint(SpringLayout.SOUTH, rdbtnMenu_2, -6, SpringLayout.NORTH, rdbtnMenu_3);
		sl_Menu_Panel.putConstraint(SpringLayout.SOUTH, rdbtnMenu_3, -10, SpringLayout.SOUTH, Menu_Panel);
		sl_Menu_Panel.putConstraint(SpringLayout.NORTH, Menu1Radio, 6, SpringLayout.SOUTH, OrderMenuLabel);
		sl_Menu_Panel.putConstraint(SpringLayout.WEST, lblAdditionalItemsLabel, 103, SpringLayout.WEST, Menu_Panel);
		sl_Menu_Panel.putConstraint(SpringLayout.WEST, OrderMenuLabel, 8, SpringLayout.WEST, Menu_Panel);
		sl_Menu_Panel.putConstraint(SpringLayout.EAST, OrderMenuLabel, -17, SpringLayout.WEST, lblAdditionalItemsLabel);
		sl_Menu_Panel.putConstraint(SpringLayout.EAST, lblAdditionalItemsLabel, -36, SpringLayout.EAST, Menu_Panel);
		sl_Menu_Panel.putConstraint(SpringLayout.NORTH, OrderMenuLabel, 0, SpringLayout.NORTH, lblAdditionalItemsLabel);
		sl_Menu_Panel.putConstraint(SpringLayout.SOUTH, lblAdditionalItemsLabel, -6, SpringLayout.NORTH, OrderSpinner);
		Menu_Panel.setLayout(sl_Menu_Panel);
		Menu_Panel.add(lblDayLabel);
		Menu_Panel.add(DayComboBox);
		Menu_Panel.add(OrderMenuLabel);
		Menu_Panel.add(Menu1Radio);
		Menu_Panel.add(rdbtnMenu_3);
		Menu_Panel.add(rdbtnMenu_2);
		Menu_Panel.add(lblOrderAdd1Label);
		Menu_Panel.add(OrderSpinner_2);
		Menu_Panel.add(lblOrderAdd2Label);
		Menu_Panel.add(OrderSpinner_1);
		Menu_Panel.add(lblAdditionalItemsLabel);
		Menu_Panel.add(lblOrderAdd3Label);
		Menu_Panel.add(OrderSpinner);
		
		JLabel lblWeekLabel = new JLabel("Week:");
		sl_Menu_Panel.putConstraint(SpringLayout.WEST, lblWeekLabel, 8, SpringLayout.WEST, Menu_Panel);
		sl_Menu_Panel.putConstraint(SpringLayout.NORTH, lblDayLabel, 6, SpringLayout.SOUTH, lblWeekLabel);
		sl_Menu_Panel.putConstraint(SpringLayout.NORTH, lblWeekLabel, 10, SpringLayout.NORTH, Menu_Panel);
		Menu_Panel.add(lblWeekLabel);
		
		JLabel WeekLabel = new JLabel("??/??/??-??/??/??");
		sl_Menu_Panel.putConstraint(SpringLayout.NORTH, WeekLabel, 0, SpringLayout.NORTH, lblWeekLabel);
		sl_Menu_Panel.putConstraint(SpringLayout.EAST, WeekLabel, -60, SpringLayout.EAST, Menu_Panel);
		Menu_Panel.add(WeekLabel);
		
		JLabel StudentLabel = new JLabel("Student:");
		sl_Menu_Panel.putConstraint(SpringLayout.NORTH, StudentLabel, 8, SpringLayout.SOUTH, lblDayLabel);
		sl_Menu_Panel.putConstraint(SpringLayout.WEST, StudentLabel, 0, SpringLayout.WEST, lblDayLabel);
		Menu_Panel.add(StudentLabel);
		
		JLabel StudentNameLabel = new JLabel("First Last");
		sl_Menu_Panel.putConstraint(SpringLayout.NORTH, StudentNameLabel, 0, SpringLayout.NORTH, StudentLabel);
		sl_Menu_Panel.putConstraint(SpringLayout.WEST, StudentNameLabel, 57, SpringLayout.EAST, StudentLabel);
		Menu_Panel.add(StudentNameLabel);
		OrderPanel.setLayout(gl_OrderPanel);
		
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
				OrderPanel.setVisible(true);
				frame1.setSize(621,487);
				LoginPanel.setVisible(false);	
			}
		});
		OrderLogoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderPanel.setVisible(false);
				frame1.setSize(628,505);
				LoginPanel.setVisible(true);	
			}
		});
		ChkHistorybtnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CheckHistoryPanel.setVisible(false);
				frame1.setSize(621,487);
				OrderPanel.setVisible(true);
				
			}
		});
		CheckHistoryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderPanel.setVisible(false);
				frame1.setSize(360, 493);
				CheckHistoryPanel.setVisible(true);
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
	}
}