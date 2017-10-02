/**
 * ---------------------------------------------------------------------------
 * File name: PersonalLibrary.java
 * Project name: Books
 * ---------------------------------------------------------------------------
 * Creator's name and email: Koi Stephanos, stephanos@goldmail.etsu.edu
 * Course:  CSCI 1260
 * Creation Date: Oct 13, 2014
 * ---------------------------------------------------------------------------
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;
/**
 * An ArrayList named library that holds and manages Book objects
 *
 * <hr>
 * Date created: Sep 16, 2014
 * <hr>
 * @author Koi Stephanos
 */
public class PersonalLibrary
{
	private int librarySize = 20;					//holds the maximum size of the library
	private int currentSize;					//the amount of books currently held in the library
	private ArrayList<Book> library;			//holds all of the books contained in the library
	private Boolean saveNeeded;					//whether or not the file needs to be saved
	private String fileLocal;					//the location of the library
	
	
	
	/**
	 * Constructor        
	 *
	 * <hr>
	 * Date created: Oct 13, 2014 
	 *
	 * 
	 * @param 
	 */
	public PersonalLibrary ()
	{
		librarySize = 20;
		currentSize = 0;
		library = new ArrayList<Book>(librarySize);

		saveNeeded = false;
	}

	/**
	 * Constructor        
	 *
	 * <hr>
	 * Date created: Oct 13, 2014 
	 *
	 * 
	 * @param librarySize
	 * @param library
	 */
	public PersonalLibrary (int librarySize, ArrayList <Book> library)
	{
		this.librarySize = librarySize;
		this.library = library;
		currentSize = library.size();
		saveNeeded = false;
	}
	
	
	/**
	 * Constructor - imports library from txt file      
	 *
	 * <hr>
	 * Date created: Oct 24, 2014 
	 *
	 * 
	 * @param txtLocal, the location of the txt file to be imported
	 */
	public PersonalLibrary (String txtLocal)
	{
		this.library = new ArrayList<Book>(librarySize);
		currentSize = 0;
		fileLocal = txtLocal;
		saveNeeded = false;
		File fileIn = new File(txtLocal);
		Scanner file = null;
		if(fileIn.exists ( ))
		{
			try
			{
				file = new Scanner(fileIn);
			}
			catch(FileNotFoundException e)
			{
				JOptionPane.showMessageDialog (null, "Error! Can not import from given .txt file!");
			}
			while(file.hasNext())
			{
				String str = file.nextLine ( );
				String [] fields = str.split ("\\|");
				String strNumAuthors = fields[0].toString ( );
				int numAuthors = Integer.parseInt (strNumAuthors);
				String title = fields[1].toString ( );
				String photo = fields[3].toString ( );
				ArrayList<String> authors = new ArrayList<String>();
				BookType type = BookType.valueOf (fields[4]);
				double price = Double.parseDouble (fields[2]);
				for(int i = 0; i < numAuthors; i++)
				{
					authors.add (fields[5 + i]);
				}
				try
				{
					Book book = new Book(title, price, authors, photo, type);
					this.library.add (book);
					currentSize++;
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog (null, ex.getMessage() + str);
				}
			}
			file.close ( );
		}
	}
	
	/**
	 * Saves library to a txt file      
	 *
	 * <hr>
	 * Date created: Oct 26, 2014
	 *
	 * <hr>
	 * @param txtLocal, the location of txt file to be output to as given by user
	 */
	public void saveFile (String txtLocal )
	{
		try 
		{
		
		//sets up the PrintWriter and for loop to ouput PersonalLibrary to a txt file	
		PrintWriter outputFile = new PrintWriter(txtLocal);
		PrintWriter outFile = new PrintWriter(outputFile);
		for(int i = 0; i < currentSize; i++)
		{
			//gets the book
			Book book = library.get(i);
			
			//sets up the authors to be saved
			ArrayList<String> authors = book.getAuthor ( );
			int numAuthors = authors.size ( );
			String fmtAuthors = "";
			for(int j = 0; j < numAuthors; j++)
			{
				fmtAuthors += authors.get(j) + "|"; 
			}
			
			//sets up the rest of the book info to be saved
			String title = book.getTitle ( );
			Double price = book.getPrice ( );
			String strPrice = price.toString ( );
			String coverPhoto = book.getCoverPhoto ( );
			BookType type = book.getType ( );
			String bookType = type.toString ( );
		
			//formats all of the info into a single string and then outputs the formatted book to the save file
			String formattedBook = numAuthors + "|" + title + "|" + strPrice + "|" + coverPhoto + "|" + bookType + "|" + fmtAuthors;
			outFile.println (formattedBook);
			
		}
		saveNeeded = false;
		outFile.close();
		}
		catch (Exception ex)
		{
			ex.printStackTrace ( );
			JOptionPane.showMessageDialog (null, ex.getMessage ( ));
		}
	}
	
