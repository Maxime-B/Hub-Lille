package ipint.glp.donnees;

public class Champs {
	String libelle, limite;
	TypeChamps typeChamps;
	
	public Champs(String libelle, String limite, TypeChamps typeChamps) {
		super();
		this.libelle = libelle;
		this.limite = limite;
		this.typeChamps = typeChamps;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getLimite() {
		return limite;
	}

	public void setLimite(String limite) {
		this.limite = limite;
	}

	public TypeChamps getTypeChamps() {
		return typeChamps;
	}

	public void setTypeChamps(TypeChamps typeChamps) {
		this.typeChamps = typeChamps;
	}
	
	
}
