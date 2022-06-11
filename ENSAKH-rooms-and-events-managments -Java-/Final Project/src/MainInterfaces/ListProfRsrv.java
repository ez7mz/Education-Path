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

public class ListProfRsrv extends JFrame{
	public String Date;
	String[] Head = {"Id", "Nom", "Prenom", "Departement","Reservation Date", "Salle Num"};
	JTable Tab;
	
	public ListProfRsrv(String Date) {
		super("Profs Reservation List");
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
		
		Object [][] Tab = new Object[100][6];
		
		ResultSet get = SelectFrom();
		
		try {
			int i=0;
			while(get.next()) {
				Object [] Temp = new Object[6];
				Temp[0] = get.getString("IdProf");
				Temp[1] = get.getString("Nom");
				Temp[2] = get.getString("Prenom");
				Temp[3] = get.getString("Departement");
				Temp[4] = get.getString("ResvDate");
				Temp[5] = get.getString("SalleNum");
				Tab[i] = Temp;
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Tab;
	}
	
	private ResultSet SelectFrom() {
		ResultSet result = null;
		// test : name of DataBase
		String url = "jdbc:mysql://localhost:3306/ez7mz";
		// SQL command in String Format
		String req = "select professeurs.IdProf, Nom, Prenom, Departement, ResvDate, SalleNum from reservations, professeurs WHERE reservations.IdProf!=0 AND reservations.IdProf=professeurs.IdProf AND ResvDate='"+Date+"'";
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
