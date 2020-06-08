package saveInJdbc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import simulateurAssurance.Client;


public class AddClientInBdd 
{

	private static Client prospect ;

	public static void SplitProspectFromFile() 
	{	
		try {
			
			File file = new File("prospect.txt");
			FileReader fileR = new FileReader(file);
			BufferedReader buffer = new BufferedReader(fileR);
			String line;
			
			while((line = buffer.readLine()) != null) {
				
				String [] splitProspect;
				splitProspect = line.split(";");
				
				String nom = splitProspect[0];
				String prenom =splitProspect[1];
				String dateNaissance =splitProspect[2];
				boolean marie =Boolean.parseBoolean(splitProspect[3]);
				boolean pacse = Boolean.parseBoolean(splitProspect[4]);
				int nbrEnfants = Integer.parseInt(splitProspect[5]);
				
				 prospect = new Client(nom, prenom, dateNaissance, marie, pacse, nbrEnfants);
				
			}
			
			buffer.close();
			
		} catch(IOException ex) {
			System.err.println("Chargement du fichier impossible");
		}	
	}
	
	public static void SaveProspectInDB(Connection conn) {
		
		String nom = prospect.getNom();
		String prenom = prospect.getPrenom();
		String dateNaissance = prospect.getDateNaissance();
		int marie = 0;
		int pacse = 0;
		
		if(prospect.isMarie() == true) {
			marie = 1;
		}
		if(prospect.isPacse() == true) {
			pacse = 1;
		}
		
		int nbrEnfants = prospect.getNbrEnfants();
		
		try {
			
			String ajoutProspect = " INSERT INTO Client (Nom,Prenom,DateNaissance,Marie,Pacse,NbrEnfants)"
									+"VALUES('"+nom+"','"+prenom+"','"+dateNaissance+"','"+marie+"','"+pacse+"','"+nbrEnfants+"')";
			
			Statement statement = conn.createStatement();
			statement.executeUpdate(ajoutProspect);
			statement.close();
			
		}catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState : " + ((SQLException) ex).getSQLState());
			System.out.println("VendorError: " + ((SQLException) ex).getErrorCode());
		}
	}
	
}
