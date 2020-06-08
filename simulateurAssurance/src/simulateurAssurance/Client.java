package simulateurAssurance;

public class Client {
	// initialisation des variables locales
	private int nbrEnfants;
	private String nom;
	private String Prenom;
	private String dateNaissance;
	private boolean marie;
	private boolean pacse;

	// constructeur de Client avec parametres
	public Client(String nom, String prenom, String dateNaissance, boolean marie, boolean pacse, int nbrEnfants) {

		this.nom = nom;
		this.Prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.marie = marie;
		this.pacse = pacse;
		this.nbrEnfants = nbrEnfants;
	}

	// getteur et setteur des variables
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return Prenom;
	}

	public void setPrenom(String prenom) {
		Prenom = prenom;
	}

	public String getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public boolean isMarie() {
		return marie;
	}

	public void setMarie(boolean marie) {
		this.marie = marie;
	}

	public boolean isPacse() {
		return pacse;
	}

	public void setPacse(boolean pacse) {
		this.pacse = pacse;
	}

	public int getNbrEnfants() {
		return nbrEnfants;
	}

	public void setNbrEnfants(int nbrEnfants) {
		this.nbrEnfants = nbrEnfants;
	}

	@Override
	public String toString() {
		return nom + ";" + Prenom + ";" + dateNaissance + ";" + marie + ";" + pacse + ";" + nbrEnfants + "";
	}

}
