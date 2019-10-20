
public class Laptop extends Item {

	private String cpu;
	private int ram;
	
	public Laptop(String _name, String _itemId, int _height, int _width, Vendor _vendor, String _cpu, int _ram) {
		super(_name, "L"+_itemId, _height, _width, _vendor);
		cpu = _cpu;
		ram = _ram;
	}
	
	public void setCPU(String _cpu) {
		cpu = _cpu;
	}
	
	public void setRAM(int _ram) {
		ram = _ram;
	}
	
	public String getCPU() {
		return cpu;
	}
	
	public int getRAM() {
		return ram;
	}
}
