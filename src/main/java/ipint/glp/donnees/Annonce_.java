package ipint.glp.donnees;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Annonce.class)
public class Annonce_ {
	public static volatile SingularAttribute<Annonce, String> titre;
	public static volatile SingularAttribute<Annonce, String> description;
	public static volatile SingularAttribute<Annonce, Integer> id;
	public static volatile SingularAttribute<Annonce, Utilisateur> utilisateur;
	public static volatile SingularAttribute<Annonce, Categorie> categorie;
	public static volatile SingularAttribute<Annonce, TypeAnnonce> type;
	public static volatile SingularAttribute<Annonce, Integer> signal;
	public static volatile SingularAttribute<Annonce, Date> datepublication;
	public static volatile SingularAttribute<Annonce, Date> datefinPublication;
//	@CollectionTable(name="libelle", joinColumns=@javax.persistence.JoinColumn(name="valeur"))
//	private HashMap<String, String> lesChamps = new HashMap<String, String>();
//	private ArrayList<String> images;
}
