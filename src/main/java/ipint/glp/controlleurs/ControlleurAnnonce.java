package ipint.glp.controlleurs;

import ipint.glp.controlleurs.forms.FormAnnonce;
import ipint.glp.controlleurs.valideurs.ValideurAnnonce;
import ipint.glp.donnees.Annonce;
import ipint.glp.donnees.Categorie;
import ipint.glp.donnees.TypeAnnonce;
import ipint.glp.metiers.MetierAnnonce;
import ipint.glp.metiers.MetierCategorie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.cas.authentication.CasAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControlleurAnnonce {

	private static final Logger logger = LoggerFactory
			.getLogger(ControlleurAnnonce.class);
	private MetierAnnonce metierAnnonce = new MetierAnnonce();
	private MetierCategorie metierCategorie = new MetierCategorie();
	
	/**
	 * Register a validator that will be lookup when a parameter is binded to a
	 * handler argument (with @ModelAttribute() for example).
	 * 
	 * @param binder
	 */
	@InitBinder("Annone")
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(new ValideurAnnonce());
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */

	@RequestMapping("/annonce/creer")
	public ModelAndView creerAnnonce(@RequestParam("categorieChoisie")String categorieChoisie, Model model) {
		System.err.println(categorieChoisie);
		Categorie categorie = metierCategorie.getCategorie(categorieChoisie);
		if (categorie == null) {
			return new ModelAndView("annonce/categorie/choix");
		}
		System.err.println(categorie);
		FormAnnonce formAnnonce = new FormAnnonce();
		formAnnonce.setCategorieObject(categorie);
		return new ModelAndView("annonce/creer", "annonce", formAnnonce);
	}
	
//	@RequestMapping(value = "/annonce/creer", method = RequestMethod.POST)
//	public String creerAnnonce(@RequestParam("categorie")String categorie,@RequestParam("titre")String titre,@RequestParam("description")String description,HttpServletRequest request,@RequestParam Map parameters, @Valid @ModelAttribute("annonce") FormAnnonce formAnnonce,
//			BindingResult bindingResultOfAnnonce,Model model) {
//		model.addAttribute("estUnSucces", true);
//	
//		if (bindingResultOfAnnonce.hasErrors()) {
//			model.addAttribute("estUnSucces", false);
//			return "annonce/creer";
//		}
//		
//		HashMap<String,String> lesChamps = new HashMap<String, String>(parameters);
//	/*	for(Champ ch : formAnnonce.getCategorieObject().getChamps())
//		{
//			if(ch.getTypeChamp() == TypeChamp.IMAGE)
//			{
//				String name="latifu.jpg";
//				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//				MultipartFile image = multipartRequest.getFile(ch.getLibelle());
//				if (!image.isEmpty()) {
//		            try {
//		                byte[] bytes = image.getBytes();
//		                //System.out.println(getServlet().getServletConfig().getServletContext().getRealPath("/"));
//		                File f2 = new File("");
//		        		
//		        		File f = new File(name);
//		                BufferedOutputStream stream =
//		                        new BufferedOutputStream(new FileOutputStream(f));
//		                stream.write(bytes);
//		                stream.close();
//		                System.err.println( "You successfully uploaded " + name + "!");
//		            } catch (Exception e) {
//		            	System.err.println("You failed to upload " + name + " => " + e.getMessage());
//		            }
//		        } else {
//		        	System.err.println( "You failed to upload " + name + " because the file was empty.");
//		        }
//			}
//		}*/
//		
//		for(Entry<String, String> entry : lesChamps.entrySet()) {
//			System.err.println(entry.getKey());
//			System.err.println(entry.getValue());	
//		}
//		
//		System.err.println("reussi");
//		Utilisateur util = new Utilisateur();
//	//	util.setEmail("test@test.fr");
//		formAnnonce.getLesChamps().put("titre", titre);
//		formAnnonce.getLesChamps().put("description", description);
//		Annonce annonce = metierAnnonce.creerAnnonce(metierCategorie.getCategorie(categorie),titre, description, (CasAuthenticationToken) request.getUserPrincipal(), TypeAnnonce.offre, formAnnonce.getLesChamps());
//		//Annonce annonce = metierAnnonce.creerAnnonce(metierCategorie.getCategorie(categorie),titre, description, util, TypeAnnonce.offre, formAnnonce.getLesChamps());
//		//model.addAttribute("annonce", annonce);
//		return "redirect:/annonce";
//	}
	@RequestMapping(value = "/annonce/creer", method = RequestMethod.POST)
	public String creerAnnonce(@RequestParam("categorie")String categorie,@RequestParam("titre")String titre,@RequestParam("description")String description,HttpServletRequest request,@RequestParam Map parameters, @Valid @ModelAttribute("annonce") FormAnnonce formAnnonce,
			BindingResult bindingResultOfAnnonce,Model model) {
		model.addAttribute("estUnSucces", true);
		if (bindingResultOfAnnonce.hasErrors()) {
			model.addAttribute("estUnSucces", false);
			return "annonce/creer";
		}
		
		HashMap<String,String> lesChamps = new HashMap<String, String>(parameters);
	/*	for(Champ ch : formAnnonce.getCategorieObject().getChamps())
		{
			if(ch.getTypeChamp() == TypeChamp.IMAGE)
			{
				String name="latifu.jpg";
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				MultipartFile image = multipartRequest.getFile(ch.getLibelle());
				if (!image.isEmpty()) {
		            try {
		                byte[] bytes = image.getBytes();
		                //System.out.println(getServlet().getServletConfig().getServletContext().getRealPath("/"));
		                File f2 = new File("");
		        		
		        		File f = new File(name);
		                BufferedOutputStream stream =
		                        new BufferedOutputStream(new FileOutputStream(f));
		                stream.write(bytes);
		                stream.close();
		                System.err.println( "You successfully uploaded " + name + "!");
		            } catch (Exception e) {
		            	System.err.println("You failed to upload " + name + " => " + e.getMessage());
		            }
		        } else {
		        	System.err.println( "You failed to upload " + name + " because the file was empty.");
		        }
			}
		}*/
		
		for(Entry<String, String> entry : lesChamps.entrySet()) {
			System.err.println(entry.getKey());
			System.err.println(entry.getValue());	
		}
		
		System.err.println("reussi");
		
	//	util.setEmail("test@test.fr");
		formAnnonce.getLesChamps().put("titre", titre);
		formAnnonce.getLesChamps().put("description", description);
		
		Annonce annonce = metierAnnonce.creerAnnonce(metierCategorie.getCategorie(categorie),titre, description, (CasAuthenticationToken) request.getUserPrincipal(), TypeAnnonce.offre, formAnnonce.getLesChamps());
		//Annonce annonce = metierAnnonce.creerAnnonce(metierCategorie.getCategorie(categorie), (CasAuthenticationToken) request.getUserPrincipal(), TypeAnnonce.offre, formAnnonce.getLesChamps());
		//model.addAttribute("annonce", annonce);
		return "redirect:/annonce";
	}


	
	
/*@RequestMapping(value = "/annonce", method = RequestMethod.GET)
	public String listerAnnonces(Locale locale,Model model,String categorie, String motCle ){
		
		List<Annonce>lesAnnonces = new ArrayList<Annonce>();
		if (categorie== null)
			lesAnnonces= metierAnnonce.listerAnnonces();
		else
			lesAnnonces =metierAnnonce.listerAnnoncesParCategorie(categorie);
		
		model.addAttribute("categories", metierCategorie.listerCategories());	
		model.addAttribute("annonces",lesAnnonces );
		model.addAttribute("categorie",categorie);
		model.addAttribute("motCle",motCle);
		//model.addAttribute("annonces", metierAnnonce.listerAnnonces());
		return "annonce/lister";
	}*/
	@RequestMapping(value = "/annonce", method = RequestMethod.GET)
	public String listerAnnonces(Locale locale,Model model,String categorie, String motCle ){
		
		List<Annonce>lesAnnonces = new ArrayList<Annonce>();
		if ( motCle == null && categorie == null)
			lesAnnonces= metierAnnonce.listerAnnonces();
		
		else 
			if( motCle == null && categorie !=null)
				lesAnnonces=metierAnnonce.listerAnnoncesParCategorie(categorie);
			else 
				if(motCle != null && categorie ==null)
					lesAnnonces=metierAnnonce.chercherAnnonceParMotCle(motCle);
			//lesAnnonces= metierAnnonce.chercherAnnonceParMotCle(motCle);
				else
					lesAnnonces= metierAnnonce.chercherAnnonceParMotCleCate(motCle,categorie);
		
		model.addAttribute("categories", metierCategorie.listerCategories());	
		model.addAttribute("annonces",lesAnnonces );
		model.addAttribute("categorie",categorie);
		model.addAttribute("motCle",motCle);
		//model.addAttribute("annonces", metierAnnonce.listerAnnonces());
		return "annonce/lister";
	}
	@RequestMapping(value = "/annonce/categorie/choisir", method = RequestMethod.GET)
	public String choixCategorie(Model model) {
		model.addAttribute("categories", metierCategorie.listerCategories());
		return "annonce/categorie/choisir";
	}
	
	@RequestMapping(value = "/annonce/consulter", method = RequestMethod.GET)
	public String consulterAnnonce(Model model, @RequestParam("ref") int ref) {
		Annonce annonce = metierAnnonce.rechercher(ref);
		model.addAttribute("annonce", annonce);
		model.addAttribute("ref",ref);
		return "annonce/consulter";
	}
	
}
