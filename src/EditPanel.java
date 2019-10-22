import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class EditPanel extends JPanel implements ActionListener {

	private JButton addItemBtn;
	private JButton addExItemBtn;
	private JButton addVendorBtn;
	private JButton addShelfBtn;
	private JButton redItemBtn;
	private JButton remVendorBtn;
	private JButton remShelfBtn;
	private JButton moveItemBtn;
	private EditListener editListener;
	
	public EditPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 200;
		setPreferredSize(dim);
		
		addItemBtn = new JButton("Add New Item");
		addExItemBtn = new JButton("Add Existing Item");
		addVendorBtn = new JButton("Add Vendor");
		addShelfBtn = new JButton("Add Shelf");
		redItemBtn = new JButton("Reduce Item");
		remVendorBtn = new JButton("Remove Vendor");
		remShelfBtn = new JButton("Remove Shelf");
		moveItemBtn = new JButton("Move Item");
		
		addItemBtn.addActionListener(this);
		addExItemBtn.addActionListener(this);
		addVendorBtn.addActionListener(this);
		addShelfBtn.addActionListener(this);
		redItemBtn.addActionListener(this);
		remVendorBtn.addActionListener(this);
		remShelfBtn.addActionListener(this);
		moveItemBtn.addActionListener(this);
		
		Border innerBorder = BorderFactory.createTitledBorder("Edit Data");
		Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.weightx = 1;
		gc.weighty = 0.5;
		gc.gridx = 0;
		
		gc.gridy = 0;
		gc.fill = GridBagConstraints.NONE;
		add(addItemBtn, gc);
		
		gc.gridy = 1;
		add(addExItemBtn, gc);
		
		gc.gridy = 2;
		add(redItemBtn, gc);
		
		gc.gridy = 3;
		add(moveItemBtn, gc);
		
		gc.gridy = 4;
		add(addVendorBtn, gc);
		
		gc.gridy = 5;
		add(remVendorBtn, gc);
		
		gc.gridy = 6;
		add(addShelfBtn, gc);
		
		gc.gridy = 7;
		add(remShelfBtn, gc);
		
		gc.weighty = 2;
		gc.gridy = 8;
		add(new JLabel(), gc);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton)e.getSource();
		
		if (editListener!=null) {
			if (clicked == addItemBtn) {
				editListener.addItem();
			}
			if (clicked == addExItemBtn) {
				editListener.addExItem();
			}
			if (clicked == addVendorBtn) {
				editListener.addVendor();
			}
			if (clicked == addShelfBtn) {
				editListener.addShelf();
			}
			if (clicked == redItemBtn) {
				editListener.redItem();
			}
			if (clicked == moveItemBtn) {
				editListener.moveItem();
			}
			if (clicked == remShelfBtn) {
				editListener.remShelf();
			}
			if (clicked == remVendorBtn) {
				editListener.remVendor();
			}
		}
	}
	
	public void setEditListener(EditListener listener) {
		editListener = listener;
	}
	
}
