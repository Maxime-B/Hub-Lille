package ipint.glp.fabriques;

import ipint.glp.donnees.Champ;
import ipint.glp.donnees.TypeChamp;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.persistence.Query;

import connexion.Connexion;

public class FabChamp {

	private Connexion connexion;
	static FabChamp fc;
	private HashMap<String,Champ> lesChamps;
	
	private FabChamp(){
		lesChamps = new HashMap<String,Champ>();
		connexion = Connexion.getConnexion();
	}
	
	public static FabChamp getInstance(){
		if(fc == null){
			fc = new FabChamp();
		}
		return fc;
	}
	
	public Champ creerChamp(String libelle, Integer limite, TypeChamp typeChamp,boolean obligatoire){
		this.listerChamps();
		for (Entry<String, Champ> entry : lesChamps.entrySet()) {
			Champ cc = entry.getValue();
			if(libelle.equals(entry.getKey())){
				return cc;
			}
		}
		Champ c = new Champ();
		c.setLibelle(libelle);
		c.setLimite(limite);
		c.setTypeChamp(typeChamp);
		c.setObligatoire(obligatoire);
		connexion.getEm().persist(c);
		lesChamps.put(libelle,c);
		return c;
	}
	
	public List<Champ> listerChamps(){
		Query query = connexion.getEm().createQuery("Select ch from Champ ch");
		List<Champ> champs =query.getResultList();
		lesChamps.clear();
		for(Champ c : champs){
			lesChamps.put(c.getLibelle(), c);
		}
		return champs;
	}
}
