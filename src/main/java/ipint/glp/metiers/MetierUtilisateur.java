package ipint.glp.metiers;

import ipint.glp.donnees.Droit;
import ipint.glp.donnees.Utilisateur;
import ipint.glp.fabriques.FabUtilisateur;
import ipint.glp.metiers.exceptions.AuMoinsUnAdminException;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jasig.cas.client.validation.Assertion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.cas.authentication.CasAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class MetierUtilisateur {
	private static final Logger logger = LoggerFactory
			.getLogger(MetierUtilisateur.class);
	FabUtilisateur fabUtilisateur = FabUtilisateur.getInstance();
	
	private Utilisateur creerUtilisateur(String login, String prenom,
			String nom, String email) {
		HashSet<Droit> droits = new HashSet<Droit>();
		return fabUtilisateur.creer(login, nom, prenom, email, droits);
	}

	/**
	 * crée l'utilisateur s'il n'est pas trouvé dans la base de données
	 * 
	 * @param principal
	 *            objet obtenu à partir de HttpServletRequest avec la méthode
	 *            getUserPrincipal
	 * @return l'utilisateur du principal (Utilisateur avec champs renvoyant
	 *         "null", uniquement s'il l'utilisateur n'est pas connecté)
	 */
	public Utilisateur getUtilisateur(CasAuthenticationToken principal) {
		// TODO supprimer ce if avant production
		if (principal == null) {
			Utilisateur utilisateur = fabUtilisateur.obtenir("null");
			if (utilisateur == null) {
				utilisateur = creerUtilisateur("null", "null", "null", "null");
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
	 * 
	 * @param assertion
	 *            objet obtenu à partir de HttpServletRequest avec la méthode
	 *            getUserPrincipal().getAssertion()
	 * @return l'utilisateur du principal (Utilisateur avec champs renvoyant
	 *         "null", uniquement s'il l'utilisateur n'est pas connecté)
	 */
	public Utilisateur getUtilisateur(Assertion assertion) {
		Utilisateur utilisateur = fabUtilisateur.obtenir(assertion
				.getPrincipal().getName());
		if (utilisateur == null) {
			Map<String, String> map = assertion.getPrincipal().getAttributes();
			utilisateur = creerUtilisateur(assertion.getPrincipal().getName(),
					map.get("givenname"), map.get("sn"), map.get("mail"));
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
	 * à utiliser uniquement si request.getPrincipal ne renvoie pas
	 * l'utilisateur qu'on souhaite obtenir (si l'utilisateur est créé ne
	 * renseigne pas les autres champs)
	 * 
	 * @param login
	 * @return Un utilisateur dont le champ email n'est pas necessairement
	 *         renseigné
	 */
	public Utilisateur getUtilisateur(String login) {
		Utilisateur utilisateur = fabUtilisateur.obtenir(login);
		if (utilisateur == null) {
			utilisateur = creerUtilisateur(login, null, null, null);
		}
		return utilisateur;
	}

	/**
	 * @return
	 * @see ipint.glp.fabriques.FabUtilisateur#listerParDroitSpeciaux()
	 */
	public List<Utilisateur> listerParRole() {
		return fabUtilisateur.listerParRole();
	}

	/**
	 * @param o
	 * @return
	 * @see ipint.glp.fabriques.FabUtilisateur#modifier(ipint.glp.donnees.Utilisateur)
	 */
	public Utilisateur modifier(Utilisateur o) {
		return fabUtilisateur.modifier(o);
	}

	public void modifierRole(String login, Set<Droit> droits)
			throws AuMoinsUnAdminException {
		Utilisateur utilisateur = getUtilisateur(login);
		boolean etaitAdmin = utilisateur.getDroits().contains(Droit.ADMIN)
				|| utilisateur.getDroits().contains(Droit.SUPER_ADMIN);
		boolean estAdmin = droits.contains(Droit.ADMIN)
				|| droits.contains(Droit.SUPER_ADMIN);
		if (etaitAdmin && !estAdmin && fabUtilisateur.getNbAdmin() == 1) {
			throw new AuMoinsUnAdminException();
		}
		utilisateur.setDroits(droits);
		fabUtilisateur.modifier(utilisateur);
	}
	
	public void supprimerDroits(String login) throws AuMoinsUnAdminException {
		modifierRole(login, new HashSet<Droit>());
	}
}
