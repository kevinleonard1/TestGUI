import javax.swing.SwingUtilities;

/**
 * Driver class for the Swing Application. Contains nothing but a main().
 * 
 * @author Kevin Leonard
 */
public class Main {
	
	public static void main(String[] args) {
		//Run swing application
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new AppFrame();
			}
		});
	}
}
