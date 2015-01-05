/**
 * 
 */
package ipint.glp.metiers;

import ipint.glp.donnees.Evenement;
import ipint.glp.donnees.compositeKey.IdEvenement;
import ipint.glp.fabriques.FabEvenement;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author duhaupas
 *
 */
@Service
public class MetierEvenement {
// 	@Autowired
	private FabEvenement fabEvenement = FabEvenement.getInstance();

	private static MetierEvenement instance;

	private MetierEvenement() {
		super();
	}

	public static MetierEvenement getInstance() {
		if (instance == null) {
			instance = new MetierEvenement();
		}
		return instance;
	}

	@Transactional(readOnly = true)
	public List<Evenement> lister() {
		return fabEvenement.lister();
	}

	public void supprimer(Evenement a) {
		fabEvenement.supprimer(a);
	}

	public void supprimerTout() {
		fabEvenement.supprimerTout();
	}

	/**
	 * @param id
	 * @return
	 * @see ipint.glp.fabriques.FabEvenement#obtenir(java.lang.Object)
	 */
	public Evenement obtenir(IdEvenement id) {
		return fabEvenement.obtenir(id);
	}

	/**
	 * @param o
	 * @return
	 * @see ipint.glp.fabriques.FabEvenement#modifier(ipint.glp.donnees.Evenement)
	 */
	public Evenement modifier(Evenement o) {
		return fabEvenement.modifier(o);
	}

	@Transactional(rollbackFor = {Exception.class})
	public Evenement creer(Evenement evenement) {
//		if (utilisateur.getDroit().ordinal() < Droit.VIE_ETUDIANTE.ordinal()) {
//			throw new DroitInsuffisantException(Droit.VIE_ETUDIANTE, utilisateur.getDroit());
//		}
		return fabEvenement.creer(evenement);
	}
}
