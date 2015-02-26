package ipint.glp.donnees;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;




@Entity
public class Job extends Publication{


	private String remuneration;
	private String description;
	private String modalite;








	public Job(String titre, String remuneration, String description,String modalite,Utilisateur utilisateur){
		super();
		super.setTitre(titre);
		this.remuneration = remuneration;
		this.description = description;
		super.setUtilisateur(utilisateur);
		this.modalite=modalite;
	}

	public Job(){
		super();
		
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

	public String getModalite() {
		return modalite;
	}

	public void setModalite(String modalite) {
		this.modalite = modalite;
	}

}