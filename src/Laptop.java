
public class Laptop extends Item {

	private int ramSize;
	private int memory;
	
	public Laptop(String _name, String _itemId, int _height, int _width, Vendor _vendor, int _ramSize, int _memory) {
		super(_name, "L"+_itemId, _height, _width, _vendor);
		ramSize = _ramSize;
		memory = _memory;
	}
	
	//setter and getter here
	

}
