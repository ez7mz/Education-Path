package MainInterfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.LookupTable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PlaningPlace extends JFrame{

	private int Id;
	private String CurrentStat;
	
	JPanel ContentPane;
	JPanel TitlePane;
	JPanel DatesPane;
	JPanel ButtonPane;
	
	JLabel Ltitle;
	JLabel LFromDate;
	JLabel LToDate;
	JLabel LTable; 
	
	JTextField FromDate;
	JTextField ToDate;
	JComboBox table;
	
	JButton Show;
	JButton Back;
	
	
	public PlaningPlace(int Id, String CurrentStat) {
		this.Id = Id;
		this.CurrentStat = CurrentStat;
		
//		@ >> Title Pane -------------------------------------
		TitlePane = new JPanel();
		Ltitle = new JLabel("Fille case bellow to show planing");
		TitlePane.add(Ltitle);
		
//		@ >> Button Pane ----------------------------------------
		DatesPane = new JPanel(new GridLayout(3, 2, 25, 25));
		LFromDate = new JLabel("From Date : ");
		FromDate = new JTextField("From date?");
		FromDate.setForeground(Color.LIGHT_GRAY);
		FromDate.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if(FromDate.getText().isEmpty()) {
					FromDate.setText("From date?");
					FromDate.setForeground(Color.LIGHT_GRAY);
				}
				
			}	
			@Override
			public void focusGained(FocusEvent e) {
				if(FromDate.getText().equals("From date?")) {
					FromDate.setText(null);
					FromDate.setForeground(Color.BLACK);
				}
				
			}
		});
		LToDate = new JLabel("To Date : ");
		ToDate = new JTextField("To date?");
		ToDate.setForeground(Color.LIGHT_GRAY);
		ToDate.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if(ToDate.getText().isEmpty()) {
					ToDate.setText("To date?");
					ToDate.setForeground(Color.LIGHT_GRAY);
				}
				
			}	
			@Override
			public void focusGained(FocusEvent e) {
				if(ToDate.getText().equals("To date?")) {
					ToDate.setText(null);
					ToDate.setForeground(Color.BLACK);
				}
				
			}
		});
		LTable = new JLabel("in what?");
		table = new JComboBox();
		table.addItem("reservations");
		table.addItem("events");
		
		DatesPane.add(LFromDate);DatesPane.add(FromDate);
		DatesPane.add(LToDate);DatesPane.add(ToDate);
		DatesPane.add(LTable);DatesPane.add(table);
		
//		@ >> Button Pane --------------------------------------------
		ButtonPane = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 10));
		Show = new JButton("Show Planing");
		Show.addActionListener(this::BtnShowBehavor);
		Back = new JButton("Go Back!");
		Back.addActionListener(this::BtnBackBehavor);
		ButtonPane.add(Show);ButtonPane.add(Back);
//		@ >> Content Pane -------------------------
		ContentPane = new JPanel(new BorderLayout(10,10));
		ContentPane.add(TitlePane, BorderLayout.NORTH);
		ContentPane.add(DatesPane, BorderLayout.CENTER);
		ContentPane.add(ButtonPane, BorderLayout.SOUTH);
		
		getContentPane().add(ContentPane);
		
		setVisible(true);
		setResizable(false);
		setSize(500,250);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
	}
	
	private void BtnShowBehavor(ActionEvent e) {
		new ShowPlaning(FromDate.getText(), ToDate.getText(), (String) table.getSelectedItem());
	}
	
	private void BtnBackBehavor(ActionEvent e) {
		dispose();
		new mainIndex(Id, CurrentStat);
	}
}
