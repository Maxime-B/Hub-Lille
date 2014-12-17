
import ipint.glp.donnees.Annonce;
import ipint.glp.donnees.Categorie;
import ipint.glp.donnees.Champ;
import ipint.glp.donnees.Droit;
import ipint.glp.donnees.TypeAnnonce;
import ipint.glp.donnees.TypeChamp;
import ipint.glp.donnees.Utilisateur;
import ipint.glp.fabriques.FabAnnonce;
import ipint.glp.fabriques.FabCategorie;
import ipint.glp.fabriques.FabChamp;
import ipint.glp.fabriques.FabUtilisateur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import connexion.Connexion;

public class Main {

	public static void main(String[] args) {

		Connexion.getConnexion();
		for (Categorie c : FabCategorie.getInstance().listerCategories()) {
			System.out.println(c.getNom());
			for (Champ ch : c.getChamps()) {
				System.out.println(ch.getLibelle());
			}
		}
		Champ c1 = FabChamp.getInstance().creerChamp("titre", 60,
				TypeChamp.TEXTE);
		Champ c2 = FabChamp.getInstance()
				.creerChamp("date", 20, TypeChamp.DATE);
		List<Champ> champs = new ArrayList<Champ>();
		champs.add(c1);
		champs.add(c2);
		Categorie categorie = FabCategorie.getInstance().creerCategorie(
				"covoiturage", champs);
		Categorie categorie2 = FabCategorie.getInstance().creerCategorie(
				"biens", champs);
		System.out.println(" ");
		for (Champ cc : FabChamp.getInstance().listerChamps()) {
			System.out.println(cc.getLibelle());
		}

		System.out.println(" ");
		Utilisateur utilisateur = FabUtilisateur.getInstance()
				.creerUtilisateur("tyyyyy", "ettme", "yyyyy9",
						Droit.ADMIN);
		for (Utilisateur u : FabUtilisateur.getInstance().listerUtilisateurs()) {
			System.out.println(u.getId() + " " + u.getNom() + " "
					+ u.getPrenom() + " " + u.getDroit());
		}
		System.out.println(" ");
		HashMap<String, String> lesChamps = new HashMap<String, String>();
		for (Champ libelle : categorie.getChamps()) {
			lesChamps.put(libelle.getLibelle(), "val");
		}
		Annonce annonce = FabAnnonce.getInstance().creerAnnonce(categorie2,
				utilisateur, TypeAnnonce.offre, lesChamps);
		for (Annonce a : FabAnnonce.getInstance().listerAnnonces()) {
			System.out.println(a.getId() + " " + a.getCategorie().getNom()
					+ " " + a.getUtilisateur().getNom() + " " + a.getType());
			for (Entry<String, String> entry : a.getLesChamps().entrySet()) {
				System.out.println(entry.getKey() + " : " + entry.getValue());
			}
		}
		//FabAnnonce.getInstance().supprimerAnnonce(annonce);

		for (Annonce an : FabAnnonce.getInstance().listerAnnoncesParCategorie(
				categorie)) {
			System.out.println(an.getId() + " " + an.getCategorie().getNom()
					+ " " + an.getUtilisateur().getNom() + " " + an.getType());
		}

		
		for (Annonce an : FabAnnonce.getInstance().listerAnnoncesParCategorie(
				categorie2)) {
			System.out.println(an.getId() + " " + an.getCategorie().getNom()
					+ " " + an.getUtilisateur().getNom() + " " + an.getType());
		}
		Connexion.getConnexion().fermerConnexion();
		
	}
}