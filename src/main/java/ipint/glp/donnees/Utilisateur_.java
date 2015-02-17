package ipint.glp.donnees;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Utilisateur.class)
public class Utilisateur_ {
	
	public static volatile SingularAttribute<Utilisateur, String> login;
	public static volatile SingularAttribute<Utilisateur, String> nom;
	public static volatile SingularAttribute<Utilisateur, String> prenom;
	public static volatile SingularAttribute<Utilisateur, String> email;
	
	public static volatile SetAttribute<Utilisateur, Droit> droits;
	public static volatile ListAttribute<Utilisateur, Annonce> lesAnnonces;
	public static volatile ListAttribute<Utilisateur, Evenement> lesEvenements;
	public static volatile ListAttribute<Utilisateur, Job> lesJobs;
}
