/**
 * 
 */
package ipint.glp.metiers;

import java.util.Date;
import java.util.List;

import ipint.glp.donnees.Droit;
import ipint.glp.donnees.Evenement;
import ipint.glp.donnees.Utilisateur;
import ipint.glp.fabriques.FabEvenement;
import ipint.glp.metiers.exceptions.DroitInsuffisantException;

/**
 * @author duhaupas
 *
 */
public class MetierEvenement {
	private FabEvenement fabEvenement = FabEvenement.getInstance();

	private static MetierEvenement instance;

	private MetierEvenement() {
		super();
	}

	public static MetierEvenement getInstance() {
		if (instance == null) {
			instance = new MetierEvenement();
		}
		return instance;
	}


	public List<Evenement> lister() {
		return fabEvenement.lister();
	}

	public void supprimer(Evenement a) {
		fabEvenement.supprimer(a);
	}

	public void supprimerTout() {
		fabEvenement.supprimerTout();
	}

	public int hashCode() {
		return fabEvenement.hashCode();
	}

	public boolean equals(Object obj) {
		return fabEvenement.equals(obj);
	}

	public String toString() {
		return fabEvenement.toString();
	}
	
	public Evenement creer(String titre, Date date, String lieu,
			String description, String duree, Utilisateur utilisateur) throws DroitInsuffisantException {
		if (utilisateur.getDroit().ordinal() < Droit.VIE_ETUDIANTE.ordinal()) {
			throw new DroitInsuffisantException(Droit.VIE_ETUDIANTE, utilisateur.getDroit());
		}
		return fabEvenement.creer(titre, date, lieu, description, duree,
				utilisateur);
	}

	public Evenement creer(Evenement evenement) {
		return fabEvenement.creer(evenement);
	}
}
