package ipint.glp.donnees;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Evenement.class)
public class Evenement_ {
	public static volatile SingularAttribute<Evenement, String> titre;
	public static volatile SingularAttribute<Evenement, String> description;
	public static volatile SingularAttribute<Evenement, String> lieu;
	public static volatile SingularAttribute<Evenement, Date> dateDebut;
	public static volatile SingularAttribute<Evenement, Time> heureDebut;
}
