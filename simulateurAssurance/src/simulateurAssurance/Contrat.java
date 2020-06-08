package simulateurAssurance;

public abstract class Contrat {
	/*
	 * CLASSE ABSTRACT ET CLASSE MERE DES CLASSES SUIVANTES :
	 * 
	 * - ContratVoiture - ContratMoto - ContratMaison
	 * 
	 * OBLIGATION D'AJOUTER UN OBJET DE TYPE CLIENT ET DE REDEFINIR LES METHODES
	 * CI-DESSOUS
	 * 
	 */

	protected Client client;
	protected Groupes groupe;
	protected int tafif;

	public abstract void initialisationMap();

	public abstract void calculAssurance();

	public abstract void affichageForfait();

}