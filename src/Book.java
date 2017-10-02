import java.util.ArrayList;
import java.text.NumberFormat;

/**
 * ---------------------------------------------------------------------------
 * File name: Book.java
 * Project name: Books
 * ---------------------------------------------------------------------------
 * Creator's name and email: Koi Stephanos, stephanos@goldmail.etsu.edu
 * Course:  CSCI 1260
 * Creation Date: Sep 16, 2014
 * ---------------------------------------------------------------------------
 */

/**
 * Provides the necessary attributes and methods for the book class
 *
 * <hr>
 * Date created: Sep 16, 2014
 * <hr>
 * @author Koi Stephanos
 */
public class Book
{

	NumberFormat fmt = NumberFormat.getCurrencyInstance ( );
	
	private String title;															//holds the title of the book
	private double price;															//holds the price of the book
	private ArrayList<String> author = new ArrayList<String>();						//holds the authors of the book
	private String coverPhoto;														//holds the cover photo of the book
	private BookType type;															//holds the type of the book as defined by the enum class BookType
	int numberOfBooks;																//holds the number of books created
	
	
	/**
	 * Constructor        
	 *
	 * <hr>
	 * Date created: Sep 16, 2014 
	 *
	 * 
	 */
	public Book ( )
	{
		// TODO Creates an instance of the Book class
	}//end of Book()

	/**
	 * Constructor   Parameterized      
	 *
	 * <hr>
	 * Date created: Sep 16, 2014 
	 * @param title 
	 * @param price 
	 * @param author 
	 * @param coverPhoto 
	 * @param type 
	 *
	 * 
	 * @param title, string
	 * @param price, double
	 * @param author, array of strings
	 * @param coverPhoto, String
	 * @param type, enum designation of Genre
	 */
	public Book (String title, double price, ArrayList<String> author, String coverPhoto, BookType type)
	{
		this.title = title;
		this.price = price;
		this.author = author;
		this.coverPhoto = coverPhoto;
		this.type = type;
	}

	/**
	 * @return title
	 */
	public String getTitle ( )
	{
		return title;
	}

	/**
	 * @param title 
	 * @param title, the title to set
	 */
	public void setTitle (String title)
	{
		this.title = title;
	}

	/**
	 * @return price
	 */
	public double getPrice ( )
	{
		return price;
	}

	/**
	 * @param price 
	 * @param price, the price to set
	 */
	public void setPrice (double price)
	{
		this.price = price;
	}

	/**
	 * @return author
	 */
	public ArrayList<String> getAuthor ( )
	{
		return author;
	}

	/**
	 * @param author, the author to set
	 */
	public void setAuthor (ArrayList<String> author)
	{
		this.author = author;
	}

	/**
	 * @return coverPhoto
	 */
	public String getCoverPhoto ( )
	{
		return coverPhoto;
	}

	/**
	 * @param coverPhoto, the coverPhoto to set
	 */
	public void setCoverPhoto (String coverPhoto)
	{
		this.coverPhoto = coverPhoto;
	}

	/**
	 * @return type
	 */
	public BookType getType ( )
	{
		return type;
	}

	/**
	 * @param type, the type to set
	 */
	public void setType (BookType type)
	{
		this.type = type;
	}

	/**
	 * @return numberOfBooks
	 */
	public int getNumberOfBooks ( )
	{
		return numberOfBooks;
	}
	
	/**
	 * Creates and returns a String containg all of the book info       
	 *
	 * <hr>
	 * Date created: Sep 16, 2014 
	 *
	 * <hr>
	 * @return
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString ( )
	{
		//sets up a string containing the authors
		int numAuthors = author.size ( );
		String authorList = "";
		for(int i = 0; i < numAuthors; i++)
		{
			authorList += author.get (i) + ", ";
		}
		
		//sets up the string containing all relevant book info
		NumberFormat fmt = NumberFormat.getCurrencyInstance ( );
		String str = "Title:  " + this.title + "        \n";
		str += "Authors: " + authorList + "			\n ";
		str += "CoverPhoto: " + coverPhoto + "			\n ";
		str += "Book Type: " + type.toString ( ) + "			\n ";
		str += "Price:  " + fmt.format (this.price) + "        \n";
		
		return str;
	}

	
	

}//end of Book
