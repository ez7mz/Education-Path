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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class mainEtudiant extends JFrame implements ProjectTools{
	
	JPanel ContentPane;
	JPanel pane1;
	JPanel pane2;
	JPanel pane3;
	
	JLabel EtudiantTitle;
	JLabel LIdEtudiant;
	JLabel Lnom;
	JLabel Lprenom;
	JLabel LAge;
	JLabel LVille;
	JLabel LBornDate;
	JLabel LFiliere;
	
	JTextField ItemId;
	JTextField ItemNom;
	JTextField ItemPrenom;
	JTextField ItemAge;
	JTextField ItemVille;
	JTextField ItemBornDate;
	
	JComboBox ItemFiliere;
	
	JButton BtnSave;
	
	
	
	public mainEtudiant(){
	}
	
//	@ method Create Etudiants registration panel 
	public JPanel mainEtudiantMaker() {
//		@ Pane1 ================================================
		EtudiantTitle = new JLabel(new ImageIcon(getClass().getResource("icons/ensa.png")));
		pane1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pane1.add(EtudiantTitle);
		
//		@ Pane2 =================================================
		LIdEtudiant = new JLabel(Tab+Flesh+" Id Etudiant : ");
		ItemId = new JTextField("Your Id");
		ItemId.setForeground(Color.LIGHT_GRAY);
		ItemId.addFocusListener(new FocusAdapter() {	// FocusListner to make cases interactive
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
		
		LAge = new JLabel(Tab+Flesh+" Age : ");
		ItemAge = new JTextField("Your Age");
		ItemAge.setForeground(Color.LIGHT_GRAY);
		ItemAge.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(ItemAge.getText().isEmpty()) {
					ItemAge.setForeground(Color.LIGHT_GRAY);
					ItemAge.setText("Your Age");
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				InitialHideHolder(ItemAge);
			}
		});
		
		LVille = new JLabel(Tab+Flesh+" Ville : ");
		ItemVille = new JTextField("Your City");
		ItemVille.setForeground(Color.LIGHT_GRAY);
		ItemVille.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(ItemVille.getText().isEmpty()) {
					ItemVille.setForeground(Color.LIGHT_GRAY);
					ItemVille.setText("Your City");
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				InitialHideHolder(ItemVille);
			}
		});
		
		LBornDate = new JLabel(Tab+Flesh+" Date de Naissance : ");
		ItemBornDate = new JTextField("Your Born Date YYYY-MM-DD");
		ItemBornDate.setForeground(Color.LIGHT_GRAY);
		ItemBornDate.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(ItemBornDate.getText().isEmpty()) {
					ItemBornDate.setForeground(Color.LIGHT_GRAY);
					ItemBornDate.setText("Your Born Date YYYY-MM-DD");
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				InitialHideHolder(ItemBornDate);
			}
		});
		
		LFiliere = new JLabel(Tab+Flesh+" Filiere : ");
		ItemFiliere = new JComboBox();
		ItemFiliere.addItem("IID");
		ItemFiliere.addItem("GI");
		ItemFiliere.addItem("GE");
		ItemFiliere.addItem("IRIC");
		ItemFiliere.addItem("GPEE");
		
		pane2 = new JPanel(new GridLayout(7,2,10,5));
		pane2.add(LIdEtudiant);pane2.add(ItemId);
		pane2.add(Lnom);pane2.add(ItemNom);
		pane2.add(Lprenom);pane2.add(ItemPrenom);
		pane2.add(LAge);pane2.add(ItemAge);
		pane2.add(LVille);pane2.add(ItemVille);
		pane2.add(LBornDate);pane2.add(ItemBornDate);
		pane2.add(LFiliere);pane2.add(ItemFiliere);
		Border titleBorder = new TitledBorder(new LineBorder(Color.decode("606363")), "Entrer Votre Information :");
		pane2.setBorder(titleBorder);
		
