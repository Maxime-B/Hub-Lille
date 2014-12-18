package ipint.glp.metiers;

import java.util.List;

import ipint.glp.donnees.Champ;
import ipint.glp.donnees.TypeChamp;
import ipint.glp.fabriques.FabChamp;

public class MetierChamp {

	public Champ creerChamp(String libelle, Integer limite, TypeChamp typeChamp) {
		return FabChamp.getInstance().creerChamp(libelle, limite, typeChamp);
	}

	public List<Champ> listerChamps() {
		return FabChamp.getInstance().listerChamps();
	}
}
