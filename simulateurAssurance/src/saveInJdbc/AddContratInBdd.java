package saveInJdbc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import simulateurAssurance.ContratMaison;
import simulateurAssurance.ContratMoto;
import simulateurAssurance.ContratVoiture;


public class AddContratInBdd 
{
	// création d'objets static 
	private static ContratVoiture contratV;
	private static ContratMoto contratMo;
	private static ContratMaison contratMa;

	//METHODE DE RECUPERATION DES INFORMATIONS STOCKEES DANS UN FICHIER TXT
	// TOUTES LES METHODES ETANT IDENTIQUES JE NE DECRIS LA PROCEDURE QU'UNE SEULE FOIS
	public static void SplitContratVoitureFromFile() {
		try {
			// récupération du fichier dans un objet de type File
			File file = new File("ContratVoiture.txt");
			FileReader fileR = new FileReader(file); // lecture du fichier dans objet fileR de type FileReader
			BufferedReader buffer = new BufferedReader(fileR); // stockage de la lecture dans objet buffer de type  BufferedReader
			String line;
			
			while((line = buffer.readLine()) != null) { // récupération de chaque ligne du buffer par variable line 
														// on reste dans la boucle tant que line n'est pas null 
				
				String [] splitContrat; 
				splitContrat = line.split(";"); // définition d'un tableau qui découpe les info à chaque fois qu'il rencontre le séparateur
												// recupération des infos du tableau dans les variables avec cast dans le type souhaité
				String nomProspect = splitContrat[0];
				int puissance = Integer.parseInt(splitContrat[1]);
				int anneeAssu = Integer.parseInt(splitContrat[2]);
				double bonusMalus = Double.parseDouble(splitContrat[3]);
				boolean assuConjoint = Boolean.parseBoolean(splitContrat[4]);
				boolean assuEnfant = Boolean.parseBoolean(splitContrat[5]);
				double tarif = Double.parseDouble(splitContrat[6]);
				//insertion dans un constructeur défini en fonction du fichier
				contratV = new ContratVoiture(nomProspect, puissance, anneeAssu, bonusMalus, assuConjoint, assuEnfant, tarif);	
			}
			buffer.close();// fermeture du buffer
		}catch(IOException ex) { 
			System.err.println("Chargement du fichier impossible");
		}
	}
	// METHODE D'INSERTION DES INFORMATIONS EN BASE DE DONNEES 
	// TOUTES LES METHODES ETANT IDENTIQUES JE NE DECRIS LA PROCEDURE QU'UNE SEULE FOIS
	public static void SaveContratVoitureInDb(Connection conn) {
		// récupération des informations de l'objet dans des variables locales
		String nomProspect = contratV.getNom();
		int puissance = contratV.getPuissance();
		double bonusMalus = contratV.getBonusMalus();
		int anneeAssu = contratV.getAnneeAssu();
		int assuConjoint = 0;
		if(contratV.isAssuranceConjoin() == true) {
			assuConjoint = 1;
		}
		int assuEnfant = 0;
		if(contratV.isAssuranceEnfant() == true) {
			assuEnfant = 1;
		}
		double tarif = contratV.getTarif();
		
		try {
			//etablissement d'un acces à la BDD 
			Statement statement = conn.createStatement();
			// définition de la requete SQL à éxécutée ici récupération de l'ID du client dans la BDD à partir de son nom
			ResultSet resultat = statement.executeQuery("SELECT NumClient as idClient FROM Client WHERE Nom ='"+nomProspect +"'");
			
			resultat.next();
			int idClient = resultat.getInt("idClient"); // id stockéd dans une variable locale 
			statement.close();// fermeture de l'acces à la BDD
			// création d'une requete sql d'insertion d'informations dans la table ContratVoiture
			String ajoutContrat = " INSERT INTO ContratVoiture (NbrChevaux,BonusMalus,NbrAnneeAssurance,AssuranceConjoint,AssuranceEnfant,TarifAssurance,NumClient)"
									+"VALUES('"+puissance+"','"+bonusMalus+"','"+anneeAssu+"','"+assuConjoint+"','"+assuEnfant+"','"+tarif+"','"+idClient+"')";
			// création d'un nouvel acces à la base de données
			statement = conn.createStatement();
			statement.executeUpdate(ajoutContrat); // execution de la mise à jours de la BDD avec la requete créée
			statement.close(); // fermeture de l'acces à la BDD
			
		} catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState : " + ((SQLException) ex).getSQLState());
			System.out.println("VendorError: " + ((SQLException) ex).getErrorCode());
		}
	}
	
	public static void SplitContratMotoFromFile() {
		try {
			File file = new File("ContratMoto.txt");
			FileReader fileR = new FileReader(file);
			BufferedReader buffer = new BufferedReader(fileR);
			String line;
			
			while((line = buffer.readLine()) != null) {
				
				String [] splitContrat;
				splitContrat = line.split(";");
				
				String nomProspect = splitContrat[0];
				int puissance = Integer.parseInt(splitContrat[1]);
				int anneeAssu = Integer.parseInt(splitContrat[2]);
				double bonusMalus = Double.parseDouble(splitContrat[3]);
				double tarif = Double.parseDouble(splitContrat[4]);
				
				contratMo = new ContratMoto(nomProspect, puissance, anneeAssu, bonusMalus, tarif);
			}
			buffer.close();
		}catch(IOException ex) {
			System.err.println("Chargement du fichier impossible");
		}
	}
	
	public static void SaveContratMotoInDb(Connection conn) {
		
		String nomProspect = contratMo.getNom();
		int puissance = contratMo.getCylindre();
		double bonusMalus = contratMo.getBonusMalus();
		int anneeAssu = contratMo.getAnnee();
		double tarif = contratMo.getTarif();
		
		try {
			
			Statement statement = conn.createStatement();
			ResultSet resultat = statement.executeQuery("SELECT NumClient as idClient FROM Client WHERE Nom ='"+nomProspect +"'");
			
			resultat.next();
			int idClient = resultat.getInt("idClient");
			statement.close();
			
			String ajoutContrat = " INSERT INTO ContratMoto (NbrCylindree,BonusMalus,NbrAnneeAssurance,TarifAssurance,NumClient)"
									+"VALUES('"+puissance+"','"+bonusMalus+"','"+anneeAssu+"','"+tarif+"','"+idClient+"')";
			
			statement = conn.createStatement();
			statement.executeUpdate(ajoutContrat);
			statement.close();
			
		} catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState : " + ((SQLException) ex).getSQLState());
			System.out.println("VendorError: " + ((SQLException) ex).getErrorCode());
		}
	}
	
	public static void SplitContratMaisonFromFile() {
		try {
			File file = new File("contratMaison.txt");
			FileReader fileR = new FileReader(file);
			BufferedReader buffer = new BufferedReader(fileR);
			String line;
			
			while((line = buffer.readLine()) != null) {
				
				String [] splitContrat;
				splitContrat = line.split(";");
				
				String nomProspect = splitContrat[0];
				int tailleFoyer = Integer.parseInt(splitContrat[1]);
				String typeLogement = splitContrat[2];
				int superficie = Integer.parseInt(splitContrat[3]);
				boolean garage = Boolean.parseBoolean(splitContrat[4]);
				double tarif = Double.parseDouble(splitContrat[5]);
				
				contratMa = new ContratMaison(nomProspect, superficie, garage, tailleFoyer, typeLogement, tarif);
			}
			buffer.close();
		}catch(IOException ex) {
			System.err.println("Chargement du fichier impossible");
		}
	}
	
	public static void SaveContratMaisonInDb(Connection conn) {
		
		String nomProspect = contratMa.getNom();
		int tailleFoyer = contratMa.getNbrOccupant();
		String typeLogement = contratMa.getLogement();
		int superficie = contratMa.getSurfaceLogement();
		int garage = 0;
		if(contratMa.isGarage()== true) {
			garage = 1;
		}
		double tarif = contratMa.getTarif();
		
		try {
			
			Statement statement = conn.createStatement();
			ResultSet resultat = statement.executeQuery("SELECT NumClient as idClient FROM Client WHERE Nom ='"+nomProspect +"'");
			
			resultat.next();
			int idClient = resultat.getInt("idClient");
			statement.close();
			
			String ajoutContrat = " INSERT INTO ContratMaison (TypeLogement,Garage,Superficie,TailleFoyer,TarifAssurance,NumClient)"
								 +"VALUES('"+typeLogement+"','"+garage+"','"+superficie+"','"+tailleFoyer+"','"+tarif+"','"+idClient+"')";
			
			statement = conn.createStatement();
			statement.executeUpdate(ajoutContrat);
			statement.close();
			
		} catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState : " + ((SQLException) ex).getSQLState());
			System.out.println("VendorError: " + ((SQLException) ex).getErrorCode());
		}
	}
}
