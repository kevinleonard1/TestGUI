import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class addVendorFrame extends JFrame implements ActionListener {
	
	private JPanel panel;
	private JLabel nameLabel;
	private JLabel locationLabel;
	private JTextField nameField;
	private JTextField locationField;
	private JButton addBtn;
	private FormListener formListener;
	
	public addVendorFrame() {
		super("Add Vendor");
		setSize(500,200);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setLayout(new BorderLayout());
		
		///////////Add Panel///////////
		panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		
		nameLabel = new JLabel("Vendor Name: ");
		locationLabel = new JLabel("Location: ");
		nameField = new JTextField(15);
		locationField = new JTextField(15);
		addBtn = new JButton("Add");
		
		addBtn.addActionListener(this);
		
		panel.setLayout(new GridBagLayout());
		
		Border innerBorder = BorderFactory.createTitledBorder("Add Vendor");
		Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
		panel.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.gridy = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		panel.add(nameLabel, gc);
		
		gc.gridx = 1;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		panel.add(nameField, gc);
		
		gc.gridx = 0;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		panel.add(locationLabel, gc);
		
		gc.gridx = 1;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		panel.add(locationField, gc);
		
		gc.weightx = 1;
		gc.weighty = 2;
		
		gc.gridx = 1;
		gc.gridy = 2;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		panel.add(addBtn, gc);
	}
	
	public void setFormListener(FormListener listener) {
		formListener = listener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String name = nameField.getText();
		String occupation = locationField.getText();
		
		FormEvent ev = new FormEvent(this, name, occupation);
		
		if (formListener != null) {
			formListener.formEventOccured(ev);
		}
		if (formListener == null) {
			System.out.println("Whoops its null");
		}
		dispose();
	}
}
