import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * ---------------------------------------------------------------------------
 * File name: Window.java
 * Project name: Books
 * ---------------------------------------------------------------------------
 * Creator's name and email: Koi Stephanos, stephanos@goldmail.etsu.edu
 * Course:  CSCI 1260
 * Creation Date: Nov 25, 2014
 * ---------------------------------------------------------------------------
 */


/**
 * To provide the window for the personal library app
 *
 * <hr>
 * Date created: Nov 25, 2014
 * <hr>
 * @author Koi Stephanos
 */
public class Window extends JFrame
{
	private static final long	serialVersionUID	= 1L; 		//needed for gui
	DecimalFormat df1 = new DecimalFormat("$#0.00");
	
	//library
	PersonalLibrary lib;						//library for gui
	String message;								//holds message for library actions
	int choice;									//holds choice made by user
	
	//panels
	private JPanel mainPanel;					//holds all panels
	private JPanel booksPanel;					//holds all components related to the books
	private JPanel fieldsPanel;					//panel for book info fields
	private JPanel infoPanel1;					//holds components related to book info
	private JPanel infoPanel2;					//holds components related to book info
	private JPanel photoPanel;					//holds the photo label
	
	//labels
	private JLabel titleLbl;					//label for book title
	private JLabel author1Lbl;					//label for author 1
	private JLabel author2Lbl;					//label for author 2
	private JLabel author3Lbl;					//label for author 3
	private JLabel author4Lbl;					//label for author 4
	private JLabel categoryLbl;					//label for book category
	private JLabel priceLbl;					//label for book price
	private JLabel coverPhoto;					//label for book cover photo
	
	//text fields
	private JTextField titleTxt;				//text field for title
	private JTextField author1Txt;				//text field for author 1
	private JTextField author2Txt;				//text field for author 2
	private JTextField author3Txt;				//text field for author 3
	private JTextField author4Txt;				//text field for author 4
	private JTextField priceTxt;				//text field for price
	
	//misc components
	private JList<String> bookList;						//list of books in library
	private JScrollPane bookScrollBar;					//scroll bar for book list
	private JComboBox<String> categoryBox;				//combo box for book categories
	
	//menu
	private JMenuBar menuBar;						//holds the menu items
	private JMenu fileMenu;							//holds the file menu
	private JMenu manageMenu;						//holds the manage menu
	private JMenu helpMenu;							//holds the help menu
	private JMenu searchMenu;						//holds the submenu for library searches
	
