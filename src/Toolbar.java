import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Toolbar extends JPanel implements ActionListener {

	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
	private JButton btn4;
	private JButton btn5;
	private JButton btn6;
	private ToolbarListener toolbarListener;
	
	public Toolbar() {
		Dimension dim = getPreferredSize();
		dim.height = 100;
		setPreferredSize(dim);
		
		btn1 = new JButton("Show All Items");
		btn2 = new JButton("Show Shelves");
		btn3 = new JButton("Show Vendors");
		btn4 = new JButton("Search Item");
		btn5 = new JButton("Search Shelf");
		btn6 = new JButton("Filter by Vendor");
		
		
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		btn4.addActionListener(this);
		btn5.addActionListener(this);
		btn6.addActionListener(this);
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		add(btn1);
		add(btn2);
		add(btn3);
		add(btn4);
		add(btn5);
		add(btn6);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton)e.getSource();
		
		if (toolbarListener != null) {
			if (clicked == btn1) {
				toolbarListener.showItems();
			} else if (clicked == btn2) {
				toolbarListener.showShelves();
			} else if (clicked == btn3) {
				toolbarListener.showVendors();
			} else if (clicked == btn4) {
				toolbarListener.searchItem();
			} else if (clicked == btn5) {
				toolbarListener.searchShelf();
			} else if (clicked == btn6) {
				toolbarListener.filterByVendor();
			}
		}
	}
	
	public void setToolbarListener(ToolbarListener listener) {
		this.toolbarListener = listener;
	}
	
}
