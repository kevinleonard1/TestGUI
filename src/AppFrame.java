import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * The main frame for the swing application. This frame decides what to do with the buttons and
 * stores/retrieves data from the storage. All data is stored in Storage class.
 * 
 * Has 3 panels in it: TextPanel, Toolbar, and EditPanel.
 * Has a bunch of functions with listeners that will decide what to do with the data.
 * 
 * Some functionality hasn't been implemented:
 * - Resuming the ID number after one item/vendor/shelf has been removed.
 * - Have a static data that can be loaded after restart
 * - Keeping items that has 0 quantity
 * - etc.
 * 
 * @author Kevin Leonard
 * @see Storage
 * @see TextPanel
 * @see Toolbar
 * @see EditPanel
 * 
 */
public class AppFrame extends JFrame {
	
	//Only this main frame has access to storage
	private Storage storage = new Storage();
	
	//Panels
	private TextPanel textPanel;
	private Toolbar toolbar;
	private EditPanel editPanel;
	
	public AppFrame() {
		super("TestGUI"); //Create JFrame with title "TestGUI"
		
		//Set the interface and adding the panels in JFrame
		setLayout(new BorderLayout());
		
		textPanel = new TextPanel();
		toolbar = new Toolbar();
		editPanel = new EditPanel();
		
		add(textPanel, BorderLayout.CENTER);
		add(toolbar, BorderLayout.SOUTH);
		add(editPanel, BorderLayout.WEST);
		
		setSize(700,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		/**
		 * Toolbar Listener for all 6 buttons
		 * - Show Vendors
		 * - Show Shelves
		 * - Show Items
		 * - Search Item
		 * - Search Shelf
		 * - Filter by Vendor
		 */
		toolbar.setToolbarListener(new ToolbarListener() {
			/**
			 * Function to show all vendors
			 */
			public void showVendors() {
				//clear and make heading
				textPanel.clearArea();
				textPanel.appendText("ID\tVendor\tLocation\n");
				textPanel.appendText("----\t------------\t------------\n");
				
				//iterate through Vendor List from Storage and append description to TextPanel
				//Using TreeMap to automatically sort the Map
				Map<String, Vendor> vList = new TreeMap<String, Vendor>(storage.getVendorList());
				for (Map.Entry<String, Vendor> entry: vList.entrySet()) {
					textPanel.appendText(entry.getValue().getDescription());
				}
			}
			
			/**
			 * Function to show all shelves
			 */
			public void showShelves() {
				//clear and make heading
				textPanel.clearArea();
				textPanel.appendText("ID\tHeight(m)\tWidth(m)\n");
				textPanel.appendText("----\t------------\t------------\n");
				
				//iterate through Shelf List from Storage and append description to TextPanel
				//Using TreeMap to automatically sort the Map
				Map<String, Shelf> sList = new TreeMap<String, Shelf>(storage.getShelfList());
				for (Map.Entry<String, Shelf> entry: sList.entrySet()) {
					textPanel.appendText(entry.getValue().getDescription());
				}
			}
			
			/**
			 * Function to show all unique items in storage and their total stock
			 */
			public void showItems() {
				//clear and make heading
				textPanel.clearArea();
				textPanel.appendText("ID\tItem\t\tQuantity\n");
				textPanel.appendText("----\t------------\t\t------------\n");
				
				Map<String, ItemStock> itemList = getAllItems(); //get all unique item stocks
				
				//iterate through map and append description to TextPanel
				for (Map.Entry<String, ItemStock> entry: itemList.entrySet()) {
					textPanel.appendText(entry.getValue().getDescription());
				}
			}
			
			/**
			 * Function to search a specific item and show which shelves contains it
			 * Search function will search through all items in storage with the substring inputted
			 */
			public void searchItem() {
				//Pop-up to insert substring to be searched
				String s = (String)JOptionPane.showInputDialog(null,"Search Item:",
						"Search Item",JOptionPane.PLAIN_MESSAGE,null,
	                    null,"");
				
				if (s!=null) {
					s = s.toLowerCase();
					//clear and make heading
					textPanel.clearArea();
					textPanel.appendText("Shelf ID\tItem ID\tItem\t\tQuantity\n");
					textPanel.appendText("----\t------------\t------------\t\t------------\n");
					
					//iterate through Shelf List from Storage and to search for the item
					//Using TreeMap to automatically sort the Map
					Map<String, Shelf> sList = new TreeMap<String, Shelf>(storage.getShelfList());
					for (Map.Entry<String, Shelf> entry:sList.entrySet()) {
						//get list of ItemStock from each shelves and iterate through it
						//to see if the the shelf contains that item
						ArrayList<ItemStock> itemStock = entry.getValue().getItemStock();
						for (ItemStock is:itemStock) {
							//append item description if item is located in the shelf
							if (is.getItem().getName().toLowerCase().contains(s)) {
								textPanel.appendText(entry.getKey() + "\t" + is.getDescription());
							}
						}
					}
				}
			}
			
			/**
			 * Function to search a specific shelf and reveal its item contents
			 * Uses JComboBox with the current shelves as the choices for the input
			 */
			public void searchShelf() {
				//Pop-up to choose which shelf to search
				Object[] shelves = createShelfArray();
				String s = (String)JOptionPane.showInputDialog(null,"Choose which shelf:",
						"Search Shelf",JOptionPane.PLAIN_MESSAGE,null,
	                    shelves,"");
				
				if (s!=null) {
					//clear and make heading
					textPanel.clearArea();
					textPanel.appendText("ID\tItem\t\tQuantity\n");
					textPanel.appendText("----\t------------\t\t------------\n");
					
					//iterate through the item stock and append description to TextPanel
					ArrayList<ItemStock> itemStock = storage.getShelfList().get(s).getItemStock();
					for (ItemStock is:itemStock) {
						textPanel.appendText(is.getDescription());
					}
				}
			}
			
			/**
			 * Function to filter items based on a vendor and reveal its stock
			 * Uses JComboBox with the current vendors as the choices for the input
			 */
			public void filterByVendor() {
				//Pop-up to choose which vendor to filter
				//Choices are in the format: "vendorKey: vendorName"
				Object[] vendors = createVendorArray();
				String s = (String)JOptionPane.showInputDialog(null,"Choose which vendor:",
						"Filter by Vendors",JOptionPane.PLAIN_MESSAGE,null,
	                    vendors,"");
				
				if (s!=null) {
					//clear and make heading
					textPanel.clearArea();
					textPanel.appendText("Vendor ID\tVendor\tItem ID\tItem\t\tQuantity\n");
					textPanel.appendText("----\t------------\t------------\t------------\t\t------------\n");
					
					//extract vendorKey and vendorName from user choice
					String vendorId = s.substring(0,3);
					String vendorName = s.substring(5);
					
					//iterate through the unique item list and show only items with the selected vendor
					Map<String, ItemStock> itemList = getAllItems();
					for (Map.Entry<String, ItemStock> entry:itemList.entrySet()) {
						String itemVendorId = entry.getValue().getItem().getVendorId();
						if (vendorId.equals(itemVendorId)) {
							textPanel.appendText(vendorId + "\t"+ vendorName + "\t" + entry.getValue().getDescription());
						}
					}
				}
			}
		});
		/****************************************************************/
		
		/**
		 * EditPanel Listener for all 8 buttons
		 * - Add New Item
		 * - Add to Existing Item
		 * - Reduce Item
		 * - Move Item
		 * - Add Vendor
		 * - Remove Vendor
		 * - Add Shelf
		 * - Remove Shelf
		 */
		editPanel.setEditListener(new EditListener() {
			/**
			 * Function to add new vendor by getting input from a new JFrame
			 * Uses FormEvent and FormListener to monitor the changes of the new JFrame
			 */
			public void addVendor() {
				//Open new addVendorFrame
				addVendorFrame frame = new addVendorFrame();
				frame.setVisible(true);
				
				//set form listener for addVendorFrame
				frame.setFormListener(new FormListener() {
					public void formEventOccured(FormEvent e) {
						//set the vendorId and get vendor elements from event
						int next_no = storage.getVendorList().size()+1;
						String vendorId = "V" + String.format("%02d", next_no);
						String name = e.getName();
						String location = e.getLocation();
						
						//create vendor instance and add it to storage
						Vendor v = new Vendor(vendorId,name,location);
						storage.addVendor(v);
					}
				});
			}
			
			/**
			 * Function to add new shelf by getting input from a new JFrame
			 * Uses FormEvent and FormListener to monitor the changes of the new JFrame
			 */
			public void addShelf() {
				//Open new addShelfFrame
				addShelfFrame frame = new addShelfFrame();
				frame.setVisible(true);
				
				//set form listener for addShelfFrame
				frame.setFormListener(new FormListener() {
					public void formEventOccured(FormEvent e) {
						//set shelfId and get shelf elements from event
						int next_no = storage.getShelfList().size()+1;
						String shelfId = "S" + String.format("%02d", next_no);
						int height = e.getHeight();
						int width = e.getWidth();
						
						//create shelf instance and add it to storage
						Shelf s = new Shelf(shelfId,height,width);
						storage.addShelf(s);
					}
				});
			}
			
			/**
			 * Function to add new item by getting input from a new JFrame
			 * Uses FormEvent and FormListener to monitor the changes of the new JFrame
			 * Passes item types and vendor list data to the new frame
			 */
			public void addItem() {
				//initialize data to pass to new addItemFrame
				Object[] itemTypes = {"Laptop","Headphone"};
				Object[] vendors = createVendorArray();
				
				//open new addItemFrame
				addItemFrame frame = new addItemFrame(itemTypes, vendors);
				frame.setVisible(true);
				
				//set form listener for addItemFrame
				frame.setFormListener(new FormListener() {
					public void formEventOccured(FormEvent e) {
						//set the itemId and get item elements from event
						int next_no = itemTypeCount(e.getType())+1;
						String itemId = e.getType().substring(0,1) + String.format("%02d", next_no);
						
						String name = e.getName();
						int height = e.getHeight();
						int width = e.getWidth();
						
						String vendorKey = e.getVendor().substring(0,3);
						Vendor v = storage.getVendorList().get(vendorKey);
						
						//create item instance
						//item is abstract class so need to define which 
						Item item = null;
						//needs to have different approach for entering unique variables of different item types
						//currently inserted random values
						if (e.getType().equals("Laptop")) {
							item = new Laptop(itemId,name,height,width,v,"",32);
						} else if (e.getType().equals("Headphone")) {
							item = new Headphone(itemId,name,height,width,v,true,true);
						}
						
						//Prompt to choose which shelf you want to add the item to
						Object[] shelves = createShelfArray();
						String s = (String)JOptionPane.showInputDialog(null,"Add Item to which shelf:",
								"Add to Shelf",JOptionPane.PLAIN_MESSAGE,null,
			                    shelves,"");
						
						//Prompt to input the quantity of the item and add it to storage
						addQuantityPrompt(item,s);
					}
				});
			}
			
			/**
			 * Function to add stock to existing item by getting input from a new JFrame
			 * Uses FormEvent and FormListener to monitor the changes of the new JFrame
			 */
			public void addExItem() {
				//get all unique items
				Map<String, ItemStock> itemStock = getAllItems();
				
				try {
					//convert map to an array object for user to select the item to add
					Object[] items = createItemArray(itemStock);
					String i = (String)JOptionPane.showInputDialog(null,"Choose which item to add stock:",
							"Add Existing Item",JOptionPane.PLAIN_MESSAGE,null,
		                    items,"");
					
					//prompt to choose which shelf to add the item to
					String key = i.substring(0,3);
					Item item = itemStock.get(key).getItem();
					
					Object[] shelves = createShelfArray();
					String s = (String)JOptionPane.showInputDialog(null,"Choose which shelf to add to:",
							"Add Existing Item",JOptionPane.PLAIN_MESSAGE,null,
		                    shelves,"");
					
					//prompt to input quantity and add the item to storage
					addQuantityPrompt(item,s);
				} catch (Exception e) {
					return;
				}
			}
			
			/**
			 * Function to reduce existing item by getting input from user
			 * Uses FormEvent and FormListener to monitor the changes of the new JFrame
			 * Uses multiple JDialogs to get the data
			 * If item in shelf becomes 0, it will automatically be deleted from shelf data
			 */
			public void redItem() {
				//get all unique items
				Map<String, ItemStock> itemStock = getAllItems();
				
				try {
					//convert map to an array object for user to select the item to add
					Object[] items = createItemArray(itemStock);
					String i = (String)JOptionPane.showInputDialog(null,"Choose which item to add stock:",
							"Add Existing Item",JOptionPane.PLAIN_MESSAGE,null,
		                    items,"");
					
					//prompt to choose which shelf to add the item to
					String key = i.substring(0,3);
					Item item = itemStock.get(key).getItem();
					
					Map<String,Shelf> shelfList = storage.getShelfList();
					ArrayList<String> shelfArray = new ArrayList<String>();
					for (Map.Entry<String, Shelf> entry:shelfList.entrySet()) {
						if (entry.getValue().containsItem(item)) {
							shelfArray.add(entry.getKey());
						}
					}
					Object[] shelves = shelfArray.toArray();
					
					String s = (String)JOptionPane.showInputDialog(null,"Choose which shelf to add to:",
							"Add Existing Item",JOptionPane.PLAIN_MESSAGE,null,
		                    shelves,"");
					
					//prompt to input quantity and reduce the item from storage
					redQuantityPrompt(item,s);
				} catch (Exception e) {
					return;
				}
			}
			
			/**
			 * Function to move existing item from one shelf to another by getting input from user
			 * Uses FormEvent and FormListener to monitor the changes of the new JFrame
			 * Uses multiple JDialogs to get the data
			 * If item in shelf becomes 0, it will automatically be deleted from shelf data
			 */
			public void moveItem() {
				//get all unique items
				Map<String, ItemStock> itemStock = getAllItems();
				
				try {
					//convert map to an array object for user to select the item to add
					Object[] items = createItemArray(itemStock);
					String i = (String)JOptionPane.showInputDialog(null,"Choose which item to add stock:",
							"Add Existing Item",JOptionPane.PLAIN_MESSAGE,null,
		                    items,"");
					
					//prompt to choose which shelf to add the item to
					String key = i.substring(0,3);
					Item item = itemStock.get(key).getItem();
					
					Map<String,Shelf> shelfList = storage.getShelfList();
					ArrayList<String> shelfArray = new ArrayList<String>();
					for (Map.Entry<String, Shelf> entry:shelfList.entrySet()) {
						if (entry.getValue().containsItem(item)) {
							shelfArray.add(entry.getKey());
						}
					}
					Object[] shelves = shelfArray.toArray();
					
					String s = (String)JOptionPane.showInputDialog(null,"Choose from which shelf to move:",
							"Move Item",JOptionPane.PLAIN_MESSAGE,null,
		                    shelves,"");
					
					//prompt to input quantity and reduce the item from storage
					int q = redQuantityPrompt(item,s);
					
					//then move the reduced item to another shelf chosen by user input
					if (q > 0) {
						Object[] moveShelves = createShelfArray();
						String ms = (String)JOptionPane.showInputDialog(null,"Choose which shelf to move to:",
								"Move Item",JOptionPane.PLAIN_MESSAGE,null,
			                    moveShelves,"");
						storage.addItem(item, ms, q);
					}
				} catch (Exception e) {
					return;
				}
			}
			
			/**
			 * Function to remove existing shelf
			 * Uses FormEvent and FormListener to monitor the changes of the new JFrame
			 * Uses multiple JDialogs to get the data
			 * Shelf can only be removed if the shelf is empty (no items inside it)
			 */
			public void remShelf() {
				try {
					//Prompt user to select which shelf they want to remove
					Object[] shelves = createShelfArray();
					String s = (String)JOptionPane.showInputDialog(null,"Choose which shelf to remove:",
							"Remove Shelf",JOptionPane.PLAIN_MESSAGE,null,
		                    shelves,"");
					
					//Check if shelf is empty, if it is then remove from storage
					if (storage.getShelfList().get(s).getItemStock().size() != 0) {
						JOptionPane.showMessageDialog(null,
							    "Shelf contain items",
							    "Cannot remove shelf",
							    JOptionPane.ERROR_MESSAGE);
					} else {
						storage.removeShelf(s);;
					}
				} catch (Exception e) {
					return;
				}
			}
			
			/**
			 * Function to move existing item from one shelf to another by getting input from user
			 * Uses FormEvent and FormListener to monitor the changes of the new JFrame
			 * Uses multiple JDialogs to get the data
			 * Vendors can only be removed if the vendor is not supplying any more items (no items has that vendor)
			 */
			public void remVendor() {
				try {
					//prompt user to select which vendor they want to remove
					Object[] vendors = createVendorArray();
					String v = (String)JOptionPane.showInputDialog(null,"Choose which vendor to remove:",
							"Remove Vendor",JOptionPane.PLAIN_MESSAGE,null,
		                    vendors,"");
					
					//Check if vendor is empty, if it is then remove
					String vendorId = v.substring(0,3);
					if (storage.emptyVendor(vendorId)) {
						storage.removeVendor(vendorId);
					} else {
						JOptionPane.showMessageDialog(null,
							    "Vendor is still supplying items",
							    "Cannot remove vendor",
							    JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e) {
					return;
				}
				
				
				
			}
		});
	}
	/*******************************************/
	
	/**
	 * Function to get all unique items from the storage and their total stock
	 * Iterates through all shelves and their item stocks and adds it to the total unique stock
	 */
	public Map<String,ItemStock> getAllItems() {
		//initialize empty item list and gets all shelf from storage
		Map<String,ItemStock> itemList = new TreeMap<String,ItemStock>();
		Map<String, Shelf> sList = new TreeMap<String, Shelf>(storage.getShelfList());
		
		//iterate through the shelves' item stocks
		for (Map.Entry<String, Shelf> entry:sList.entrySet()) {
			ArrayList<ItemStock> itemStock = entry.getValue().getItemStock();
			for (ItemStock is:itemStock) {
				//adds any new item to the item list and increases stock for any existing item
				String key = is.getItem().getItemId();
				if (itemList.containsKey(key)) {
					itemList.get(key).addQuantity(is.getQuantity());
				} else {
					itemList.put(key, is);
				}
			}
		}
		return itemList;
	}
	
	/**
	 * Function to create an object array of vendors for the choices of JDialog
	 */
	public Object[] createVendorArray() {
		Map<String, Vendor> vList = new TreeMap<String, Vendor>(storage.getVendorList());
		
		ArrayList<String> vIdName = new ArrayList<String>();
		for (Map.Entry<String, Vendor> entry:vList.entrySet()) {
			vIdName.add(entry.getKey() + ": " + entry.getValue().getName());
		}
		Object[] vendors = vIdName.toArray();
		return vendors;
	}
	
	/**
	 * Function to create an object array of shelves for the choices of JDialog
	 */
	public Object[] createShelfArray() {
		Map<String, Shelf> sList = new TreeMap<String, Shelf>(storage.getShelfList());
		Set<String> shelfSet = sList.keySet();
		Object[] shelves = shelfSet.toArray();
		return shelves;
	}
	
	/**
	 * Function to create an object array of items based on itemStock for the choices of JDialog
	 * 
	 * @param itemStock: a map of item stock to iterate and create the object array
	 */
	public Object[] createItemArray(Map<String,ItemStock> itemStock) {
		ArrayList<String> itemIdName = new ArrayList<String>();
		for (Map.Entry<String, ItemStock> entry:itemStock.entrySet()) {
			itemIdName.add(entry.getKey() + ": " + entry.getValue().getItem().getName());
		}
		Object[] items = itemIdName.toArray();
		return items;
	}
	
	/**
	 * Function to count the number of unique items of a specific type in the storage
	 * 
	 * @param type: a string that represents the type
	 */
	public int itemTypeCount(String type) {
		Map<String, ItemStock> itemStock = getAllItems();
		
		//iterate through the unique items and count the number of specific type items in it
		int count = 0;
		for (Map.Entry<String, ItemStock> entry:itemStock.entrySet()) {
			if (type.equals("Laptop")) {
				if (entry.getKey().substring(0,1).equals("L")) {
					count = count+1;
				}
			}
			if (type.equals("Headphone")) {
				if (entry.getKey().substring(0,1).equals("H")) {
					count = count+1;
				}
			}
		}
		return count;
	}
	
	/**
	 * Function to show a pop-up dialog to prompt user to input item quantity to add
	 * Then adds it to the storage
	 * 
	 * @param item: the item to add
	 * @param shelfId: the id of the shelf where you want to add it to
	 */
	public void addQuantityPrompt(Item item, String shelfId) {
		try {
			//Prompt user to input quantity
			String qString = (String)JOptionPane.showInputDialog(null,"How many items:",
					"Add to Shelf",JOptionPane.PLAIN_MESSAGE,null,
                    null,"");
			int q = Integer.parseInt(qString);
			
			//Quantity needs to be a positive integer,
			//if it is then add the whole thing to the storage
			if (q>0) {
				storage.addItem(item, shelfId, q);
			} else {
				JOptionPane.showMessageDialog(null,
					    "Input needs to be a positive integer",
					    "Input Error",
					    JOptionPane.ERROR_MESSAGE);
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null,
				    "Input needs to be an integer",
				    "Input Error",
				    JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Function to show a pop-up dialog to prompt user to input item quantity to reduce
	 * Then reduces it from the storage
	 * Any item which quantity becomes 0 will automatically be removed from shelf
	 * 
	 * @param item: the item to reduce
	 * @param shelfId: the id of the shelf where you want to add it to
	 */
	public int redQuantityPrompt(Item item, String shelfId) {
		try {
			int stockQuantity = storage.getShelfList().get(shelfId).getStock(item).getQuantity();
			String qString = (String)JOptionPane.showInputDialog(null,
					"Current stock quantity: " + stockQuantity + "\nHow many items:",
					"Reduce from Shelf",JOptionPane.PLAIN_MESSAGE,null,
                    null,"");
			int q = Integer.parseInt(qString);
			
			//Quantity needs to be a positive integer and not bigger than stock quantity in shelf,
			//if it is then reduce the item from the storage and automatically delete 0 items
			if (q > stockQuantity) {
				JOptionPane.showMessageDialog(null,
					    "Not enough Quantity",
					    "Input Error",
					    JOptionPane.ERROR_MESSAGE);
			} else if (q>0) {
				storage.subtractItem(item, shelfId, q);
				return q;
			} else {
				JOptionPane.showMessageDialog(null,
					    "Input needs to be a positive integer",
					    "Input Error",
					    JOptionPane.ERROR_MESSAGE);
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null,
				    "Input needs to be an integer",
				    "Input Error",
				    JOptionPane.ERROR_MESSAGE);
		}
		return -1;
	}
}
