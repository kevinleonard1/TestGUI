import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Shelf {
	
	private String shelfId;
	private int height;
	private int width;
	private ArrayList<ItemStock> itemShelfed = new ArrayList<ItemStock>();
	private int emptySpace;
	
	public Shelf(String _shelfId, int _height, int _width) {
		shelfId = _shelfId;
		height = _height;
		width = _width;
		emptySpace = width;
	}
	
	public void addItem(Item i) {
		if (i.getHeight() < height) {
			if (i.getWidth() < emptySpace) {
				itemShelfed.add(i);
				emptySpace = emptySpace - i.getWidth();
			} else {
				showError();
			}
		} else {
			showError();
		}
	}
	
	public void removeItem(String itemId) {
		//identify the item first
		Item i = null;
		for (Item counter: itemShelfed) {
			if (counter.identifyItem(itemId)) {
				i = counter;
			}
		}
		if (i == null) {
			showError();
		} else {
			itemShelfed.remove(i);
		}
		
	}
	
	public void showError() {
		JOptionPane frame = null;
		JOptionPane.showMessageDialog(frame,
			    "Item cannot be placed on this shelf",
			    "Input Error",
			    JOptionPane.ERROR_MESSAGE);
	}
	
}
