package ipint.glp.donnees;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;



@Entity
public class Champ {
	
	
	@Id
	private String libelle;
	
	@Enumerated(EnumType.STRING)
	private TypeChamp typeChamp;
	
	private boolean obligatoire;
	
	public Champ(){
		super();
	}
	
	public Champ(String libelle,TypeChamp typeChamp,boolean obligatoire) {
		super();
		this.libelle = libelle;
		this.typeChamp = typeChamp;
		this.setObligatoire(obligatoire);
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public TypeChamp getTypeChamp() {
		return typeChamp;
	}

	public void setTypeChamp(TypeChamp typeChamp) {
		this.typeChamp = typeChamp;
	}

	public boolean isObligatoire() {
		return obligatoire;
	}

	public void setObligatoire(boolean obligatoire) {
		this.obligatoire = obligatoire;
	}
	
}
