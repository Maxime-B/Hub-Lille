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
				utilisateur = creerUtilisateur("null", "null", "null", "null", Droit.ROLE_DEFAUT);
			}
			return utilisateur;
		}
		return getUtilisateur(principal.getAssertion());
	}
	
	public List<Utilisateur> listerUtilisateurs() {
		return fabUtilisateur.listerUtilisateurs();
	}

	/**
	 * crée l'utilisateur s'il n'est pas trouvé dans la base de données
	 * @param assertion objet obtenu à partir de HttpServletRequest avec la méthode getUserPrincipal().getAssertion()
	 * @return l'utilisateur du principal (Utilisateur avec champs renvoyant "null", uniquement s'il l'utilisateur n'est pas connecté)
	 */
	public Utilisateur getUtilisateur(Assertion assertion) {
		Utilisateur utilisateur = fabUtilisateur.obtenir(assertion.getPrincipal().getName());
		if (utilisateur == null) {
			Map<String, String> map = assertion.getPrincipal().getAttributes();
			utilisateur = creerUtilisateur(assertion.getPrincipal().getName(), map.get("givenname"), map.get("sn"), map.get("mail"), Droit.ROLE_DEFAUT);
		} else if (utilisateur.getEmail() == null) {
			Map<String, String> map = assertion.getPrincipal().getAttributes();
			utilisateur.setPrenom(map.get("givenname"));
			utilisateur.setNom(map.get("sn"));
			utilisateur.setEmail(map.get("mail"));
			fabUtilisateur.modifier(utilisateur);
		}
		return utilisateur;
	}
	/**
	 * à utiliser uniquement si request.getPrincipal ne renvoie pas l'utilisateur qu'on souhaite obtenir (si l'utilisateur est créé ne renseigne pas les autres champs)
	 * @param login
	 * @return Un utilisateur dont le champ email n'est pas necessairement renseigné
	 */
	public Utilisateur getUtilisateur(String login) {
		Utilisateur utilisateur = fabUtilisateur.obtenir(login);
		if (utilisateur == null) {
			utilisateur = creerUtilisateur(login,null,null,null, Droit.ROLE_DEFAUT);
		}
		return utilisateur;
	}

	/**
	 * @return
	 * @see ipint.glp.fabriques.FabUtilisateur#listerParDroitSpeciaux()
	 */
	public List<Utilisateur> listerParDroitSpeciaux() {
		return fabUtilisateur.listerParDroitSpeciaux();
	}

	/**
	 * @param o
	 * @return
	 * @see ipint.glp.fabriques.FabUtilisateur#modifier(ipint.glp.donnees.Utilisateur)
	 */
	public Utilisateur modifier(Utilisateur o) {
		return fabUtilisateur.modifier(o);
	}
}
