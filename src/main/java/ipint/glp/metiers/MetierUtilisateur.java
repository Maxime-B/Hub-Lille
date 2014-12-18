package ipint.glp.metiers;

import java.util.List;

import ipint.glp.donnees.Droit;
import ipint.glp.donnees.Utilisateur;
import ipint.glp.fabriques.FabUtilisateur;

public class MetierUtilisateur {
	FabUtilisateur fabUtilisateur = FabUtilisateur.getInstance();

	
	public Utilisateur creerUtilisateur(String nom, String prenom,
			String email, Droit droit) {
		return fabUtilisateur.creerUtilisateur(nom, prenom, email, droit);
	}

	public List<Utilisateur> listerUtilisateurs() {
		return fabUtilisateur.listerUtilisateurs();
	}
}
