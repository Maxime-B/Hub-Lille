/**
 * 
 */
package ipint.glp.metiers;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import ipint.glp.donnees.Evenement;

import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author clement
 *
 */
public class MetierEvenementTest {
	private MetierEvenement metierEvenement = MetierEvenement.getInstance();
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link ipint.glp.metiers.MetierEvenement#lister()}.
	 * @throws ParseException 
	 */
	@Test
	public void testLister() throws ParseException {
		List<Evenement> evenements = metierEvenement.lister();
		assertTrue(evenements.isEmpty());
		Evenement evenement = new Evenement();
		evenement.setTitre("titre");
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.add(Calendar.DAY_OF_YEAR, 1);
		evenement.setDateDebut(gregorianCalendar.getTime());
		metierEvenement.creer(evenement);
		evenements = metierEvenement.lister();
		assertTrue(!evenements.isEmpty());
	}

//	/**
//	 * Test method for {@link ipint.glp.metiers.MetierEvenement#supprimer(ipint.glp.donnees.Evenement)}.
//	 */
//	@Test
//	public void testSupprimer() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link ipint.glp.metiers.MetierEvenement#supprimerTout()}.
//	 */
//	@Test
//	public void testSupprimerTout() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link ipint.glp.metiers.MetierEvenement#creer(java.lang.String, java.util.Date, java.lang.String, java.lang.String, java.lang.String, ipint.glp.donnees.Utilisateur)}.
//	 */
//	@Test
//	public void testCreer() {
//		
//	}

}
