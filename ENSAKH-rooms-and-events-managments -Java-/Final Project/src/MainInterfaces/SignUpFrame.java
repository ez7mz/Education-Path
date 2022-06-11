package MainInterfaces;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

// Frame Access just by Admins to add members
public class SignUpFrame extends JFrame implements ProjectTools{
	
	private int Id;
	private String CurrentStat;
	
	JPanel EtudiantContentPane;
	JPanel ProfContentPane;
	JPanel AdminContentPane;
	
//	@ Constructor Of Class That display The Frame
	public SignUpFrame(int Id,String CurrentStat) {
//		@ Frame name
		super("Inscriver Vous");
		
		this.Id = Id;
		this.CurrentStat = CurrentStat;
		
//		@ Call Menu Creation Method ==================================
		MenuMaker();
		
//		@Creation du Tabbed Pane ================================
		getContentPane().add(TabbedMaker());
		
//		@Frame Settings =============================================
		FrameSetting(this);
	}
	
//	@ MenuBar Maker	
	private void MenuMaker() {
		JMenuBar MenuBar = new JMenuBar();
		JMenu menuEdition = new JMenu("Edition"); // Menu Title
		
		JMenuItem newIt = new JMenuItem("New"); // Menu First Item
		newIt.addActionListener(this::New);
		newIt.setIcon(new ImageIcon(getClass().getResource("icons/new.png"))); // Add Icon
		newIt.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK)); // Add short-cut to Item
		newIt.setMnemonic('N'); // Add Mnemonic to Item
		
		
		JMenuItem Back = new JMenuItem("Go Back"); // Menu Second Item
		Back.addActionListener(this::GoBack); // Add Action to Item
		Back.setIcon(new ImageIcon(getClass().getResource("icons/back.png"))); // Add Icon
		Back.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK)); // Add short-cut to Item
		Back.setMnemonic('R'); // Add Mnemonic to Item
		
		JMenuItem Exit = new JMenuItem("Exit!"); //Menu third Item
		Exit.addActionListener(this::Exit); // Add Action to Item
		Exit.setIcon(new ImageIcon(getClass().getResource("icons/exit.png"))); // Add Icon
		Exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK)); // Add short-cut to Item
		Exit.setMnemonic('X'); // Add Mnemonic to Item
		
		// Add Items to a Menu
		menuEdition.add(newIt);
		menuEdition.add(Back);
		menuEdition.add(Exit);
		
		// Add Menu to frame menu bar
		MenuBar.add(menuEdition);
		
		setJMenuBar(MenuBar);	// set Frame Menu Bar
	}
	
	private JTabbedPane TabbedMaker() { // Group bellow Panel in TabbedPane
		JTabbedPane tpane = new JTabbedPane(); // Define a TabbedPane
		// Add Items to TabbedPane
		tpane.add("Member d'Adminstration",AdminPaneMaker());
		tpane.add("Professeurs",ProfPaneMaker());
		tpane.add("Etudiants",EtudiantPaneMaker());
		
		return tpane;
	}
	
//	@ Necessary Panel Maker 
	private JPanel EtudiantPaneMaker() { // Etudiant Panel Maker
		EtudiantContentPane = new JPanel();
		mainEtudiant Obj = new mainEtudiant();
		EtudiantContentPane = Obj.mainEtudiantMaker(); // Call a method that make sign up etudiant form panel

		return EtudiantContentPane;
	}
	
	private JPanel ProfPaneMaker() { // Prof Panel Maker
		ProfContentPane = new JPanel();
		mainProf Obj = new mainProf();
		ProfContentPane = Obj.mainProfMaker(); // Call a method that make sign up Prof form panel
		return ProfContentPane;
	}
	
	private JPanel AdminPaneMaker() {	// Admin Panel Maker
		AdminContentPane = new JPanel();
		mainAdmin Obj = new mainAdmin();
		AdminContentPane = Obj.mainAdminMaker(); // Call a method that make sign up Admins form panel
		return AdminContentPane;
	}
	
//	@ MenuBar Item Event
	private void New(ActionEvent e) {
		dispose();
		new SignUpFrame(Id, CurrentStat);
	}

	private void GoBack(ActionEvent e) {
		dispose();
		new mainIndex(Id, CurrentStat);
	}	
	
	private void Exit(ActionEvent e) {
		int choix = JOptionPane.showConfirmDialog(null, "Are you sure?", "Close", JOptionPane.YES_NO_OPTION);
		if(choix == JOptionPane.YES_OPTION) {
			dispose();
		}
	}
}
