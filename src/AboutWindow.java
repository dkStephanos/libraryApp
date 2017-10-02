import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * ---------------------------------------------------------------------------
 * File name: AboutWindow.java
 * Project name: Books
 * ---------------------------------------------------------------------------
 * Creator's name and email: Koi Stephanos, stephanos@goldmail.etsu.edu
 * Course:  CSCI 1260
 * Creation Date: Dec 4, 2014
 * ---------------------------------------------------------------------------
 */

/**
 * To provide an about window for the personal library gui
 *
 * <hr>
 * Date created: Dec 4, 2014
 * <hr>
 * @author Koi Stephanos
 */
public class AboutWindow extends JDialog
{	
	private static final long	serialVersionUID	= 1L; 		//needed for gui
	private JLabel icon;										//holds icon info
	private JLabel title;										//holds title info
	private JLabel author;										//holds author info
	private JLabel copyright;									//holds copyright info
	private JPanel grid;										//holds JPanels
	private JPanel leftPanel;									//holds JLabel image
	private JPanel rightPanel;									//holds JLabels
	
	
	/**
	 * Constructor        
	 *
	 * <hr>
	 * Date created: Dec 4, 2014 
	 *
	 * 
	 * @param parent
	 */
	public AboutWindow (JFrame parent )
	{
		super(parent, "About Personal Library...", true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		grid = new JPanel();
		grid.setLayout(new GridLayout(1,2));
		setLeftPanel();
		setRightPanel();
		add (grid);
		setAboutDialogIcon();
		this.setResizable (false);
		pack();
		setLocationRelativeTo(parent);
		setVisible(true);
		
		
	}

	/**
	 * Sets about window icon      
	 *
	 * <hr>
	 * Date created: Dec 4, 2014
	 *
	 * <hr>
	 */
	private void setAboutDialogIcon ( )
	{
		try
		{
			File file = new File("Pictures/fireworks.png");
			BufferedImage icon = ImageIO.read(file);
			setIconImage(icon);
		}
		catch(Exception e)
		{
			
		}
		
	}

	/**
	 * Sets up right panel with all about info      
	 *
	 * <hr>
	 * Date created: Dec 4, 2014
	 *
	 * <hr>
	 */
	private void setRightPanel ( )
	{
		rightPanel = new JPanel(new GridLayout(3,1));
		
		title = new JLabel("Personal Library Manager");
		title.setFont (new Font("Serif", Font.BOLD, 32));
		title.setForeground (Color.BLUE);

		author = new JLabel("Author: Koi Stephanos");
		author.setFont (new Font("Serif", Font.BOLD + Font.ITALIC, 24));

		copyright = new JLabel("Copyright: Koi Stephanos, 2014");
		copyright.setFont (new Font("Serif", Font.ITALIC, 16));

		rightPanel.add(title);
		rightPanel.add(author);
		rightPanel.add(copyright);
		rightPanel.setBorder (BorderFactory.createEmptyBorder (10,10,10,10 ));
		
		grid.add (rightPanel);
		
	}

	/**
	 * Sets up left panel with about image icon       
	 *
	 * <hr>
	 * Date created: Dec 4, 2014
	 *
	 * <hr>
	 */
	private void setLeftPanel ( )
	{
		leftPanel = new JPanel();
		
		ImageIcon image = new ImageIcon("Pictures/icon.png");
		icon = new JLabel(image);
		
		try
		{
			leftPanel.add (icon);
		}
		catch(Exception e)
		{
			
		}
		
		leftPanel.setBorder (BorderFactory.createEmptyBorder (10,10,10,10 ));
		grid.add (leftPanel);
	}

}
