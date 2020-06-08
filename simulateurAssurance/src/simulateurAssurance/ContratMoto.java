package simulateurAssurance;

//import des librairies utils hashmap et map
import java.util.HashMap;
import java.util.Map;

public class ContratMoto extends ContratVehicule {// initialisation des variables locales

	private String nom;
	private int anneeAssu;
	private int puissance;
	private double tarif;
	private double bonusMalus;
	private int[] tabPuissance = { 200, 400, 600, 800, 1000, 1200, 1900 };
	private double[] tabMajorationPuissance = { 1, 1.1, 1.2, 1.3, 1.4, 1.5, 1.6 };
	private int[] tabAnneeAssu = { 0, 1, 2, 3, 4, 5, 6 };
	private int[] tabReductionAnneeEuro = { 0, 1, 2, 3, 4, 5, 6 };
	// creation d'un objet prospect de type Client
	Client prospect;
	// création des objets de type Groupes
	Groupes groupeMajCylindre;
	Groupes groupeReducAnneeAssu;
	// création des collections MAP de type Integer avec des Objets Groupes
	Map<Integer, Groupes> majCylindre = new HashMap<Integer, Groupes>();
	Map<Integer, Groupes> reducAnneeAssu = new HashMap<Integer, Groupes>();

	// constructeur de ContratMoto avec parametres
	public ContratMoto(Client client, int pPuissance, int pAnneeAssu, double pBonusMalus) {
		this.tarif = 30;
		this.puissance = pPuissance;
		this.anneeAssu = pAnneeAssu;
		this.bonusMalus = pBonusMalus;
		this.prospect = client;
	}
	
	public ContratMoto(String pNomClient, int pPuissance, int pAnneeAssu, double pBonusMalus, double tarif) {
		this.tarif = tarif;
		this.puissance = pPuissance;
		this.anneeAssu = pAnneeAssu;
		this.bonusMalus = pBonusMalus;
		this.nom = pNomClient;
	}

	// getteur et setteur des variables
	public String getNom() {
		return nom;
	}

	public void setNom(String pNomClient) {
		this.nom = pNomClient;
	}
	
	public int getAnnee() {
		return anneeAssu;
	}

	public void setAnnee(int pAnneeAssu) {
		this.anneeAssu = pAnneeAssu;
	}

	public int getCylindre() {
		return puissance;
	}

	public void setCylindre(int pPuissance) {
		this.puissance = pPuissance;
	}

	public double getTarif() {
		return tarif;
	}

	public void setTarif(double tarif) {
		this.tarif = tarif;
	}

	public double getBonusMalus() {
		return bonusMalus;
	}

	public void setBonusMalus(double pBonusMalus) {
		this.bonusMalus = pBonusMalus;
	}

	public int calculCylindree(int valeur) { // methode calculCylindree retourne une valeur de type int
		// valeur prend la valeur courante de getCylindre()
		valeur = getCylindre();

		if (valeur <= 200) {
			valeur = 200;
		} else if (valeur > 200 && valeur <= 400) {
			valeur = 400;
		} else if (valeur > 400 && valeur <= 600) {
			valeur = 600;
		} else if (valeur > 600 && valeur <= 800) {
			valeur = 800;
		} else if (valeur > 800 && valeur <= 1000) {
			valeur = 1000;
		} else if (valeur > 1000 && valeur <= 1200) {
			valeur = 1200;
		} else if (valeur > 1200) {
			valeur = 1900;
		}
		// ajuste dans le block IF / ELSE IF / ELSE la valeur en fonction des clé
		// contenues dans les collection Maps afin d'éviter un AOBE
		return valeur;
	}

