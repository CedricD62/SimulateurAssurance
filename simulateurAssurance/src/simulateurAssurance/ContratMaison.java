package simulateurAssurance;


//import des librairies utils hashmap et map
import java.util.HashMap;
import java.util.Map;



public class ContratMaison extends Contrat {// initialisation des variables locales
	
	private String nom;
	private int surfaceLogement;
	private int nbrOccupant;
	private boolean garage;
	private double tarif;
	private int[] surface = { 50, 60, 70, 80, 90, 100, 101 };
	private double[] majorationMaison = { 1, 1.1, 1.2, 1.3, 1.4, 1.5, 1.6 };
	private int[] majorationGarage = { 0, 1, 2, 3, 4, 5, 6 };
	private int[] nbrPersonne = { 1, 2, 3, 4, 5, 6, 7 };
	private int[] majorationEuroPersonne = { 0, 2, 3, 4, 5, 6, 7 };
	private String logement;
	// creation d'un objet prospect de type Client
	Client prospect;
	// création des objets de type Groupe
	Groupes majSurMaison;
	Groupes majSurGarage;
	Groupes majfoyerEuro;
	// création des collections MAP de type Integer avec des Objets Groupes
	Map<Integer, Groupes> majSurfaceMaison = new HashMap<Integer, Groupes>();
	Map<Integer, Groupes> majSurfaceGarage = new HashMap<Integer, Groupes>();
	Map<Integer, Groupes> majEuroFoyer = new HashMap<Integer, Groupes>();

	// constructeur de ContratMoto avec parametres
	public ContratMaison(Client client, int pSurface, boolean pGarage, int pOccupant, String pLogement) {

		this.prospect = client;
		this.garage = pGarage;
		this.surfaceLogement = pSurface;
		this.nbrOccupant = pOccupant;
		this.tarif = 15;
		this.logement = pLogement;
	}
	
	public ContratMaison(String pNomClient, int pSurface, boolean pGarage, int pOccupant, String pLogement, double pTarif) {

		this.nom = pNomClient;
		this.garage = pGarage;
		this.surfaceLogement = pSurface;
		this.nbrOccupant = pOccupant;
		this.tarif = pTarif;
		this.logement = pLogement;
	}

	// getteur et setteur des variables
	public String getNom() {
		return nom;
	}

	public void setNom(String pNomClient) {
		this.nom = pNomClient;
	}
	public int getSurfaceLogement() {
		return surfaceLogement;
	}

	public void setSurfaceLogement(int pSurface) {
		this.surfaceLogement = pSurface;
	}

	public boolean isGarage() {
		return garage;
	}

	public void setGarage(boolean pGarage) {
		this.garage = pGarage;
	}

	public int getNbrOccupant() {
		return nbrOccupant;
	}

	public void setNbrOccupant(int pOccupant) {
		this.nbrOccupant = pOccupant;
	}

	public double getTarif() {
		return tarif;
	}

	public void setTarif(double pTarif) {
		this.tarif = pTarif;
	}

	public String getLogement() {
		return logement;
	}

	public void setLogement(String pLogement) {
		this.logement = pLogement;
	}

	public int calculSurface(int valeur) {// methode calculSurface retourne une valeur de type int
		// valeur prend la valeur courante de getSurfaceLogement()
		valeur = getSurfaceLogement();

		if (valeur <= 50) {
			valeur = 50;
		} else if (valeur > 50 && valeur <= 60) {
			valeur = 60;
		} else if (valeur > 60 && valeur <= 70) {
			valeur = 70;
		} else if (valeur > 70 && valeur <= 80) {
			valeur = 80;
		} else if (valeur > 80 && valeur <= 90) {
			valeur = 90;
		} else if (valeur > 90 && valeur <= 100) {
			valeur = 100;
		} else if (valeur > 100) {
			valeur = 101;
		}
		// ajuste dans le block IF / ELSE IF / ELSE la valeur en fonction des clé
		// contenues dans les collection Maps afin d'éviter un AOBE

		return valeur;
	}

