import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;

public class FundsEditor extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FundsEditor frame = new FundsEditor();
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
	public FundsEditor() {
		setTitle("Fund Editor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 332, 289);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		JLabel lblParentFundEditor = new JLabel("Parent Fund Editor");
		lblParentFundEditor.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		sl_panel.putConstraint(SpringLayout.NORTH, lblParentFundEditor, 10, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, lblParentFundEditor, 10, SpringLayout.WEST, panel);
		panel.add(lblParentFundEditor);
		
		JLabel lblParentsUsername = new JLabel("Parent's Username:");
		sl_panel.putConstraint(SpringLayout.NORTH, lblParentsUsername, 41, SpringLayout.SOUTH, lblParentFundEditor);
		sl_panel.putConstraint(SpringLayout.WEST, lblParentsUsername, 0, SpringLayout.WEST, lblParentFundEditor);
		panel.add(lblParentsUsername);
		
		textField = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, textField, -5, SpringLayout.NORTH, lblParentsUsername);
		sl_panel.putConstraint(SpringLayout.WEST, textField, 6, SpringLayout.EAST, lblParentsUsername);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblAmountToBe = new JLabel("Amount to Add/Subtract:");
		sl_panel.putConstraint(SpringLayout.EAST, lblAmountToBe, 0, SpringLayout.EAST, lblParentFundEditor);
		panel.add(lblAmountToBe);
		
		textField_1 = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, textField_1, -5, SpringLayout.NORTH, lblAmountToBe);
		sl_panel.putConstraint(SpringLayout.WEST, textField_1, 6, SpringLayout.EAST, lblAmountToBe);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblCurrentBalance = new JLabel("Current Balance:");
		sl_panel.putConstraint(SpringLayout.NORTH, lblCurrentBalance, 16, SpringLayout.SOUTH, lblParentsUsername);
		sl_panel.putConstraint(SpringLayout.WEST, lblCurrentBalance, 0, SpringLayout.WEST, lblParentFundEditor);
		panel.add(lblCurrentBalance);
		
		textField_2 = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, lblAmountToBe, 16, SpringLayout.SOUTH, textField_2);
		sl_panel.putConstraint(SpringLayout.NORTH, textField_2, 6, SpringLayout.SOUTH, textField);
		sl_panel.putConstraint(SpringLayout.WEST, textField_2, 0, SpringLayout.WEST, textField);
		textField_2.setEditable(false);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		sl_panel.putConstraint(SpringLayout.SOUTH, btnSubmit, -10, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, btnSubmit, -10, SpringLayout.EAST, panel);
		panel.add(btnSubmit);
		
		JButton btnBack = new JButton("Back");
		sl_panel.putConstraint(SpringLayout.NORTH, btnBack, 0, SpringLayout.NORTH, btnSubmit);
		sl_panel.putConstraint(SpringLayout.WEST, btnBack, 0, SpringLayout.WEST, lblParentFundEditor);
		panel.add(btnBack);
		
		JRadioButton rdbtnConfirmThisIs = new JRadioButton("Confirm this is the correct amount");
		sl_panel.putConstraint(SpringLayout.NORTH, rdbtnConfirmThisIs, 15, SpringLayout.SOUTH, textField_1);
		sl_panel.putConstraint(SpringLayout.WEST, rdbtnConfirmThisIs, 0, SpringLayout.WEST, lblParentFundEditor);
		panel.add(rdbtnConfirmThisIs);
	}
}
