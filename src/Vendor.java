
public class Vendor {
	private String vendorId;
	private String name;
	private String location;
	
	public Vendor(String _vid, String _name, String _loc) {
		vendorId = _vid;
		name = _name;
		location = _loc;
	}
	
	public void setVendorId(String _vid) {
		name = _vid;
	}
	
	public void setName(String _name) {
		name = _name;
	}
	
	public void setLocation(String _loc) {
		name = _loc;
	}
	
	public String getVendorId() {
		return vendorId;
	}
	
	public String getName() {
		return name;
	}
	
	public String getLocation() {
		return location;
	}
	
	public String getDescription() {
		return (vendorId + "\t" + name + "\t" + location + "\n");
	}
}
