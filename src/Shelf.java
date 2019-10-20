import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Shelf {
	
	private String shelfId;
	private int height;
	private int width;
	private ArrayList<ItemStock> itemShelfed = new ArrayList<ItemStock>();
	//private int emptySpace;
	
	public Shelf(String _shelfId, int _height, int _width) {
		shelfId = _shelfId;
		height = _height;
		width = _width;
		//emptySpace = width;
		Item l = new Laptop("1","abc",10,10,new Vendor("x","10","somewhere"),"Core i5", 16);
		addItem(l,10);
		addItem(l,2);
		subtractItem(l,11);
		subtractItem(l,1);
	}
	
	public void addItem(Item i, int quantity) {
		ItemStock stock = getStock(i);
		if (stock == null) {
			itemShelfed.add(new ItemStock(i, quantity));
			System.out.println(Integer.toString(quantity));

		} else {
			stock.addQuantity(quantity);
			System.out.println(Integer.toString(stock.getQuantity()));
		}
	}
	
	public void subtractItem(Item i, int quantity) {
		ItemStock stock = getStock(i);
		if (stock == null) {
			System.out.println("Item does not exist");
		} else {
			if (stock.getQuantity() >= quantity) {
				stock.subtractQuantity(quantity);
				System.out.println(Integer.toString(stock.getQuantity()));
			} else {
				System.out.println("Not enough items in stock");
			}
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
	
	public ArrayList<ItemStock> getItemStock() {
		ArrayList<ItemStock> cloneStock = new ArrayList<ItemStock>();
		for (ItemStock counter: itemShelfed) {
			cloneStock.add(counter.clone());
		}
		return cloneStock;
	}
	
	public ItemStock getStock(Item i) {
		for (ItemStock counter: itemShelfed) {
			if (counter.getItem().getItemId() == i.getItemId()) {
				return counter.clone();
			}
		}
		return null;
	}
	
	public void showError() {
		JOptionPane frame = null;
		JOptionPane.showMessageDialog(frame,
			    "Item cannot be placed on this shelf",
			    "Input Error",
			    JOptionPane.ERROR_MESSAGE);
	}
	
}
