package simulateurAssurance;


// import des librairies utils hashmap et map
import java.util.HashMap;
import java.util.Map;

public class ContratVoiture extends ContratVehicule { // initialisation des variables locales
	private String nom;
	private int puissance;
	private int anneeAssu;
	private double tarif;
	private double bonusMalus;
	private boolean assuranceConjoin;
	private boolean assuranceEnfant;
	private int[] tabPuissance = { 4, 5, 6, 7, 8, 9, 10 };
	private double[] tabMajorationPuissance = { 1, 1.1, 1.2, 1.3, 1.4, 1.5, 1.6 };
	private int[] tabAnneeAssu = { 0, 1, 2, 3, 4, 5, 6 };
	private int[] tabReductionAnneeEuro = { 0, 1, 2, 3, 4, 5, 6 };
	private int[] tabMajorationEuroAssures = { 1, 2, 3, 4, 5, 6, 7 };
	// cr�ation des objets de type Groupes
	Groupes groupeNbrCheveaux;
	Groupes groupeReducAnneeAssu;
	Groupes groupeMajEuroConjoint;
	Groupes groupeMajEuroEnfants;
	// cr�ation des collections MAP de type Integer avec des Objets Groupes
	Map<Integer, Groupes> majCheveaux = new HashMap<Integer, Groupes>();
	Map<Integer, Groupes> reducAnneeAssu = new HashMap<Integer, Groupes>();
	Map<Integer, Groupes> majEuroConjoint = new HashMap<Integer, Groupes>();
	Map<Integer, Groupes> majEuroenfants = new HashMap<Integer, Groupes>();

	// creation d'un objet prospect de type Client
	Client prospect;

	// constructeur de ContratVoiture avec parametres
	public ContratVoiture(Client client, int pPuissance, int pAnneeAssurance, double pBonusMalus,boolean pConjoinAssur�, boolean pEnfantAssure) {
		this.prospect = client;
		this.puissance = pPuissance;
		this.anneeAssu = pAnneeAssurance;
		this.bonusMalus = pBonusMalus;
		this.assuranceConjoin = pConjoinAssur�;
		this.assuranceEnfant = pEnfantAssure;
		this.tarif = 20;
	}

	public ContratVoiture(String pNomClient, int pPuissance, int pAnneeAssurance, double pBonusMalus,boolean pConjoinAssur�, boolean pEnfantAssure, double tarif) {
		this.nom = pNomClient;
		this.puissance = pPuissance;
		this.anneeAssu = pAnneeAssurance;
		this.bonusMalus = pBonusMalus;
		this.assuranceConjoin = pConjoinAssur�;
		this.assuranceEnfant = pEnfantAssure;
		this.tarif = tarif;
	}
	
	// getteur et setteur des variables
	
	public String getNom() {
		return nom;
	}

	public void setNom(String pNomClient) {
		this.nom = pNomClient;
	}
	
	
	public int getPuissance() {
		return puissance;
	}

	public void setPuissance(int pPuissance) {
		this.puissance = pPuissance;
	}

	public int getAnneeAssu() {
		return anneeAssu;
	}

