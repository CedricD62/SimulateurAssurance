package simulateurAssurance;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveClientsInFile {
	public static File file;
	public static FileWriter fileW;
	public static BufferedWriter buffer;

	// SURCHARGE DE METHODE SAVEFILE POUR CHAQUE OBJETS 
	// IMPRESSION DES INFORMATIONS CLIENTS DANS UN FICHIER TXT A PARTIR DE LA METHODE TOSTRING()
	
	public static void SaveFile(Client client) {

		try {
			file = new File("prospect.txt");
			fileW = new FileWriter(file, false);
			buffer = new BufferedWriter(fileW);

			buffer.write(client.toString());
			buffer.flush();
			buffer.close();
		} catch (IOException ex) {
			ex.printStackTrace();
			System.err.println("erreur d'impression");
		}
	}

	public static void SaveFile(ContratMaison contrat) {

		try {
			file = new File("contratMaison.txt");
			fileW = new FileWriter(file, false);
			buffer = new BufferedWriter(fileW);

			buffer.write(contrat.toString());
			buffer.flush();
			buffer.close();
		} catch (IOException ex) {
			ex.printStackTrace();
			System.err.println("erreur d'impression");
		}

	}

	public static void SaveFile(ContratVoiture contrat) {

		try {
			file = new File("ContratVoiture.txt");
			fileW = new FileWriter(file, false);
			buffer = new BufferedWriter(fileW);

			buffer.write(contrat.toString());
			buffer.flush();
			buffer.close();
		} catch (IOException ex) {
			ex.printStackTrace();
			System.err.println("erreur d'impression");
		}

	}

	public static void SaveFile(ContratMoto contrat) {

		try {
			file = new File("ContratMoto.txt");
			fileW = new FileWriter(file, false);
			buffer = new BufferedWriter(fileW);

			buffer.write(contrat.toString());
			buffer.flush();
			buffer.close();
		} catch (IOException ex) {
			ex.printStackTrace();
			System.err.println("erreur d'impression");
		}

	}

}
