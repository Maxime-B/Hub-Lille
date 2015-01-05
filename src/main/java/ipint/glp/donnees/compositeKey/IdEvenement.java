package ipint.glp.donnees.compositeKey;

import java.io.Serializable;
import java.util.Date;

public class IdEvenement implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5818367738288315962L;
	private String titre;
	private Date dateDebut;
	
	public IdEvenement() {
	}
	
	public IdEvenement(String titre, Date dateDebut) {
		super();
		this.titre = titre;
		this.dateDebut = dateDebut;
	}

	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public Date getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dateDebut == null) ? 0 : dateDebut.hashCode());
		result = prime * result + ((titre == null) ? 0 : titre.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IdEvenement other = (IdEvenement) obj;
		if (dateDebut == null) {
			if (other.dateDebut != null)
				return false;
		} else if (!dateDebut.equals(other.dateDebut))
			return false;
		if (titre == null) {
			if (other.titre != null)
				return false;
		} else if (!titre.equals(other.titre))
			return false;
		return true;
	}
	
	
}
