/**
 * 
 */
package ipint.glp.fabriques;

import ipint.glp.donnees.Evenement;
import ipint.glp.donnees.Utilisateur;

import java.util.Date;
import java.util.List;

import connexion.Connexion;

/**
 * @author duhaupas
 *
 */
public class FabEvenement {

	private static FabEvenement fb;
	private Connexion connexion;

	private FabEvenement() {
		connexion = Connexion.getConnexion();
	}

	public static FabEvenement getInstance() {
		if (fb == null) {
			fb = new FabEvenement();
		}
		return fb;
	}
	
	public Evenement creer(String titre, Date date, String lieu, String description,
			String duree, Utilisateur utilisateur) {
		Evenement o = new Evenement(titre, lieu, description, duree, utilisateur);
		connexion.getEm().persist(o);
		return o;
	}

	public List<Evenement> lister() {
		return connexion.getEm().createQuery("Select e from Evenement e").getResultList();
	}

	public void supprimer(Evenement a) {
		connexion.getEm()
			.createQuery("Delete from Evenement where titre = :titre")
			.setParameter("titre", a.getTitre())
			.executeUpdate();
	}

	public void supprimerTout() {
		connexion.getEm().getTransaction().begin();
		connexion.getEm().createQuery("Delete from Evenement").executeUpdate();
		connexion.getEm().getTransaction().commit();
	}

	public Evenement creer(Evenement o) {
		connexion.getEm().persist(o);
		return o;
	}
}
