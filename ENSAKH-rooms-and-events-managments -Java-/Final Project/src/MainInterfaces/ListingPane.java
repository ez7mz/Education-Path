package MainInterfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class ListingPane extends JFrame implements ProjectTools{
	private int Id;
	private String CurrentStat;
	
	JPanel ContentPane;
	JPanel TitlePane;
	JPanel SrchPane;
	JPanel ButtonPane;
	
	JLabel Title;
	JLabel LSrchId;
	JLabel LSrchDate;
	JLabel LSrchStat;
	
	JTextField SrchId;
	JTextField SrchDate;
	
	JComboBox SrchStat;
	
	JRadioButton RadioRsrv;
	JRadioButton RadionOrg;
	
	
	JButton BtnSrch;
	JButton BtnPlan;
	JButton BtnBack;
	
	
	public ListingPane(int Id, String CurrentStat) {
		super("Reservation and Events Listing Place");
		this.Id = Id;
		this.CurrentStat = CurrentStat;
//		@ Title Pane ------------------------------------------
		TitlePane = new JPanel();
		Title = new JLabel("What you looking for?");
		TitlePane.add(Title);

//		@ Search Pane --------------------------------------------
		SrchPane = new JPanel(new GridLayout(3,2,10,10));
		LSrchDate = new JLabel("Date : ");
		SrchDate = new JTextField("Date goes here");
		SrchDate.setForeground(Color.LIGHT_GRAY);
		SrchDate.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if(SrchDate.getText().isEmpty()) {
					SrchDate.setForeground(Color.LIGHT_GRAY);
					SrchDate.setText("Date goes here");
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				if(SrchDate.getText().equals("Date goes here")) {
					SrchDate.setText(null);
					SrchDate.setForeground(Color.BLACK);
				}
			}
		});
		
		LSrchStat = new JLabel("Status");
		SrchStat = new JComboBox();
		SrchStat.addItem("Admin");
		SrchStat.addItem("Prof");
		SrchStat.addItem("Etudiant");
		
		RadioRsrv = new JRadioButton("reservations");
		RadionOrg = new JRadioButton("events");
		ButtonGroup grp = new ButtonGroup();
		grp.add(RadioRsrv);grp.add(RadionOrg);
		

		SrchPane.add(LSrchDate);SrchPane.add(SrchDate);
		SrchPane.add(LSrchStat);SrchPane.add(SrchStat);SrchPane.add(RadioRsrv);SrchPane.add(RadionOrg);
		
//		@ Button Pane ------------------------------------------
		ButtonPane = new JPanel(new FlowLayout(new FlowLayout().CENTER, 20, 15));
		BtnSrch = new JButton("Find");
		BtnSrch.addActionListener(this::BtnSrchBehavor);
		BtnPlan = new JButton("Planing");
		BtnPlan.addActionListener(this::BtnPlaningBehavor);
		BtnBack = new JButton("Go Back");
		BtnBack.addActionListener(this::BtnBackBehavor);
		ButtonPane.add(BtnSrch);
		ButtonPane.add(BtnPlan);
		ButtonPane.add(BtnBack);
		
//		@ Content Pane --------------------------------------
		ContentPane = new JPanel(new BorderLayout(10,10));
		ContentPane.add(TitlePane, BorderLayout.NORTH);
		ContentPane.add(SrchPane, BorderLayout.CENTER);
		ContentPane.add(ButtonPane, BorderLayout.SOUTH);
		
		getContentPane().add(ContentPane);
		
		setVisible(true);
		setSize(500,250);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

	}
	
	private void BtnSrchBehavor(ActionEvent e) {
//		dispose();
		if(RadioRsrv.isSelected()) {
			if(SrchStat.getSelectedItem().equals("Admin")) {
				new ListAdminRsrv(SrchDate.getText());
			}else if(SrchStat.getSelectedItem().equals("Prof")) {
				new ListProfRsrv(SrchDate.getText());
			}else if(SrchStat.getSelectedItem().equals("Etudiant")) {
				new ListEtudRsrv(SrchDate.getText());
			}
		}else if(RadionOrg.isSelected()) {
			if(SrchStat.getSelectedItem().equals("Admin")) {
				new ListAdminOrg(SrchDate.getText());
			}else if(SrchStat.getSelectedItem().equals("Prof")) {
				new ListProfOrg(SrchDate.getText());
			}else if(SrchStat.getSelectedItem().equals("Etudiant")) {
				new ListEtudOrg(SrchDate.getText());
			}
		}
	}
	
	private void BtnPlaningBehavor(ActionEvent e) {
		dispose();
		new PlaningPlace(Id, CurrentStat);
	}
	
	private void BtnBackBehavor(ActionEvent e) {
		dispose();
		new mainIndex(Id, CurrentStat);
	}
	
}
