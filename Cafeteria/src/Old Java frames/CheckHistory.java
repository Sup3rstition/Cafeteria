import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.SpringLayout;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.Font;

public class CheckHistory extends JFrame {

	private JPanel CheckHistoryPanel;
	private JTextField ChkHistorytextField;
	private JTextField ChkHistorytextField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckHistory frame = new CheckHistory();
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
	public CheckHistory() {
		setTitle("Check History");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 360, 493);
		CheckHistoryPanel = new JPanel();
		CheckHistoryPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		CheckHistoryPanel.setLayout(new BorderLayout(0, 0));
		setContentPane(CheckHistoryPanel);
		
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
	}
}
