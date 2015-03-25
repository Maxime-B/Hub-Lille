/**
 * 
 */
package ipint.glp.fabriques;

import ipint.glp.donnees.Evenement;
import ipint.glp.donnees.Evenement_;
import ipint.glp.donnees.Publication_;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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

		return em.createQuery(
				cq.select(root)
				.where(cb.greaterThan(root.get(Evenement_.dateDebut), cb.currentDate()))
				.orderBy(cb.asc(root.get(Evenement_.dateDebut)), cb.asc(root.get(Evenement_.heureDebut)))
			)
			.setMaxResults(50)
			.getResultList();
	}
	
	public Evenement obtenir(Integer id) {
		return em.find(Evenement.class, id);
	}
	
	public void supprimer(Evenement o) {
		Connexion.getConnexion().getTx().begin();
		em.remove(o);
		o.getUtilisateur().getLesEvenements().remove(o);
		Connexion.getConnexion().getTx().commit();
	}


	public Evenement creer(Evenement o) {
		Connexion.getConnexion().getTx().begin();
		em.persist(o);
		em.flush();
		Connexion.getConnexion().getTx().commit();
		o.getUtilisateur().getLesEvenements().add(o);
		return o;
	}

	public Evenement modifier(Evenement o) {
		Connexion.getConnexion().getTx().begin();
		em.merge(o);
		Connexion.getConnexion().getTx().commit();;
		return o;
	}

	public Evenement obtenir(String titre, Date dateDebut) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Evenement> cq = cb.createQuery(Evenement.class);
		Root<Evenement> root = cq.from(Evenement.class);
		try {
			return em.createQuery(
					cq.select(root)
					.where(
							cb.and(
								cb.equal(root.get(Publication_.titre), titre),
								cb.equal(root.get(Evenement_.dateDebut), dateDebut)
							)
					)
				)
				.getSingleResult();
		}
		catch (NoResultException noResultException) {
			return null;
		}
	}

	public List<Evenement> chercherParMotCle(String motCle) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Evenement> cq = cb.createQuery(Evenement.class);
		Root<Evenement> root = cq.from(Evenement.class);
		try {
			return em.createQuery(
					cq.select(root)
					.where(
							cb.or(
								cb.like(cb.lower(root.get(Publication_.titre)), "%" + motCle.toLowerCase() + "%"),
								cb.like(cb.lower(root.get(Evenement_.description)), "%" + motCle.toLowerCase() + "%"),
								cb.like(cb.lower(root.get(Evenement_.lieu)), "%" + motCle.toLowerCase() + "%")
							)
					)
				)
				.getResultList();
		}
		catch (NoResultException noResultException) {
			return null;
		}
	}
}
