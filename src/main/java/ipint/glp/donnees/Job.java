package ipint.glp.donnees;

import ipint.glp.interfaces.Publication;

public class Job {

	private String titre;
	private String remuneration;
	private String description;

	public Job(String titre, String remuneration, String description){
		this.titre = titre;
		this.remuneration = remuneration;
		this.description = description;
	}

	public Job(){
		super();
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getRemuneration() {
		return remuneration;
	}
	public void setRemuneration(String remuneration) {
		this.remuneration = remuneration;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}



}
