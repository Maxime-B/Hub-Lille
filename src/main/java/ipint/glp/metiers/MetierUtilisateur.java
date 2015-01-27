package ipint.glp.metiers;

import ipint.glp.donnees.Droit;
import ipint.glp.donnees.Utilisateur;
import ipint.glp.fabriques.FabUtilisateur;

import java.util.List;
import java.util.Map;

import org.springframework.security.cas.authentication.CasAuthenticationToken;

public class MetierUtilisateur {
	FabUtilisateur fabUtilisateur = FabUtilisateur.getInstance();

	
	private Utilisateur creerUtilisateur(String login, String prenom, String nom,
			String email, Droit droit) {
		return fabUtilisateur.creer(login, nom, prenom, email, droit);
	}
	
	/**
	 * crée l'utilisateur s'il n'est pas trouvé dans la base de données
	 * @param principal objet obtenu à partir de HttpServletRequest avec la méthode getUserPrincipal
	 * @return l'utilisateur du principal (Utilisateur avec champs renvoyant "null", uniquement s'il l'utilisateur n'est pas connecté)
	 */
	public Utilisateur getUtilisateur(CasAuthenticationToken principal){
		//TODO supprimer ce if en production
		if (principal == null) {
			return new Utilisateur("null", "null", "null", "null", Droit.DEFAUT);
		}
		
		Utilisateur utilisateur = fabUtilisateur.obtenir(principal.getName());
		if (utilisateur == null) {
			Map<String, String> map = principal.getAssertion().getAttributes();
			utilisateur = creerUtilisateur(principal.getName(), map.get("givenname"), map.get("sn"), map.get("email"), Droit.DEFAUT);
		}
		return utilisateur;
	}
	
	public List<Utilisateur> listerUtilisateurs() {
		return fabUtilisateur.listerUtilisateurs();
	}
}
