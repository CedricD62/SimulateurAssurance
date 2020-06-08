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
	// cr�ation des objets de type Groupe
	Groupes majSurMaison;
	Groupes majSurGarage;
	Groupes majfoyerEuro;
	// cr�ation des collections MAP de type Integer avec des Objets Groupes
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
		// ajuste dans le block IF / ELSE IF / ELSE la valeur en fonction des cl�
		// contenues dans les collection Maps afin d'�viter un AOBE

		return valeur;
	}

	@Override
	public void initialisationMap() {// methode initialisationMap()
		// remplissage des ciollections MAP avec les objets de type Groupes
		for (int i = 0; i < surface.length; i++) {

			// initialisation de majSurMaison de type Groupes prend en parametre 2 valeurs
			// valeur contenue dans tableau surface � l'indice i ainsi que valeur contenue
			// dans tableau majorationMaison � l'indice i
			majSurMaison = new Groupes(surface[i], majorationMaison[i]);
			// la collection map majSurfaceMaison ajoute comme cl� majSurMaison.getClee() (
			// correxpond � surface[i])
			// et comme valeur l'objet majSurMaison correspondant
			majSurfaceMaison.put(majSurMaison.getClee(), majSurMaison);

			// initialisation de majSurGarage de type Groupes prend en parametre 2 valeurs
			// valeur contenue dans tableau surface � l'indice i ainsi que valeur contenue
			// dans tableau majorationGarage � l'indice i
			majSurGarage = new Groupes(surface[i], majorationGarage[i]);
			// la collection map majSurfaceGarage ajoute comme cl� majSurGarage.getClee() (
			// correxpond � surface[i])
			// et comme valeur l'objet majSurGarage correspondant
			majSurfaceGarage.put(majSurGarage.getClee(), majSurGarage);

			// initialisation de majfoyerEuro de type Groupes prend en parametre 2 valeurs
			// valeur contenue dans tableau nbrPersonne � l'indice i ainsi que valeur
			// contenue dans tableau majorationEuroPersonne � l'indice i
			majfoyerEuro = new Groupes(nbrPersonne[i], majorationEuroPersonne[i]);
			// la collection map majEuroFoyer ajoute comme cl� majfoyerEuro.getClee() (
			// correxpond � surface[i])
			// et comme valeur l'objet majfoyerEuro correspondant
			majEuroFoyer.put(majfoyerEuro.getClee(), majfoyerEuro);
		}
	}

	@Override
	public void calculAssurance() {// redefinition de la methode calculAssurance() presente dans la classe asbtract
									// Contrat
		// initialisation variable locales
		double montant;
		setSurfaceLogement(calculSurface(getSurfaceLogement()));// mise � jour de la variable cylindre avec
																// setCylindre() qui stock le resultat en retour
																// de la methode calculCylindree qui prend en parametre
																// la valeur de la variable cylindre via getCylindre()
																// AVANT LA MODIFICATION !!!!

		// initialisation d'objets de type Groupes qui r�cup�rent les valeurs selon les
		// cl�s enregistr�es dans les collection Map
		Groupes majMaison = majSurfaceMaison.get(getSurfaceLogement());// majMaison r�cup�re l'objet stock� � la cl�
																		// correspondant � la valeur de
																		// getSurfaceLogement()
																		// de la collection majSurfaceMaison
		Groupes groupeGarage = majSurfaceGarage.get(getSurfaceLogement());// garage r�cup�re l'objet stock� � la cl�
																			// correspondant � la valeur de
																			// getSurfaceLogement()
		// de la collection majSurfaceGarage
		Groupes foyer = majEuroFoyer.get(getNbrOccupant());// foyer r�cup�re l'objet stock� � la cl� correspondant � la
															// valeur de getNbrOccupant()
															// de la collection majEuroFoyer

		montant = getTarif() * majMaison.getValeurDouble(); // montant r�cup�re le r�sultat de l'op�ration valeur tarif
															// * la valeurDouble de l'objet majMaison

		if (isGarage() == true) { // si la booleenne garage est sur true
			montant = montant + groupeGarage.getValeur(); // on r�cup�re la valeur de la majoration en euro en fonction
															// de la surface et on l'additionne � montant
		}

		montant = montant + foyer.getValeur(); // on r�cup�re la valeur de la majoration en euro en fonction de la
												// taille du foyer et on l'additionne � montant

		setTarif(montant);// on met � jour la variable tarif avec la valeur actuelle de montant
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
		System.out.println("Nom : " + prospect.getNom());// r�cup�ration du nom du client contenu dans l'objet prospect
															// de type Client
		System.out.println(" ");
		System.out.println("Pr�nom : " + prospect.getPrenom());// r�cup�ration du prenom du client contenu dans l'objet
																// prospect de type Client
		System.out.println(" ");
		System.out.println("Le tarif � payer pour l'assurance habitation est de : " + getTarif() + " euros");// affichage
																												// du
																												// tarif
																												// avec
																												// getTarif()
		System.out.println(" ");
		System.out.println("Veuillez joindre :");
		System.out.println(" ");
		System.out.println(" - RIB");
		System.out.println(" - Contrat de location");
		System.out.println(" - Carte d�identit� assureur");
	}

	@Override
	public String toString() {
		return prospect.getNom() + ";" + nbrOccupant + ";" + logement + ";" + surfaceLogement + ";" + garage + ";"
				+ tarif;
	}

}
