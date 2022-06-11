package MainInterfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

// Administration registration form class
public class mainAdmin extends JFrame implements ProjectTools {
	
	JPanel ContentPane;
	JPanel pane1;
	JPanel pane2;
	JPanel pane3;
	
	JLabel AdminTitle;
	JLabel LIdAdmin;
	JLabel Lnom;
	JLabel Lprenom;
	JLabel LFonction;
	
	
	JTextField ItemId;
	JTextField ItemNom;
	JTextField ItemPrenom;
	JTextField ItemFonction;
	
	
	JButton BtnSave;
	
	public mainAdmin() {
	}
	
//	@ method Create Administration registration panel 
	public JPanel mainAdminMaker() {
		
//		@ Pane1 ================================================
		AdminTitle = new JLabel(new ImageIcon(getClass().getResource("icons/ensa.png")));
		pane1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pane1.add(AdminTitle);
		
//		@ Pane2 =================================================
		LIdAdmin = new JLabel(Tab+Flesh+" Id Admin : ");
		ItemId = new JTextField("Your Id");
		ItemId.setForeground(Color.LIGHT_GRAY);
		ItemId.addFocusListener(new FocusAdapter() { // FocusListner to make cases interactive
			@Override
			public void focusLost(FocusEvent e) {	// Case behavior When case lost the focus
				if(ItemId.getText().isEmpty()) {
					ItemId.setForeground(Color.LIGHT_GRAY);
					ItemId.setText("Your Id");
				}
			}
			@Override
			public void focusGained(FocusEvent e) {	// Case behavior When case lost the focus
				InitialHideHolder(ItemId);
			}
		});
		
		Lnom = new JLabel(Tab+Flesh+" Last Name : ");
		ItemNom = new JTextField("Your Last name goes here");
		ItemNom.setForeground(Color.LIGHT_GRAY);
		ItemNom.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(ItemNom.getText().isEmpty()) {
					ItemNom.setForeground(Color.LIGHT_GRAY);
					ItemNom.setText("Your Last name goes here");
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				InitialHideHolder(ItemNom);
			}
		});
		
		Lprenom= new JLabel(Tab+Flesh+" First Name : ");
		ItemPrenom = new JTextField("Your First name goes here");
		ItemPrenom.setForeground(Color.LIGHT_GRAY);
		ItemPrenom.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(ItemPrenom.getText().isEmpty()) {
					ItemPrenom.setForeground(Color.LIGHT_GRAY);
					ItemPrenom.setText("Your First name goes here");
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				InitialHideHolder(ItemPrenom);
			}
		});
		
		LFonction = new JLabel(Tab+Flesh+" Fonction : ");
		ItemFonction = new JTextField("Your Fonction");
		ItemFonction.setForeground(Color.LIGHT_GRAY);
		ItemFonction.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(ItemFonction.getText().isEmpty()) {
					ItemFonction.setForeground(Color.LIGHT_GRAY);
					ItemFonction.setText("Your Fonction");
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				InitialHideHolder(ItemFonction);
			}
		});
		
		pane2 = new JPanel(new GridLayout(4,2,10,5));
		pane2.add(LIdAdmin);pane2.add(ItemId);
		pane2.add(Lnom);pane2.add(ItemNom);
		pane2.add(Lprenom);pane2.add(ItemPrenom);
		pane2.add(LFonction);pane2.add(ItemFonction);
		Border titleBorder = new TitledBorder(new LineBorder(Color.decode("606363")), "Entrer Votre Information ");
		pane2.setBorder(titleBorder);
		
//		@ Pane3 ===========================================
		BtnSave = new JButton("Save!");
		BtnSave.addActionListener(this::saveAdminAct);
		pane3 = new JPanel();
		pane3.add(BtnSave);
		
//		@ Content Pane =======================================
		ContentPane = new JPanel(new BorderLayout(20,10));
		ContentPane.add(pane1,BorderLayout.NORTH);
		ContentPane.add(pane2,BorderLayout.CENTER);
		ContentPane.add(pane3,BorderLayout.SOUTH);
		
		return ContentPane;
	}
	
//	@ Define save Button Action -- we call it with this operator --
	private void saveAdminAct(ActionEvent e) {
		if(AdminItemverification()) { // if Item Verification return true do -- insert into data base --
			InsertIntoAdmin();
			JOptionPane.showMessageDialog(null, "Member Added Successfully");
		}else {	// else do -- Show Error message
			JOptionPane.showMessageDialog(null, "Errors Found! Try Again ...","Erreur",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private boolean AdminItemverification() { // Form Items Verification
		boolean Flag = true;
		
//		@ Id Verification -------------------------------------
		try {
			Integer.parseInt(ItemId.getText());
			Flag = true;
		}catch(NumberFormatException ex) {
			Flag = ErrorBeahavor(ItemId, "Id is not Valide!", "Your Id");
		}
		
//		@ Nom Verification ---------------------------------------
		if(ItemNom.getText().length() > 20 || ItemNom.getText().isEmpty()) {
			Flag = ErrorBeahavor(ItemNom, "Nom lenght > 20 Or Empty", "Your Last name goes here");
		}
		
//		@ Prenom Verification ---------------------------------------
		if(ItemPrenom.getText().length() > 20 || ItemPrenom.getText().isEmpty()) {
			Flag = ErrorBeahavor(ItemPrenom, "Prenom lenght > 20 Or Empty", "Your First name goes here");
		}
		
		return Flag;
	}
	
	
//	@ method to hide cases placeholder
	private void InitialHideHolder(JTextField place) {
		if(place.getText().equals("Your Id")) {
			place.setForeground(Color.BLACK);
			place.setText(null);
		}
		
		if(place.getText().equals("Your Last name goes here")) {
			place.setForeground(Color.BLACK);
			place.setText(null);
		}
		
		if(place.getText().equals("Your First name goes here")) {
			place.setForeground(Color.BLACK);
			place.setText(null);
		}
		
		if(place.getText().equals("Your Fonction")) {
			place.setForeground(Color.BLACK);
			place.setText(null);
		}
	}
	
//	@ SQL DB Operations
	private void InsertIntoAdmin() { // insert form inputs into DB admins table
		// SQL Query in String Format
		String req = "insert into admins values('"+ItemId.getText()+"', '"+ItemNom.getText()+"','"+ItemPrenom.getText()+"','"+ItemFonction.getText()+"')";
		try {
			Statement stat = connexion();
			stat.executeUpdate(req);
			stat.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
