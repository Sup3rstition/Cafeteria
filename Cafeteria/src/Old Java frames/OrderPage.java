import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.border.EtchedBorder;
import javax.swing.AbstractListModel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.table.TableModel;
import javax.swing.JScrollBar;
import javax.swing.JSpinner;
import javax.swing.SpringLayout;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OrderPage extends JFrame {

	private JPanel panel_1;
	private JTextField TotalTxtOrder;
	private JTextField BalanceRemainingTxt;
	private final ButtonGroup OrderButtonGroup = new ButtonGroup();
	private String[] column_headers = {"Extras","#"};
	private String[][] extras = {
	{"Extra","1"},{"Extra","2"},{"Extra","3"},{"Extra","4"},{"Extra","5"},{"Extra","6"},{"Extra","7"},{"Extra","8"},{"Extra","9"},{"Extra","10"},
	{"Extra","11"},{"Extra","12"},{"Extra","13"},{"Extra","14"},{"Extra","15"},{"Extra","16"},{"Extra","17"},{"Extra","18"},{"Extra","19"},{"Extra","20"},
	{"Extra","21"},{"Extra","22"},{"Extra","23"},{"Extra","24"},{"Extra","25"},{"Extra","26"},{"Extra","27"},{"Extra","28"},{"Extra","29"},{"Extra","30"},
	{"Extra","31"},{"Extra","32"},{"Extra","33"},{"Extra","34"},{"Extra","35"},{"Extra","36"},{"Extra","37"},{"Extra","38"},{"Extra","39"},{"Extra","40"}};
	private JTable table;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderPage frame = new OrderPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OrderPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 621,487);
		panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JLabel AccountLabel = new JLabel("Account:");
		
		JLabel BalanceLabel = new JLabel("Balance:");
		
		JLabel lblCurrentDateLabel = new JLabel("Current Date:");
		
		JLabel CurrentDateLabel = new JLabel("??/??/????");
		
		JLabel lblparentnameLabel = new JLabel("(ParentName)");
		
		JLabel BalanceAmount = new JLabel("??.??");
		
		JButton CheckHistoryBtn = new JButton("Check History");
		CheckHistoryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		
		JButton OrderLogoutBtn = new JButton("Log Out");
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(CheckHistoryBtn)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(OrderLogoutBtn))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(AccountLabel)
								.addComponent(BalanceLabel)
								.addComponent(lblCurrentDateLabel))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
									.addComponent(BalanceAmount)
									.addComponent(CurrentDateLabel))
								.addComponent(lblparentnameLabel))))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblparentnameLabel)
						.addComponent(AccountLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(BalanceLabel)
						.addComponent(BalanceAmount))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCurrentDateLabel)
						.addComponent(CurrentDateLabel))
					.addGap(14)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(CheckHistoryBtn)
						.addComponent(OrderLogoutBtn))
					.addGap(17))
		);
		panel_2.setLayout(gl_panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JLayeredPane layeredPane = new JLayeredPane();
		
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
		
		JPanel panel_4 = new JPanel();
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(44)
							.addComponent(lblStudentsOrderLabel))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(1)
							.addComponent(OrderList, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_3, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(panel_2, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 268, GroupLayout.PREFERRED_SIZE)
						.addComponent(layeredPane, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblOrderProblemsContactUsLabel, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
					.addGap(12))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblStudentsOrderLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(OrderList, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
							.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 118, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblOrderProblemsContactUsLabel)))
					.addGap(12))
				.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
					.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		SpringLayout sl_panel_5 = new SpringLayout();
		panel_5.setLayout(sl_panel_5);
		
		JLabel lblMenu_1Label = new JLabel("Menu 1:");
		panel_5.add(lblMenu_1Label);
		
		JLabel lblMenu_3Label = new JLabel("Menu 2:");
		sl_panel_5.putConstraint(SpringLayout.WEST, lblMenu_1Label, 0, SpringLayout.WEST, lblMenu_3Label);
		sl_panel_5.putConstraint(SpringLayout.SOUTH, lblMenu_1Label, -6, SpringLayout.NORTH, lblMenu_3Label);
		sl_panel_5.putConstraint(SpringLayout.NORTH, lblMenu_3Label, 73, SpringLayout.NORTH, panel_5);
		sl_panel_5.putConstraint(SpringLayout.WEST, lblMenu_3Label, 10, SpringLayout.WEST, panel_5);
		panel_5.add(lblMenu_3Label);
		
		JLabel lblMenu_4Label = new JLabel("Menu 3:");
		sl_panel_5.putConstraint(SpringLayout.WEST, lblMenu_4Label, 10, SpringLayout.WEST, panel_5);
		sl_panel_5.putConstraint(SpringLayout.NORTH, lblMenu_4Label, 6, SpringLayout.SOUTH, lblMenu_3Label);
		panel_5.add(lblMenu_4Label);
		
		JLabel lblAddLabel = new JLabel("Add 1:");
		sl_panel_5.putConstraint(SpringLayout.NORTH, lblAddLabel, 6, SpringLayout.SOUTH, lblMenu_4Label);
		sl_panel_5.putConstraint(SpringLayout.WEST, lblAddLabel, 10, SpringLayout.WEST, panel_5);
		panel_5.add(lblAddLabel);
		
		JLabel lblAdd_1Label = new JLabel("Add 2:");
		sl_panel_5.putConstraint(SpringLayout.NORTH, lblAdd_1Label, 6, SpringLayout.SOUTH, lblAddLabel);
		sl_panel_5.putConstraint(SpringLayout.WEST, lblAdd_1Label, 10, SpringLayout.WEST, panel_5);
		panel_5.add(lblAdd_1Label);
		
		JLabel lblAdd_2Label = new JLabel("Add 3:");
		sl_panel_5.putConstraint(SpringLayout.NORTH, lblAdd_2Label, 6, SpringLayout.SOUTH, lblAdd_1Label);
		sl_panel_5.putConstraint(SpringLayout.WEST, lblAdd_2Label, 10, SpringLayout.WEST, panel_5);
		panel_5.add(lblAdd_2Label);
		
		JLabel lblMenuBoxLabel = new JLabel("Menu");
		sl_panel_5.putConstraint(SpringLayout.NORTH, lblMenu_1Label, 25, SpringLayout.SOUTH, lblMenuBoxLabel);
		sl_panel_5.putConstraint(SpringLayout.NORTH, lblMenuBoxLabel, 10, SpringLayout.NORTH, panel_5);
		sl_panel_5.putConstraint(SpringLayout.WEST, lblMenuBoxLabel, 43, SpringLayout.WEST, panel_5);
		sl_panel_5.putConstraint(SpringLayout.SOUTH, lblMenuBoxLabel, -198, SpringLayout.SOUTH, panel_5);
		sl_panel_5.putConstraint(SpringLayout.EAST, lblMenuBoxLabel, 93, SpringLayout.WEST, panel_5);
		lblMenuBoxLabel.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		panel_5.add(lblMenuBoxLabel);
		
		JLabel lblMenu_2WeekLabel = new JLabel("??/??/????");
		sl_panel_5.putConstraint(SpringLayout.SOUTH, lblMenu_2WeekLabel, -6, SpringLayout.NORTH, lblMenu_1Label);
		sl_panel_5.putConstraint(SpringLayout.EAST, lblMenu_2WeekLabel, 0, SpringLayout.EAST, lblMenuBoxLabel);
		panel_5.add(lblMenu_2WeekLabel);
		
		JLabel Menu_1ItemLabel = new JLabel("New label");
		sl_panel_5.putConstraint(SpringLayout.NORTH, Menu_1ItemLabel, 0, SpringLayout.NORTH, lblMenu_1Label);
		sl_panel_5.putConstraint(SpringLayout.WEST, Menu_1ItemLabel, 6, SpringLayout.EAST, lblMenu_1Label);
		panel_5.add(Menu_1ItemLabel);
		
		JLabel Menu_2ItemLabel = new JLabel("New label");
		sl_panel_5.putConstraint(SpringLayout.WEST, Menu_2ItemLabel, 6, SpringLayout.EAST, lblMenu_3Label);
		sl_panel_5.putConstraint(SpringLayout.SOUTH, Menu_2ItemLabel, 0, SpringLayout.SOUTH, lblMenu_3Label);
		panel_5.add(Menu_2ItemLabel);
		
		JLabel Menu_3ItemLabel = new JLabel("New label");
		sl_panel_5.putConstraint(SpringLayout.WEST, Menu_3ItemLabel, 6, SpringLayout.EAST, lblMenu_4Label);
		sl_panel_5.putConstraint(SpringLayout.SOUTH, Menu_3ItemLabel, 0, SpringLayout.SOUTH, lblMenu_4Label);
		panel_5.add(Menu_3ItemLabel);
		
		JLabel Add_1ItemLabel = new JLabel("New label");
		sl_panel_5.putConstraint(SpringLayout.NORTH, Add_1ItemLabel, 6, SpringLayout.SOUTH, lblMenu_4Label);
		sl_panel_5.putConstraint(SpringLayout.WEST, Add_1ItemLabel, 6, SpringLayout.EAST, lblAddLabel);
		panel_5.add(Add_1ItemLabel);
		
		JLabel Add_2itemLabel = new JLabel("New label");
		sl_panel_5.putConstraint(SpringLayout.NORTH, Add_2itemLabel, 6, SpringLayout.SOUTH, lblAddLabel);
		sl_panel_5.putConstraint(SpringLayout.WEST, Add_2itemLabel, 6, SpringLayout.EAST, lblAdd_1Label);
		panel_5.add(Add_2itemLabel);
		
		JLabel Add_3itemLabel = new JLabel("New label");
		sl_panel_5.putConstraint(SpringLayout.NORTH, Add_3itemLabel, 6, SpringLayout.SOUTH, lblAdd_1Label);
		sl_panel_5.putConstraint(SpringLayout.WEST, Add_3itemLabel, 6, SpringLayout.EAST, lblAdd_2Label);
		panel_5.add(Add_3itemLabel);
		
		JLabel lblOrderExtrasLabel = new JLabel("Extras");
		DefaultTableModel model = new DefaultTableModel(extras,column_headers) {
			@Override
	        public boolean isCellEditable(int row, int column)
	        {
	         return false;
	        }
		};
		table = new JTable(model);
		JScrollPane OrderscrollPane = new JScrollPane(table);
		
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addContainerGap()
							.addComponent(OrderscrollPane, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_4.createSequentialGroup()
							.addGap(66)
							.addComponent(lblOrderExtrasLabel)))
					.addContainerGap(8, Short.MAX_VALUE))
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addComponent(lblOrderExtrasLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(OrderscrollPane, GroupLayout.PREFERRED_SIZE, 424, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_4.setLayout(gl_panel_4);
		
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
		SpringLayout sl_panel_3 = new SpringLayout();
		sl_panel_3.putConstraint(SpringLayout.NORTH, OrderSpinner_2, -1, SpringLayout.NORTH, rdbtnMenu_3);
		sl_panel_3.putConstraint(SpringLayout.EAST, OrderSpinner_2, 0, SpringLayout.EAST, OrderSpinner_1);
		sl_panel_3.putConstraint(SpringLayout.WEST, OrderSpinner, 171, SpringLayout.WEST, panel_3);
		sl_panel_3.putConstraint(SpringLayout.WEST, OrderSpinner_1, 171, SpringLayout.WEST, panel_3);
		sl_panel_3.putConstraint(SpringLayout.SOUTH, OrderSpinner, -3, SpringLayout.NORTH, OrderSpinner_1);
		sl_panel_3.putConstraint(SpringLayout.WEST, lblOrderAdd2Label, 31, SpringLayout.EAST, rdbtnMenu_2);
		sl_panel_3.putConstraint(SpringLayout.EAST, lblOrderAdd2Label, -6, SpringLayout.WEST, OrderSpinner_1);
		sl_panel_3.putConstraint(SpringLayout.NORTH, OrderSpinner_1, -1, SpringLayout.NORTH, rdbtnMenu_2);
		sl_panel_3.putConstraint(SpringLayout.WEST, lblOrderAdd1Label, 31, SpringLayout.EAST, rdbtnMenu_3);
		sl_panel_3.putConstraint(SpringLayout.NORTH, lblOrderAdd2Label, 4, SpringLayout.NORTH, rdbtnMenu_2);
		sl_panel_3.putConstraint(SpringLayout.NORTH, lblOrderAdd1Label, 4, SpringLayout.NORTH, rdbtnMenu_3);
		sl_panel_3.putConstraint(SpringLayout.NORTH, DayComboBox, -4, SpringLayout.NORTH, lblDayLabel);
		sl_panel_3.putConstraint(SpringLayout.WEST, DayComboBox, 91, SpringLayout.WEST, panel_3);
		sl_panel_3.putConstraint(SpringLayout.EAST, DayComboBox, 0, SpringLayout.EAST, lblAdditionalItemsLabel);
		sl_panel_3.putConstraint(SpringLayout.WEST, lblDayLabel, 0, SpringLayout.WEST, OrderMenuLabel);
		sl_panel_3.putConstraint(SpringLayout.EAST, lblDayLabel, 72, SpringLayout.WEST, OrderMenuLabel);
		sl_panel_3.putConstraint(SpringLayout.WEST, rdbtnMenu_2, 8, SpringLayout.WEST, panel_3);
		sl_panel_3.putConstraint(SpringLayout.WEST, rdbtnMenu_3, 8, SpringLayout.WEST, panel_3);
		sl_panel_3.putConstraint(SpringLayout.WEST, Menu1Radio, 8, SpringLayout.WEST, panel_3);
		sl_panel_3.putConstraint(SpringLayout.EAST, lblOrderAdd3Label, -13, SpringLayout.WEST, OrderSpinner);
		sl_panel_3.putConstraint(SpringLayout.NORTH, lblOrderAdd3Label, 4, SpringLayout.NORTH, Menu1Radio);
		sl_panel_3.putConstraint(SpringLayout.SOUTH, rdbtnMenu_2, -6, SpringLayout.NORTH, rdbtnMenu_3);
		sl_panel_3.putConstraint(SpringLayout.SOUTH, rdbtnMenu_3, -10, SpringLayout.SOUTH, panel_3);
		sl_panel_3.putConstraint(SpringLayout.NORTH, Menu1Radio, 6, SpringLayout.SOUTH, OrderMenuLabel);
		sl_panel_3.putConstraint(SpringLayout.WEST, lblAdditionalItemsLabel, 103, SpringLayout.WEST, panel_3);
		sl_panel_3.putConstraint(SpringLayout.WEST, OrderMenuLabel, 8, SpringLayout.WEST, panel_3);
		sl_panel_3.putConstraint(SpringLayout.EAST, OrderMenuLabel, -17, SpringLayout.WEST, lblAdditionalItemsLabel);
		sl_panel_3.putConstraint(SpringLayout.EAST, lblAdditionalItemsLabel, -36, SpringLayout.EAST, panel_3);
		sl_panel_3.putConstraint(SpringLayout.NORTH, OrderMenuLabel, 0, SpringLayout.NORTH, lblAdditionalItemsLabel);
		sl_panel_3.putConstraint(SpringLayout.SOUTH, lblAdditionalItemsLabel, -6, SpringLayout.NORTH, OrderSpinner);
		panel_3.setLayout(sl_panel_3);
		panel_3.add(lblDayLabel);
		panel_3.add(DayComboBox);
		panel_3.add(OrderMenuLabel);
		panel_3.add(Menu1Radio);
		panel_3.add(rdbtnMenu_3);
		panel_3.add(rdbtnMenu_2);
		panel_3.add(lblOrderAdd1Label);
		panel_3.add(OrderSpinner_2);
		panel_3.add(lblOrderAdd2Label);
		panel_3.add(OrderSpinner_1);
		panel_3.add(lblAdditionalItemsLabel);
		panel_3.add(lblOrderAdd3Label);
		panel_3.add(OrderSpinner);
		
		JLabel lblWeekLabel = new JLabel("Week:");
		sl_panel_3.putConstraint(SpringLayout.WEST, lblWeekLabel, 8, SpringLayout.WEST, panel_3);
		sl_panel_3.putConstraint(SpringLayout.NORTH, lblDayLabel, 6, SpringLayout.SOUTH, lblWeekLabel);
		sl_panel_3.putConstraint(SpringLayout.NORTH, lblWeekLabel, 10, SpringLayout.NORTH, panel_3);
		panel_3.add(lblWeekLabel);
		
		JLabel WeekLabel = new JLabel("??/??/??-??/??/??");
		sl_panel_3.putConstraint(SpringLayout.NORTH, WeekLabel, 0, SpringLayout.NORTH, lblWeekLabel);
		sl_panel_3.putConstraint(SpringLayout.EAST, WeekLabel, -60, SpringLayout.EAST, panel_3);
		panel_3.add(WeekLabel);
		
		JLabel StudentLabel = new JLabel("Student:");
		sl_panel_3.putConstraint(SpringLayout.NORTH, StudentLabel, 8, SpringLayout.SOUTH, lblDayLabel);
		sl_panel_3.putConstraint(SpringLayout.WEST, StudentLabel, 0, SpringLayout.WEST, lblDayLabel);
		panel_3.add(StudentLabel);
		
		JLabel StudentNameLabel = new JLabel("First Last");
		sl_panel_3.putConstraint(SpringLayout.NORTH, StudentNameLabel, 0, SpringLayout.NORTH, StudentLabel);
		sl_panel_3.putConstraint(SpringLayout.WEST, StudentNameLabel, 57, SpringLayout.EAST, StudentLabel);
		panel_3.add(StudentNameLabel);
		panel_1.setLayout(gl_panel_1);
	}
}
