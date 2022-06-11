package MainClasses;

import java.util.Objects;

public class Professeurs {
	
	// Class Attributes
	private int IdProf;
	private String Nom;
	private String Prenom;
	private String Departement;
	// Class Constructor
	public Professeurs() {
	}
	public Professeurs(int idProf, String nom, String prenom, String departement) {
		IdProf = idProf;
		Nom = nom;
		Prenom = prenom;
		Departement = departement;
	}
	
	// Class Getters
	public int getIdProf() {
		return IdProf;
	}
	public String getNom() {
		return Nom;
	}
	public String getPrenom() {
		return Prenom;
	}
	public String getDepartement() {
		return Departement;
	}
	
	// Class Setters
	public void setIdProf(int idProf) {
		IdProf = idProf;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public void setPrenom(String prenom) {
		Prenom = prenom;
	}
	public void setDepartement(String departement) {
		Departement = departement;
	}
	
	// Overriding ToString and Equals Methods
	@Override
	public String toString() {
		return "IdProf=" + IdProf + "\nNom=" + Nom + "\nPrenom=" + Prenom + "\nDepartement=" + Departement;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Professeurs other = (Professeurs) obj;
		return Objects.equals(Departement, other.Departement) && IdProf == other.IdProf
				&& Objects.equals(Nom, other.Nom) && Objects.equals(Prenom, other.Prenom);
	}
	
}
