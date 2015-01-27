/**
 * 
 */
package ipint.glp.fabriques;

import ipint.glp.donnees.Evenement;
import ipint.glp.donnees.Evenement_;
import ipint.glp.donnees.compositeKey.IdEvenement;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import connexion.Connexion;

/**
 * @author duhaupas
 *
 */
@Repository
public class FabEvenement {
	private static FabEvenement fb;

	//@PersistenceContext
	private EntityManager em = Connexion.getConnexion().getEm();

	private FabEvenement() {
	}

	public static FabEvenement getInstance() {
		if (fb == null) {
			fb = new FabEvenement();
		}
		return fb;
	}

	public List<Evenement> lister() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Evenement> cq = cb.createQuery(Evenement.class);
		Root<Evenement> root = cq.from(Evenement.class);
		System.out.println(Evenement_.dateDebut);
		return em.createQuery(
				cq.select(root)
				.where(cb.greaterThan(root.get(Evenement_.dateDebut), cb.currentDate()))
				.orderBy(cb.asc(root.get(Evenement_.dateDebut)), cb.asc(root.get(Evenement_.heureDebut)))
				)
				.setMaxResults(50)
				.getResultList();
	}
	
	public Evenement obtenir(IdEvenement id) {
		return em.find(Evenement.class, id);
	}
	
	public void supprimer(Evenement o) {
		em.remove(o);
	}


	public Evenement creer(Evenement o) {
		em.persist(o);
		return o;
	}

	public Evenement modifier(Evenement o) {
		em.merge(o);
		return o;
	}
	
	public void supprimerTout() {
		em.getTransaction().begin();
		em.createQuery("Delete from Evenement").executeUpdate();
		em.getTransaction().commit();
	}
}
