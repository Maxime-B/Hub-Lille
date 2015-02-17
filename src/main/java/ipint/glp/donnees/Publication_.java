package ipint.glp.donnees;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;


@StaticMetamodel(Publication.class)
public class Publication_ {
	public static volatile SingularAttribute<Publication, Integer> id;
	public static volatile SingularAttribute<Evenement, String> titre;
	public static volatile SingularAttribute<Publication, Utilisateur> utilisateur;
}