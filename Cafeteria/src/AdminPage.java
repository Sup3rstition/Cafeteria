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
		setBounds(100, 100, 162, 258);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		JButton btnNewButton = new JButton("Menu Editor");
		sl_panel.putConstraint(SpringLayout.NORTH, btnNewButton, 10, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, btnNewButton, 148, SpringLayout.WEST, panel);
		panel.add(btnNewButton);
		
		JButton btnGetReports = new JButton("Get Order");
		sl_panel.putConstraint(SpringLayout.NORTH, btnGetReports, 66, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, btnGetReports, 10, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, btnGetReports, -4, SpringLayout.EAST, panel);
		sl_panel.putConstraint(SpringLayout.WEST, btnNewButton, 0, SpringLayout.WEST, btnGetReports);
		sl_panel.putConstraint(SpringLayout.SOUTH, btnNewButton, -6, SpringLayout.NORTH, btnGetReports);
		panel.add(btnGetReports);
		
		JButton btnGetDelievery = new JButton("Get Report");
		sl_panel.putConstraint(SpringLayout.NORTH, btnGetDelievery, 114, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, btnGetDelievery, 10, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, btnGetDelievery, -4, SpringLayout.EAST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, btnGetReports, -6, SpringLayout.NORTH, btnGetDelievery);
		sl_panel.putConstraint(SpringLayout.SOUTH, btnGetDelievery, 160, SpringLayout.NORTH, panel);
		panel.add(btnGetDelievery);
		
		JButton btnGetdelivery = new JButton("Get delivery");
		sl_panel.putConstraint(SpringLayout.NORTH, btnGetdelivery, 6, SpringLayout.SOUTH, btnGetDelievery);
		sl_panel.putConstraint(SpringLayout.WEST, btnGetdelivery, 10, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, btnGetdelivery, 50, SpringLayout.SOUTH, btnGetDelievery);
		sl_panel.putConstraint(SpringLayout.EAST, btnGetdelivery, -4, SpringLayout.EAST, panel);
		panel.add(btnGetdelivery);
	}

}
