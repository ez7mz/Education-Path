package MainInterfaces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ListRsrvPane extends JFrame implements ProjectTools{

	private int Id;
	private String CurrentStat;
	
//	@ Define Table header
	String[] Head = {"Id", "Nom", "Prenom", "Reservation Date", "Salle Num", "Statut"};
	
	JTable ListRsrvTab;
	
	public ListRsrvPane(int Id, String CurrentStat) {
		this.Id = Id;
		this.CurrentStat = CurrentStat;	
	}
	
//	@ method Create ListRsrv panel 
	public JScrollPane ListRsrvPaneMaker() {
		
//		@ Create JTable
		Object[][] data = extractTable();
		ListRsrvTab = new JTable(data, Head);
		ListRsrvTab.setAutoCreateRowSorter(true);
		ListRsrvTab.setBounds(30,40,200,300);
		
//		@ Add Table to scroll Pane
		JScrollPane sp = new JScrollPane(ListRsrvTab);
		
		return sp;
	}
	
//	@ Stock Data selected from data base in an two dimension table
	private Object[][] extractTable(){
		
		Object [][] Tab = new Object[100][7];
		
		ResultSet get = SelectFrom();
		
		try {
			int i=0;
			while(get.next()) {
				Object [] Temp = new Object[6];
				Temp[0] = get.getString("Id"+CurrentStat);
				Temp[1] = get.getString("Nom");
				Temp[2] = get.getString("Prenom");
				Temp[3] = get.getString("ResvDate");
				Temp[4] = get.getString("SalleNum");
				Temp[5] = CurrentStat;
				Tab[i] = Temp;
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Tab;
	}

	
//	@ Extract Data from DB
	private ResultSet SelectFrom() {
		ResultSet result = null;
		String req=null;
		if(CurrentStat.equals("Admin")) {
			req = "select admins.IdAdmin, Nom, Prenom, ResvDate, SalleNum from reservations, admins WHERE reservations.IdAdmin!=0 AND reservations.IdAdmin="+Id+" AND admins.IdAdmin="+Id;
		}else if(CurrentStat.equals("Prof")) {
			req = "select professeurs.IdProf, Nom, Prenom, ResvDate, SalleNum from reservations, professeurs WHERE reservations.IdProf!=0 AND reservations.IdProf="+Id+" AND professeurs.IdProf="+Id;
		}else if(CurrentStat.equals("Etudiant")) {
			req = "select etudiants.IdEtudiant, Nom, Prenom, ResvDate, SalleNum from reservations, etudiants WHERE reservations.IdEtudiant!=0 AND reservations.IdEtudiant="+Id+" AND etudiants.IdEtudiant="+Id;
		}
		
		try {
			Statement stat = connexion();
			result = stat.executeQuery(req);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return result;
	}
	
}
