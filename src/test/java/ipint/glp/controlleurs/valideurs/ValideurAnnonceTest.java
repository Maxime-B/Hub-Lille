package ipint.glp.controlleurs.valideurs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import ipint.glp.controlleurs.forms.FormAnnonce;
import ipint.glp.donnees.Categorie;
import ipint.glp.donnees.Champ;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

public class ValideurAnnonceTest {
	private static Categorie categorie;
	private static FormAnnonce formAnnonce;
	private static Errors errors;
	private static ValideurAnnonce valideurAnnonce;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		valideurAnnonce = new ValideurAnnonce();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		formAnnonce = Mockito.mock(FormAnnonce.class);
		categorie = Mockito.mock(Categorie.class);
		Mockito.when(categorie.getNom()).thenReturn("test");
		
		Mockito.when(formAnnonce.getCategorieObject()).thenReturn(categorie);
		Mockito.when(formAnnonce.getCategorie()).thenReturn("test");
		
		errors = new BeanPropertyBindingResult(formAnnonce, "FormAnnonce");
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void requisOk() {
		Champ champ = Mockito.mock(Champ.class);
		Mockito.when(champ.getLibelle()).thenReturn("requis");
		Mockito.when(champ.isObligatoire()).thenReturn(true);
		Mockito.when(champ.getLimite()).thenReturn(200);
		List<Champ> champs = new ArrayList<Champ>();
		champs.add(champ);
		Mockito.when(categorie.getChamps()).thenReturn(champs);
		
		HashMap<String, String> valeurs = new HashMap<String, String>();
		valeurs.put("requis","requis");
		Mockito.when(formAnnonce.getLesChamps()).thenReturn(valeurs);
		
		valideurAnnonce.validate(formAnnonce, errors);
		for (ObjectError error : errors.getAllErrors()) {
			System.err.println(error.getCode());
		}
		assertFalse(errors.hasErrors());
	}
	
	@Test
	public void requisKO() {
		Champ champ = Mockito.mock(Champ.class);
		Mockito.when(champ.getLibelle()).thenReturn("requis");
		Mockito.when(champ.isObligatoire()).thenReturn(true);
		Mockito.when(champ.getLimite()).thenReturn(200);
		List<Champ> champs = new ArrayList<Champ>();
		champs.add(champ);
		Mockito.when(categorie.getChamps()).thenReturn(champs);
		
		HashMap<String, String> valeurs = new HashMap<String, String>();
		valeurs.put("requis","");
		Mockito.when(formAnnonce.getLesChamps()).thenReturn(valeurs);
		
		valideurAnnonce.validate(formAnnonce, errors);
		assertTrue(errors.hasErrors());
	}
	
	@Test
	public void nonRequisOk() {
		Champ champ = Mockito.mock(Champ.class);
		Mockito.when(champ.getLibelle()).thenReturn("nonRequis");
		Mockito.when(champ.isObligatoire()).thenReturn(false);
		Mockito.when(champ.getLimite()).thenReturn(200);
		List<Champ> champs = new ArrayList<Champ>();
		champs.add(champ);
		Mockito.when(categorie.getChamps()).thenReturn(champs);
		
		HashMap<String, String> valeurs = new HashMap<String, String>();
		valeurs.put("nonRequis","");
		Mockito.when(formAnnonce.getLesChamps()).thenReturn(valeurs);

		valideurAnnonce.validate(formAnnonce, errors);
		assertFalse(errors.hasErrors());
	}
	
	@Test
	public void tailleOk() {
		Champ champ = Mockito.mock(Champ.class);
		Mockito.when(champ.getLibelle()).thenReturn("taille");
		Mockito.when(champ.isObligatoire()).thenReturn(false);
		Mockito.when(champ.getLimite()).thenReturn(6);
		List<Champ> champs = new ArrayList<Champ>();
		champs.add(champ);
		Mockito.when(categorie.getChamps()).thenReturn(champs);
		
		HashMap<String, String> valeurs = new HashMap<String, String>();
		valeurs.put("taille","taille");
		Mockito.when(formAnnonce.getLesChamps()).thenReturn(valeurs);
		
		valideurAnnonce.validate(formAnnonce, errors);
		assertFalse(errors.hasErrors());
	}
	
	@Test
	public void tailleKo() {
		Champ champ = Mockito.mock(Champ.class);
		Mockito.when(champ.getLibelle()).thenReturn("taille");
		Mockito.when(champ.isObligatoire()).thenReturn(false);
		Mockito.when(champ.getLimite()).thenReturn(6);
		List<Champ> champs = new ArrayList<Champ>();
		champs.add(champ);
		Mockito.when(categorie.getChamps()).thenReturn(champs);
		
		HashMap<String, String> valeurs = new HashMap<String, String>();
		valeurs.put("taille","tailleTropGrande");
		Mockito.when(formAnnonce.getLesChamps()).thenReturn(valeurs);
		
		valideurAnnonce.validate(formAnnonce, errors);
		assertTrue(errors.hasErrors());
	}
}
