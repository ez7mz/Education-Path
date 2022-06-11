package MainInterfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument.Content;

import com.mysql.cj.log.Log;

// @ First class in the App -- Login Interface --
public class IndexLogin extends JFrame implements ProjectTools{

//	@ Necessary Panes Declaration
	JPanel ContentPane;
	JPanel pane;
	JPanel pane1;
	JPanel pane2;
	
//	@ Necessary Items Declaration	
	JLabel LTitle;
	JLabel img;
	
	JLabel Luser;
	JTextField user;
	JLabel Lpwd;
	JPasswordField pwd;
	
	JLabel LStatut;
	JComboBox Statut;
	
	JButton Login;
	JButton Help;
	
//	@ Constructor Of Class That display The Frame
	public IndexLogin() {
//		@ Frame Title
		super("Login");
//		@ Title Pane
		pane1 = new JPanel();
		LTitle = new JLabel("Do you Have Acces to the application?");
		pane1.add(LTitle);
		
//		@ Login Pane
		Luser = new JLabel(new ImageIcon(getClass().getResource("icons/profile.png")));
		user = new JTextField("user");
		user.setForeground(Color.LIGHT_GRAY);
		user.addFocusListener(new FocusListener() { // Case Focus Listener to make the case interactive
			@Override
			public void focusLost(FocusEvent e) { // Case behavior When case lost the focus
				if(user.getText().isEmpty()) {
					user.setForeground(Color.LIGHT_GRAY);
					user.setText("user");
				}
			}	
			@Override
			public void focusGained(FocusEvent e) { // Case behavior When case lost the focus
				if(user.getText().equals("user")) {
					user.setForeground(Color.BLACK);
					user.setText(null);
				}
			}
		});
		
		Lpwd = new JLabel(new ImageIcon(getClass().getResource("icons/pswd.png"))); // Add Icons instead of text in form
		pwd = new JPasswordField("Password");
		pwd.setForeground(Color.LIGHT_GRAY);
		pwd.setForeground(Color.LIGHT_GRAY);
		pwd.addFocusListener(new FocusListener() { // Case Focus Listener to make the case interactive
			@Override
			public void focusLost(FocusEvent e) {	// Case behavior When case lost the focus
				if(pwd.getText().isEmpty()) {
					pwd.setForeground(Color.LIGHT_GRAY);
					pwd.setText("Password");
				}
			}	
			@Override
			public void focusGained(FocusEvent e) {	// Case behavior When case lost the focus
				if(pwd.getText().equals("Password")) {
					pwd.setForeground(Color.BLACK);
					pwd.setText(null);
				}
			}
		});
		
		LStatut = new JLabel(new ImageIcon(getClass().getResource("icons/status.png")));
		Statut = new JComboBox();
		Statut.addItem("Admin");
		Statut.addItem("Prof");
		Statut.addItem("Etudiant");
		Login = new JButton("Login");
		Login.addActionListener(this::LoginAct); // easy way to Call a method in ActionListener with this Operator
		Help = new JButton("Help?", new ImageIcon(getClass().getResource("icons/help.png")));
		Help.addActionListener(this::Help);
		
		
		pane2 = new JPanel(new GridLayout(4,2,15,10)); // Panel with 4 rows 2 columns -- 15px hgap, 10px vgap --
		pane2.add(Luser);pane2.add(user);
		pane2.add(Lpwd);pane2.add(pwd);
		pane2.add(LStatut);pane2.add(Statut);
		pane2.add(Login);pane2.add(Help);
		
		pane = new JPanel(new BorderLayout(0, 20));
		pane.add(LTitle, BorderLayout.NORTH);
		pane.add(pane2, BorderLayout.CENTER);
		
//		@ Content Pane
		ContentPane = new JPanel(new GridLayout(1,2,10,10));
		img = new JLabel(new ImageIcon(getClass().getResource("icons/mainBack.png")));
		ContentPane.add(img);
		ContentPane.add(pane);
		
		getContentPane().add(ContentPane); // Add panels to Frame
		
//		@ Frame Settings
		setVisible(true);
		setResizable(false);
		setSize(500,200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	
	
	public String LoginAct(ActionEvent e) { // Button Click Action method
		String Stat=null;
		
		if(Statut.getSelectedItem().equals("Admin")) { // Check users Status
			Stat = "Admin";
		}else if(Statut.getSelectedItem().equals("Prof")) {
			Stat = "Prof";
		}else if(Statut.getSelectedItem().equals("Etudiant")) {
			Stat = "Etudiant";
		}
		
		if(LoginVerf(Stat)) {	// check if the inputs is valid or not
			if(Stat.equals("Admin")) {
				dispose();
				new mainIndex(Integer.parseInt(user.getText()), Stat);
			}else if(Stat.equals("Prof") || Stat.equals("Etudiant") ) {
				dispose();
				new mainEtudProf(Integer.parseInt(user.getText()), Stat);
			}
		}else {
			user.setText(null);
			pwd.setText(null);
			JOptionPane.showMessageDialog(null, "User Or Password Incorrect!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		return Stat;
	}
	
	public boolean LoginVerf(String st) { // Extract Data from DB and Verify if the login is valid
		boolean Flag = false;
		ResultSet set = SelectFrom(st); // ResultSet contains table column from DB
		try {
			while(set.next()) {
				String BDuser = set.getString("Id"+st);
				String BDpwd = set.getString("Nom");
				if(user.getText().equals(BDuser) && pwd.getText().equals(BDpwd)) {
					Flag = true;
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Flag;
	}
	
	private ResultSet SelectFrom(String st) { // Extract Ids and Names From DataBase in purpose of comparing it with users Inputs
		ResultSet result = null;
		String Id = null;
		String tabname = null;
		
		if(st.equals("Admin")) { // check the status to know wish table to extract date from
			Id = "IdAdmin";
			tabname = "admins";
		}else if(st.equals("Prof")) {
			Id = "IdProf";
			tabname = "professeurs";
		}else if(st.equals("Etudiant")) {
			Id = "IdEtudiant";
			tabname = "etudiants";
		}
		// SQL Query in String Format
		String req = "SELECT "+Id+", Nom FROM "+tabname;
		Statement statment = connexion(); // connect to DB with -- connexion is a method defined in ProjectTool --
		try {
			result = statment.executeQuery(req); // Execute query and return the result
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result; // return the clean data in a ResultSet
	}
}
