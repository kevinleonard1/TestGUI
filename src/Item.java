
public abstract class Item {
	
	private String itemId;
	private String name;
	private int height;
	private int width;
	private Vendor vendor;
	
	public Item(String _itemId, String _name, int _height, int _width, Vendor _vendor) {
		itemId = _itemId;
		name = _name;
		height = _height;
		width = _width;
		vendor = _vendor;
	}
	
	public void setName(String _name) {
		name = _name;
	}
	
	public void setHeight(int _height) {
		height = _height;
	}
	
	public void setWidth(int _width) {
		width = _width;
	}
	
	public void setVendor(Vendor v) {
		vendor = v;
	}
	
	public String getName() {
		return name;
	}
	
	public String getItemId() {
		return itemId;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public Vendor getVendor() {
		return vendor;
	}
	
	/*public boolean identifyItem(String _itemId) {
		return itemId == _itemId;
	}*/
}
