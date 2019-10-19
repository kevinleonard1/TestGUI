
public abstract class Item {
	
	private String name;
	private String itemId;
	private int height;
	private int width;
	private Vendor vendor;
	
	public Item(String _name, String _itemId, int _height, int _width, Vendor _vendor) {
		name = _name;
		itemId = _itemId;
		height = _height;
		width = _width;
		vendor = _vendor;
		quantity = 1;
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
	
	public boolean identifyItem(String _itemId) {
		return itemId == _itemId;
	}
}
