import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JEditorPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpringLayout;

public class Login {

	private JFrame frmElvelsCafeteria;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmElvelsCafeteria.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmElvelsCafeteria = new JFrame();
		frmElvelsCafeteria.setTitle("Elvel's Cafeteria");
		frmElvelsCafeteria.setBounds(100, 100, 477, 310);
		frmElvelsCafeteria.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmElvelsCafeteria.getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("Username:");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password:");
		
		passwordField = new JPasswordField();
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Remember me");
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					frmElvelsCafeteria.dispose();
					OrderPage orderPage = new OrderPage();
					orderPage.setVisible(true);
	
			}
		});
		SpringLayout sl_panel = new SpringLayout();
		sl_panel.putConstraint(SpringLayout.NORTH, passwordField, 156, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, passwordField, -102, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.NORTH, btnNewButton, 4, SpringLayout.SOUTH, passwordField);
		sl_panel.putConstraint(SpringLayout.WEST, btnNewButton, 255, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.NORTH, chckbxNewCheckBox, 1, SpringLayout.NORTH, btnNewButton);
		sl_panel.putConstraint(SpringLayout.WEST, chckbxNewCheckBox, 128, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, chckbxNewCheckBox, -6, SpringLayout.WEST, btnNewButton);
		sl_panel.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 167, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, textField, -6, SpringLayout.NORTH, passwordField);
		sl_panel.putConstraint(SpringLayout.NORTH, lblNewLabel, 129, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, lblNewLabel, -22, SpringLayout.NORTH, lblNewLabel_1);
		sl_panel.putConstraint(SpringLayout.WEST, passwordField, 146, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, passwordField, -155, SpringLayout.EAST, panel);
		sl_panel.putConstraint(SpringLayout.WEST, textField, 146, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, textField, -155, SpringLayout.EAST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, lblNewLabel_1, 0, SpringLayout.EAST, lblNewLabel);
		sl_panel.putConstraint(SpringLayout.EAST, lblNewLabel, -23, SpringLayout.WEST, textField);
		panel.setLayout(sl_panel);
		panel.add(chckbxNewCheckBox);
		panel.add(btnNewButton);
		panel.add(lblNewLabel);
		panel.add(lblNewLabel_1);
		panel.add(textField);
		panel.add(passwordField);
		
		JLabel lblSchoolLogo = new JLabel("School Logo");
		sl_panel.putConstraint(SpringLayout.SOUTH, lblSchoolLogo, -46, SpringLayout.NORTH, textField);
		sl_panel.putConstraint(SpringLayout.EAST, lblSchoolLogo, -206, SpringLayout.EAST, panel);
		panel.add(lblSchoolLogo);
		
		JLabel lblForgotPassword = new JLabel("Forgot Password?");
		sl_panel.putConstraint(SpringLayout.NORTH, lblForgotPassword, 161, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, lblForgotPassword, 3, SpringLayout.EAST, passwordField);
		sl_panel.putConstraint(SpringLayout.SOUTH, lblForgotPassword, -112, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, lblForgotPassword, -43, SpringLayout.EAST, panel);
		panel.add(lblForgotPassword);
	}
}
