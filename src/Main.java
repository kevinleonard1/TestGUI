import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.JFrame;

public class Main {
	
	private Map<String,Shelf> shelfList = new HashMap<String,Shelf>();
	private Set<Vendor> vendorList = new HashSet<Vendor>();
	
	public static void main(String[] args) {
		/*listAllItem() //returns HashMap
		
		App.main(args);
		Vendor v = new Vendor();
		Item i = new Item(.,,,, v);
		vendorList.add(v);
		itemList.add(i);*/
		Shelf s = new Shelf("a",10,10);
	}

	public void initializeData() {
		
	}
	
	public void listAllItems() {
		//for each shelves
		//	for each itemstock
		//		extract item and quantity
		//		store it to a hashmap (key=item/itemID, value=quantity)
		//go through each shelves to calculate the total quantity of each item
		//convert hashmap to a suitable format (JTable)
		//voila
	}
	
	//function search specific item
	//	just search through hashmap lol
}
