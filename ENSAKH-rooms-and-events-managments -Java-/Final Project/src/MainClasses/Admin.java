package MainClasses;

import java.util.Objects;

public class Admin {
	
	// Class Attributes
	private int IdAdmin;
	private String Nom;
	private String Prenom;
	private String Fonction;

	
	// Class Constructor
	public Admin() {
	}
	
	public Admin(String Nom, String Prenom) {
		this.Nom = Nom;
		this.Prenom = Prenom;
	}
	
	public Admin(int idAdmin, String nom, String prenom, String fonction) {
		IdAdmin = idAdmin;
		Nom = nom;
		Prenom = prenom;
		Fonction = fonction;
	}
	
	// Class Getters
	public int getIdAdmin() {
		return IdAdmin;
	}

	public String getNom() {
		return Nom;
	}

	public String getPrenom() {
		return Prenom;
	}

	public String getFonction() {
		return Fonction;
	}
	
	// Class Setters
	public void setIdAdmin(int idAdmin) {
		IdAdmin = idAdmin;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public void setPrenom(String prenom) {
		Prenom = prenom;
	}

	public void setFonction(String fonction) {
		Fonction = fonction;
	}

	// Overriding ToString and Equals Methods
	@Override
	public String toString() {
		return "IdAdmin=" + IdAdmin + "\nNom=" + Nom + "\nPrenom=" + Prenom + "\nFonction=" + Fonction;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Admin other = (Admin) obj;
		return Objects.equals(Fonction, other.Fonction) && IdAdmin == other.IdAdmin && Objects.equals(Nom, other.Nom)
				&& Objects.equals(Prenom, other.Prenom);
	}
	
}
