package MainClasses;
import java.util.Objects;

public class Etudiants {
	
	// Class Attributes
	private int IdEtudiant;
	private String Nom;
	private String Prenom;
	private int Age;
	private String Ville;
	private String BornDate;
	private String Filiere;
	
	
	// Class Constructor
	public Etudiants() {
	}
	
	public Etudiants(String Nom, String Prenom) {
		this.Nom = Nom;
		this.Prenom = Prenom;
	}
	
	public Etudiants(int idEtudiant, String nom, String prenom, int age, String ville, String bornDate, String filiere) {
		IdEtudiant = idEtudiant;
		Nom = nom;
		Prenom = prenom;
		Age = age;
		Ville = ville;
		BornDate = bornDate;
		Filiere = filiere;
	}

	
	// Class Getters
	public String getNom() {
		return Nom;
	}
	public String getPrenom() {
		return Prenom;
	}
	public int getAge() {
		return Age;
	}
	public String getVille() {
		return Ville;
	}
	public String getBornDate() {
		return BornDate;
	}
	public String getFiliere() {
		return Filiere;
	}
	public int getIdEtudiant() {
		return IdEtudiant;
	}

	
	// Class Setters
	public void setIdEtudiant(int idEtudiant) {
		IdEtudiant = idEtudiant;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public void setPrenom(String prenom) {
		Prenom = prenom;
	}
	public void setAge(int age) {
		Age = age;
	}
	public void setVille(String ville) {
		Ville = ville;
	}
	public void setBornDate(String bornDate) {
		BornDate = bornDate;
	}
	public void setFiliere(String filiere) {
		Filiere = filiere;
	}
	
	
	// Overriding ToString and Equals Methods
	@Override
	public String toString() {
		return "IdEtudiant=" + IdEtudiant + "\nNom=" + Nom + "\nPrenom=" + Prenom + "\nAge=" + Age
				+ "\nVille=" + Ville + "\nBornDate=" + BornDate + "\nFiliere=" + Filiere;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Etudiants other = (Etudiants) obj;
		return Age == other.Age && Objects.equals(BornDate, other.BornDate) && Objects.equals(Filiere, other.Filiere)
				&& IdEtudiant == other.IdEtudiant && Objects.equals(Nom, other.Nom)
				&& Objects.equals(Prenom, other.Prenom) && Objects.equals(Ville, other.Ville);
	}
	
}
