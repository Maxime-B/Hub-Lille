package ipint.glp.metiers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import ipint.glp.donnees.Annonce;
import ipint.glp.donnees.Categorie;
import ipint.glp.donnees.Champ;
import ipint.glp.donnees.TypeAnnonce;
import ipint.glp.donnees.Utilisateur;

import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

public class MetierAnnonceTest {
	private MetierAnnonce metierAnnonce = new MetierAnnonce();
	
	@Test
	public void testGetAnnoncesPerimees() {
		List<Annonce> annonces = metierAnnonce.getAnnoncesPerimees();
		assertTrue(annonces.isEmpty());
		
		List<Champ> champs = new ArrayList<Champ>();
		Annonce annonce = metierAnnonce.creerAnnonce(new Categorie("categorie", champs) , "titre", "description", new Utilisateur("login", "email", "nom", "prenom"), TypeAnnonce.offre, new HashMap<String, String>());
		
		annonces = metierAnnonce.getAnnoncesPerimees();
		assertTrue(annonces.isEmpty());
		
		
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.add(GregorianCalendar.DAY_OF_MONTH, - 1);
		annonce.setFinpublication(new Date(gregorianCalendar.getTimeInMillis()));
		annonces = metierAnnonce.getAnnoncesPerimees();
		assertFalse(annonces.isEmpty());
	}
	
}
