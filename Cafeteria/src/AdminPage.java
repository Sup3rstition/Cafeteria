import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JSeparator;

public class AdminPage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPage frame = new AdminPage();
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
	public AdminPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 440, 309);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblHello = new JLabel("Welcome,");
		panel.add(lblHello);
		
		JLabel lbladministratorName = new JLabel("(Administrator Name)");
		panel.add(lbladministratorName);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		SpringLayout sl_panel_1 = new SpringLayout();
		panel_1.setLayout(sl_panel_1);
		
		JButton btnMenuEditor = new JButton("Menu Editor");
		sl_panel_1.putConstraint(SpringLayout.NORTH, btnMenuEditor, 10, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, btnMenuEditor, 10, SpringLayout.WEST, panel_1);
		panel_1.add(btnMenuEditor);
		
		JButton btnGetReports = new JButton("View Reports");
		sl_panel_1.putConstraint(SpringLayout.NORTH, btnGetReports, 5, SpringLayout.SOUTH, btnMenuEditor);
		sl_panel_1.putConstraint(SpringLayout.WEST, btnGetReports, 0, SpringLayout.WEST, btnMenuEditor);
		panel_1.add(btnGetReports);
		
		JButton btnNewButton = new JButton("Get Cafeteria Order");
		sl_panel_1.putConstraint(SpringLayout.NORTH, btnNewButton, 7, SpringLayout.SOUTH, btnGetReports);
		sl_panel_1.putConstraint(SpringLayout.WEST, btnNewButton, 0, SpringLayout.WEST, btnMenuEditor);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Get Delievery Order");
		sl_panel_1.putConstraint(SpringLayout.NORTH, btnNewButton_1, 6, SpringLayout.SOUTH, btnNewButton);
		sl_panel_1.putConstraint(SpringLayout.EAST, btnNewButton_1, 0, SpringLayout.EAST, btnNewButton);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Edit Parent's Balance");
		sl_panel_1.putConstraint(SpringLayout.NORTH, btnNewButton_2, 6, SpringLayout.SOUTH, btnNewButton_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, btnNewButton_2, 0, SpringLayout.WEST, btnMenuEditor);
		panel_1.add(btnNewButton_2);
		
		JButton btnLogOut = new JButton("Log Out");
		sl_panel_1.putConstraint(SpringLayout.NORTH, btnLogOut, 6, SpringLayout.SOUTH, btnNewButton_2);
		sl_panel_1.putConstraint(SpringLayout.WEST, btnLogOut, 0, SpringLayout.WEST, btnMenuEditor);
		panel_1.add(btnLogOut);
	}
}
