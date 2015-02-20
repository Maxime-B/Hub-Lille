package ipint.glp.donnees;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import ipint.glp.interfaces.Publication;


@Entity
public class Job implements Publication{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	


	private String titre;
	private String remuneration;
	private String description;
	private String modalite;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}




	@ManyToOne(cascade= CascadeType.PERSIST)
	private Utilisateur utilisateur;

	public Job(String titre, String remuneration, String description,String modalite,Utilisateur utilisateur){
		super();
		this.titre = titre;
		this.remuneration = remuneration;
		this.description = description;
		this.utilisateur = utilisateur;
		this.modalite=modalite;
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

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public String getModalite() {
		return modalite;
	}

	public void setModalite(String modalite) {
		this.modalite = modalite;
	}

}