//		@ Pane3 ===========================================
		BtnSave = new JButton("Save!");
		BtnSave.addActionListener(this::saveEtudAct);
		pane3 = new JPanel();
		pane3.add(BtnSave);
		
//		@ Content Pane =======================================
		ContentPane = new JPanel(new BorderLayout());
		ContentPane.add(pane1,BorderLayout.NORTH);
		ContentPane.add(pane2,BorderLayout.CENTER);
		ContentPane.add(pane3,BorderLayout.SOUTH);
		
		
		return ContentPane;
	}
	
//	@ Define save Button Action -- we call it with this operator --
	private void saveEtudAct(ActionEvent e) {
		if(EtudItemverification() == true) { // if Item Verification return true do -- insert into data base --
			InsertIntoEtud();
			JOptionPane.showMessageDialog(null, "Member Added Successfully");
		}else {	// else do -- Show Error message
			JOptionPane.showMessageDialog(null, "Erreur Found! Try Again ...","Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private boolean EtudItemverification() {	// Form Items Verification
		boolean Flag = true;
//		@ Id Verification --------------------------------------
		try {
			Integer.parseInt(ItemId.getText());
			Flag = true;
		}catch(NumberFormatException ex) {
			Flag = ErrorBeahavor(ItemId, "Id is not Valide!", "Your id");
		}
		
//		@ Nom Verification -------------------------------------------
		if(ItemNom.getText().length() > 20 || ItemNom.getText().isEmpty()) {
			Flag = ErrorBeahavor(ItemNom, "Nom lenght > 20 Ou Empty", "Your Last name goes here");
		}
		
//		@ Prenom Verification ----------------------------------------------
		if(ItemPrenom.getText().length() > 20 || ItemPrenom.getText().isEmpty()) {
			Flag = ErrorBeahavor(ItemPrenom, "Prenom lenght > 20 Ou Empty", "Your First name goes here");
		}
		
//		@ Age Verification ------------------------------------------------------
		try {
			int age = Integer.parseInt(ItemAge.getText());
			if(age < 18) {
				Flag = ErrorBeahavor(ItemAge, "Age doit etre >= 18 et juste des chiffre!", "Your age");
			}
		}catch(NumberFormatException ex) {
			Flag = ErrorBeahavor(ItemAge, "Age doit etre >= 18 et juste des chiffre!", "Your age");
		}
		
//		@ Born Date Verification -------------------
		if(!(ItemBornDate.getText().isEmpty()) && (int)ItemBornDate.getText().indexOf("-") != -1) {
			StringBuffer Date = new StringBuffer(10);
			Date.append(ItemBornDate.getText()); 
			int indx1 = Date.indexOf("-");
			Date.delete(indx1, indx1+1);
			int indx2 = Date.indexOf("-");
			if(indx1 !=4 || indx2 != 6) {
			Flag = ErrorBeahavor(ItemBornDate, "Date must in YYYY-MM-DD Format", "Your Born Date YYYY-MM-DD");
			}
		}else {
			Flag = ErrorBeahavor(ItemBornDate, "Date must in YYYY-MM-DD Format", "Your Born Date YYYY-MM-DD");
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
		
		if(place.getText().equals("Your Age")) {
			place.setForeground(Color.BLACK);
			place.setText(null);
		}
		
		if(place.getText().equals("Your City")) {
			place.setForeground(Color.BLACK);
			place.setText(null);
		}
		
		if(place.getText().equals("Your Born Date YYYY-MM-DD")) {
			place.setForeground(Color.BLACK);
			place.setText(null);
		}
	}
	
//	@ SQL DB Operations
	private void InsertIntoEtud() {
		// SQL Query in String Format
		String req = "insert into etudiants values('"+ItemId.getText()+"', '"+ItemNom.getText()+"','"+ItemPrenom.getText()+"','"+ItemAge.getText()+"','"+ItemVille.getText()+"','"+ItemBornDate.getText()+"','"+ItemFiliere.getSelectedItem()+"')";
		try {
			Statement stat = connexion();
			stat.executeUpdate(req);
			stat.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
