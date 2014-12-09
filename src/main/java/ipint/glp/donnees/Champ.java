package ipint.glp.donnees;

public class Champ {
	private String libelle;
	private Integer limite;
	private TypeChamp typeChamp;
	
	public Champ(){
		super();
	}
	
	public Champ(String libelle, Integer limite, TypeChamp typeChamp) {
		super();
		this.libelle = libelle;
		this.limite = limite;
		this.typeChamp = typeChamp;
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

	public TypeChamp getTypeChamp() {
		return typeChamp;
	}

	public void setTypeChamp(TypeChamp typeChamp) {
		this.typeChamp = typeChamp;
	}
	
	
}
