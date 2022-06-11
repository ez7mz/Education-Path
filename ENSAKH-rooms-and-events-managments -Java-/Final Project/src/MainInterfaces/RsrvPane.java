package MainInterfaces;

import java.awt.BorderLayout;
import java.awt.Color;
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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class RsrvPane extends JFrame implements ProjectTools{

	
	private int Id;
	private String CurrentStat;
	
	// Reservation Items
	JPanel RsrvPlaceContentPane;
	JPanel pane1;
	JPanel pane2;
	JPanel pane3;
	
	JLabel RsrvTitle;
	JLabel LRsrvSalleNum;
	JLabel LRsrvDate;
	JLabel LRsrvNbrPers;
	JLabel LRadio;
	
	JTextField RsrvSalleNum;
	JTextField RsrvDate;
	JTextField RsrvNbrPers;
	
	JButton BtnRsrv;
	
//	# empty place just to fix design
	JLabel [] empty = new JLabel[2];{
		for(int i=0;i<empty.length;i++) {
			empty[i] = new JLabel("",(int)CENTER_ALIGNMENT);
		}
	}
	
	public RsrvPane(int Id, String CurrentStat) {
		this.Id = Id;
		this.CurrentStat = CurrentStat;
	}

//	@ method Create room reservation panel 
	JPanel ReservationPlaceMaker() {
//		@ >> Pane1 : Head Pane ===============================================
		JPanel souspane1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel souspane2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel Mylogo = new JLabel(new ImageIcon(getClass().getResource("icons/logo-xs.png")));
		RsrvTitle = new JLabel(new ImageIcon(getClass().getResource("icons/ensa.png")));
		pane1 = new JPanel(new GridLayout(1,2, 10, 10));
		souspane1.add(RsrvTitle);souspane2.add(Mylogo);
		pane1.add(souspane1);pane1.add(souspane2);
		
//		@ >> Pane2 : Inputs Pane ===============================================
		LRsrvSalleNum = new JLabel(tab+Symbol+" Numero de Salle : ");
		RsrvSalleNum = new JTextField("Salle Num");
		RsrvSalleNum.setForeground(Color.LIGHT_GRAY);
		RsrvSalleNum.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				ShowPlaceHolder(RsrvSalleNum, "Salle Num");
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				Hide(RsrvSalleNum);
			}
		});
		
		LRsrvDate = new JLabel(tab+Symbol+" Reservation Date : ");
		RsrvDate = new JTextField("Reservation Date");
		RsrvDate.setForeground(Color.LIGHT_GRAY);
		RsrvDate.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				ShowPlaceHolder(RsrvDate, "Reservation Date");
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				Hide(RsrvDate);
			}
		});
		
		LRsrvNbrPers = new JLabel(tab+Symbol+" Nomber de Personne : ");
		RsrvNbrPers = new JTextField("Nomber du personne");
		RsrvNbrPers.setForeground(Color.LIGHT_GRAY);
		RsrvNbrPers.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				ShowPlaceHolder(RsrvNbrPers, "Nomber du personne");
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				Hide(RsrvNbrPers);
				normalLook(RsrvNbrPers);
			}
		});
		
		pane2 = new JPanel(new GridLayout(3,2,10,10));
		pane2.add(LRsrvSalleNum);pane2.add(RsrvSalleNum);pane2.add(LRsrvDate);pane2.add(RsrvDate);
		pane2.add(LRsrvNbrPers);pane2.add(RsrvNbrPers);
		Border titleBorder = new TitledBorder(new LineBorder(Color.decode("606363")), "Entrer les Informations suivante ");
		pane2.setBorder(titleBorder);
		
//		@ >> Pane3 : Buttons Pane ===========================================================
		pane3 = new JPanel();
		BtnRsrv = new JButton("Reserver!");
		BtnRsrv.addActionListener(this::BtnSaveBeahavor);
		pane3.add(BtnRsrv);
		