	/**
	 * Returns a string containing the location of the library         
	 *
	 * <hr>
	 * Date created: Oct 24, 2014
	 *
	 * <hr>
	 * @return fileLocal, position of library txt file
	 */
	public String getFileLocal()
	{
		return this.fileLocal;
	}
	
	/**
	 * Returns saveNeeded        
	 *
	 * <hr>
	 * Date created: Oct 24, 2014
	 *
	 * <hr>
	 * @return saveNeeded, whether or not the file needs to be saved
	 */
	public Boolean isSaveNeeded()
	{
		return this.saveNeeded;
	}

	/**
	 * Adds a book to the library, if no book with the same title is already present   
	 * @exception Exception, thrown if a book with the same title already exists in the library    
	 *
	 * <hr>
	 * Date created: Oct 13, 2014
	 *
	 * <hr>
	 * @param b, the book to be added
	 */
	public void addBook(Book b) throws Exception
	{
			Boolean value = library.contains (b);
			if(value == false)
			{
				library.add (b);
				currentSize = library.size ( );
				saveNeeded = true;
			}
			else throw new Exception("Book with that Title already exists in Library!");
	}
	
	/**
	 * Returns the amount of books currently in the Library         
	 *
	 * <hr>
	 * Date created: Oct 13, 2014
	 *
	 * <hr>
	 * @return int, currentSize
	 */
	public int determineSize ()
	{
		return currentSize;
	}
	
	/**
	 * Returns the total value of the library as a double         
	 *
	 * <hr>
	 * Date created: Oct 13, 2014
	 *
	 * <hr>
	 * @return dTotal, the total library value
	 */
	public double libraryValue ()
	{
		double dTotal = 0.0;					//holds the total value of the library
		double dTemp = 0.0;						//temp value
		for(int i = 0; i < currentSize; i++)
		{
			
			Book b = library.get (i);
			dTemp = b.getPrice();
			dTotal += dTemp;
		}
		return dTotal;
	}

	/**
	 * Retrieves a Book designated by its position within the library
	 *
	 * <hr>
	 * Date created: Oct 13, 2014
	 *
	 * <hr>
	 * @param position
	 */
	public Book retrieveBook(int position)
	{
		Book b = library.get (position);
		return b;
	}
	
	/**
	 * Searches Library for first book with a matching title and upon finding, deletes the book from the library 
	 * @exception Exception, thrown if book with title given is not found       
	 *
	 * <hr>
	 * Date created: Oct 13, 2014
	 *
	 * <hr>
	 * @param title
	 */
	public void removeBook(String title) throws Exception
	{
		Book book;						//holds the books being searched
		String tempTitle;				//holds the temporary title
		for(int i = 0; i < currentSize; i++)
		{
			book = library.get (i);
			tempTitle = book.getTitle ( );
			if(tempTitle.equals (title))
			{
				library.remove (i);
				currentSize--;
				saveNeeded = true;
				break;
			}
		}
		if(saveNeeded = false)
			throw new Exception("Error! No book with that title is present within the library!");
	}
	
	/**
	 * Retrieves the first book of a given title from the library   
	 * @exception Exception, thrown if book with given title is not found in the Library    
	 *
	 * <hr>
	 * Date created: Oct 13, 2014
	 *
	 * <hr>
	 * @param title
	 * @return book
	 */
	public Book retrieveBook(String title) throws Exception
	{
		Book book = null;						//holds the books being searched
		String tempTitle;						//holds the temporary title
		for(int i = 0; i < currentSize; i++)
		{
			book = library.get (i);
			tempTitle = book.getTitle ( );
			if(tempTitle.equals (title))
			break;
		}
		if(book == null)
			throw new Exception("Error! No book with that title is present within the library!");
		return book;
	}
	
	/**
	 * Determines whether a book is in the library       
	 *
	 * <hr>
	 * Date created: Oct 13, 2014
	 *
	 * <hr>
	 * @param title
	 * @return blnValue, true if the book is found
	 */
	public Boolean determineBook(String title)
	{
		Book book = null;						//holds the books being searched
		String tempTitle;						//holds the temporary title
		Boolean blnValue = false;				//holds whether or not the book is in the library
		for(int i = 0; i < currentSize; i++)
		{
			book = library.get (i);
			tempTitle = book.getTitle ( );
			if(tempTitle.equals (title))
			{
				blnValue = true;
				break;
			}
		}
		return blnValue;
	}

