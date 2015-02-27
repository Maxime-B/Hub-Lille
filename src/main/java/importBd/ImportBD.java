package importBd;

import ipint.glp.donnees.Droit;
import ipint.glp.donnees.Utilisateur;
import ipint.glp.metiers.MetierUtilisateur;
import ipint.glp.metiers.exceptions.AuMoinsUnAdminException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import connexion.Connexion;

public class ImportBD {

	private MetierUtilisateur metierUtilisateur = new MetierUtilisateur();

	public void ajoutUtilisateur() {
		try {
			Reader csvData = new InputStreamReader(
					ImportBD.class
							.getResourceAsStream("ressource/utilisateurs.csv"),
					Charset.forName("UTF-8"));
			CSVParser cSVParser = new CSVParser(csvData, CSVFormat.DEFAULT);
			Droit[] droits = Droit.values();
			int i = 0;
			for (CSVRecord csvRecord : cSVParser) {
				String mail = csvRecord.get(2);
				if (mail == null || "".equals(mail.trim())) {
					continue;
				}
				i++;
				String nom = csvRecord.get(0);
				String prenom = csvRecord.get(1);
				String login = mail.split("@")[0];
				Set<Droit> authorities = new HashSet<Droit>();
				if (i % 2 == 0) {
					authorities.add(droits[0]);
				} else {
					authorities.add(droits[1]);
				}
				if (i % 3 == 0) {
					authorities.add(droits[0]);
				} else {
					authorities.add(droits[1]);
				}
				
				if (i % 20 == 0) {
					authorities.add(droits[3]);
				}
				if (i % 15 == 0) {
					authorities.add(droits[2]);
				}
				Utilisateur utilisateur = metierUtilisateur
						.getUtilisateur(login);
				utilisateur.setPrenom(prenom);
				utilisateur.setPrenom(nom);
				utilisateur.setDroits(authorities);
				utilisateur.setEmail(mail);
				metierUtilisateur.modifier(utilisateur);
			}
			cSVParser.close();
			Connexion.getConnexion().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException,
			AuMoinsUnAdminException {
		ImportBD importBD = new ImportBD();
		importBD.ajoutUtilisateur();
	}
}