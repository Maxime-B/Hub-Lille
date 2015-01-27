package ipint.glp.metiers;

import java.util.List;

import ipint.glp.donnees.Droit;
import ipint.glp.donnees.Utilisateur;
import ipint.glp.fabriques.FabUtilisateur;

public class MetierUtilisateur {
	FabUtilisateur fabUtilisateur = FabUtilisateur.getInstance();

	
	private Utilisateur creerUtilisateur(String login, String prenom, String nom,
			String email, Droit droit) {
		return fabUtilisateur.creerUtilisateur(nom, prenom, email, droit);
	}
	
	public Utilisateur getUtilisateur(){
		Utilisateur utilisateur = fabUtilisateur.getUtilisateur();
		if (utilisateur == null) {
			utilisateur = creerUtilisateur(null, null, null, null, null);
		}
		return utilisateur;
	}
	
	

	public List<Utilisateur> listerUtilisateurs() {
		return fabUtilisateur.listerUtilisateurs();
	}
}
