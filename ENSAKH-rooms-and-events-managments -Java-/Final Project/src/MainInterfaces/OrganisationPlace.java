package MainInterfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class OrganisationPlace extends JFrame implements ProjectTools{
	
	private int Id;
	private String CurrentStat;
	
//	# empty place just to fix design
	JLabel [] empty = new JLabel[14];{
		for(int i=0;i<empty.length;i++) {
			empty[i] = new JLabel("",Center);
		}
	}
	
//	@ Constructor Of Class That display The Frame
	public OrganisationPlace(int Id, String CurrentStat) {
		super("Organisation");
		this.Id = Id;
		this.CurrentStat = CurrentStat;
		
		MenuMaker();
		
		JTabbedPane Tpane = new JTabbedPane();
		OrgPane Obj = new OrgPane(this.Id, this.CurrentStat);
		AnnOrgPane Obj1 = new AnnOrgPane(this.Id, this.CurrentStat);
		ListOrgPane Obj2 = new ListOrgPane(this.Id, this.CurrentStat);
		Tpane.add("Event Organisation",Obj.OrganisationPlaceMaker());
		Tpane.add("Event Annulation ", Obj1.EventAnnulationPlaceMaker());
		Tpane.add("Lister", Obj2.ListOrgPaneMaker());
		getContentPane().add(Tpane);
		FrameSetting(this);
	}

//	@ Method Create MenuBar
	private void MenuMaker() {
		JMenuBar MenuBar = new JMenuBar();
		JMenu menuEdition = new JMenu("Edition");
		
		JMenuItem newIt = new JMenuItem("New");
		newIt.addActionListener(this::New);
		newIt.setIcon(new ImageIcon(getClass().getResource("icons/new.png")));
		newIt.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
		newIt.setMnemonic('N');
		
		
		JMenuItem Back = new JMenuItem("Go Back");
		Back.addActionListener(this::GoBack);
		Back.setIcon(new ImageIcon(getClass().getResource("icons/back.png")));
		Back.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK));
		Back.setMnemonic('R');
		
		JMenuItem Exit = new JMenuItem("Exit!");
		Exit.addActionListener(this::Exit);
		Exit.setIcon(new ImageIcon(getClass().getResource("icons/exit.png")));
		Exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
		Exit.setMnemonic('X');
		
		
		menuEdition.add(newIt);
		menuEdition.add(Back);
		menuEdition.add(Exit);
		
		MenuBar.add(menuEdition);
		setJMenuBar(MenuBar);
	}
	
//	@ MenuBar Item Event
	private void New(ActionEvent e) {
		dispose();
		new OrganisationPlace(Id, CurrentStat);
	}

	private void GoBack(ActionEvent e) {
		dispose();
		if(CurrentStat.equals("Admin")) {
			new mainIndex(Id, CurrentStat);
		}else {
			new mainEtudProf(Id, CurrentStat);
		}
	}	
	
	private void Exit(ActionEvent e) {
		int choix = JOptionPane.showConfirmDialog(null, "Are you sure?", "Close", JOptionPane.YES_NO_OPTION);
		if(choix == JOptionPane.YES_OPTION) {
			dispose();
		}
	}
	
}
