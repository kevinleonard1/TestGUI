import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.stream.IntStream;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

public class App {

	private JFrame frmSimpleOopUi;
	private JTextField textField;
	private JButton b1;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frmSimpleOopUi.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSimpleOopUi = new JFrame();
		frmSimpleOopUi.setTitle("Simple OOP UI");
		frmSimpleOopUi.setBounds(100, 100, 655, 416);
		frmSimpleOopUi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		b1 = new JButton("Click here!");
		
		textField = new JTextField();
		textField.setColumns(20);
		
		int[] date = IntStream.range(1, 31).toArray();
		String[] a=Arrays.toString(date).split("[\\[\\]]")[1].split(", "); 
		String[] month = {"January", "February", "March", "April", "May"};
		
		comboBox = new JComboBox(month);
		
		comboBox_1 = new JComboBox(a);
		
		lblNewLabel = new JLabel("");
		GroupLayout groupLayout = new GroupLayout(frmSimpleOopUi.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(b1)))
					.addContainerGap(286, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(38)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(b1))
					.addGap(58)
					.addComponent(lblNewLabel)
					.addContainerGap(206, Short.MAX_VALUE))
		);
		frmSimpleOopUi.getContentPane().setLayout(groupLayout);
		
		JMenuBar menuBar = new JMenuBar();
		frmSimpleOopUi.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Database");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmAddItems = new JMenuItem("Add items");
		mnNewMenu.add(mntmAddItems);
		
		JMenuItem mntmRemoveItems = new JMenuItem("Remove items");
		mnNewMenu.add(mntmRemoveItems);
		
		JMenuItem mntmViewItems = new JMenuItem("Refresh Database");
		mnNewMenu.add(mntmViewItems);
		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblNewLabel.setText(textField.getText() + " born in " + comboBox.getSelectedItem() + " " + comboBox_1.getSelectedItem());
			}	
		});
	}
}
