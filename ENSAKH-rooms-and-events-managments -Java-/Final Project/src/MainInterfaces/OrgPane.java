package MainInterfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class OrgPane extends JFrame implements ProjectTools{
	
	private int Id;
	private String CurrentStat;
	
	
	// ORganisation Items
	JPanel OrgPlaceContentPane;
	JPanel	pane1;
	JPanel pane2;
	JPanel pane3;
	
	JLabel OrgTitle;
	JLabel LOrgSalleNum;
	JLabel LOrgDate;
	JLabel LOrgSujet;
	
	JTextField OrgSalleNum;
	JTextField OrgDate;
	JTextField OrgSujet;
	
	JButton BtnOrg;
	
	public OrgPane(int Id, String CurrentStat) {
		this.Id = Id;
		this.CurrentStat = CurrentStat;
	}
	
//	@ method Create Event Organisation panel 
	public JPanel OrganisationPlaceMaker() {
		
//		@ Pane1 ===============================================	
		JPanel souspane1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel souspane2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel Mylogo = new JLabel(new ImageIcon(getClass().getResource("icons/logo-xs.png")));
		OrgTitle = new JLabel(new ImageIcon(getClass().getResource("icons/ensa.png")));
		pane1 = new JPanel(new GridLayout(1,2, 10, 10));
		souspane1.add(OrgTitle);souspane2.add(Mylogo);
		pane1.add(souspane1);pane1.add(souspane2);
			
//		@ Pane2 ================================================
		
		LOrgSalleNum = new JLabel(tab+Symbol+" Numero de Salle : ");
		OrgSalleNum = new JTextField("Salle Num");
		OrgSalleNum.setForeground(Color.LIGHT_GRAY);
		OrgSalleNum.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				ShowPlaceHolder(OrgSalleNum, "Salle Num");
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				Hide(OrgSalleNum);
			}
		});
		
		LOrgDate = new JLabel(tab+Symbol+" Date d'evenment : ");
		OrgDate = new JTextField("Event Date");
		OrgDate.setForeground(Color.LIGHT_GRAY);
		OrgDate.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				ShowPlaceHolder(OrgDate, "Event Date");
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				Hide(OrgDate);
			}
		});
		
		LOrgSujet = new JLabel(Tab+Symbol+" Sujet D'evenment :  ");
		OrgSujet = new JTextField("Event Subject");
		OrgSujet.setForeground(Color.LIGHT_GRAY);
		OrgSujet.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				ShowPlaceHolder(OrgSujet, "Event Subject");
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				Hide(OrgSujet);
			}
		});
		
		
		pane2 = new JPanel(new GridLayout(3,2,10,10));
		pane2.add(LOrgSalleNum);pane2.add(OrgSalleNum);pane2.add(LOrgDate);pane2.add(OrgDate);
		pane2.add(LOrgSujet);pane2.add(OrgSujet);
		Border titleBorder = new TitledBorder(new LineBorder(Color.decode("606363")), "Entrer les Informations suivante ");
		pane2.setBorder(titleBorder);
		
//		@ Pane3 ===========================================================
		pane3 = new JPanel();
		BtnOrg = new JButton("Reserver!");
		BtnOrg.addActionListener(this::BtnSaveBeahavor);
		pane3.add(BtnOrg);
		
//		@ Content Pane =========================================================
		OrgPlaceContentPane = new JPanel(new BorderLayout(10, 10));
		OrgPlaceContentPane.add(pane1, BorderLayout.NORTH);
		OrgPlaceContentPane.add(pane2, BorderLayout.CENTER);
		OrgPlaceContentPane.add(pane3, BorderLayout.SOUTH);
		
		
		return OrgPlaceContentPane;
	}
	
//	@ Hide Place holder in TextField
	private void Hide(JTextField item) {
		if(item.getText().equals("Salle Num") || item.getText().equals("Salle Num Not Found!")) {
			item.setText(null);
			normalLook(item);
		}
		if(item.getText().equals("Event Date") || item.getText().equals("Event Subject")) {
			item.setText(null);
			normalLook(item);
		}
		
	}
	
