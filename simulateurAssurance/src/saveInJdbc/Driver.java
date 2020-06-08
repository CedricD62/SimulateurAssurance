package saveInJdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Driver 
{

	// METHODE DE CONNECTION A LA BDD ( je me suis trompé je voulais créer une classe et non pas un nouveau main ..... )
	public static void main(String[] args)
	{
		
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3308/BdAssurance?useSSL=false&serverTimezone=UTC";
		String login="root";
		String password="";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection(url,login,password);
			System.out.println("Connexion OK");
			//METHODE DE RECUPERATION DINFO DEPUIS FICHIER TEXTE ET INSERTION EN BDD
				AddClientInBdd.SplitProspectFromFile();
				AddClientInBdd.SaveProspectInDB(conn);
				AddContratInBdd.SplitContratVoitureFromFile();
				AddContratInBdd.SaveContratVoitureInDb(conn);
				AddContratInBdd.SplitContratMotoFromFile();
				AddContratInBdd.SaveContratMotoInDb(conn);
				AddContratInBdd.SplitContratMaisonFromFile();
				AddContratInBdd.SaveContratMaisonInDb(conn);
			
		} catch (SQLException ex) {
			System.out.println("SQLException : " + ex.getMessage());
			System.out.println("SQLState : " + ((SQLException) ex).getSQLState());
			System.out.println("VendorError: " + ((SQLException) ex).getErrorCode());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}

	}
	
	
}