	//menu items
	private JMenuItem addBook;					//add book menu option
	private JMenuItem removeBook;				//remove book
	private JMenuItem retrieveBook;				//book search
	private JMenuItem retrieveViaTitle;			// search by title
	private JMenuItem retrieveViaAuthor;		//search by author
	private JMenuItem retrieveViaBookType;		//search by book type
	private JMenuItem retrieveViaPosition;		//search by book position
	private JMenuItem determineSize;			//determine size
	private JMenuItem determineValue;			//determine value
	private JMenuItem sortBooks;				//sort books
	private JMenuItem displayBooks;				//display books
	private JMenuItem importBooks;				//import books
	private JMenuItem exportBooks;				//export books
	private JMenuItem about;					//about the program
	private JMenuItem endProgram;				//end the program
	
	
	/**
	 * Constructor        
	 *
	 * <hr>
	 * Date created: Nov 25, 2014 
	 *
	 * 
	 */
	Window ()
	{
		//call super constructor
		super("Personal Library - Stephanos");
		
		//import library
		lib = new PersonalLibrary("LibraryData/LibraryData.txt");
		
		//set dimensions
		final int WINDOW_WIDTH = 440,		//sets window width
				  WINDOW_HEIGHT = 420,      //sets window height
				  IMG_WIDTH = 100,			//sets image width
				  IMG_HEIGHT = 100;			//sets image height
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		
		setIcon();						//sets the window icon
		
		initComponents ( );     //initiates all components
		
		initMainPanel (); 		//sets up main panel
		add(mainPanel);
		
		//sets up and adds other panels to main
		initBooksPanel ( ); 
		mainPanel.add(booksPanel, BorderLayout.WEST);
		initInfoPanel ( );
		mainPanel.add (fieldsPanel, BorderLayout.CENTER);
		initPhotoPanel ( );
		mainPanel.add(photoPanel, BorderLayout.SOUTH);
				
		addListeners ();		//adds listeners for buttons
			
		//adds menu
		initMenuBar ( );
		this.setJMenuBar (menuBar);
				
		//set default settings
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	/**
	 * Initializes the menu bar         
	 *
	 * <hr>
	 * Date created: Nov 25, 2014
	 *
	 * <hr>
	 */
	private void initMenuBar ( )
	{
		//initializes menu options and adds listeners
		addBook = new JMenuItem("Add a Book");
		addBook.addActionListener (new MenuItem());
		removeBook = new JMenuItem("Remove a Book");
		removeBook.addActionListener (new MenuItem());
		retrieveBook = new JMenuItem("Retrieve a Book");
		retrieveBook.addActionListener (new MenuItem());
		retrieveViaTitle = new JMenuItem("Search by Title");
		retrieveViaTitle.addActionListener (new MenuItem());
		retrieveViaAuthor = new JMenuItem("Search by Author");
		retrieveViaAuthor.addActionListener (new MenuItem());
		retrieveViaBookType = new JMenuItem("Search by Book Type");
		retrieveViaBookType.addActionListener (new MenuItem());
		retrieveViaPosition = new JMenuItem("Search by Position in Library");
		retrieveViaPosition.addActionListener (new MenuItem());
		determineSize = new JMenuItem("Determine Size of Library");
		determineSize.addActionListener (new MenuItem());
		determineValue = new JMenuItem("Determine Value of Library");
		determineValue.addActionListener (new MenuItem());
		sortBooks = new JMenuItem("Sort Books");
		sortBooks.addActionListener (new MenuItem());
		displayBooks = new JMenuItem("Display Books");
		displayBooks.addActionListener (new MenuItem());
		importBooks = new JMenuItem("Import Books");
		importBooks.addActionListener (new MenuItem());
		exportBooks = new JMenuItem("Export Books");
		exportBooks.addActionListener (new MenuItem());
		about = new JMenuItem("About...");
		about.addActionListener (new MenuItem());
		endProgram = new JMenuItem("End Program");
		endProgram.addActionListener (new MenuItem());
		
		//initializes menus
		menuBar = new JMenuBar ();
		fileMenu = new JMenu("File");
		manageMenu = new JMenu("Manage");
		helpMenu = new JMenu("Help");
		searchMenu = new JMenu("Search");
		
		//adds menu items to menus
		searchMenu.add (retrieveViaTitle);
		searchMenu.add (retrieveViaAuthor);
		searchMenu.add (retrieveViaBookType);
		searchMenu.add (retrieveViaPosition);
		fileMenu.add (importBooks);
		fileMenu.add (exportBooks);
		fileMenu.add (determineSize);
		fileMenu.add (determineValue);
		fileMenu.add (displayBooks);
		fileMenu.add (endProgram);
		manageMenu.add (addBook);
		manageMenu.add (removeBook);
		manageMenu.add (searchMenu);
		manageMenu.add (sortBooks);
		helpMenu.add (about);
		
		//adds menus to menu bars
		menuBar.add (fileMenu);
		menuBar.add (manageMenu);
		menuBar.add (helpMenu);
		menuBar.add (searchMenu);
		
	}
	
	
	/**
	 * Sets up the listeners for the menu
	 *
	 * <hr>
	 * Date created: Nov 25, 2014
	 * <hr>
	 * @author Koi Stephanos
	 */
	private class MenuItem implements ActionListener
	{
		/**
		 * determines which menu item was selected and registers the appropriate choice       
		 *
		 * <hr>
		 * Date created: Nov 25, 2014 
		 *
		 * <hr>
		 * @param e, the event triggered by selecting a menu option
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource ( ) == addBook)
			{
				choice = 1;
			}
			else if(e.getSource ( ) == removeBook)
			{
				choice = 2;
			}
			
			else if(e.getSource ( ) == retrieveViaTitle)
			{
				choice = 3;
			}
			else if(e.getSource ( ) == retrieveViaAuthor)
			{
				choice = 4;
			}
			else if(e.getSource ( ) == retrieveViaBookType)
			{
				choice = 5;
			}
			else if(e.getSource ( ) == retrieveViaPosition)
			{
				choice = 6;
			}
			else if(e.getSource ( ) == determineSize)
			{
				choice = 7;
			}
			else if(e.getSource ( ) == determineValue)
			{
				choice = 8;
			}
			else if(e.getSource ( ) == sortBooks)
			{
				choice = 9;
			}
			else if(e.getSource ( ) == displayBooks)
			{
				choice = 10;
			}
			else if(e.getSource ( ) == importBooks)
			{
				choice = 11;
			}
			else if(e.getSource ( ) == exportBooks)
			{
				choice = 12;
			}
			else if(e.getSource ( ) == about)
			{
				new AboutWindow(Window.this);
			}
			else
			{
				choice = 13;
			}
			try
			{
				message = proccessChoice(choice);
				if(message != "")
				JOptionPane.showMessageDialog (null, message);
			}
			catch(Exception e1)
			{
				JOptionPane.showMessageDialog (null, e1.getMessage ( ), "Proccesssing Error", -1);
			}
			
		}
	}

	
	/**
	 * Detects list events and populates fields accordingly
	 *
	 * <hr>
	 * Date created: Dec 4, 2014
	 * <hr>
	 * @author Koi Stephanos
	 */
	private class ListItem implements ListSelectionListener
	{

		/**
		 * Populates fields with appropriate info from selected book within list        
		 *
		 * <hr>
		 * Date created: Dec 4, 2014 
		 *
		 * <hr>
		 * @param e, list event
		 * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
		 */
		@Override
		public void valueChanged (ListSelectionEvent e)
		{
			String selectedBook = "";								//holds selected book title
			
			ArrayList<String> authors = new ArrayList<String>(4);  //holds all authors
			String[] author = new String[4];
			BookType bookType = null;								//book type
			String type = "";										//book type in string format
			String photo = "";										//book cover photo
			double price = 0.0;										//book price
			int numAuthors = 0;
			Book book = null;										//the selected book
			
			//collects the book from list
			selectedBook = bookList.getSelectedValue ( );
			try
			{
				book = lib.retrieveBook (selectedBook);
			}
			catch(Exception e2)
			{
				
			}
			
			//collects book info from book
			bookType = book.getType ( );
			type = bookType.toString ( );
			photo = book.getCoverPhoto ( );
			price = book.getPrice ( );
			authors = book.getAuthor ( );
			numAuthors = authors.size ( );
			for(int i = 0; i < numAuthors; i++)
			{
				author[i] = authors.get (i);
			}
			
			
			//adds book info to window
			titleTxt.setText (selectedBook);
			author1Txt.setText (author[0]);
			author2Txt.setText (author[1]);
			author3Txt.setText (author[2]);
			author4Txt.setText (author[3]);
			priceTxt.setText (df1.format (price));
			categoryBox.setSelectedItem (type);
			ImageIcon image = getPhotoIcon(photo, 180);
			coverPhoto.setIcon(image);
		
		}
		
	}
	
	/**
	 * Adds listeners       
	 *
	 * <hr>
	 * Date created: Dec 4, 2014
	 *
	 * <hr>
	 */
	private void addListeners ( )
	{
		bookList.addListSelectionListener(new ListItem());
		
	}

	/**
	 * Instantiates the photo panel         
	 *
	 * <hr>
	 * Date created: Nov 25, 2014
	 *
	 * <hr>
	 */
	private void initPhotoPanel ( )
	{
		photoPanel = new JPanel();
		photoPanel.add(coverPhoto);
		
	}

	/**
	 * Instantiates the Info Panel        
	 *
	 * <hr>
	 * Date created: Dec 4, 2014
	 *
	 * <hr>
	 */
	private void initInfoPanel ( )
	{
		//sets up panels
		fieldsPanel = new JPanel ();
		fieldsPanel.setLayout (new BorderLayout());
		infoPanel1 = new JPanel ();
		infoPanel1.setLayout (new GridLayout(5,2,5,5));
		infoPanel2 = new JPanel ();
		infoPanel2.setLayout (new FlowLayout());
		
		//adds components to info panels
		infoPanel1.add(titleLbl);
		infoPanel1.add(titleTxt);
		infoPanel1.add(author1Lbl);
		infoPanel1.add(author1Txt);
		infoPanel1.add(author2Lbl);
		infoPanel1.add(author2Txt);
		infoPanel1.add(author3Lbl);
		infoPanel1.add(author3Txt);
		infoPanel1.add(author4Lbl);
		infoPanel1.add(author4Txt);
		infoPanel2.add(categoryLbl);
		infoPanel2.add(categoryBox);
		infoPanel2.add(priceLbl);
		infoPanel2.add(priceTxt);
		
		//adds info panels to fields panel
		fieldsPanel.add(infoPanel1, BorderLayout.NORTH);
		fieldsPanel.add (infoPanel2, BorderLayout.CENTER);
		
	}


	/**
	 * Initiates the Books panel      
	 *
	 * <hr>
	 * Date created: Dec 4, 2014
	 *
	 * <hr>
	 */
	private void initBooksPanel ( )
	{
		//initializes panel and list
		String[] titleList = lib.titlesToArray();
		booksPanel = new JPanel ();
		bookList = new JList<String> (titleList);
		
		//sets up list with border and scroll bar
		bookList.setBorder(BorderFactory.createLineBorder(Color.black,1));
		bookList.setVisibleRowCount(8);
		bookList.setFixedCellWidth (100);
		bookScrollBar = new JScrollPane (bookList);
		
		//adds formatted list to panel
		booksPanel.add(bookScrollBar);
	}


	/**
	 * Initiates main panel        
	 *
	 * <hr>
	 * Date created: Dec 4, 2014
	 *
	 * <hr>
	 */
	private void initMainPanel ( )
	{
		mainPanel = new JPanel ();
		mainPanel.setLayout (new BorderLayout());
	}

	/**
	 * Initiates components, all JLabels, JTextFields, JComboBoxes        
	 *
	 * <hr>
	 * Date created: Dec 4, 2014
	 *
	 * <hr>
	 */
	private void initComponents ( )
	{
		//initializes labels
		titleLbl = new JLabel("Book Title");
		author1Lbl = new JLabel("Author Name");
		author2Lbl = new JLabel("Author 2");
		author3Lbl = new JLabel("Author 3");
		author4Lbl = new JLabel("Author 4");
		categoryLbl = new JLabel("Book Category");
		priceLbl = new JLabel("Price");
		ImageIcon icon = getPhotoIcon("Pictures/icon.png", 180);
		coverPhoto = new JLabel(icon);
		
		//initializes text fields
		titleTxt = new JTextField(12);
		author1Txt = new JTextField(12);
		author2Txt = new JTextField(12);
		author3Txt = new JTextField(12);
		author4Txt = new JTextField(12);
		priceTxt = new JTextField(5);
		priceTxt.setHorizontalAlignment(JTextField.RIGHT);
		
		//initializes combo box
		String [] categories = {"FICTION", "BIOGRAPHY", "HISTORY", "REFERENCE", "OTHER"};
		categoryBox = new JComboBox<String>(categories);
	}

	/**
	 * Sets icon image        
	 *
	 * <hr>
	 * Date created: Nov 25, 2014
	 *
	 * <hr>
	 */
	private void setIcon ()
	{
		try
		{
			File file = new File("Pictures/large_open_book.png");
			BufferedImage icon = ImageIO.read(file);
			setIconImage(icon);
		}
		catch(Exception e)
		{
			
		}
	}
	
	/**
	 * Uses dialog boxes to collect the book info and creates a book with the given parameters
	 * @Exception Exception, thrown if user enters invalid data
	 *
	 * <hr>
	 * Date created: Sep 16, 2014
	 *
	 * <hr>
	 * @return Book, the created book object
	 */
	public Book getBookInfo() throws Exception
	{
		int i;													//loop variable
		BookType type;											//holds the booktype
		String author;											//holds an author input by user
		ArrayList<String> authors = new ArrayList<String>();	//holds all authors for a given book
		
		
		//collects the title and cover photo from the user
		String title = JOptionPane.showInputDialog (null, "What is the Title of the Book?", "Book Info", JOptionPane.QUESTION_MESSAGE);
		String coverPhoto = JOptionPane.showInputDialog (null, "What is the Cover Photo of the Book?", "Book Info", JOptionPane.QUESTION_MESSAGE);
		if(title.equals ("") || coverPhoto.equals (""))
			throw new Exception("Error! Must enter a title and cover photo!");
		
		//collects the book type from the user and converts it to an enum
		String strType = JOptionPane.showInputDialog (null, "What is the Genre? Pick from: FICTION, BIOGRAPHY, HISTORY, REFERENCE, OTHER (Case Sensitive)", "Book Info", JOptionPane.QUESTION_MESSAGE);
		type = BookType.valueOf (strType);
		
		
		//collects the price of the book
		String strPrice = JOptionPane.showInputDialog (null, "What is the Price of the book in decimal form?", "Book Info", JOptionPane.QUESTION_MESSAGE);
		double price = Double.parseDouble (strPrice);
		if(price < 0)
			throw new Exception("Error! Price cannot be negative!");
		
		//collects the amount of authors from the user, and then prompts the user to input that many authors into the authors array
		String numOfAuthors = JOptionPane.showInputDialog (null, "How Many Authors? (Max 4)", "Book Info", JOptionPane.QUESTION_MESSAGE);
		int numberOfAuthors = Integer.parseInt (numOfAuthors);
		if(numberOfAuthors != 1 && numberOfAuthors != 2 && numberOfAuthors != 3 && numberOfAuthors != 4)
			throw new Exception("Error! Number of authors must be between 1 and 4!");
		for(i = 0; i < numberOfAuthors; i++)
			{
				author = JOptionPane.showInputDialog (null, "What is the Author Name?", "Book Info", JOptionPane.QUESTION_MESSAGE);
				authors.add(author);
			}
		if(authors.isEmpty ( ))
			throw new Exception("Error! Must enter at least one author!");
		
		
		//creates and returns the book with the given info and increases the book count by 1
		Book book = new Book(title, price, authors, coverPhoto, type);
		return book;
		
	}//end of getBookInfo()
	
	/**
	 * To take the user's menu selection conduct the appropriate operations   
	 *     
	 *
	 * <hr>
	 * Date created: Oct 15, 2014
	 *
	 * <hr>
	 * @param choice, given from menu, entered by user
	 * @return message, displays the success or failure of the given operation
	 * @throws Exception, multiple if bad input is given or if input is out of the bounds of the library
	 */
	public String proccessChoice(int choice) throws Exception 
	{
		Book book;
		int currentSize = lib.determineSize ( );
		ArrayList<Book> books = new ArrayList<Book>(currentSize);
		String tempChoice;
		int temp;
		String title;
		String author;
		String bookType;
		String message = "";
		String[] titleList;
		DecimalFormat df = new DecimalFormat ("$0.00");
		
		switch(choice)
		{
			//add a new book
			case 1:
				book = getBookInfo();
				lib.addBook(book);
				titleList = lib.titlesToArray();
				bookList.setListData(titleList);
				message = "\nBook Added!";
				break;
			
			//remove a book
			case 2:
				title = JOptionPane.showInputDialog (null, "What is the Title of the Book?", "Book Info", JOptionPane.QUESTION_MESSAGE);
				lib.removeBook (title);
				titleList = lib.titlesToArray();
				bookList.setListData(titleList);
				message = "\nBook Removed";
				break;
				
			//retrieve a book
				//by title
					case 3:
						title = JOptionPane.showInputDialog (null, "What is the Title of the Book?", "Book Info", JOptionPane.QUESTION_MESSAGE);
						book = lib.retrieveBook (title);
						message = "The book with that title is: \n" + book.toString ( );
						break;
						
					//by author
					case 4:
						author = JOptionPane.showInputDialog (null, "What is the Author of the Book?", "Book Info", JOptionPane.QUESTION_MESSAGE);
						books = lib.retrieveBooksByAuthor (author);
						message = "The books with matching authors are as follows: \n" + books.toString ( );
						break;
						
					//by type
					case 5:
						bookType = JOptionPane.showInputDialog (null, "What is the Type of the Book? Must be FICTION, BIOGRAPHY, HISTORY, REFERENCE, or OTHER", "Book Info", JOptionPane.QUESTION_MESSAGE);
						books = lib.retrieveBooksByType (bookType);
						message = "The books with matching types are as follows: \n" + books.toString ( );
						break;
						
					//by position in library
					case 6:
						tempChoice = JOptionPane.showInputDialog (null, "What is the position of the book within the library?", "Book Info", JOptionPane.QUESTION_MESSAGE);
						temp = Integer.parseInt (tempChoice);
						book = lib.retrieveBook (temp-1);
						if(temp > currentSize)
							throw new Exception("Error! Library does not contain that many books!");
						else
							message = "The book in that position is: \n" + book.toString ( );
						break;
			
				
			//determine amount of books
			case 7:
				int amount = lib.determineSize ( );
				message = "\nThe amount of books currently in the library is " + Integer.toString (amount);
				break;
				
			//determine value of books
			case 8:
				double value = lib.libraryValue ( );
				message = "\nThe total value of the library is " + df.format(value);
				break;
				
			//sort books
			case 9:
				lib.sortBooks ( );
				titleList = lib.titlesToArray();
				bookList.setListData(titleList);
				message = "\nBooks Sorted";
				break;
				
			//display books
			case 10:
				message = lib.toString ( );
				break;
				
			//imports existing library from a txt file
			case 11:
				Boolean save = lib.isSaveNeeded ( );
				if(save == true)
				{
					String local = lib.getFileLocal ( );
					lib.saveFile (local);
				}
				//sets up file chooser
				JFileChooser chooser = new JFileChooser("LibraryData");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
				chooser.setFileFilter (filter);
				chooser.setDialogTitle ("Select the existing file! ");
				chooser.setApproveButtonToolTipText ("Select the file you want to open and click it! ");
				
				//collects chosen file from user
				int button = chooser.showOpenDialog (null);
				
				//Processes choice if made
				try
				{
					if(button == JFileChooser.APPROVE_OPTION)
					{
						File file = chooser.getSelectedFile();
						String inputFile = file.getPath ( );
						lib = new PersonalLibrary(inputFile);
						message = "File Imported!";
					}
				}	
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog (null, ex.getMessage ( ));
				}
				break;
				
			//saves library to a txt file
			case 12:
				//sets up file chooser
				JFileChooser chooser2 = new JFileChooser("LibraryData");
				FileNameExtensionFilter filter2 = new FileNameExtensionFilter("Text Files", "txt");
				chooser2.setFileFilter (filter2);
				chooser2.setDialogTitle ("Select the file you would like to save to! ");
				chooser2.setApproveButtonToolTipText ("Select the file you want to open and click it! ");
				
				//collects chosen file from user
				button = chooser2.showOpenDialog (null);
				
				//Processes choice if made
				try
				{
					if(button == JFileChooser.APPROVE_OPTION)
					{
						File file = chooser2.getSelectedFile();
						String outputFile = file.getPath();
						lib.saveFile (outputFile);
						message = "File Saved!";
					}
				}	
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog (null, "Error! No File Seclected!");
				}
				break;

			//end program
			case 13:
				save = lib.isSaveNeeded ( );
				if(save == true)
				{
					String local = lib.getFileLocal ( );
					lib.saveFile (local);
				}
				message = "XXX";
				break;
				
		}
		return message;
	}

	/**
	 * Get and scale the photo to fit the panel         
	 *
	 * <hr>
	 * Date created: Dec 4, 2014
	 *
	 * <hr>
	 * @param fileName, path and file name of the image
	 * @param desiredHeight, the desiredHeight for the formatted pic
	 * @return the image icon of the scaled photo
	 */
	public ImageIcon getPhotoIcon(String fileName, int desiredHeight)
	{
		ImageIcon icon = new ImageIcon(fileName);
		
		Image pic = icon.getImage ( );
		double ratio = (double) pic.getWidth(null)/pic.getHeight (null);
		
		BufferedImage b1 = new BufferedImage ((int) (desiredHeight * ratio), desiredHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics g1 = b1.getGraphics ( );
		g1.drawImage (pic, 0, 0, (int)(desiredHeight * ratio), desiredHeight, null);
		return new ImageIcon(b1);
	}
}
