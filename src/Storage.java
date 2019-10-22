import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The main storage where all the data is stored
 * Currently using hashmaps to store data of shelf and vendor
 * and only store inputs temporarily, will reset when shut down
 * 
 * Future improvements include using Gson or any text file to store data
 * and using storage as the gateway from that file and the app
 * 
 * @author Kevin Leonard
 * @see AppFrame
 * 
 */
public class Storage {

	private HashMap<String,Shelf> shelfList = new HashMap<String,Shelf>();
	private HashMap<String,Vendor> vendorList = new HashMap<String,Vendor>();
	
	public Storage() {
		initializeData();
		/*Gson gson = new Gson();
		String jsonString = gson.toJson(shelfList);
		System.out.println(jsonString);*/
	}
	
	private void initializeData() {
		//put in shelves, items, and vendors
		Vendor v1 = new Vendor("V01", "Samsung", "Korea");
		Vendor v2 = new Vendor("V02", "Apple", "America");
		Vendor v3 = new Vendor("V03", "Sony", "Japan");
		Vendor v4 = new Vendor("V04", "Dell", "America");
		addVendor(v1);
		addVendor(v2);
		addVendor(v3);
		addVendor(v4);
		
		Item macbook = new Laptop("L01","Macbook Pro",1,1,v2,"Macintosh",64);
		Item xps = new Laptop("L02","Dell XPS 15",1,1,v4,"Nvidia GeForce",64);
		Item galaxy = new Laptop("L03","Galaxy Book S",1,1,v1,"AMD",32);
		Item sonywh = new Headphone("H01","Sony WH-XB700",1,1,v3,true,true);
		Item airpod = new Headphone("H02","AirPods",1,1,v2,true,false);
				
		Shelf s1 = new Shelf("S01",5,10);
		Shelf s2 = new Shelf("S02",6,10);
		Shelf s3 = new Shelf("S03",5,15);
		
		s1.addItem(macbook, 40);
		s1.addItem(xps, 35);
		s1.addItem(galaxy, 30);
		s2.addItem(sonywh, 50);
		s2.addItem(airpod, 45);
		s3.addItem(galaxy, 10);
		s3.addItem(airpod, 15);
		s3.addItem(macbook, 5);
		
		addShelf(s1);
		addShelf(s2);
		addShelf(s3);
	}
	
	public HashMap<String,Vendor> getVendorList() {
		return (HashMap<String,Vendor>) vendorList.clone();
	}
	
	public HashMap<String,Shelf> getShelfList() {
		return (HashMap<String,Shelf>) shelfList.clone();
	}
	
	public void addShelf(Shelf s) {
		shelfList.put(s.getShelfId(), s);
	}
	
	public void addVendor(Vendor v) {
		vendorList.put(v.getVendorId(), v);
	}
	
	public void addItem(Item i, String _shelfId, int q) {
		if (shelfList.containsKey(_shelfId)) {
			shelfList.get(_shelfId).addItem(i, q);
		} else {
			System.out.println("Shelf doesn't exist");
		}
	}
	
	public void subtractItem(Item i, String _shelfId, int q) {
		if (shelfList.containsKey(_shelfId)) {
			shelfList.get(_shelfId).subtractItem(i, q);
		} else {
			System.out.println("Shelf doesn't exist");
		}
	}
	
	public void removeShelf(String _shelfId) {
		if (shelfList.containsKey(_shelfId)) {
			shelfList.remove(_shelfId);
		} else {
			System.out.println("Shelf doesn't exist");
		}
	}
	
	public void removeVendor(String _vendorId) {
		if (vendorList.containsKey(_vendorId)) {
			vendorList.remove(_vendorId);
		} else {
			System.out.println("Shelf doesn't exist");
		}
	}
	
	public boolean emptyVendor(String vendorId) {
		for (Map.Entry<String,Shelf> shelf:shelfList.entrySet()) {
			ArrayList<ItemStock> itemStock = shelf.getValue().getItemStock();
			for (ItemStock is:itemStock) {
				if (is.getItem().getVendorId().equals(vendorId)) {
					return false;
				}
			}
		}
		return true;
	}
	
}