	@Override
	public void initialisationMap() {// methode initialisationMap()
		// remplissage des ciollections MAP avec les objets de type Groupes
		for (int i = 0; i < tabPuissance.length; i++) {

			// initialisation de groupeMajCylindre de type Groupes prend en parametre 2
			// valeurs
			// valeur contenue dans tableau nbrCylindre à l'indice i ainsi que valeur
			// contenue dans tableau majorationCheveauxFiscaux à l'indice i
			groupeMajCylindre = new Groupes(tabPuissance[i], tabMajorationPuissance[i]);
			// la collection map majCylindre ajoute comme clé groupeMajCylindre.getClee() (
			// correxpond à nbrCylindre[i])
			// et comme valeur l'objet groupeMajCylindre correspondant
			majCylindre.put(groupeMajCylindre.getClee(), groupeMajCylindre);

			// initialisation de groupeReducAnneeAssu de type Groupes prend en parametre 2
			// valeurs
			// valeur contenue dans tableau anneeAssu à l'indice i ainsi que valeur contenue
			// dans tableau reducEuroAnnee à l'indice i
			groupeReducAnneeAssu = new Groupes(tabAnneeAssu[i], tabReductionAnneeEuro[i]);
			// la collection map reducAnneeAssu ajoute comme clé
			// groupeReducAnneeAssu.getClee() ( correxpond à anneeAssu[i])
			// et comme valeur l'objet groupeReducAnneeAssu correspondant
			reducAnneeAssu.put(groupeReducAnneeAssu.getClee(), groupeReducAnneeAssu);
		}
	}

	@Override
	public void calculAssurance() {// redefinition de la methode calculAssurance() presente dans la classe asbtract
									// Contrat
		// initialisation variable locales
		double montant;

		setCylindre(calculCylindree(getCylindre())); // mise à jour de la variable cylindre avec setCylindre() qui stock
														// le resultat en retour de la methode calculCylindree
														// qui prend en parametre la valeur de la variable cylindre via
														// getCylindre() AVANT LA MODIFICATION !!!!

		// initialisation d'objets de type Groupes qui récupèrent les valeurs selon les
		// clés enregistrées dans les collection Map
		Groupes majCheveaux = majCylindre.get(getCylindre()); // majCheveaux récupère l'objet stocké à la clé
																// correspondant à la valeur de getCylindre()
																// de la collection majCylindre
		Groupes reducAnnee = reducAnneeAssu.get(getAnnee()); // reducAnnee récupère l'objet stocké à la clé
																// correspondant à la valeur de getAnnee()
																// de la collection reducAnneeAssu

		montant = getTarif() * majCheveaux.getValeurDouble(); // montant récupère le résultat de l'opération valeur
																// tarif * la valeurDouble de l'objet nbCheveaux
		montant = montant - reducAnnee.getValeur(); // on applique la réduction par rapport au nombre d'année
													// d'assurance en récupérant la valeur contenue
													// dans getValeur de l'objet reducAnnee

		montant = montant * getBonusMalus();// on multiplie montant par la valeur de la variable bonusMalus

		setTarif(montant);// on met à jour la variable tarif avec la valeur actuelle de montant
	}

	@Override
	public void affichageForfait() { // redefinition de la methode affichageForfait() presente dans la classe
										// asbtract Contrat

		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("DEVIS ASSURANCE MOTO");
		System.out.println(" ");
		System.out.println("Nom : " + prospect.getNom());// récupération du nom du client contenu dans l'objet prospect
															// de type Client
		System.out.println(" ");
		System.out.println("Prénom : " + prospect.getPrenom());// récupération du prenom du client contenu dans l'objet
																// prospect de type Client
		System.out.println(" ");
		System.out.println("Le tarif à payer pour l'assurance moto est de : " + getTarif() + " euros"); // affichage du
																										// tarif avec
																										// getTarif()
		System.out.println(" ");
		System.out.println("Veuillez joindre :");
		System.out.println(" ");
		System.out.println(" - Copie de carte grise moto");
		System.out.println(" - Copie de permis moto");
		System.out.println(" - RIB");
		System.out.println(" - Carte d’identité assureur");
	}

	@Override
	public String toString() {
		return prospect.getNom() + ";" + puissance + ";" + anneeAssu + ";" + bonusMalus + ";" + tarif;
	}

}
