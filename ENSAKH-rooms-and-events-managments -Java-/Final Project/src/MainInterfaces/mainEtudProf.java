package MainInterfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import MainClasses.Etudiants;

public class mainEtudProf extends JFrame implements ProjectTools{
	
//	@ Attributes to save the user Id and Statute to avoid every time ask him to enter an information
//		-- All information are extracted from Data Base --
	private String CurrentStat;
	private int Id;
	
//	@ Necessary Panes Declaration
	JPanel IContentPane;
	JPanel Ipane1;
	JPanel Ipane2;
	JPanel Ipane3;

//	@ Necessary Items Declaration	
	JLabel Ltitle;
	JLabel CopyRight;
	
	JButton BtnRsrv;
	JLabel Btn2Label;
	JButton BtnOrg;
	JLabel Btn3Label;
	JButton BtnHelp;
	
//	@ Define Empty Space Just to Fix Design
	JLabel [] empty = new JLabel[4];{
		for(int i=0;i<empty.length;i++) {
			empty[i] = new JLabel("",(int)CENTER_ALIGNMENT);
		}
	}
	

//	@ Constructor Of Class That display The Frame
	public mainEtudProf(int Id,String CurrentStat) {
//		@Frame Name
		super(CurrentStat+" Place");
		this.Id = Id;
		this.CurrentStat = CurrentStat;
		
//		@ >> Pane1 : Title Pane ===========================================
		Ipane1 = new JPanel();
		Ltitle = new JLabel("Welcome "+getInfo().getNom()+" "+getInfo().getPrenom()+" To ReservatLand "+CurrentStat+" Place",new ImageIcon("D:\\JAVA PROJECT\\icons\\logo.png"), (int)CENTER_ALIGNMENT);
		Ltitle.setFont(new Font("Montserrat", Font.BOLD, 16));
		Ltitle.setPreferredSize(new Dimension(600,75));
		Ipane1.add(Ltitle);
		
//		@ >> Pane2 : Button Choice Pane ======================================
		Btn2Label = new JLabel("Reserver une Salle",(int)CENTER_ALIGNMENT);
		Btn2Label.setForeground(Color.decode("005555"));
		Btn2Label.setFont(new Font("Montserrat", Font.BOLD, 13));
		BtnRsrv = new JButton(new ImageIcon(getClass().getResource("icons/rsrv.png")));
		BtnRsrv.setHorizontalAlignment((int)CENTER_ALIGNMENT);
		BtnRsrv.setToolTipText("Resrver une Salle");
		
		Btn3Label = new JLabel("Organiser un Evenment",(int)CENTER_ALIGNMENT);
		Btn3Label.setForeground(Color.decode("005555"));
		Btn3Label.setFont(new Font("Montserrat", Font.BOLD, 13));
		BtnOrg = new JButton(new ImageIcon(getClass().getResource("icons/org.png")));
		BtnOrg.setHorizontalAlignment((int)CENTER_ALIGNMENT);
		BtnOrg.setToolTipText("Organiser un evenment");
		
		Ipane2 = new JPanel(new GridLayout(2,2,40,0));
		Ipane2.add(BtnRsrv);
		Ipane2.add(BtnOrg);
		Ipane2.add(Btn2Label);
		Ipane2.add(Btn3Label);
		
//		-----> Buttons Events
		BtnRsrv.addActionListener(this::Reserver);	// easy way to Call a method in ActionListener with this Operator
		BtnOrg.addActionListener(this::Organiser);
		
//		@ >> Pane3 : Footer Pane ===========================================
		CopyRight = new JLabel(" "+Character.toString(169)+"ez7mz | made with Love!");
		CopyRight.setFont(new Font("consolas",Font.BOLD,16));
		BtnHelp = new JButton("Help?", new ImageIcon(getClass().getResource("icons/help.png")));
		
		
		JPanel HelpPane = new JPanel(new GridLayout(1,3,10,10));
		HelpPane.add(empty[0]);
		HelpPane.add(empty[1]);
		HelpPane.add(BtnHelp);
		
		Ipane3 = new JPanel(new GridLayout(1,2,50,50));
		Ipane3.add(CopyRight);Ipane3.add(HelpPane);
		
//		-----> Help Admin Event
		BtnHelp.addActionListener(this::Help); // easy way to Call a method in ActionListener with this Operator
//		@======================= Content Pane =====================================
		empty[2].setPreferredSize(new Dimension(50,50));
		empty[3].setPreferredSize(new Dimension(50,50));
		
		IContentPane = new JPanel(new BorderLayout(50,50));
		IContentPane.add(Ipane1,BorderLayout.NORTH);
		IContentPane.add(Ipane2,BorderLayout.CENTER);
		IContentPane.add(Ipane3,BorderLayout.SOUTH);
		IContentPane.add(empty[2],BorderLayout.WEST);
		IContentPane.add(empty[3],BorderLayout.EAST);
		
		JMenuItem Desconnect = new JMenuItem("Disconnect", new ImageIcon(getClass().getResource("icons/exit.png")));
		Desconnect.addActionListener(this::Disc);
		JMenuBar menu = new JMenuBar();
		menu.add(Desconnect);
		
		setJMenuBar(menu);
		this.getContentPane().add(IContentPane);
		
//		====================== Frame Setting ===================================
		setVisible(true);
//		pack();
		setSize(700,400);
		setResizable(false);
		this.setLocationRelativeTo(null);
		this.getContentPane().add(IContentPane);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		
	}
	
	private void Reserver(ActionEvent e) {  // Click reservation Button Action
		dispose(); // Dispose current frame
		new ReservationPlace(Id, CurrentStat); // Open destination frame -- parameter in purpose to save user id and status --
	}
	
	private void Organiser(ActionEvent e) {	// Click reservation Button Action
		dispose();	// Dispose current frame
		new OrganisationPlace(Id, CurrentStat); // Open destination frame -- parameter in purpose to save user id and status --
	}
	
	private void Disc(ActionEvent e) {
		if(JOptionPane.showConfirmDialog(null, "Are you sure?", "Disconnect", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			dispose();
			new IndexLogin();
		}
	}
	
	public Etudiants getInfo() { // Extract Data from DB -- get user Info --
		String req = null;
		
		Etudiants Etd = new Etudiants();
		
		if(CurrentStat.equals("Etudiant")) { // check the status to know wish Query should be execute
			req = "SELECT Nom, Prenom FROM etudiants WHERE IdEtudiant="+Id;
		}else if(CurrentStat.equals("Prof")) {
			req = "SELECT Nom, Prenom FROM professeurs WHERE IdProf="+Id;
		}
		
		ResultSet result = null;
		try {
			Statement stat = connexion();
			result = stat.executeQuery(req); // Take First and Last Name of user to display it in Home Frame
			while(result.next()) {
				Etd = new Etudiants(result.getString("Nom"), result.getString("Prenom"));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return Etd;
	}
	
}
