package connexion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Connexion {

	private static final String PERSISTENCE_UNIT_NAME = "Hub-Lille1";
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	private EntityManager em = factory.createEntityManager();
	private static Connexion connexion;
	private EntityTransaction tx = em.getTransaction();
	
	private Connexion(){
		
	}
	
	public static Connexion getConnexion(){
		if(connexion == null){
			connexion = new Connexion();		
		}
		return connexion;
	}

	public EntityManager getEm() {
		return em;
	}
	
	public EntityTransaction getTx() {
		return tx;
	}

	public void fermerConnexion(){
		connexion.getEm().getTransaction().commit();
		em.close();
	}
	public void flush() {
		em.flush();
	}
	public void commit() {
		em.getTransaction().commit();
		connexion.getEm().getTransaction().begin();
	}
}
