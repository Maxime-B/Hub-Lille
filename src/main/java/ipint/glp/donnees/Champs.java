package ipint.glp.donnees;

public class Champs {
	private String libelle;
	private Integer limite;
	private TypeChamps typeChamps;
	
	public Champs(String libelle, Integer limite, TypeChamps typeChamps) {
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

	public Integer getLimite() {
		return limite;
	}

	public void setLimite(Integer limite) {
		this.limite = limite;
	}

	public TypeChamps getTypeChamps() {
		return typeChamps;
	}

	public void setTypeChamps(TypeChamps typeChamps) {
		this.typeChamps = typeChamps;
	}
	
	
}
