package ipint.glp.fabriques;

import ipint.glp.donnees.Categorie;
import ipint.glp.donnees.Champ;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import javax.persistence.Query;
import connexion.Connexion;

public class FabCategorie {

	private Connexion connexion;
	static FabCategorie fc;
	private HashMap<String,Categorie> lesCategories;
	
	private FabCategorie(){
		lesCategories = new HashMap<String,Categorie>();
		connexion = Connexion.getConnexion();
	}
	
	public static FabCategorie getInstance(){
		if(fc == null){
			fc = new FabCategorie();
		}
		return fc;
	}
	
	public Categorie creerCategorie(String nom, List<Champ> champs){
		this.listerCategories();
		for (Entry<String, Categorie> entry : lesCategories.entrySet()) {
			Categorie cc = entry.getValue();
			if(nom.equals(entry.getKey())){
				return cc;
			}
		}
		Categorie c = new Categorie();
		c.setNom(nom);
		c.setChamps(champs);
		connexion.getEm().persist(c);
		lesCategories.put(nom, c);
		return c;
	}
	
	public List<Categorie> listerCategories(){
		Query query = connexion.getEm().createQuery("Select cat from Categorie cat");
		List<Categorie> categories =query.getResultList();
		lesCategories.clear();
		for(Categorie c : categories){
			lesCategories.put(c.getNom(), c);
		}
		return categories;
	}
	
	public Categorie getCategorie(String nomCategorie){
		return lesCategories.get(nomCategorie);
	}
}
