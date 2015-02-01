package ipint.glp.metiers;

import ipint.glp.donnees.Droit;
import ipint.glp.donnees.Utilisateur;
import ipint.glp.fabriques.FabUtilisateur;

import java.util.List;
import java.util.Map;

import org.jasig.cas.client.validation.Assertion;
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
		//TODO supprimer ce if avant production
		if (principal == null) {
			Utilisateur utilisateur = fabUtilisateur.obtenir("null");
			if (utilisateur == null) {
				utilisateur = creerUtilisateur("null", "null", "null", "null", Droit.DEFAUT);
			}
			return utilisateur;
		}
		
		return getUtilisateur(principal.getAssertion());
	}
	
	public List<Utilisateur> listerUtilisateurs() {
		return fabUtilisateur.listerUtilisateurs();
	}

	public Utilisateur getUtilisateur(Assertion assertion) {
		Utilisateur utilisateur = fabUtilisateur.obtenir(assertion.getPrincipal().getName());
		if (utilisateur == null) {
			Map<String, String> map = assertion.getAttributes();
			utilisateur = creerUtilisateur(assertion.getPrincipal().getName(), map.get("givenname"), map.get("sn"), map.get("email"), Droit.DEFAUT);
		}
		return utilisateur;
	}
}
