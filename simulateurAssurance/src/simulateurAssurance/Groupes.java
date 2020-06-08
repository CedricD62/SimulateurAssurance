package simulateurAssurance;

public class Groupes {
	// initialisation des variables locales
	private int clee;
	private int valeur;
	private double valeurDouble;
	private double cleeDouble;

	// constructeur de Groupes avec parametres int et int
	public Groupes(int clee, int valeur) {

		this.clee = clee;
		this.valeur = valeur;
	}

	// constructeur de Groupes avec parametres int et double
	public Groupes(int clee, double valeurDouble) {

		this.clee = clee;
		this.valeurDouble = valeurDouble;
	}

	// constructeur de Groupes avec parametres double et double
	public Groupes(double cleeDouble, double valeurDouble) {

		this.valeurDouble = valeurDouble;
		this.cleeDouble = cleeDouble;
	}

	// getteur et setteur des variables
	public int getClee() {
		return clee;
	}

	public void setClee(int clee) {
		this.clee = clee;
	}

	public int getValeur() {
		return valeur;
	}

	public void setValeur(int valeur) {
		this.valeur = valeur;
	}

	public double getValeurDouble() {
		return valeurDouble;
	}

	public void setValeurDouble(double valeurDouble) {
		this.valeurDouble = valeurDouble;
	}

	public double getCleeDouble() {
		return cleeDouble;
	}

	public void setCleeDouble(double cleeDouble) {
		this.cleeDouble = cleeDouble;
	}
}