//		@ Content Pane =========================================================
		RsrvPlaceContentPane = new JPanel(new BorderLayout(10, 10));
		RsrvPlaceContentPane.add(pane1, BorderLayout.NORTH);
		RsrvPlaceContentPane.add(pane2, BorderLayout.CENTER);
		RsrvPlaceContentPane.add(pane3, BorderLayout.SOUTH);
		
		return RsrvPlaceContentPane;
	}
	
//	@ Save Button Action
	private void BtnSaveBeahavor(ActionEvent e) {
		if(ItemVerf()) {
			InsertIntoRsrv();
			JOptionPane.showMessageDialog(null, "Salle Reserved Successfully");
			RsrvSalleNum.setText(null);
			RsrvDate.setText(null);
			RsrvNbrPers.setText(null);
		}	
	}
	
//	@ Hide Place holder in TextField
	private void Hide(JTextField item) {
		if(item.getText().equals("Salle Num") || item.getText().equals("Salle Num Not Found!")) {
			item.setText(null);
			normalLook(item);
		}
		if(item.getText().equals("Reservation Date") || item.getText().equals("Nomber du personne")) {
			item.setText(null);
			normalLook(item);
		}
		
	}
	
//	@ SQL DB Operations
	
//	@ >> Method insert into reservation table	
	private void InsertIntoRsrv(){
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
		String req = "insert into reservations values('"+0+"', '"+RsrvDate.getText()+"','"+idAdmin+"','"+idProf+"','"+idEtud+"','"+RsrvSalleNum.getText()+"')";
		
		try {
			Statement stat = connexion();
			stat.executeUpdate(req);
			stat.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
//	@ Verify user input to avoid the maximum of error before send Data to DataBase
	private boolean ItemVerf() {
		boolean Flag = true;
		ResultSet res = SelectSalleNum();
		try {
			// > if room num not exist
			Set ht = new HashSet();
			while(res.next()) {
				ht.add(res.getString("SalleNum"));
			}
			
			// > if room already exist in input date
			if(!ht.contains(RsrvSalleNum.getText())) {
				errorLook(RsrvSalleNum);
				RsrvSalleNum.setText("Salle Num Not Found!");
				Flag = false;
			}
			
			Hashtable ht1 = SelectResrvSalle();
			if(ht1.containsKey(RsrvSalleNum.getText())) {
				if(ht1.get(RsrvSalleNum.getText()).equals(RsrvDate.getText())) {
					JOptionPane.showMessageDialog(null, "Salle Already reserved in this date!", "Error", JOptionPane.ERROR_MESSAGE);
					Flag = false;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// > if num persons attending greater than room capacity
		if(getSalleCapacity() < Integer.parseInt(RsrvNbrPers.getText())) {
			RsrvNbrPers.setText("The number Of Personnel attending is too big");
			errorLook(RsrvNbrPers);
			JOptionPane.showMessageDialog(null, "Salle Capacity is smaller than Personnes Number attend!", "Error", JOptionPane.ERROR_MESSAGE);
			Flag = false;
		}
		
		return Flag;
	}
	
//	@ extract Data from DataBase and put it in a HashTable
	private Hashtable SelectResrvSalle() {
		Hashtable ht = new Hashtable();
		String req = "SELECT SalleNum, ResvDate FROM reservations";
		ResultSet res = null;
		Statement stat = connexion();
		try {
			res = stat.executeQuery(req);
			while(res.next()) {
				ht.put(res.getString("SalleNum"), res.getString("ResvDate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ht; // return HashTable -- < SalleNum, RsrvDate > --
	}
	
//	@ Extract rooms Capacity
	private int getSalleCapacity() {
		int cap=0;
		String req="SELECT Capacity FROM salles WHERE SalleNum='"+RsrvSalleNum.getText()+"'";
		ResultSet res = null;
		Statement stat = connexion();
		try {
			res = stat.executeQuery(req);
			while(res.next()) {
				cap = (int)res.getInt("Capacity");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (int) cap;
	}
	
}
