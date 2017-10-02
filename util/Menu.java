package util;

import java.awt.Font;
import java.text.DecimalFormat;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

/**
 * Class that represents a menu that can be displayed in a console window or in
 * a dialog.<br>
 * 
 * <hr>
 * Date created: Feb 18, 2014<br>
 * Date last modified: Feb 18, 2014<br>
 * <hr>
 * 
 * @author Don Bailes
 */
public class Menu
{
	private final String [ ]	choices		= new String [15];
	private String				title;
	private int					entries;
	private final int			maxChoices	= 15;

	/**
	 * Default constructor with default title <br>
	 * 
	 * <hr>
	 * Date created: Feb 18, 2014 <br>
	 * Date last modified: Feb 18, 2014 <br>
	 * 
	 * <hr>
	 */
	public Menu ( )
	{
		entries = 0;
		title = "Untitled Menu";
		UIManager.put ("OptionPane.messageFont", new FontUIResource (new Font ("Arial", Font.PLAIN,
						18)));
	}

	/**
	 * Constructor that sets menu title <br>
	 * 
	 * <hr>
	 * Date created: Feb 18, 2014 <br>
	 * Date last modified: Feb 18, 2014 <br>
	 * 
	 * <hr>
	 * 
	 * @param menutitle
	 */
	public Menu (String menutitle)
	{
		entries = 0;
		title = menutitle;
		UIManager.put ("OptionPane.messageFont", new FontUIResource (new Font ("Lucida Console",
						Font.PLAIN, 18)));
	}

	/**
	 * Retrieve the title <br>
	 * 
	 * <hr>
	 * Date created: Feb 18, 2014 <br>
	 * Date last modified: Feb 18, 2014 <br>
	 * 
	 * <hr>
	 * 
	 * @return the title of the menu
	 */
	public String getTitle ( )
	{
		return title;
	}

	/**
	 * Change the menu title <br>
	 * 
	 * <hr>
	 * Date created: Feb 18, 2014 <br>
	 * Date last modified: Feb 18, 2014 <br>
	 * 
	 * <hr>
	 * 
	 * @param title
	 */
	public void setTitle (String title)
	{
		this.title = title;
	}

	/**
	 * Add a new choice at the end of the menu if there is room for it <br>
	 * 
	 * <hr>
	 * Date created: Feb 18, 2014 <br>
	 * Date last modified: Feb 18, 2014 <br>
	 * 
	 * <hr>
	 * 
	 * @param choice
	 */
	public void addChoice (String choice)
	{
		if (entries < maxChoices)
		{
			choices [entries++ ] = choice;
		}
		else
		{
			JOptionPane.showMessageDialog (null, choice +
							" could not be added to the menu because the menu is already full");
		}
	}

	/**
	 * Replace a specific menu choice if the integer parameter is a valid choice
	 * number <br>
	 * 
	 * <hr>
	 * Date created: Mar 7, 2011 <br>
	 * Date last modified: Mar 7, 2011 <br>
	 * 
	 * <hr>
	 * 
	 * @param n
	 * @param choice
	 */
	public void replaceChoice (int n, String choice)
	{
		if (n > 0 && n <= entries)
			choices [n - 1] = choice;
	}

	/**
	 * Display menu in console window and get user's selection. Verify validity
	 * and return the selection. <br>
	 * 
	 * <hr>
	 * Date created: Feb 18, 2014 <br>
	 * Date last modified: Feb 18, 2014 <br>
	 * 
	 * <hr>
	 * 
	 * @return int corresponding to user selection
	 */
	public int getChoice ( )
	{
		int choice = 1;
		if (entries <= 0)
			return -1;
		boolean successfulChoice = false;
		Scanner kb = new Scanner (System.in);

		while ( !successfulChoice)
		{
			displayMenu ( );
			try
			{
				System.out.print ("\n\tPlease type the number of your choice: ");
				choice = kb.nextInt ( );
				kb.nextLine ( );
				if (choice >= 1 && choice <= entries)
					return choice;
				else
					throw new Exception (" ");
			}
			catch (Exception ex)
			{
				System.out.println ("\nYour choice is invalid.\n" +
								"You must type an integer between 1 and " + entries + ".");
				kb.nextLine ( );
				Util.pressEnter ( );
			}
		}
		kb.close ( );
		return choice; // to make the compiler happy
	}

	/**
	 * Same as getChoice above, but uses a dialog instead <br>
	 * 
	 * <hr>
	 * Date created: Feb 18, 2014 <br>
	 * Date last modified: Feb 18, 2014 <br>
	 * 
	 * <hr>
	 * 
	 * @return int corresponding to user's selection
	 */
	public int getChoiceDialog ( )
	{
		int choice = 1;
		if (entries <= 0)
			return -1;
		boolean successfulChoice = false;
		String strMenu = this.toString ( ) + "\n\n\nPlease type the number of your choice:";

		while ( !successfulChoice)
		{
			try
			{
				choice = Integer.parseInt (JOptionPane.showInputDialog (strMenu));
				if (choice >= 1 && choice <= entries)
					return choice;
				else
					throw new Exception (" ");
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog (null, "\nYour choice is invalid.\n" +
								"You must type an integer between 1 and " + entries + ".");
			}
		}
		return choice;
	} // end getChoiceDialog

	/**
	 * Same as getChoice above, but uses a dialog instead; adds some text after
	 * the title <br>
	 * 
	 * <hr>
	 * Date created: Feb 18, 2014 <br>
	 * Date last modified: Feb 18, 2014 <br>
	 * 
	 * <hr>
	 * 
	 * @param str
	 *            - a string added to the menu display
	 * 
	 * @return int corresponding to user's selection
	 */
	public int getChoiceDialog (String str)
	{
		int choice = 1;
		if (entries <= 0)
			return -1;
		boolean successfulChoice = false;
		String strMenu = str + this.toString ( ) + "\n\n\nPlease type the number of your choice:";

		while ( !successfulChoice)
		{
			try
			{
				choice = Integer.parseInt (JOptionPane.showInputDialog (strMenu));
				if (choice >= 1 && choice <= entries)
					return choice;
				else
					throw new Exception (" ");
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog (null, "\nYour choice is invalid.\n" +
								"You must type an integer between 1 and " + entries + ".");
			}
		}
		return choice;
	} // end getChoiceDialog

	/**
	 * Display the menu in a formatted manner on the console window. <br>
	 * 
	 * <hr>
	 * Date created: Feb 18, 2014 <br>
	 * Date last modified: Feb 18, 2014 <br>
	 * 
	 * <hr>
	 */
	private void displayMenu ( )
	{
		DecimalFormat fmt = new DecimalFormat ("#0");
		Util.skip (1);
		int i;
		System.out.print ("\t" + title + "\n\t");
		for (i = 0; i < title.length ( ); i++ )
		{
			System.out.print ("-");
		}
		System.out.println ("\n");

		for (i = 0; i < entries; i++ )
		{
			System.out.println ("\t" + Util.right (fmt.format (i + 1), 2) + ". " + choices [i]);
		}
	} // end displayMenu

	/**
	 * Format the menu as a string for possible display in the console window<br>
	 * 
	 * <hr>
	 * Date created: Feb 18, 2014 <br>
	 * Date last modified: Feb 18, 2014 <br>
	 * 
	 * <hr>
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString ( )
	{
		int i;
		String result = "\t" + title + "\n\t";
		for (int n = 0; n < title.length ( ); n++ )
			result += "=";
		result += "\n";

		for (i = 0; i < entries; i++ )
			result += "\n\t" + (i + 1) + ". " + choices [i];

		return result;
	} // end toString
} // end Menu

