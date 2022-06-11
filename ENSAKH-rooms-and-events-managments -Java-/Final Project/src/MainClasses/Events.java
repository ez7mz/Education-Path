package MainClasses;

import java.util.Objects;

public class Events {

	// Class Attributes
	private int EventNum;
	private String Sujet;
	private String EventDate;
	private int IdAdmin;
	private int IdProf;
	private int IdEtudiant;
	private String SalleNum;
	
	// Class Constructor
	public Events() {
	}

	public Events(int eventNum, String sujet, String eventDate, int idAdmin, int idProf, int idEtudiant,
			String salleNum) {
		EventNum = eventNum;
		Sujet = sujet;
		EventDate = eventDate;
		IdAdmin = idAdmin;
		IdProf = idProf;
		IdEtudiant = idEtudiant;
		SalleNum = salleNum;
	}
	
	// Class Getters
	public int getEventNum() {
		return EventNum;
	}

	public String getSujet() {
		return Sujet;
	}

	public String getEventDate() {
		return EventDate;
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
	public void setEventNum(int eventNum) {
		EventNum = eventNum;
	}

	public void setSujet(String sujet) {
		Sujet = sujet;
	}

	public void setEventDate(String eventDate) {
		EventDate = eventDate;
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
		return "EventNum=" + EventNum + "\nSujet=" + Sujet + "\nEventDate=" + EventDate + "\nIdAdmin=" + IdAdmin
				+ "\nIdProf=" + IdProf + "\nIdEtudiant=" + IdEtudiant + "\nSalleNum=" + SalleNum;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Events other = (Events) obj;
		return Objects.equals(EventDate, other.EventDate) && EventNum == other.EventNum && IdAdmin == other.IdAdmin
				&& IdEtudiant == other.IdEtudiant && IdProf == other.IdProf && Objects.equals(SalleNum, other.SalleNum)
				&& Objects.equals(Sujet, other.Sujet);
	}
	
	
}
