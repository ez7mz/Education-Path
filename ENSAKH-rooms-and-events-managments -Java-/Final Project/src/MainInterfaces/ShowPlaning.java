package MainInterfaces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ShowPlaning extends JFrame{
	
	private String fromdate;
	private String todate;
	private String table;
	
	String[] RsrvHead = {"Number", "Salle", "Date"};
	String[] OrgHead = {"Number", "Salle", "Date", "Sujet"};
	JTable Tab;
	
	public ShowPlaning(String fromdate, String todate, String table) {
		super("Palning between "+fromdate+" And "+todate);
		this.fromdate = fromdate;
		this.todate = todate;
		this.table = table;
		
		if(table.equals("reservations")) {
			Object[][] data = extractRsrvTable();
			Tab = new JTable(data, RsrvHead);
		}else if(table.equals("events")) {
			Object[][] data = extractOrgTable();
			Tab = new JTable(data, OrgHead);
		}
		
		Tab.setBounds(30,40,200,300);
		JScrollPane sp = new JScrollPane(Tab);
		this.add(sp);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setSize(450, 400);
		this.setLocationRelativeTo(null);
		
	}
	
private Object[][] extractRsrvTable(){
		
		Object [][] Tab = new Object[100][3];
		
		ResultSet get = SelectFrom();
		
		try {
			int i=0;
			while(get.next()) {
				Object [] Temp = new Object[3];
				Temp[0] = get.getString("ResvNum");
				Temp[1] = get.getString("ResvDate");
				Temp[2] = get.getString("SalleNum");
				Tab[i] = Temp;
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Tab;
	}

	private Object[][] extractOrgTable(){
		
		Object [][] Tab = new Object[100][4];
		
		ResultSet get = SelectFrom();
		
		try {
			int i=0;
			while(get.next()) {
				Object [] Temp = new Object[4];
				Temp[0] = get.getString("EventNum");
				Temp[1] = get.getString("EventDate");
				Temp[2] = get.getString("SalleNum");
				Temp[3] = get.getString("Sujet");
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
		// SQL Query in String Format
		String req = null;
		if(table.equals("reservations")) {
			req = "SELECT ResvNum, ResvDate, SalleNum FROM reservations WHERE ResvDate BETWEEN '"+fromdate+"' AND '"+todate+"' ORDER BY ResvDate ASC";
		}else if(table.equals("events")) {
			req = "SELECT EventNum , EventDate, SalleNum, Sujet FROM events WHERE EventDate BETWEEN '"+fromdate+"' AND '"+todate+"' ORDER BY EventDate ASC";
		}
		
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
