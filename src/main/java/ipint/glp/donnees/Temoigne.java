package ipint.glp.donnees;

public class Temoigne {

	private String titre;
	private String continent;
	private String description;
	
	public Temoigne(){
		super();
	}
	public Temoigne(String titre, String continent, String description){
		this.titre=titre;
		this.continent=description;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getContinent() {
		return continent;
	}
	public void setContinent(String continent) {
		this.continent = continent;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
