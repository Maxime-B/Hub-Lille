import ipint.glp.donnees.Annonce;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Main {
  private static final String PERSISTENCE_UNIT_NAME = "Hub-Lille1";
  private static EntityManagerFactory factory;

  public static void main(String[] args) {
    factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    EntityManager em = factory.createEntityManager();
    // read the existing entries and write to console
    Query q = em.createQuery("select a from Annonce a");
    List<Annonce> annonceList = q.getResultList();
    for (Annonce aa : annonceList) {
      System.out.println(aa.getId());
    }
    System.out.println("Size: " + annonceList.size());

    // create new todo
    em.getTransaction().begin();
    Annonce ann = new Annonce();
    em.persist(ann);
    em.getTransaction().commit();

    em.close();
  }
}