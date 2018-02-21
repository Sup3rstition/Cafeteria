import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class ConfirmOrder extends JFrame {

	private JPanel ConfirmPanel;
	private JTable Confirmtable;
	private String[] column_headers = {"Student" ,"Date","Menu Item","Addtitional item","extras", "Student Total"};
	private String[][] Order = {{"Bryan Cordes","??/??/????", "Menu 1", "Add 1 and Add 2", "3 Extras", "$999.99"}};
	private JTextField ConfirmBalanceTxtfield;
	private JTextField ConfirmOrderTotalTxtField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfirmOrder frame = new ConfirmOrder();
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
	public ConfirmOrder() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 629, 300);
		ConfirmPanel = new JPanel();
		ConfirmPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		ConfirmPanel.setLayout(new BorderLayout(0, 0));
		setContentPane(ConfirmPanel);
		
		JPanel Confirmpanel_1 = new JPanel();
		ConfirmPanel.add(Confirmpanel_1, BorderLayout.CENTER);
		SpringLayout sl_Confirmpanel_1 = new SpringLayout();
		Confirmpanel_1.setLayout(sl_Confirmpanel_1);
		
		JLabel lblOrderConfirmation = new JLabel("Order Confirmation");
		lblOrderConfirmation.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		sl_Confirmpanel_1.putConstraint(SpringLayout.NORTH, lblOrderConfirmation, 10, SpringLayout.NORTH, Confirmpanel_1);
		sl_Confirmpanel_1.putConstraint(SpringLayout.WEST, lblOrderConfirmation, 10, SpringLayout.WEST, Confirmpanel_1);
		Confirmpanel_1.add(lblOrderConfirmation);
		
		DefaultTableModel model = new DefaultTableModel(Order,column_headers);
		Confirmtable = new JTable(model);
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
			}
		});
		
		JButton ConfirmOrderMoreBtn = new JButton("Confirm and Order More");
		ConfirmOrderMoreBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
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
	}
}
