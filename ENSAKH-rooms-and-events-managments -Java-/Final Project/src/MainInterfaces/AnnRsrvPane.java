package MainInterfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class AnnRsrvPane extends JFrame implements ProjectTools{
	
	private int Id;
	private String CurrentStat;
	
	JPanel AnnPlaceContentPane;
	JPanel Apane1;
	JPanel Apane2;
	JPanel Apane3;
	
	JLabel AnnTitle;
	JLabel LAnnDate;
	JLabel LAnnSalle;
	
	JTextField AnnSalle;
	JTextField AnnDate;
	
	JButton BtnAnn;
	
//	# empty place just to fix design
	JLabel [] empty = new JLabel[2];{
		for(int i=0;i<empty.length;i++) {
			empty[i] = new JLabel("",(int)CENTER_ALIGNMENT);
		}
	}
	
	public AnnRsrvPane(int Id, String CurrentStat) {
		this.Id = Id;
		this.CurrentStat = CurrentStat;
	}
	
	public JPanel AnnulationPlaceMaker() {
//		@ Pane1 ===============================================
		JPanel Asouspane1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel Asouspane2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel Mylogo = new JLabel(new ImageIcon(getClass().getResource("icons/logo-xs.png")));
		AnnTitle = new JLabel(new ImageIcon(getClass().getResource("icons/ensa.png")));
		Apane1 = new JPanel(new GridLayout(1,2, 10, 10));
		Asouspane1.add(AnnTitle);Asouspane2.add(Mylogo);
		Apane1.add(Asouspane1);Apane1.add(Asouspane2);
		
//		@ Pane2 ================================================
		LAnnDate = new JLabel(tab+Symbol+" Reservation Date : ");
		AnnDate = new JTextField("Reservation Date");
		AnnDate.setForeground(Color.LIGHT_GRAY);
		AnnDate.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				ShowPlaceHolder(AnnDate, "Reservation Date");
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				Hide(AnnDate);
			}
		});
		
		LAnnSalle = new JLabel(tab+Symbol+" Salle Num : ");
		AnnSalle = new JTextField("Salle Num");
		AnnSalle.setForeground(Color.LIGHT_GRAY);
		AnnSalle.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				ShowPlaceHolder(AnnSalle, "Reservation Date");
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				Hide(AnnSalle);
			}
		});
		
		Apane2 = new JPanel(new GridLayout(3,2,10,10));
		Apane2.add(LAnnDate);Apane2.add(AnnDate);
		Apane2.add(LAnnSalle);Apane2.add(AnnSalle);
		Border titleBorder = new TitledBorder(new LineBorder(Color.decode("606363")), "Entrer les Informations suivante ");
		Apane2.setBorder(titleBorder);
		
//		@ Pane3 ===========================================================
		Apane3 = new JPanel();
		BtnAnn = new JButton("Confirmer!");
		BtnAnn.addActionListener(this::BtnConfirmBeahavor);
		Apane3.add(BtnAnn);
		
//		@ Content Pane =========================================================
		AnnPlaceContentPane = new JPanel(new BorderLayout(10, 10));
		AnnPlaceContentPane.add(Apane1, BorderLayout.NORTH);
		AnnPlaceContentPane.add(Apane2, BorderLayout.CENTER);
		AnnPlaceContentPane.add(Apane3, BorderLayout.SOUTH);
		
		return AnnPlaceContentPane;
	}
	
	private void BtnConfirmBeahavor(ActionEvent e) {
		int flag = JOptionPane.showConfirmDialog(null, "Are you sure?","Confirm", JOptionPane.YES_NO_OPTION);
		if(flag == JOptionPane.YES_OPTION) {
			if(DeleteFromRsrv()==0) {
				JOptionPane.showMessageDialog(null, "No Salle reserved in this date with your ID!", "Error", JOptionPane.WARNING_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null, "Salle reservation canceled Successfully");
			}
		}
	}
	
	private void Hide(JTextField item) {
		if(item.getText().equals("Reservation Date") || item.getText().equals("Salle Num")) {
			item.setText(null);
			item.setForeground(Color.BLACK);
		}
	}
	
//	@ SQL DB Operations
	private int DeleteFromRsrv() {
		String req = "DELETE FROM reservations WHERE ResvDate='"+AnnDate.getText()+"' AND ";
		int flag = -1;
		
		if(CurrentStat.equals("Admin")) {
			req += "IdAdmin="+Id;
		}else if(CurrentStat.equals("Prof")) {
			req += "IdProf="+Id;
		}else if (CurrentStat.equals("Etudiant")) {
			req += "IdEtudiant="+Id;
		}
		
		try {
			Statement stat = connexion();
			flag = stat.executeUpdate(req);
			stat.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return flag;
	}
	
}