//	@ Save Button Action
	private void BtnSaveBeahavor(ActionEvent e) {
		if(OrgItemVerf()) {
			InsertIntoOrg();
			JOptionPane.showMessageDialog(null, "Event Organized added Successfully");
			OrgSalleNum.setText(null);
			OrgDate.setText(null);
			OrgSujet.setText(null);
		}
	}
	
//	! >> Method insert into events table
	private void InsertIntoOrg(){
		int idAdmin = 0;
		int idProf = 0;
		int idEtud = 0;
		
		if(CurrentStat.equals("Admin")) {
			idAdmin = this.Id;
		}else if(CurrentStat.equals("Prof")) {
			idProf = this.Id;
		}else if(CurrentStat.equals("Etudiant")) {
			idEtud = this.Id;
		}
			// 0 : Reservation Num is an auto Increment Column
		String req = "insert into events values('"+0+"', '"+OrgSujet.getText()+"','"+OrgDate.getText()+"','"+idAdmin+"','"+idProf+"','"+idEtud+"','"+OrgSalleNum.getText()+"')";
		
		try {
			
			Statement stat = connexion();
			stat.executeUpdate(req);
			stat.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

//	@ Verify user input to avoid the maximum of error before send Data to DataBase
	private boolean OrgItemVerf() {
		boolean Flag = true;
		ResultSet res = SelectSalleNum();
		try {
			Set ht = new HashSet();
			while(res.next()) {
				ht.add(res.getString("SalleNum"));
			}
			
			if(!ht.contains(OrgSalleNum.getText())) {
				errorLook(OrgSalleNum);
				OrgSalleNum.setText("Salle Num Not Found!");
				Flag = false;
			}
			
			Hashtable ht1 = SelectOrgSalle();
			if(ht1.containsKey(OrgSalleNum.getText())) {
				if(ht1.get(OrgSalleNum.getText()).equals(OrgDate.getText())) {
					JOptionPane.showMessageDialog(null, "this date already Has an event!", "Error", JOptionPane.ERROR_MESSAGE);
					Flag = false;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if(!getRsrv().next()) {
				String err = "Error : Reserve this Salle in mentionned date First to be able to organize an event in!";
				JOptionPane.showMessageDialog(null, err, "Error", JOptionPane.ERROR_MESSAGE);
				Flag = false; 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Flag;
	}
	
//	@ extract Data from DataBase and put it in a HashTable
	private Hashtable SelectOrgSalle() {
		Hashtable ht = new Hashtable();
		String req = "SELECT SalleNum, EventDate FROM events";
		ResultSet res = null;
		Statement stat = connexion();
		try {
			res = stat.executeQuery(req);
			while(res.next()) {
				ht.put(res.getString("SalleNum"), res.getString("EventDate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ht;
	}
	
	private ResultSet getRsrv() { // Extract room reserved in a specific date
		String req = null;
		if(CurrentStat.equals("Admin")) {
			req = "SELECT * FROM reservations WHERE ResvDate='"+OrgDate.getText()+"' AND SalleNum='"+OrgSalleNum.getText()+"' AND IdAdmin="+Id;
		}else if(CurrentStat.equals("Prof")) {
			req = "SELECT * FROM reservations WHERE ResvDate='"+OrgDate.getText()+"' AND SalleNum='"+OrgSalleNum.getText()+"' AND IdProf="+Id;
		}else if(CurrentStat.equals("Etudiant")) {
			req = "SELECT * FROM reservations WHERE ResvDate='"+OrgDate.getText()+"' AND SalleNum='"+OrgSalleNum.getText()+"' AND IdEtudiant="+Id;
		}
		
		ResultSet res = null;
		Statement stat = connexion();
		try {
			res = stat.executeQuery(req);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
}
