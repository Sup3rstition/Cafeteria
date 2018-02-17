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

public class OrderPage extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private String[] column_headers = {"Extras","#"};
	private String[][] extras = {
	{"Chips","0"},{"Soda","0"},{"Extra","3"},{"Extra","4"},{"Extra","5"},{"Extra","6"},{"Extra","7"},{"Extra","8"},{"Extra","9"},{"Extra","10"},
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
		setBounds(100, 100, 626,487);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JLabel label = new JLabel("Account:");
		
		JLabel label_1 = new JLabel("Balance:");
		
		JLabel lblCurrentDate = new JLabel("Current Date:");
		
		JLabel label_3 = new JLabel("1/1/2000");
		
		JLabel label_4 = new JLabel("AccountName");
		
		JLabel label_5 = new JLabel("$0.00");
		
		JButton button = new JButton("Check History");
		
		JButton button_1 = new JButton("Log Out");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(button)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button_1))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(label)
								.addComponent(label_1)
								.addComponent(lblCurrentDate))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addComponent(label_5)
									.addComponent(label_3))
								.addComponent(label_4))))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_4)
						.addComponent(label))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(label_5))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCurrentDate)
						.addComponent(label_3))
					.addGap(14)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(button)
						.addComponent(button_1))
					.addGap(17))
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JLayeredPane layeredPane = new JLayeredPane();
		
		JLabel label_14 = new JLabel("Total:");
		
		textField = new JTextField();
		textField.setText("$0.00");
		textField.setColumns(10);
		
		JLabel label_15 = new JLabel("Balance remaining:");
		
		textField_1 = new JTextField();
		textField_1.setText("$0.00");
		textField_1.setColumns(10);
		
		JButton button_2 = new JButton("Order");
		GroupLayout gl_layeredPane = new GroupLayout(layeredPane);
		gl_layeredPane.setHorizontalGroup(
			gl_layeredPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_layeredPane.createSequentialGroup()
					.addContainerGap(15, Short.MAX_VALUE)
					.addGroup(gl_layeredPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_layeredPane.createSequentialGroup()
							.addGroup(gl_layeredPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_layeredPane.createSequentialGroup()
									.addComponent(label_14)
									.addGap(18)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_layeredPane.createSequentialGroup()
									.addComponent(label_15)
									.addGap(18)
									.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)))
							.addGap(5))
						.addGroup(gl_layeredPane.createSequentialGroup()
							.addComponent(button_2)
							.addContainerGap())))
		);
		gl_layeredPane.setVerticalGroup(
			gl_layeredPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_layeredPane.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_layeredPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_14))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_layeredPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_15)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button_2)
					.addGap(37))
		);
		layeredPane.setLayout(gl_layeredPane);
		
		JLabel lblProblemsContactUs = new JLabel("Problems? Contact us at _");
		
		JList list = new JList();
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Cordes,Bryan", "Cordes,Bryan", "Cordes,Bryan", "Cordes,Bryan", "Cordes,Bryan", "Cordes,Bryan", "Cordes,Bryan", "Cordes,Bryan", "Cordes,Bryan", ""};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		
		JLabel lblStudents = new JLabel("Students");
		
		JPanel panel_2 = new JPanel();
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(44)
							.addComponent(lblStudents))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(1)
							.addComponent(list, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_1, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 268, GroupLayout.PREFERRED_SIZE)
						.addComponent(layeredPane, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblProblemsContactUs, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
					.addGap(12))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblStudents)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(list, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
							.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 118, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblProblemsContactUs)))
					.addGap(12))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		SpringLayout sl_panel_3 = new SpringLayout();
		panel_3.setLayout(sl_panel_3);
		
		JLabel lblMenu_1 = new JLabel("Menu 1:");
		sl_panel_3.putConstraint(SpringLayout.NORTH, lblMenu_1, 35, SpringLayout.NORTH, panel_3);
		panel_3.add(lblMenu_1);
		
		JLabel label_16 = new JLabel("Menu 1:");
		sl_panel_3.putConstraint(SpringLayout.NORTH, label_16, 57, SpringLayout.NORTH, panel_3);
		sl_panel_3.putConstraint(SpringLayout.WEST, label_16, 10, SpringLayout.WEST, panel_3);
		sl_panel_3.putConstraint(SpringLayout.WEST, lblMenu_1, 0, SpringLayout.WEST, label_16);
		sl_panel_3.putConstraint(SpringLayout.SOUTH, lblMenu_1, -6, SpringLayout.NORTH, label_16);
		panel_3.add(label_16);
		
		JLabel label_20 = new JLabel("Menu 1:");
		sl_panel_3.putConstraint(SpringLayout.NORTH, label_20, 6, SpringLayout.SOUTH, label_16);
		sl_panel_3.putConstraint(SpringLayout.WEST, label_20, 10, SpringLayout.WEST, panel_3);
		panel_3.add(label_20);
		
		JLabel lblAdd = new JLabel("Add 1:");
		sl_panel_3.putConstraint(SpringLayout.NORTH, lblAdd, 6, SpringLayout.SOUTH, label_20);
		sl_panel_3.putConstraint(SpringLayout.WEST, lblAdd, 10, SpringLayout.WEST, panel_3);
		panel_3.add(lblAdd);
		
		JLabel label_21 = new JLabel("Add 1:");
		sl_panel_3.putConstraint(SpringLayout.NORTH, label_21, 6, SpringLayout.SOUTH, lblAdd);
		sl_panel_3.putConstraint(SpringLayout.WEST, label_21, 10, SpringLayout.WEST, panel_3);
		panel_3.add(label_21);
		
		JLabel label_22 = new JLabel("Add 1:");
		sl_panel_3.putConstraint(SpringLayout.NORTH, label_22, 6, SpringLayout.SOUTH, label_21);
		sl_panel_3.putConstraint(SpringLayout.WEST, label_22, 10, SpringLayout.WEST, panel_3);
		panel_3.add(label_22);
		
		JLabel lblMenu_2 = new JLabel("Menu");
		sl_panel_3.putConstraint(SpringLayout.NORTH, lblMenu_2, 0, SpringLayout.NORTH, panel_3);
		sl_panel_3.putConstraint(SpringLayout.WEST, lblMenu_2, 39, SpringLayout.WEST, panel_3);
		sl_panel_3.putConstraint(SpringLayout.SOUTH, lblMenu_2, -6, SpringLayout.NORTH, lblMenu_1);
		sl_panel_3.putConstraint(SpringLayout.EAST, lblMenu_2, 89, SpringLayout.WEST, panel_3);
		lblMenu_2.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		panel_3.add(lblMenu_2);
		
		JLabel lblExtras = new JLabel("Extras");
		DefaultTableModel model = new DefaultTableModel(extras,column_headers);
		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(66)
							.addComponent(lblExtras)))
					.addContainerGap(8, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addComponent(lblExtras)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 424, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		JLabel label_17 = new JLabel("Order Date:");
		
		JComboBox comboBox = new JComboBox();
		
		JLabel lblMenu = new JLabel("Menu");
		
		JLabel lblAdditionalItems = new JLabel("Additional Items:");
		
		JRadioButton rdbtnMenu = new JRadioButton("Menu 1");
		buttonGroup.add(rdbtnMenu);
		
		JRadioButton rdbtnMenu_1 = new JRadioButton("Menu 2");
		buttonGroup.add(rdbtnMenu_1);
		
		JRadioButton rdbtnMenu_2 = new JRadioButton("Menu 3");
		buttonGroup.add(rdbtnMenu_2);
		
		JLabel label_2 = new JLabel("");
		
		JLabel label_7 = new JLabel("");
		
		JLabel label_8 = new JLabel("");
		
		JLabel label_9 = new JLabel("");
		
		JLabel label_10 = new JLabel("");
		
		JLabel label_11 = new JLabel("");
		
		JLabel label_12 = new JLabel("");
		
		JLabel label_13 = new JLabel("");
		
		JLabel label_18 = new JLabel("");
		
		JLabel label_19 = new JLabel("");
		
		JLabel lblExta = new JLabel("Add 1:");
		
		JLabel lblExtra = new JLabel("Add 2:");
		
		JLabel lblExtra_1 = new JLabel("Add 3:");
		
		JSpinner spinner = new JSpinner();
		
		JSpinner spinner_1 = new JSpinner();
		
		JSpinner spinner_2 = new JSpinner();
		SpringLayout sl_panel_1 = new SpringLayout();
		sl_panel_1.putConstraint(SpringLayout.NORTH, comboBox, -4, SpringLayout.NORTH, label_17);
		sl_panel_1.putConstraint(SpringLayout.WEST, comboBox, 91, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, comboBox, 0, SpringLayout.EAST, lblAdditionalItems);
		sl_panel_1.putConstraint(SpringLayout.WEST, label_17, 0, SpringLayout.WEST, lblMenu);
		sl_panel_1.putConstraint(SpringLayout.EAST, label_17, 72, SpringLayout.WEST, lblMenu);
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblExtra_1, 4, SpringLayout.NORTH, rdbtnMenu_2);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblExtra_1, 0, SpringLayout.WEST, lblExtra);
		sl_panel_1.putConstraint(SpringLayout.WEST, rdbtnMenu_1, 8, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, rdbtnMenu_2, 8, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, rdbtnMenu, 8, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, lblExta, -13, SpringLayout.WEST, spinner);
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblExtra, 17, SpringLayout.SOUTH, lblExta);
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblExta, 4, SpringLayout.NORTH, rdbtnMenu);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, rdbtnMenu_1, -6, SpringLayout.NORTH, rdbtnMenu_2);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblExtra, 117, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, lblExtra, -6, SpringLayout.WEST, spinner_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, rdbtnMenu_2, -10, SpringLayout.SOUTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.NORTH, rdbtnMenu, 6, SpringLayout.SOUTH, lblMenu);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblAdditionalItems, 103, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblMenu, 8, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, lblMenu, -17, SpringLayout.WEST, lblAdditionalItems);
		sl_panel_1.putConstraint(SpringLayout.EAST, lblAdditionalItems, -36, SpringLayout.EAST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblMenu, 0, SpringLayout.NORTH, lblAdditionalItems);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, lblAdditionalItems, -6, SpringLayout.NORTH, spinner);
		sl_panel_1.putConstraint(SpringLayout.WEST, spinner, 0, SpringLayout.WEST, spinner_2);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, spinner, -6, SpringLayout.NORTH, spinner_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, spinner_2, 0, SpringLayout.EAST, spinner_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, spinner_2, -3, SpringLayout.SOUTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, spinner_1, -34, SpringLayout.SOUTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, spinner_1, 171, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.NORTH, label_19, 264, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, label_19, 393, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, label_19, 285, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, label_19, 518, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.NORTH, label_18, 264, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, label_18, 264, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, label_18, 285, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, label_18, 389, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.NORTH, label_13, 238, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, label_13, 393, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, label_13, 261, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, label_13, 518, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.NORTH, label_12, 238, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, label_12, 264, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, label_12, 261, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, label_12, 389, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.NORTH, label_11, 212, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, label_11, 393, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, label_11, 235, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, label_11, 518, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.NORTH, label_10, 212, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, label_10, 264, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, label_10, 235, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, label_10, 389, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.NORTH, label_9, 186, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, label_9, 393, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, label_9, 209, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, label_9, 518, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.NORTH, label_8, 186, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, label_8, 264, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, label_8, 209, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, label_8, 389, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.NORTH, label_7, 159, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, label_7, 393, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, label_7, 180, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, label_7, 518, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.NORTH, label_2, 159, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, label_2, 264, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, label_2, 180, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, label_2, 389, SpringLayout.WEST, panel_1);
		panel_1.setLayout(sl_panel_1);
		panel_1.add(label_2);
		panel_1.add(label_7);
		panel_1.add(label_8);
		panel_1.add(label_9);
		panel_1.add(label_10);
		panel_1.add(label_11);
		panel_1.add(label_12);
		panel_1.add(label_13);
		panel_1.add(label_18);
		panel_1.add(label_19);
		panel_1.add(label_17);
		panel_1.add(comboBox);
		panel_1.add(lblMenu);
		panel_1.add(rdbtnMenu);
		panel_1.add(rdbtnMenu_2);
		panel_1.add(rdbtnMenu_1);
		panel_1.add(lblExtra_1);
		panel_1.add(spinner_2);
		panel_1.add(lblExtra);
		panel_1.add(spinner_1);
		panel_1.add(lblAdditionalItems);
		panel_1.add(lblExta);
		panel_1.add(spinner);
		
		JLabel lblWeek = new JLabel("Week:");
		sl_panel_1.putConstraint(SpringLayout.WEST, lblWeek, 8, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.NORTH, label_17, 6, SpringLayout.SOUTH, lblWeek);
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblWeek, 10, SpringLayout.NORTH, panel_1);
		panel_1.add(lblWeek);
		
		JLabel label_6 = new JLabel("??/??/??-??/??/??");
		sl_panel_1.putConstraint(SpringLayout.NORTH, label_6, 0, SpringLayout.NORTH, lblWeek);
		sl_panel_1.putConstraint(SpringLayout.EAST, label_6, 0, SpringLayout.EAST, spinner_2);
		panel_1.add(label_6);
		
		JLabel label_23 = new JLabel("Student:");
		sl_panel_1.putConstraint(SpringLayout.NORTH, label_23, 8, SpringLayout.SOUTH, label_17);
		sl_panel_1.putConstraint(SpringLayout.WEST, label_23, 0, SpringLayout.WEST, label_17);
		panel_1.add(label_23);
		
		JLabel label_24 = new JLabel("First Last");
		sl_panel_1.putConstraint(SpringLayout.NORTH, label_24, 0, SpringLayout.NORTH, label_23);
		sl_panel_1.putConstraint(SpringLayout.WEST, label_24, 0, SpringLayout.WEST, lblExtra_1);
		panel_1.add(label_24);
		contentPane.setLayout(gl_contentPane);
	}
}
