package ipint.glp.controlleurs;

import ipint.glp.controlleurs.forms.FormAnnonce;
import ipint.glp.controlleurs.forms.FormContact;
import ipint.glp.controlleurs.valideurs.ValidateurContact;
import ipint.glp.controlleurs.valideurs.ValideurAnnonce;
import ipint.glp.donnees.Annonce;
import ipint.glp.donnees.Categorie;
import ipint.glp.donnees.Champ;
import ipint.glp.donnees.TypeAnnonce;
import ipint.glp.donnees.TypeChamp;
import ipint.glp.donnees.Utilisateur;
import ipint.glp.metiers.MetierAnnonce;
import ipint.glp.metiers.MetierCategorie;
import ipint.glp.metiers.MetierUtilisateur;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.cas.authentication.CasAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControlleurAnnonce implements ServletContextAware{

	private static final Logger logger = LoggerFactory
			.getLogger(ControlleurAnnonce.class);
	private MetierAnnonce metierAnnonce = new MetierAnnonce();
	private MetierCategorie metierCategorie = new MetierCategorie();
	private MetierUtilisateur metierUtilisateur = new MetierUtilisateur();
	
	ValideurAnnonce valideurAnnonce =  new ValideurAnnonce();
	ValidateurContact validateurcontact = new ValidateurContact();
	@Autowired
	ServletContext servletcontext;
	/**
	 * Simply selects the home view to render by returning its name.
	 */

	@RequestMapping("/annonce/creer")
	public ModelAndView creerAnnonce(@RequestParam("categorieChoisie")String categorieChoisie, Model model) {
		Categorie categorie = metierCategorie.getCategorie(categorieChoisie);
		if (categorie == null) {
			return new ModelAndView("annonce/categorie/choix");
		}
		FormAnnonce formAnnonce = new FormAnnonce();
		formAnnonce.setCategorieObject(categorie);
		return new ModelAndView("annonce/creer", "annonce", formAnnonce);
	}
	
	@RequestMapping(value = "/annonce/creer", method = RequestMethod.POST)
	public String creerAnnonce(@RequestParam("categorie")String categorie, HttpServletRequest request,@RequestParam Map parameters, @ModelAttribute("annonce") FormAnnonce formAnnonce,
			BindingResult bindingResultOfAnnonce,Model model) {
		model.addAttribute("estUnSucces", true);
		valideurAnnonce.validate(formAnnonce, bindingResultOfAnnonce);
		if (bindingResultOfAnnonce.hasErrors()) {
			model.addAttribute("estUnSucces", false);
			return "annonce/creer";
		}
		
//		HashMap<String,String> lesChamps = new HashMap<String, String>(parameters);
		for(Champ ch : formAnnonce.getCategorieObject().getChamps())
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
		                
		        		String path = servletcontext.getRealPath("ressources/photos");
		        		System.out.println(path);
		        		File f = new File(path+File.separator+name);
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
		}
		
//		for(Entry<String, String> entry : lesChamps.entrySet()) {
//			System.err.println(entry.getKey());
//			System.err.println(entry.getValue());	
//		}
		
	//	util.setEmail("test@test.fr");
		Utilisateur utilisateur = metierUtilisateur.getUtilisateur((CasAuthenticationToken) request.getUserPrincipal());
		Annonce annonce = metierAnnonce.creerAnnonce(metierCategorie.getCategorie(categorie),formAnnonce.getTitre(), formAnnonce.getDescription(), utilisateur, TypeAnnonce.offre, formAnnonce.getLesChamps());
		//Annonce annonce = metierAnnonce.creerAnnonce(metierCategorie.getCategorie(categorie), (CasAuthenticationToken) request.getUserPrincipal(), TypeAnnonce.offre, formAnnonce.getLesChamps());
		//model.addAttribute("annonce", annonce);
		System.err.println(annonce.getId());
		return "redirect:/annonce/consulter?ref=" + annonce.getId();
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
				if(motCle != null && categorie == "")
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

	@RequestMapping(value = "/annonce/contacter")
	public String contactAnnonce(@RequestParam("ref")int ref, @ModelAttribute("formcontact") FormContact formcontact,
			BindingResult bindingResultOfContact,Model model)
			{
				Annonce annonce = metierAnnonce.rechercher(ref);
				formcontact.setEmeteur("latifou.sano@gmail.com");
				model.addAttribute("a", annonce);
				return "annonce/contacter";
			}
	
	@RequestMapping(value = "/annonce/contacter",method = RequestMethod.POST)
	public String contacterAnnonce(@RequestParam("ref")int ref, @ModelAttribute("formcontact") FormContact formcontact,
			BindingResult bindingResultOfContact,Model model)
			{
		validateurcontact.validate(formcontact, bindingResultOfContact);
		if (bindingResultOfContact.hasErrors()) {
			return "annonce/contacter";
		}
		
		
				Annonce annonce = metierAnnonce.rechercher(ref);
		model.addAttribute("estUnSucces", true);
		model.addAttribute("a", annonce);
				return "annonce/contacter";
			}
			

	@Override
	public void setServletContext(ServletContext sc) {
		this.servletcontext = sc;
		
	}
	
}
