
public class Vendor {
	private String name;
	private String vendorId;
	private String location;
	
	public Vendor(String _name, String _vid, String _loc) {
		name = _name;
		vendorId = _vid;
		location = _loc;
	}
	
	public void setName(String _name) {
		name = _name;
	}
	
	public void setVendorId(String _vid) {
		name = _vid;
	}
	
	public void setLocation(String _loc) {
		name = _loc;
	}
	
	public String getName() {
		return name;
	}
	
	public String getVendorId() {
		return vendorId;
	}
	
	public String getLocation() {
		return location;
	}
}
