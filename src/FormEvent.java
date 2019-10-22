import java.util.EventObject;

/**
 * An event form to temporarily store data fired from an event
 * Data can be accessed by any form that listens to this event
 * 
 * @author Kevin Leonard
 * @see EditPanel
 * 
 */
public class FormEvent extends EventObject {

	private String name;
	private String type;
	private String location;
	private int height;
	private int width;
	private String vendor;

	public FormEvent(Object source) {
		super(source);
	}
	
	//Vendor Event Constructor
	public FormEvent(Object source, String name, String location) {
		super(source);
		this.name = name;
		this.location = location;
	}
	
	//Shelf Event Constructor
	public FormEvent(Object source, int height, int width) {
		super(source);
		this.height = height;
		this.width = width;
	}
	
	//Item Event Constructor
	public FormEvent(Object source, String type, String name, int height, int width, String vendor) {
		super(source);
		this.type = type;
		this.name = name;
		this.height = height;
		this.width = width;
		this.vendor = vendor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
