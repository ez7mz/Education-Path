package MainInterfaces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ListAdminOrg extends JFrame{
	public String Date;
	String[] Head = {"Id", "Nom", "Prenom", "Fonction","Event Date", "Salle Num", "Sujet"};
	JTable Tab;
	
	public ListAdminOrg(String Date) {
		super("Admin Events Organisation List");
		this.Date = Date;
		
		Object[][] data = extractTable();
		Tab = new JTable(data, Head);
		
		Tab.setBounds(30,40,200,300);
		JScrollPane sp = new JScrollPane(Tab);
		this.add(sp);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setSize(600, 400);
		this.setLocationRelativeTo(null);
	}
	
	
	
	private Object[][] extractTable(){
		
		Object [][] Tab = new Object[100][7];
		
		ResultSet get = SelectFrom();
		
		try {
			int i=0;
			while(get.next()) {
				Object [] Temp = new Object[7];
				Temp[0] = get.getString("IdAdmin");
				Temp[1] = get.getString("Nom");
				Temp[2] = get.getString("Prenom");
				Temp[3] = get.getString("Fonction");
				Temp[4] = get.getString("EventDate");
				Temp[5] = get.getString("SalleNum");
				Temp[6] = get.getString("Sujet");
				Tab[i] = Temp;
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Tab;
	}
	
	private ResultSet SelectFrom() {
		ResultSet result = null;
		// test : name of DataBase
		String url = "jdbc:mysql://localhost:3306/ez7mz";
		// SQL command in String Format
		String req = "SELECT admins.IdAdmin, Nom, Prenom, Fonction, EventDate, SalleNum, Sujet FROM events, admins WHERE events.IdAdmin!=0 AND events.IdAdmin=admins.IdAdmin AND EventDate='"+Date+"'";
		try {
			// url + login + password of phpMyAdmin
			Connection connexion = DriverManager.getConnection(url, "ez7mz", "ez7mz");
			Statement stat = connexion.createStatement();
			result = stat.executeQuery(req);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return result;
	}
	
}
