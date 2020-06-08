package simulateurAssurance;

import java.util.Scanner;

public class Informations {
	// constante en static pour d�finir le type de logement
	private static final String MAISON = "maison";
	private static final String APPARTEMENT = "appartement";

	static Scanner sc = new Scanner(System.in);

	public static void informationClient() {
		// cr�ation d'un objet client non initialis�
		Client client;
		// initialisation des variables String pour r�cup�rer les informations client
		// ainsi que le type de logement
		String nom = "";
		String prenom = "";
		String date = "";
		String reponse = " ";
		String type = "";
		// initialisation des variables numerique int et double
		int choix = 0;
		int nbrEnfants = 0;
		int puissance = 0;
		int nbrAnneeAssurance = 0;
		int surface = 0;
		int foyer = 0;

		double bonusMalus = 0.0;
		// initialisation sur false des variables booleennes pour informations ne
		// n�cessitant pas une valeur particuli�re
		boolean garage = false;
		boolean conjointAssure = false;
		boolean marie = false;
		boolean pacse = false;
		boolean enfantAssure = false;

		// R�cup�ration des informations clients ( nom, prenom, date de naissance)
		System.out.print("Votre nom : ");
		nom = sc.next().toUpperCase(); // mise en majuscule du nom automatiquement
		System.out.print("Votre pr�nom : ");
		prenom = sc.next();
		System.out.print("Votre date de naissance : ");
		date = sc.next();
		System.out.print("Etes-vous mari�(e)?(o/n)"); // recup�ration de la situation familliale du client
		reponse = sc.next().toUpperCase(); // mise en majuscule de la r�ponse automatiquement
		while (!reponse.equals("O") && !reponse.equals("N")) // boucle de v�rification tant que la r�ponse n'est pas "O"
																// ou "N" on r�hit�re la demande
		{
			System.out.println(" ");
			System.out.println("Veuillez r�pondre par O pour oui ou N pour non");
			reponse = sc.next().toUpperCase();// mise en majuscule de la r�ponse automatiquement
		}
		if (reponse.equals("O")) { // bloc de condition => si reponse == "O"
			marie = true; // variable boolean marie passe sur true
		} else { // sinon
			System.out.println("Etes-vous pacs�(e)?"); // r�cup�ration situation pacse
			reponse = sc.next().toUpperCase();
			while (!reponse.equals("O") && !reponse.equals("N")) // boucle de v�rification de r�ponse
			{
				System.out.println(" ");
				System.out.println("Veuillez r�pondre par O pour oui ou N pour non");
				reponse = sc.next().toUpperCase();
			}
			if (reponse.equals("O")) { // si r�ponse == "O"
				pacse = true;// variable booleenne pacse passe sur true
			}
		} // dans le bloc de condition si le client r�pond "N" les variables booleennes
			// marie et pacse restent sur false
		System.out.print("Nombre d'enfants : "); // demande du nombre d'enfants
		nbrEnfants = Exeption.numericExceptions(nbrEnfants);// valeur r�cup�r�e dans la m�thode numericExceptions de la
															// classe Exeption
		while (nbrEnfants < 0) { // boucle de v�rification si chiffre < 0 => demande de nouvelle saisie
									// utilisateur
			System.out.println(" ");
			System.out.print("Si vous n'avez pas d'enfants veuillez saisir 0 : ");
			nbrEnfants = Exeption.numericExceptions(nbrEnfants);// valeur r�cup�r�e dans la m�thode numericExceptions de
																// la classe Exeption
		}
		// initialisation de mon objet client de type Client qui prend en parametre les
		// informations saisient par le client ainsi que
		// les variables booleenne marie et pacse
		client = new Client(nom, prenom, date, marie, pacse, nbrEnfants);
		SaveClientsInFile.SaveFile(client); // utilisation de la m�thode de sauvegarde des donn�es dans un fichier txt

		do {// entre dans la boucle DO / WHILE
			// affichage des types de contrats disponibles
			System.out.println(" ");
			System.out.println("Type de contrat souhait� :");
			System.out.println(" ");
			System.out.println("1 - Contrat d'assurance voiture");
			System.out.println("2 - Contrat d'assurance moto");
			System.out.println("3 - Contrat d'assurance maison");
			System.out.println(" ");
			System.out.print("Indiquez votre choix : ");
			choix = Exeption.numericExceptions(choix);// valeur r�cup�r�e dans la m�thode numericExceptions de la classe
														// Exeption
			while (choix < 1 || choix > 3) {// boucle de v�rification si valeur non disponible => demande de nouvelle
											// saisie utilisateur
				System.out.println(" ");
				System.out.print("Veuillez s�lectionner l'un des choix disponibles : ");
				choix = Exeption.numericExceptions(choix); // valeur r�cup�r�e dans la m�thode numericExceptions de la
															// classe Exeption
			}

			switch (choix) { // ENTRE DANS LE SWITCH/CASE se dirige vers le switch/case correspondant � la
								// saisie utilisateur

			case 1: // si choix == 1 entre dans la simulation contrat voiture
				// r�cup�ration d'informations compl�mentaires
				System.out.println(" ");
				System.out.println(
						"Informations compl�mentaires n�cessaire � la simulation de votre contrat d'assurance voiture :");
				System.out.println(" ");
				System.out.print("Nombre de cheveaux fiscaux : ");
				puissance = Exeption.numericExceptions(puissance);// valeur r�cup�r�e dans la m�thode numericExceptions
																	// de la classe Exeption
				while (puissance < 4 || puissance > 10) { // v�rification cheveaux fiscaux saisient compris entre 4 et
															// 10 inclus
					System.out.println(" ");
					System.out.print("le nombre de cheveaux fiscaux doit �tre situ� entre 4 et 10 (inclus) : ");
					puissance = Exeption.numericExceptions(puissance);// valeur r�cup�r�e dans la m�thode
																		// numericExceptions de la classe Exeption
				}
				System.out.print("Votre bonus/malus (compris entre 0,5 et 1,5) : ");
				bonusMalus = Exeption.numericExceptions(bonusMalus);// surcharge de m�thode cette m�thode
																	// numericExceptions est de type double
				while (bonusMalus < 0.5 || bonusMalus > 1.5) { // v�rification bonus/malus saisient compris entre 0.5 et
																// 1.5 inclus
					System.out.println(" ");
					System.out.print("Votre bonus/malus doit �tre compris entre 0,5 et 1,5 inclus : ");
					bonusMalus = Exeption.numericExceptions(bonusMalus);// surcharge de m�thode cette m�thode
																		// numericExceptions est de type double
				}
				System.out.print("Nombre d'ann�es d'assurances : ");
				nbrAnneeAssurance = Exeption.numericExceptions(nbrAnneeAssurance);// valeur r�cup�r�e dans la m�thode
																					// numericExceptions de la classe
																					// Exeption
				while (nbrAnneeAssurance < 1) { // si valeur saisie inf�rieur � 1
					System.out.println(" ");
					System.out.print("si vous disposez de moins d'un an d'assurance veuillez saisir 0 : "); // demande
																											// de
																											// choisir 0
					// valeur r�cup�r�e dans la m�thode numericExceptions de la classe Exeption
					nbrAnneeAssurance = Exeption.numericExceptions(nbrAnneeAssurance);
				}
				// bloc de condition if
				if (marie == true || pacse == true) { // si variable booleenne marie ou pacse est sur true
					System.out.println("Votre conjoint(e) sera-t-il(elle) assur� ? (O/N)"); // demande si le conjoin
																							// doit �tre assur�
					reponse = sc.next().toUpperCase();
					while (!reponse.equals("O") && !reponse.equals("N")) // v�rification que choix egal "O" ou "N"
					{
						System.out.println(" ");
						System.out.println("Veuillez r�pondre par O pour oui ou N pour non");
						reponse = sc.next().toUpperCase();
					}
					if (reponse.equals("O")) { // si le client r�pond "O"
						conjointAssure = true; // variable booleenne conjointAssure prend la valeur true
					}
				}
				System.out.println(" ");
				if (nbrEnfants != 0) { // si nombre d'enfants est diff�rent de 0
					System.out.println("Votre enfant sera assur� ? (O/N)"); // demande si le/les enfant(s) doit(vent)
																			// �tre assur�(s)
					reponse = sc.next().toUpperCase();
					while (!reponse.equals("O") && !reponse.equals("N")) // v�rification de la r�ponse
					{
						System.out.println(" ");
						System.out.println("Veuillez r�pondre par O pour oui ou N pour non");
						reponse = sc.next().toUpperCase();
					}
					if (reponse.equals("O")) { // si la r�ponse est "O" variable booleenne enfantAssur� prend la valeur
												// true
						enfantAssure = true;
					}
				}
				// initialisation d'un objet forfaitVoiture de type ContratVoiture avec
				// constructeur avec paramettre
				ContratVoiture forfaitVoiture = new ContratVoiture(client, puissance, nbrAnneeAssurance, bonusMalus,conjointAssure, enfantAssure);

				forfaitVoiture.initialisationMap(); // lancement de la m�thode initialisationMap() de l'objet
													// forfaitVoiture
				forfaitVoiture.calculAssurance(); // lancement de la m�thode calculAssurance() de l'objet forfaitVoiture
				forfaitVoiture.affichageForfait(); // lancement de la m�thode affichageForfait() de l'objet
													// forfaitVoiture
				SaveClientsInFile.SaveFile(forfaitVoiture);// utilisation de la m�thode de sauvegarde des donn�es dans un fichier txt
				break;

			case 2:// si choix == 2 entre dans la simulation contrat moto
					// r�cup�ration d'informations compl�mentaires
				/*********************************************************************************************************************
				 * ATTENTION POUR LES ACTIONS IDENTIQUES AU CASE 1 IL N'Y AURA PLUS DE
				 * COMMENTAIRE POUR EVITER LES REPETITIONS INUTILES
				 *********************************************************************************************************************/
				System.out.println(" ");
				System.out.println(
						"Informations compl�mentaires n�cessaire � la simulation de votre contrat d'assurance moto :");
				System.out.println(" ");
				System.out.print("Nombre de cylindr�e : ");
				puissance = Exeption.numericExceptions(puissance);
				while (puissance < 50 || puissance > 1900) {
					System.out.println(" ");
					System.out.print("la cylindr�e de votre moto doit �tre situ�e entre 40 et 1900 (inclus) : ");
					puissance = Exeption.numericExceptions(puissance);
				}
				System.out.print("Nombre d'ann�es d'assurances : ");
				nbrAnneeAssurance = Exeption.numericExceptions(nbrAnneeAssurance);
				while (nbrAnneeAssurance < 0) {
					System.out.println(" ");
					System.out.print("si vous disposez de moins d'un an d'assurance veuillez saisir 0 : ");
					nbrAnneeAssurance = Exeption.numericExceptions(nbrAnneeAssurance);
				}
				System.out.print("Votre bonus/malus (entre 0,5 et 1,5 inclus) : ");
				bonusMalus = Exeption.numericExceptions(bonusMalus);
				while (bonusMalus < 0.5 || bonusMalus > 1.5) {
					System.out.println(" ");
					System.out.print("Votre bonus/malus doit �tre compris entre 0,5 et 1,5 inclus : ");
					bonusMalus = Exeption.numericExceptions(bonusMalus);
				}
				// initialisation d'un objet forfaitMoto de type ContratMoto avec constructeur
				// avec paramettre
				ContratMoto forfaitMoto = new ContratMoto(client, puissance, nbrAnneeAssurance, bonusMalus);

				forfaitMoto.initialisationMap();// lancement de la m�thode initialisationMap()
				forfaitMoto.calculAssurance(); // lancement de la m�thode calculAssurance()
				forfaitMoto.affichageForfait(); // lancement de la m�thode affichageForfait()
				SaveClientsInFile.SaveFile(forfaitMoto);// utilisation de la m�thode de sauvegarde des donn�es dans un fichier txt
				break;

			default:// si choix == 3 entre dans la simulation contrat voiture
				// r�cup�ration d'informations compl�mentaires
				/*********************************************************************************************************************
				 * ATTENTION POUR LES ACTIONS IDENTIQUES AU CASE 1 IL N'Y AURA PLUS DE
				 * COMMENTAIRE POUR EVITER LES REPETITIONS INUTILES
				 *********************************************************************************************************************/
				System.out.println(" ");
				System.out.println(
						"Informations compl�mentaires n�cessaire � la simulation de votre contrat d'assurance maison :");
				System.out.println(" ");
				System.out.println("votre logement");
				System.out.println("- 1 Maison");
				System.out.println("- 2 Appartement");
				System.out.println(" ");
				System.out.print("Indiquez votre type de logement : ");
				choix = Exeption.numericExceptions(choix);
				while (choix != 1 && choix != 2) {
					System.out.println(" ");
					System.out.print("Selectionnez 1 pour Maison ou 2 pour Appartement : ");
					choix = Exeption.numericExceptions(choix);
				}
				if (choix == 1) {// si choix egal � 1 variable String type stock la constante MAISON
					type = MAISON;
				} else {// sinon variable String type stock la constante APPARTEMENT
					type = APPARTEMENT;
				}
				System.out.println(" ");
				System.out.println("Disposez-vous d'un garage? (O/N) ");
				reponse = sc.next().toUpperCase();
				while (!reponse.equals("O") && !reponse.equals("N")) {
					System.out.println(" ");
					System.out.println("Veuillez r�pondre par O pour oui ou N pour non");
					reponse = sc.next().toUpperCase();
				}
				if (reponse.equals("O")) {// si le client dispose d'un garage booleenne gara prend la valeur true
					garage = true;
				}
				System.out.print("Surface en m� : ");
				surface = Exeption.numericExceptions(surface);
				while (surface < 10) {
					System.out.println(" ");
					System.out.print("Veuillez indiquer une surface minimum de 10 m� : ");
					surface = Exeption.numericExceptions(surface);
				}
				System.out.print("Nombre de personne dans votre foyer : ");
				foyer = Exeption.numericExceptions(foyer);
				while (foyer < 1) {
					System.out.println(" ");
					System.out.print("il doit y avoir au minimum une personne dans votre foyer");
					foyer = Exeption.numericExceptions(foyer);
				}
				// initialisation d'un objet forfaitMaison de type ContratMaison avec
				// constructeur avec paramettre
				ContratMaison forfaitMaison = new ContratMaison(client, surface, garage, foyer, type);

				forfaitMaison.initialisationMap();// lancement de la m�thode initialisationMap()
				forfaitMaison.calculAssurance();// lancement de la m�thode calculAssurance()
				forfaitMaison.affichageForfait();// lancement de la m�thode affichageForfait()
				SaveClientsInFile.SaveFile(forfaitMaison);// utilisation de la m�thode de sauvegarde des donn�es dans un fichier txt
				break;
			}
			System.out.println(" ");
			System.out.println("Souhaitez-vous obtenir un devis pour un autre contrat ? (O/N)");
			reponse = sc.next().toUpperCase();
			while (!reponse.equals("O") && !reponse.equals("N")) {
				System.out.println(" ");
				System.out.println("Veuillez r�pondre par O pour oui ou N pour non");
				reponse = sc.next().toUpperCase();
			}
		} while (!reponse.equals("N")); // on reste dans la boucle DO WHILE tant que l'utilisateur saisir "O" sinon on
										// sort de la boucle et fin du programme

		sc.close();
	}
}
