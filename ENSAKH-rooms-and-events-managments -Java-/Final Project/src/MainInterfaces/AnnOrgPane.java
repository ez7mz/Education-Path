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
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class AnnOrgPane extends JFrame implements ProjectTools{
//	-- save user information with id and status
	private int Id;
	private String CurrentStat;
	
//	@ Declare composants
	JPanel AnnPlaceContentPane;
	JPanel Apane1;
	JPanel Apane2;
	JPanel Apane3;
	
	JLabel AnnTitle;
	JLabel LAnnDate;
	JLabel LAnnSujet;
	JLabel LAnnRadio;
	
	JTextField AnnDate;
	JTextField AnnSujet;
	
	JButton BtnAnn;
	
//	# empty place just to fix design
	JLabel [] empty = new JLabel[4];{
		for(int i=0;i<empty.length;i++) {
			empty[i] = new JLabel("",(int)CENTER_ALIGNMENT);
		}
	}
	
	public AnnOrgPane(int Id, String CurrentStat) {
		this.Id = Id;
		this.CurrentStat = CurrentStat;
	}
	
	public JPanel EventAnnulationPlaceMaker() {

//		@ Pane1 ===============================================	
		JPanel Asouspane1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel Asouspane2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel Mylogo = new JLabel(new ImageIcon(getClass().getResource("icons/logo-xs.png")));
		AnnTitle = new JLabel(new ImageIcon(getClass().getResource("icons/ensa.png")));
		Apane1 = new JPanel(new GridLayout(1,2, 10, 10));
		Asouspane1.add(AnnTitle);Asouspane2.add(Mylogo);
		Apane1.add(Asouspane1);Apane1.add(Asouspane2);
			
//		@ Pane2 ================================================
		LAnnDate = new JLabel(tab+Symbol+" Date d'evenment : ");
		AnnDate = new JTextField("Event Date");
		AnnDate.setForeground(Color.LIGHT_GRAY);
		AnnDate.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				ShowPlaceHolder(AnnDate, "Event Date");
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				Hide(AnnDate);
			}
		});
		
		LAnnSujet = new JLabel(tab+Symbol+" Sujet D'evenment :  ");
		AnnSujet = new JTextField("Event Subject");
		AnnSujet.setForeground(Color.LIGHT_GRAY);
		AnnSujet.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				ShowPlaceHolder(AnnSujet, "Event Subject");
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				Hide(AnnSujet);
			}
		});
		
		Apane2 = new JPanel(new GridLayout(3,2,10,10));
		Apane2.add(LAnnDate);Apane2.add(AnnDate);
		Apane2.add(LAnnSujet);Apane2.add(AnnSujet);
		Border titleBorder = new TitledBorder(new LineBorder(Color.decode("606363")), "Entrer les Informations suivante :");
		Apane2.setBorder(titleBorder);
		
//		@ Pane3 ===========================================================
		Apane3 = new JPanel();
		BtnAnn = new JButton("Confirm!");
		BtnAnn.addActionListener(this::BtnconfirmBeahavor);
		Apane3.add(BtnAnn);
		
//		@ Content Pane =========================================================
		AnnPlaceContentPane = new JPanel(new BorderLayout(10, 10));
		AnnPlaceContentPane.add(Apane1, BorderLayout.NORTH);
		AnnPlaceContentPane.add(Apane2, BorderLayout.CENTER);
		AnnPlaceContentPane.add(Apane3, BorderLayout.SOUTH);
		
		
		return AnnPlaceContentPane;
	}

	
	private void Hide(JTextField item) {
		if(item.getText().equals("Event Date") || item.getText().equals("Event Subject")) {
			item.setText(null);
			item.setForeground(Color.BLACK);
		}
	}

	
	private void BtnconfirmBeahavor(ActionEvent e) {
		if(DeleteFromOrg()==0) {
			JOptionPane.showMessageDialog(null, "No Event with this info!", "Error", JOptionPane.WARNING_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null, "Event canceled Successfully", "Valid",JOptionPane.OK_OPTION);
		}
	}
	
	private int DeleteFromOrg() {
		
		int flag = -1;
		
		String req = "DELETE FROM events WHERE EventDate='"+AnnDate.getText()+"' AND ";
		
		if(CurrentStat.equals("Admin")) {
			int idAdmin = Id;
			req += "IdAdmin="+idAdmin;
		}else if(CurrentStat.equals("Prof")) {
			int idProf = Id;
			req += "IdProf="+idProf;
		}else if(CurrentStat.equals("Etudiant")) {
			int idEtud = Id;
			req += "IdEtudiant="+idEtud;
		}
		
		try {
			Statement stat = connexion();
			flag = stat.executeUpdate(req);
			stat.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return flag;
	}
	
}
