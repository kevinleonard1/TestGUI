import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * The class for the shelf
 * 
 * @author Kevin Leonard
 * 
 */
public class Shelf {
	
	private String shelfId;
	private int height;
	private int width;
	private ArrayList<ItemStock> itemShelfed = new ArrayList<ItemStock>();
	
	public Shelf(String _shelfId, int _height, int _width) {
		shelfId = _shelfId;
		height = _height;
		width = _width;
	}
	
	/**
	 * Function to add item to this shelf
	 */
	public void addItem(Item i, int quantity) {
		ItemStock stock = getStock(i);
		if (stock == null) {
			itemShelfed.add(new ItemStock(i, quantity));

		} else {
			stock.addQuantity(quantity);
		}
	}
	
	/**
	 * Function to subtract item from this shelf
	 * Any item that reaches 0 quantity will be automatically removed from shelf
	 */
	public void subtractItem(Item i, int quantity) {
		ItemStock stock = getStock(i);
		if (stock == null) {
			System.out.println("Item does not exist");
		} else {
			if (stock.getQuantity() >= quantity) {
				stock.subtractQuantity(quantity);
			} else {
				System.out.println("Not enough items in stock");
			}
			//check if item quantity is 0
			if (stock.getQuantity()==0) {
				itemShelfed.remove(stock);
			}
		}
	}
	
	public void setShelfId(String _shelfId) {
		shelfId = _shelfId;
	}
	
	public void setHeight(int _height) {
		height = _height;
	}
	
	public void setWidth(int _width) {
		width = _width;
	}
	
	public String getShelfId() {
		return shelfId;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	/**
	 * Function that returns the list of item stock in this shelf
	 */
	public ArrayList<ItemStock> getItemStock() {
		ArrayList<ItemStock> cloneStock = new ArrayList<ItemStock>();
		for (ItemStock counter: itemShelfed) {
			cloneStock.add(counter.clone());
		}
		return cloneStock;
	}
	
	/**
	 * Function that returns the stock of a specific item in this shelf
	 * 
	 * @param item: item to search for
	 */
	public ItemStock getStock(Item i) {
		for (ItemStock counter: itemShelfed) {
			if (counter.getItem().getItemId().equals(i.getItemId())) {
				return counter;
			}
		}
		return null;
	}
	
	/**
	 * Function to check if shelf contains a specific item
	 */
	public boolean containsItem(Item i) {
		return (getStock(i) != null);
	}
	
	public String getDescription() {
		return (shelfId + "\t" + Integer.toString(height) + "\t" + Integer.toString(width) + "\n");
	}
	
}