	@Override
	public void initialisationMap() {// methode initialisationMap()
		// remplissage des ciollections MAP avec les objets de type Groupes
		for (int i = 0; i < surface.length; i++) {

			// initialisation de majSurMaison de type Groupes prend en parametre 2 valeurs
			// valeur contenue dans tableau surface à l'indice i ainsi que valeur contenue
			// dans tableau majorationMaison à l'indice i
			majSurMaison = new Groupes(surface[i], majorationMaison[i]);
			// la collection map majSurfaceMaison ajoute comme clé majSurMaison.getClee() (
			// correxpond à surface[i])
			// et comme valeur l'objet majSurMaison correspondant
			majSurfaceMaison.put(majSurMaison.getClee(), majSurMaison);

			// initialisation de majSurGarage de type Groupes prend en parametre 2 valeurs
			// valeur contenue dans tableau surface à l'indice i ainsi que valeur contenue
			// dans tableau majorationGarage à l'indice i
			majSurGarage = new Groupes(surface[i], majorationGarage[i]);
			// la collection map majSurfaceGarage ajoute comme clé majSurGarage.getClee() (
			// correxpond à surface[i])
			// et comme valeur l'objet majSurGarage correspondant
			majSurfaceGarage.put(majSurGarage.getClee(), majSurGarage);

			// initialisation de majfoyerEuro de type Groupes prend en parametre 2 valeurs
			// valeur contenue dans tableau nbrPersonne à l'indice i ainsi que valeur
			// contenue dans tableau majorationEuroPersonne à l'indice i
			majfoyerEuro = new Groupes(nbrPersonne[i], majorationEuroPersonne[i]);
			// la collection map majEuroFoyer ajoute comme clé majfoyerEuro.getClee() (
			// correxpond à surface[i])
			// et comme valeur l'objet majfoyerEuro correspondant
			majEuroFoyer.put(majfoyerEuro.getClee(), majfoyerEuro);
		}
	}

	@Override
	public void calculAssurance() {// redefinition de la methode calculAssurance() presente dans la classe asbtract
									// Contrat
		// initialisation variable locales
		double montant;
		setSurfaceLogement(calculSurface(getSurfaceLogement()));// mise à jour de la variable cylindre avec
																// setCylindre() qui stock le resultat en retour
																// de la methode calculCylindree qui prend en parametre
																// la valeur de la variable cylindre via getCylindre()
																// AVANT LA MODIFICATION !!!!

		// initialisation d'objets de type Groupes qui récupèrent les valeurs selon les
		// clés enregistrées dans les collection Map
		Groupes majMaison = majSurfaceMaison.get(getSurfaceLogement());// majMaison récupère l'objet stocké à la clé
																		// correspondant à la valeur de
																		// getSurfaceLogement()
																		// de la collection majSurfaceMaison
		Groupes groupeGarage = majSurfaceGarage.get(getSurfaceLogement());// garage récupère l'objet stocké à la clé
																			// correspondant à la valeur de
																			// getSurfaceLogement()
		// de la collection majSurfaceGarage
		Groupes foyer = majEuroFoyer.get(getNbrOccupant());// foyer récupère l'objet stocké à la clé correspondant à la
															// valeur de getNbrOccupant()
															// de la collection majEuroFoyer

		montant = getTarif() * majMaison.getValeurDouble(); // montant récupère le résultat de l'opération valeur tarif
															// * la valeurDouble de l'objet majMaison

		if (isGarage() == true) { // si la booleenne garage est sur true
			montant = montant + groupeGarage.getValeur(); // on récupère la valeur de la majoration en euro en fonction
															// de la surface et on l'additionne à montant
		}

		montant = montant + foyer.getValeur(); // on récupère la valeur de la majoration en euro en fonction de la
												// taille du foyer et on l'additionne à montant

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
		System.out.println("DEVIS ASSURANCE MAISON");
		System.out.println(" ");
		System.out.println("Nom : " + prospect.getNom());// récupération du nom du client contenu dans l'objet prospect
															// de type Client
		System.out.println(" ");
		System.out.println("Prénom : " + prospect.getPrenom());// récupération du prenom du client contenu dans l'objet
																// prospect de type Client
		System.out.println(" ");
		System.out.println("Le tarif à payer pour l'assurance habitation est de : " + getTarif() + " euros");// affichage
																												// du
																												// tarif
																												// avec
																												// getTarif()
		System.out.println(" ");
		System.out.println("Veuillez joindre :");
		System.out.println(" ");
		System.out.println(" - RIB");
		System.out.println(" - Contrat de location");
		System.out.println(" - Carte d’identité assureur");
	}

	@Override
	public String toString() {
		return prospect.getNom() + ";" + nbrOccupant + ";" + logement + ";" + surfaceLogement + ";" + garage + ";"
				+ tarif;
	}

}
