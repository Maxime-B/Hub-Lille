package ipint.glp.metiers;

import java.util.List;

import ipint.glp.donnees.Champ;
import ipint.glp.donnees.TypeChamp;
import ipint.glp.fabriques.FabChamp;

public class MetierChamp {

	public Champ creerChamp(String libelle,TypeChamp typeChamp,boolean obligatoire) {;
		return FabChamp.getInstance().creerChamp(libelle, typeChamp, obligatoire);
	}

	public List<Champ> listerChamps() {
		return FabChamp.getInstance().listerChamps();
	}
	
	public Champ getChamp(String libelle){
		return FabChamp.getInstance().getChamp(libelle);
		
	}
}
