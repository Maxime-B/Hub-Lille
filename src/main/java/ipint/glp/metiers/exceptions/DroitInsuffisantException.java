/**
 * 
 */
package ipint.glp.metiers.exceptions;

import ipint.glp.donnees.Droit;

/**
 * @author clement
 *
 */
public class DroitInsuffisantException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6696216096079737741L;
	private Droit droitRequis, droitDeUtilisateur;
	
	public DroitInsuffisantException(Droit droitRequis, Droit droitDeUtilisateur) {
		super("necessite " + droitRequis.toString() + "alors que l'utilisateur a " + droitDeUtilisateur.toString());
		this.droitRequis = droitRequis;
		this.droitDeUtilisateur = droitDeUtilisateur;
	}

	public Droit getDroitRequis() {
		return droitRequis;
	}

	public Droit getDroitDeUtilisateur() {
		return droitDeUtilisateur;
	}
	
}
