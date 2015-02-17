/**
 * 
 */
package ipint.glp.metiers;

import ipint.glp.donnees.Evenement;
import ipint.glp.fabriques.FabEvenement;

import java.util.Date;
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

	/**
	 * @param id
	 * @return
	 * @see ipint.glp.fabriques.FabEvenement#obtenir(java.lang.Object)
	 */
	public Evenement obtenir(Integer id) {
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
		return fabEvenement.creer(evenement);
	}

	public Evenement obtenir(String titre, Date dateDebut) {
		return fabEvenement.obtenir(titre, dateDebut);
	}
}
