package MainInterfaces;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

// interface Definition
public interface ProjectTools{

//	@ Define Constant 
	final int Center = (int)java.awt.Component.CENTER_ALIGNMENT;
	final String Tab = "                            "; // Huge Space
	final String Flesh = "\u2022    "; // some symbol
	final String Symbol = "\u25b6    ";
	final String tab = "    ";
	
//	@ Frame Setting -- Default properties for Frames --
	default public void FrameSetting(JFrame Frame) {
		Frame.setVisible(true); // Show Frame
		Frame.setResizable(false);
		Frame.setSize(700,400);;	// Set Frame Size -- width, Height --
		Frame.setLocationRelativeTo(null); // Set Frame Location Relative to null -- Desktop --
		Frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); // Dispose Frame after closing the program
	}
	
//	@ Select SalleNum From DataBase -- ResultSet of SalleNum --
	default public ResultSet SelectSalleNum() {
		String req = "SELECT SalleNum FROM salles";
		ResultSet res = null;
		Statement stat = connexion();
		try {
			res = stat.executeQuery(req);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
//	@ Create Connexion Statement with DataBase
	default public Statement connexion() {
		String url = "jdbc:mysql://localhost:3306/ez7mz";
		Connection connexion;
		Statement stat=null;
		try {
			connexion = DriverManager.getConnection(url, "ez7mz", "ez7mz");
			stat = connexion.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return stat;
	}
	
//	@ Change text and TextField look to match Error looking
	default public void errorLook(JTextField place) {
		place.setForeground(Color.RED);
		place.setBorder(new LineBorder(Color.RED));
	}
//	@ Change text and TextField look Back to normal.
	default public  void normalLook(JTextField place) {
		place.setForeground(Color.BLACK);
		place.setBorder(new LineBorder(Color.BLACK));
	}

//	@ set Case TextField to null when contains PlaceHolder
	default public void HidePlaceHolder(JTextField place, String S) {
		if(place.getText().equals(S)) {
			place.setText(null);
			place.setForeground(Color.BLACK);
        }
	}
	
//	@ Show Case TextField PlaceHolder when case is empty
	default public void ShowPlaceHolder(JTextField place, String S) {
		if(place.getText().isEmpty()) {
			place.setForeground(Color.LIGHT_GRAY);
			place.setText(S);
		}
		
	}
	
//	@ Make TextField Interactive when a error is raised
	default public boolean ErrorBeahavor(JTextField place, String err, String holder) {
		place.setText(err);
		place.setForeground(Color.RED);
		place.setBorder(new LineBorder(Color.RED));
		place.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				ShowPlaceHolder(place, holder);
			}
			@Override
			public void focusGained(FocusEvent e) {
				place.setBorder(new LineBorder(Color.BLACK));
				HidePlaceHolder(place, err);
			}
		});
		return false;
	}
	
//	@ Extract A text from a textFile and display it in JOptionPane
	default public void Help(ActionEvent e) {
		
		String HelpMessage = "Help Section : \n";
		
		 InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream ("help.txt"));
		Scanner myReader = new Scanner(reader);
		while (myReader.hasNextLine()) {
			String data = myReader.nextLine();
			HelpMessage += data+"\n";
		}
		myReader.close();
		JOptionPane.showMessageDialog(null, HelpMessage);
	}
	
}
