import javax.swing.SwingUtilities;

/**
 * ---------------------------------------------------------------------------
 * File name: BooksDriver.java
 * Project name: Books
 * ---------------------------------------------------------------------------
 * Creator's name and email: Koi Stephanos, stephanos@goldmail.etsu.edu
 * Course:  CSCI 1260
 * Creation Date: Nov 26, 2014
 * ---------------------------------------------------------------------------
 */

/**
 * To run the personal library application
 *
 * <hr>
 * Date created: Nov 26, 2014
 * <hr>
 * @author Koi Stephanos
 */
public class BooksDriver
{

	/**
	 * To initiate and run the personal library gui app      
	 *
	 * <hr>
	 * Date created: Nov 26, 2014
	 *
	 * <hr>
	 * @param args
	 */
	public static void main (String [ ] args)
	{
		SwingUtilities.invokeLater 
	 	   (
		 		 new Runnable()
		 		 {
			 		    @Override
			 		    public void run()
			 		    {
			 		    	Window personalLibrary = new Window();
			 		    }	
		 		 }
	 	   );   


	}

}
