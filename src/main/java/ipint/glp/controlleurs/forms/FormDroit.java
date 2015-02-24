package ipint.glp.controlleurs.forms;

import ipint.glp.donnees.Droit;

import java.util.HashSet;
import java.util.Set;

public class FormDroit {
	private String login;
	private String[] droits;
	private String filtre;
	
	public FormDroit() {
		super();
	}
	
	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}
	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	
	/**
	 * @return the droits
	 */
	public Set<Droit> getDroitsObject() {
		HashSet<Droit> droitsObject = new HashSet<Droit>();
		for(String droit : droits) {
			try {
				droitsObject.add(Droit.valueOf(droit));
			} catch (IllegalArgumentException illegalArgumentException) {}
		}
		return droitsObject;
	}

	/**
	 * @return the filtre
	 */
	public String getFiltre() {
		return filtre;
	}

	/**
	 * @param filtre the filtre to set
	 */
	public void setFiltre(String filtre) {
		this.filtre = filtre;
	}

	/**
	 * @return the droits
	 */
	public String[] getDroits() {
		return droits;
	}

	/**
	 * @param droits the droits to set
	 */
	public void setDroits(String[] droits) {
		this.droits = droits;
	}


}
