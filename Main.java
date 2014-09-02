package tlc.buildpoints;

import javax.swing.SwingUtilities;

/**
 * Created for The Legend Continues Community
 * Code may be distributed and edited freely so long as credit is given to the author.
 * @author Bilbo Baggins
 *
 */
public class Main {
	static double version = 1.1;
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
    	    @Override
    	    public void run() {
    	    	System.out.println("Developer Debug Console");
    	    	System.out.println("If you're seeing this, stop looking at my code D;\n");
    	    	
    	    	System.out.println("Program Started. Opening Main Window");
    	        GradingWindow window = new GradingWindow();
    	        window.setVisible(true);
    	        
    	        //Called when the VM shuts down or the porgram exits.
    	        Runtime.getRuntime().addShutdownHook(new Thread() {
    	            @Override
    	            public void run() {
    	                System.out.println("Program ended.");
    	            }
    	        });
    	    }
		});
	}
}
