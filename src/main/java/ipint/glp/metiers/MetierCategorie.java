package ipint.glp.metiers;

import java.util.ArrayList;
import java.util.List;

import ipint.glp.donnees.Categorie;
import ipint.glp.donnees.Champ;
import ipint.glp.donnees.TypeChamp;
import ipint.glp.fabriques.FabCategorie;

public class MetierCategorie {
	
	public MetierCategorie(){
		
	}
	
	public Categorie getCategorie(String nomCategorie){
		return FabCategorie.getInstance().getCategorie(nomCategorie);
	}
	
	public List<Categorie> listerCategories(){
		return FabCategorie.getInstance().listerCategories();
	}
	
	public Categorie creerCategorie(String nom, List<Champ> champs){
		return FabCategorie.getInstance().creerCategorie(nom, champs);
	}
	
	public List<String> listeTypeChamp(){
		List<String> tmp = new ArrayList<String>();
		for(TypeChamp c : TypeChamp.values()){
			tmp.add(c.toString());
		}
		return tmp;
	}
	
	public void supprimerCategorie(String nomCategorie){
		FabCategorie.getInstance().supprimerCategorie(nomCategorie);
	}
	
	public boolean existeAnnonce(String nomCategorie){
		return !FabCategorie.getInstance().getCategorie(nomCategorie).getLesAnnonces().isEmpty();
	}

}
