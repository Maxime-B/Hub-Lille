package ipint.glp.metiers;

import java.util.List;

import ipint.glp.donnees.Categorie;
import ipint.glp.donnees.Champ;
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

}
