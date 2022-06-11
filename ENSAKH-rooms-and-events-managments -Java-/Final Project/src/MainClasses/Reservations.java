package MainClasses;

import java.util.Objects;

public class Reservations {
	
	// Class Attributes
	private int ResvNum;
	private String ResvDate;
	private int IdAdmin;
	private int IdProf;
	private int IdEtudiant;
	private String SalleNum;
	
	// Class Constructor
	public Reservations() {
	}

	public Reservations(int resvNum, String resvDate, int idAdmin, int idProf, int idEtudiant, String salleNum) {
		ResvNum = resvNum;
		ResvDate = resvDate;
		IdAdmin = idAdmin;
		IdProf = idProf;
		IdEtudiant = idEtudiant;
		SalleNum = salleNum;
	}
	
	// Class Getters
	public int getResvNum() {
		return ResvNum;
	}

	public String getResvDate() {
		return ResvDate;
	}

	public int getIdAdmin() {
		return IdAdmin;
	}

	public int getIdProf() {
		return IdProf;
	}

	public int getIdEtudiant() {
		return IdEtudiant;
	}

	public String getSalleNum() {
		return SalleNum;
	}
	
	// Class Setters
	public void setResvNum(int resvNum) {
		ResvNum = resvNum;
	}

	public void setResvDate(String resvDate) {
		ResvDate = resvDate;
	}

	public void setIdAdmin(int idAdmin) {
		IdAdmin = idAdmin;
	}

	public void setIdProf(int idProf) {
		IdProf = idProf;
	}

	public void setIdEtudiant(int idEtudiant) {
		IdEtudiant = idEtudiant;
	}

	public void setSalleNum(String salleNum) {
		SalleNum = salleNum;
	}
	
	// Overriding ToString and equals Methods
	@Override
	public String toString() {
		return "ResvNum=" + ResvNum + "\nResvDate=" + ResvDate + "\nIdAdmin=" + IdAdmin + "\nIdProf="
				+ IdProf + "\nIdEtudiant=" + IdEtudiant + "\nSalleNum=" + SalleNum;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservations other = (Reservations) obj;
		return IdAdmin == other.IdAdmin && IdEtudiant == other.IdEtudiant && IdProf == other.IdProf
				&& Objects.equals(ResvDate, other.ResvDate) && ResvNum == other.ResvNum
				&& Objects.equals(SalleNum, other.SalleNum);
	}
	
}
