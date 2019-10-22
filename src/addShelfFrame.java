import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class addShelfFrame extends JFrame implements ActionListener{
	
	private JPanel panel;
	private JLabel heightLabel;
	private JLabel widthLabel;
	private JTextField heightField;
	private JTextField widthField;
	private JButton addBtn;
	private FormListener formListener;
	
	public addShelfFrame() {
		super("Add Shelf");
		setSize(400,200);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setLayout(new BorderLayout());
		
		///////////Add Panel///////////
		panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		
		heightLabel = new JLabel("Shelf Height(m): ");
		widthLabel = new JLabel("Shelf Width(m): ");
		heightField = new JTextField(10);
		widthField = new JTextField(10);
		addBtn = new JButton("Add");
		
		addBtn.addActionListener(this);
		
		panel.setLayout(new GridBagLayout());
		
		Border innerBorder = BorderFactory.createTitledBorder("Add Shelf");
		Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
		panel.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.gridy = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		panel.add(heightLabel, gc);
		
		gc.gridx = 1;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		panel.add(heightField, gc);
		
		gc.gridx = 0;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		panel.add(widthLabel, gc);
		
		gc.gridx = 1;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		panel.add(widthField, gc);
		
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

	public void actionPerformed(ActionEvent e) {
		try {
			int height = Integer.parseInt(heightField.getText());
			int width = Integer.parseInt(widthField.getText());
			
			if (height>0 && width>0) {
				FormEvent ev = new FormEvent(this, height, width);
				
				if (formListener != null) {
					formListener.formEventOccured(ev);
				}
				if (formListener == null) {
					System.out.println("Whoops its null");
				}
				dispose();
			} else {
				JOptionPane.showMessageDialog(this,
					    "Input needs to be a positive integer",
					    "Input Error",
					    JOptionPane.ERROR_MESSAGE);
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this,
				    "Input needs to be an integer",
				    "Input Error",
				    JOptionPane.ERROR_MESSAGE);
		}
	}
}