	public void setAnneeAssu(int pAnneeAssurance) {
		this.anneeAssu = pAnneeAssurance;
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

	public boolean isAssuranceConjoin() {
		return assuranceConjoin;
	}

	public void setAssuranceConjoin(boolean pConjoinAssur�) {
		this.assuranceConjoin = pConjoinAssur�;
	}

	public boolean isAssuranceEnfant() {
		return assuranceEnfant;
	}

	public void setAssuranceEnfant(boolean pEnfantAssure) {
		this.assuranceEnfant = pEnfantAssure;
	}

	@Override
	public void initialisationMap() { // redefinition de la methode initialisationMap() presente dans la classe
										// asbtract Contrat
		// remplissage des ciollections MAP avec les objets de type Groupes
		for (int i = 0; i < tabPuissance.length; i++) {

			// initialisation de groupeNbrCheveaux de type Groupes prend en parametre 2
			// valeurs
			// valeur contenue dans tableau cheveauxFiscaux � l'indice i ainsi que valeur
			// contenue dans tableau majorationCheveauxFiscaux � l'indice i
			groupeNbrCheveaux = new Groupes(tabPuissance[i], tabMajorationPuissance[i]);
			// la collection map majCheveaux ajoute comme cl� groupeNbrCheveaux.getClee() (
			// correxpond � cheveauxFiscaux[i] )
			// et comme valeur l'objet groupeNbrCheveaux correspondant
			majCheveaux.put(groupeNbrCheveaux.getClee(), groupeNbrCheveaux);

			// initialisation de groupeReducAnneeAssu de type Groupes prend en parametre 2
			// valeurs
			// valeur contenue dans tableau anneeAssu � l'indice i ainsi que valeur contenue
			// dans tableau reducEuroAnnee � l'indice i
			groupeReducAnneeAssu = new Groupes(tabAnneeAssu[i], tabReductionAnneeEuro[i]);
			// la collection map reducAnneeAssu ajoute comme cl�
			// groupeReducAnneeAssu.getClee() ( correxpond � anneeAssu[i] )
			// et comme valeur l'objet groupeReducAnneeAssu correspondant
			reducAnneeAssu.put(groupeReducAnneeAssu.getClee(), groupeReducAnneeAssu);

			// initialisation de groupeMajEuroConjoint de type Groupes prend en parametre 2
			// valeurs
			// valeur contenue dans tableau cheveauxFiscaux � l'indice i ainsi que valeur
			// contenue dans tableau majorationEuroAssures � l'indice i
			groupeMajEuroConjoint = new Groupes(tabPuissance[i], tabMajorationEuroAssures[i]);
			// la collection map majEuroConjoint ajoute comme cl�
			// groupeMajEuroConjoint.getClee() ( correxpond � cheveauxFiscaux[i] )
			// et comme valeur l'objet groupeMajEuroConjoint correspondant
			majEuroConjoint.put(groupeMajEuroConjoint.getClee(), groupeMajEuroConjoint);

			// initialisation de groupeMajEuroEnfants de type Groupes prend en parametre 2
			// valeurs
			// valeur contenue dans tableau cheveauxFiscaux � l'indice i ainsi que valeur
			// contenue dans tableau majorationEuroAssures � l'indice i
			groupeMajEuroEnfants = new Groupes(tabPuissance[i], tabMajorationEuroAssures[i]);
			// la collection map majEuroenfants ajoute comme cl�
			// groupeMajEuroEnfants.getClee() ( correxpond � cheveauxFiscaux[i] )
			// et comme valeur l'objet groupeMajEuroEnfants correspondant
			majEuroenfants.put(groupeMajEuroEnfants.getClee(), groupeMajEuroEnfants);
		}
	}
	
	@Override
	public void calculAssurance() { // redefinition de la methode calculAssurance() presente dans la classe asbtract
									// Contrat
		// initialisation variable locales
		double montant;
		// initialisation d'objets de type Groupes qui r�cup�rent les valeurs selon les
		// cl�s enregistr�es dans les collection Map
		Groupes nbCheveaux = majCheveaux.get(getPuissance()); // nbCheveaux r�cup�re l'objet stock� � la cl�
																// correspondant � la valeur de getNbrCheveaux()
																// de la collection majCheveaux
		Groupes reducAnnee = reducAnneeAssu.get(getAnneeAssu()); // reducAnnee r�cup�re l'objet stock� � la cl�
																	// correspondant � la valeur de getAnn�eAssu()
																	// de la collection reducAnneeAssu
		Groupes majConjoint = majEuroConjoint.get(getPuissance()); // majConjoint r�cup�re l'objet stock� � la cl�
																		// correspondant � la valeur de getNbrCheveaux()
																		// de la collection majEuroConjoint
		Groupes majEnfant = majEuroenfants.get(getPuissance()); // majEnfant r�cup�re l'objet stock� � la cl�
																	// correspondant � la valeur de getNbrCheveaux()
																	// de la collection majEuroenfants

		montant = getTarif() * nbCheveaux.getValeurDouble(); // montant r�cup�re le r�sultat de l'op�ration valeur tarif
																// * la valeurDouble de l'objet nbCheveaux
		montant = montant - reducAnnee.getValeur(); // on applique la r�duction par rapport au nombre d'ann�e
													// d'assurance en r�cup�rant la valeur contenue
													// dans getValeur de l'objet reducAnnee

		if (isAssuranceConjoin() == true) { // si la variable booleenne assuranceConjoin est sur true
			montant = montant + majConjoint.getValeur(); // on r�cup�re la valeur de la majoration conjoin en fonction
															// des cheveaux fiscaux et on l'additionne � montant
		}

		if (isAssuranceEnfant() == true) { // si variable booleenne assuranceEnfant est sur true
			montant = montant + majEnfant.getValeur(); // on r�cup�re la valeur de la majoration enfant en fonction des
														// cheveaux fiscaux et on l'additionne � montant
		}

		montant = montant * getBonusMalus(); // on multiplie montant par la valeur de la variable bonusMalus

		setTarif(montant); // on met � jour la variable tarif avec la valeur actuelle de montant
	}

	@Override
	public void affichageForfait() { // redefinition de la methode affichageForfait() presente dans la classe
										// asbtract Contrat

		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("DEVIS ASSURANCE VOITURE");
		System.out.println(" ");
		System.out.println("Nom : " + prospect.getNom()); // r�cup�ration du nom du client contenu dans l'objet prospect
															// de type Client
		System.out.println(" ");
		System.out.println("Pr�nom : " + prospect.getPrenom()); // r�cup�ration du prenom du client contenu dans l'objet
																// prospect de type Client
		System.out.println(" ");
		System.out.println("Le tarif � payer pour l'assurance de votre voiture est de : " + getTarif() + " euros"); // affichage
																													// du
																													// tarif
																													// avec
																													// getTarif()
		System.out.println(" ");
		System.out.println("Veuillez joindre :");
		System.out.println(" ");
		System.out.println(" - RIB");
		System.out.println(" - Copie de carte grise");
		System.out.println(" - Copie de permis");
		if (prospect.isMarie() == true && isAssuranceConjoin() == true) { // si les variables booleenne marie ( de
																			// l'objet prospect) et assuranceConjoin
																			// sont sur true
			System.out.println(" - Copie permis conjoint"); // affichage du message
		}
		if (prospect.isPacse() == true && isAssuranceConjoin() == true) {// si les variables booleenne pacse ( de
																			// l'objet prospect) et assuranceConjoin
																			// sont sur true
			System.out.println(" - Copie permis conjoint"); // affichage du message
		}
		if (prospect.getNbrEnfants() > 0 && isAssuranceEnfant() == true) { // si la valeur de variable NbrEnfants de
																			// l'objet prospect est sup�rieur � 0 et
																			// si variable booleenne assuranceEnfant est
																			// sur true
			System.out.println(" - Copie permis enfant");
		}
		System.out.println(" - Carte d�identit� assureur");
	}

	@Override
	public String toString() {
		return prospect.getNom() + ";" + puissance + ";" + anneeAssu + ";" + bonusMalus + ";" + assuranceConjoin + ";"
				+ assuranceEnfant + ";" + tarif;
	}

}
