import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

/**
 * @author Akshay
 *
 */
public class Notepad extends JFrame implements ActionListener {

	/**
	 * @param args
	 */
	JMenu fileMenu;
	JMenu editMenu;
	JMenu format;
	JMenuBar menuBar;
	JMenuItem newItem;
	JMenuItem openItem;
	JMenuItem saveItem;
	JMenuItem saveAsItem;
	JMenuItem exitItem;
	JMenuItem cutItem;
	JMenuItem copyItem;
	JMenuItem pasteItem;
	JMenuItem findreplaceItem;
	JMenu font;
	JMenu background;
	JMenu fontsize;
	JMenuItem arial,TNR,courier,algerian,tahoma,comicsansms;
    JMenuItem fs10,fs12,fs14,fs18,fs25,fs30;
    JMenuItem yellow,red,orange,white;
	JTextArea textBox;
	JScrollPane sp;
	String copy;
	File file=null;
	Font fset;
	String fname;

	public Notepad() {
		fileMenu=new JMenu("File");
		newItem=new JMenuItem("New");
		openItem=new JMenuItem("Open");
		saveItem=new JMenuItem("Save");
		saveAsItem=new JMenuItem("Save As");
		exitItem=new JMenuItem("Exit");
		newItem.addActionListener(this);
		openItem.addActionListener(this);
		saveItem.addActionListener(this);
		saveAsItem.addActionListener(this);
		exitItem.addActionListener(this);
		fileMenu.add(newItem);
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.add(saveAsItem);
		fileMenu.add(exitItem);
		
		format=new JMenu("Format");
		editMenu=new JMenu("Edit");
		cutItem=new JMenuItem("Cut");
		copyItem=new JMenuItem("Copy");
		pasteItem=new JMenuItem("Paste");
		findreplaceItem=new JMenuItem("Find & Replace");
		findreplaceItem.addActionListener(this);
		cutItem.addActionListener(this);
		copyItem.addActionListener(this);
		pasteItem.addActionListener(this);
		editMenu.add(cutItem);
		editMenu.add(copyItem);
		editMenu.add(pasteItem);
		editMenu.add(findreplaceItem);

		menuBar=new JMenuBar();
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(format);
		
		 font = new JMenu("Font");
		 arial = new JMenuItem("Arial");
		 TNR = new JMenuItem("TimesNewRoman");
		 courier = new JMenuItem("Courier");
		 tahoma = new JMenuItem("Tahoma");
		 comicsansms = new JMenuItem("ComicSansMs");
		 algerian = new JMenuItem("Algerian");
		                 
		                 font.add(TNR);
		 font.add(arial);
		 font.add(tahoma);
		 font.add(comicsansms);
		 font.add(algerian);
		 font.add(courier);
		                 format.add(font);
		                 
		                 fontsize=new JMenu("Font Size");
		                 fs10 = new JMenuItem("10");
		 fs12 = new JMenuItem("12");
		 fs14 = new JMenuItem("14");
		 fs18 = new JMenuItem("18");
		 fs25 = new JMenuItem("25");
		 fs30 = new JMenuItem("30");
		                 
		                 fontsize.add(fs10);
		 fontsize.add(fs12);
		 fontsize.add(fs14);
		 fontsize.add(fs18);
		 fontsize.add(fs25);
		 fontsize.add(fs30);
		                 format.add(fontsize);

		                 background=new JMenu("Background");   
		                 yellow = new JMenuItem("Yellow");
		 white = new JMenuItem("White");
		 orange = new JMenuItem("Orange");
		 red = new JMenuItem("Red");
		                 
		                 background.add(yellow);
		                 background.add(white);
		                 background.add(red);
		                 background.add(orange);
		                 format.add(background);
		                 
		                 TNR.addActionListener(this);
		                 algerian.addActionListener(this);
		                 arial.addActionListener(this);
		                 courier.addActionListener(this);
		                 tahoma.addActionListener(this);
		                 comicsansms.addActionListener(this);
		                                 fs10.addActionListener(this);
		                 fs12.addActionListener(this);
		                 fs14.addActionListener(this);
		                 fs18.addActionListener(this);
		                 fs25.addActionListener(this);
		                 fs30.addActionListener(this);
		                                 yellow.addActionListener(this);
		                                 white.addActionListener(this);
		                                 red.addActionListener(this);
		                                 orange.addActionListener(this);
		                                 
		                 
		setJMenuBar(menuBar);
		textBox=new JTextArea();
		sp=new JScrollPane(textBox);
		add(sp);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,600);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Notepad main=new Notepad();
		main.setTitle("New Notepad");

	}
	
    public void actionPerformed(ActionEvent e) {
    	 
        // Menu item actions
        String command = e.getActionCommand();
        if(command.equals("Save")&&file==null) {
        	command="Save As";
        }
 
        if (command.equals("Exit")) {
            System.exit(0);
        }
        if (command.equals("Open")) {
            // Open menu item action
            System.out.println("Open menu item clicked");
        	JFileChooser Open=new JFileChooser();
        	int val=Open.showOpenDialog(null);
        	file=null;
        	if (val == Open.APPROVE_OPTION) {
        		file = Open.getSelectedFile();
        	}
        	else
        		return;
        	try {
        	textBox.setText(null);	
        	BufferedReader in = new BufferedReader(new FileReader(file));
        	String line = in.readLine();
        	while(line != null){
        	  textBox.append(line + "\n");
        	  line = in.readLine();
        	}
        	in.close();
        	Open.setVisible(true);
            return;
        	}
        	catch(Exception exp) {
        		
        	}
        	finally {
        		this.setTitle(Open.getSelectedFile().toString());
        	}
        }
        if (command.equals("Save As")) {
            // Save menu item action
            System.out.println("Save menu item clicked");
        	JFileChooser SaveAs=new JFileChooser();
        	int val=SaveAs.showSaveDialog(null);
        	file=null;
        	if(val==SaveAs.APPROVE_OPTION) {
        		file=new File(SaveAs.getSelectedFile()+".txt");
        	}
        	else
        		return;
        	try {
        		BufferedWriter in=new BufferedWriter(new FileWriter(file));
        		textBox.write(in);
        	}
        	catch(Exception exp) {
        		
        	}
        	finally {
        		this.setTitle(SaveAs.getSelectedFile().toString()+".txt");
        	}
        	SaveAs.setVisible(true);
            return;
        }
        if (command.equals("Save")) {
            // Save menu item action
            System.out.println("Save menu item clicked");

        	try {
        		BufferedWriter in=new BufferedWriter(new FileWriter(file));
        		textBox.write(in);
        	}
        	catch(Exception exp) {
        		
        	}
            return;
        }
        if (command.equals("New")) {
        	//New menu item action
        	System.out.println("New menu item clicked");
        	textBox.setText(null);
        	this.setTitle("New Notepad");
        	return;
        }
        if (command.equals("Cut")) {
        	//Cut menu item action
        	System.out.println("Cut menu item clicked");
        	textBox.cut();
        	return;
        }
        if (command.equals("Copy")) {
        	//Copy menu item action
        	System.out.println("Copy menu item clicked");
        	textBox.copy();
        	return;
        }
        if (command.equals("Paste")) {
        	//Paste menu item action
        	System.out.println("Paste menu item clicked");
        	textBox.paste();
        	return;
        }
        if (command.equals("Find & Replace")) {
        	//Find &Replace menu item action
        	System.out.println("Find & Replace menu item clicked");
        	String find=JOptionPane.showInputDialog(this, "Enter the word to search for:");
        	String replace=JOptionPane.showInputDialog(this, "Enter the word for replacement:");
        	textBox.setText(textBox.getText().replaceAll(find, replace));
        	return;
        }
        if(command.equals("Arial"))
        {
        fset = new Font("Arial",Font.PLAIN,12);
        textBox.setFont(fset);
                                fname = fset.getFontName();
        }
        else if(command.equals("Tahoma"))
        {
        fset = new Font("Tahoma",Font.PLAIN,12);
        textBox.setFont(fset);
                                fname = fset.getFontName();
                }
        else if(command.equals("TimesNewRoman"))
        {
        fset = new Font("Times New Roman",Font.PLAIN,12);
        textBox.setFont(fset);
                                fname = fset.getFontName();
        }
        else if(command.equals("ComicSansMs"))
        {
        fset = new Font("Comic Sans Ms",Font.PLAIN,12);
        textBox.setFont(fset);
                                fname = fset.getFontName();
        }
        else if(command.equals("Courier"))
        {
        fset = new Font("Courier",Font.PLAIN,12);
        textBox.setFont(fset);
                                fname = fset.getFontName();
        }
        else if(command.equals("Algerian"))
        {
                fset = new Font("Algerian",Font.PLAIN,12);
        textBox.setFont(fset);
                                fname = fset.getFontName();
        }
                        else if(command.equals("10"))
        {
        fset = new Font(fname,Font.PLAIN,10);
        textBox.setFont(fset);
        }
        else if(command.equals("12"))
        {
        fset = new Font(fname,Font.PLAIN,12);
        textBox.setFont(fset);
        }
        else if(command.equals("14"))
        {
        fset = new Font(fname,Font.PLAIN,14);
        textBox.setFont(fset);
        }
        else if(command.equals("18"))
        {
        fset = new Font(fname,Font.PLAIN,18);
        textBox.setFont(fset);
        }
        else if(command.equals("25"))
        {
        fset = new Font(fname,Font.PLAIN,25);
        textBox.setFont(fset);
        }
        else if(command.equals("30"))
        {
        fset = new Font(fname,Font.PLAIN,30);
        textBox.setFont(fset);
        }
                       else if(command.equals("Yellow"))
        {
        textBox.setBackground(Color.yellow);
        }
                       else if(command.equals("White"))
        {
        textBox.setBackground(Color.white);
        }
                       else if(command.equals("Red"))
        {
        textBox.setBackground(Color.red);
        }
                       else if(command.equals("Orange"))
        {
        textBox.setBackground(Color.orange);
        }
        
    }

}
