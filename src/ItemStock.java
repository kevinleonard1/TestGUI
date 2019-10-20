public class ItemStock {
	private Item item;
	private int quantity;
	
	public ItemStock(Item i, int q) {
		item = i;
		quantity = q;
	}
	
	public ItemStock(Item i) {
		item = i;
		quantity = 0;
	}
	
	public void addQuantity(int q) {
		if (q>0) {
			quantity = quantity + q;
		} else {
			//error display here
		}
	}
	
	public void subtractQuantity(int q) {
		if (q>0 && quantity>=q) {
			quantity = quantity - q;
		} else {
			//error display here
		}
	}
	
	public Item getItem() {
		return item;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public ItemStock clone() {
		return new ItemStock(item, quantity);
	}
}