	/**
	 * Returns an Array list containing all the books with a matching Book Type      
	 * @exception Exception, thrown if no books with given type are found in the library
	 *
	 * <hr>
	 * Date created: Oct 13, 2014
	 *
	 * <hr>
	 * @param type
	 * @return books
	 */
	public ArrayList<Book> retrieveBooksByType(String type) throws Exception
	{
		Book book = null;															//holds the books being searched
		ArrayList<Book> books = new ArrayList<Book> (currentSize);					//holds the books with matching types
		BookType temp;																//holds the temporary BookType
		String tempType;															//holds the temporary type as a string
		for(int i = 0; i < currentSize; i++)	
		{
			book = library.get (i);
			temp = book.getType ( );
			tempType = temp.toString ( );
			if(tempType.equals (type))
			{
				books.add (book);
			}
		}
		Boolean value = books.isEmpty ( );
		if(value == true)
			throw new Exception("Error! No books with that type found in library!");
		return books;
	}

	/**
	 * Returns an Array List containing all the books with the specified author      
	 * @exception Exception, thrown if no book in the library shares the author given by the user  
	 *
	 * <hr>
	 * Date created: Oct 13, 2014
	 *
	 * <hr>
	 * @param author
	 * @return books
	 */
	public ArrayList<Book> retrieveBooksByAuthor(String author) throws Exception
	{
		Book book = null;															//holds the books being searched
		ArrayList<Book> books = new ArrayList<Book> (currentSize);					//holds the books with matching types
		ArrayList<String> temp = new ArrayList<String>();																//holds the temporary type as a string
		for(int i = 0; i < currentSize; i++)
		{
			book = library.get (i);
			temp = book.getAuthor ( );
			for(String authors: temp)
			{
				if(authors.contains (author))
				{
					books.add (book);
				}
			}	
		}
		if(books.isEmpty ( ))
			throw new Exception("Error! Book with that author not found in the library!");
		return books;
	}
	
	/**
	 * Runs through all books in the Library and sorts by ascending Alphabetical order        
	 *
	 * <hr>
	 * Date created: Oct 13, 2014
	 *
	 * <hr>
	 */
	public void sortBooks()
	{
		Book temp1;				//holds a temporary book
		Book temp2;				//holds a temporary book
		Book temp3;				//holds a temporary book
		String tempTitle1;		//holds a temporary title of a book object
		String tempTitle2;		//holds a temporary title of a book object
		
		for(int i = 0; i < currentSize; i++)
		{
			for(int j = i + 1; j < currentSize; j++)
			{
				//creates instances of book objects that are position next to each other and formats their titles to compare
				temp1 = library.get(i);
				temp2 = library.get(j);
				tempTitle1 = temp1.getTitle();
				tempTitle2 = temp2.getTitle();
				
				//determines if the books need to be switched, and if so, switches them
				if(tempTitle1.compareToIgnoreCase(tempTitle2) > 0)
				{
					library.set (i, temp2);
					library.set (j, temp1);
				}
			}
		}
		saveNeeded = true;
	}

	/**
	 * Returns the maximum and current size of the library as well as the books contained within as a string      
	 *
	 * <hr>
	 * Date created: Oct 13, 2014 
	 *
	 * <hr>
	 * @return
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString ( )
	{
		String bookList = "";					//holds the books contained in the library
		Book temp;							//temporary book object
		
		for(int i = 0; i < currentSize; i++)
		{
			temp = library.get (i);
			bookList += "\n" + temp.toString ( );
		}
		return "Personal Library:" + "\nLibrary Size =" + librarySize + "\nCurrent Size =" + currentSize +
						"\nBook List: " + bookList;
	}

	/**
	 * Returns an array or book Titles        
	 *
	 * <hr>
	 * Date created: Dec 2, 2014
	 *
	 * <hr>
	 * @return titles, array of book titles
	 */
	public String[] titlesToArray()
	{
		String[] titles = new String[currentSize];
		
		for(int i = 0; i < currentSize; i++)
		{
			Book book = new Book();
			book = library.get (i);
			String title = book.getTitle ( );
			titles[i] = title;
		}
		
		return titles;
	}



}
