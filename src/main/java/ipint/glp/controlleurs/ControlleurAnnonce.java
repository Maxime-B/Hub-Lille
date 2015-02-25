package ipint.glp.controlleurs;

import ipint.glp.controlleurs.forms.FormAnnonce;
import ipint.glp.controlleurs.forms.FormContact;
import ipint.glp.controlleurs.valideurs.ValidateurContact;
import ipint.glp.controlleurs.valideurs.ValideurAnnonce;
import ipint.glp.donnees.Annonce;
import ipint.glp.donnees.Categorie;
import ipint.glp.donnees.Champ;
import ipint.glp.donnees.EmailManager;
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
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
	public ModelAndView creerAnnonce(@RequestParam("categorieChoisie")String categorieChoisie, @RequestParam("typeChoisit")String typeChoisit, Model model) {
		Categorie categorie = metierCategorie.getCategorie(categorieChoisie);
		if (categorie == null) {
			return new ModelAndView("annonce/categorie/choix");
		}
		FormAnnonce formAnnonce = new FormAnnonce();
		formAnnonce.setCategorieObject(categorie);
		formAnnonce.setType(TypeAnnonce.valueOf(typeChoisit));
		System.out.println(formAnnonce.getType());
		return new ModelAndView("annonce/creer", "annonce", formAnnonce);
	}

	@RequestMapping(value = "/annonce/creer", method = RequestMethod.POST)
	public String creerAnnonce(@RequestParam("categorie")String categorie,@RequestParam("type")String type, HttpServletRequest request,@RequestParam Map parameters, @ModelAttribute("annonce") FormAnnonce formAnnonce,
			BindingResult bindingResultOfAnnonce,@RequestParam("photos")MultipartFile[] lesphotos,Model model) {
		model.addAttribute("estUnSucces", true);
		valideurAnnonce.validate(formAnnonce, bindingResultOfAnnonce);
		if (bindingResultOfAnnonce.hasErrors()) {
			model.addAttribute("estUnSucces", false);
			return "annonce/creer";
		}
		System.out.println("------------------------------------------------");
		Utilisateur utilisateur = metierUtilisateur.getUtilisateur((CasAuthenticationToken) request.getUserPrincipal());
		System.out.println("------------------------------------------------");
		Annonce annonce = metierAnnonce.creerAnnonce(metierCategorie.getCategorie(categorie),formAnnonce.getTitre(), formAnnonce.getDescription(), utilisateur, TypeAnnonce.valueOf(type), formAnnonce.getLesChamps());
		int nb = 1;
		ArrayList<String> liens = new ArrayList<String>();
		for(MultipartFile image : lesphotos)
		{

			String name=annonce.getId()+"_"+nb+".jpg";

			if (!image.isEmpty()) {
				try {
					byte[] bytes = image.getBytes();
					String path = servletcontext.getRealPath("/ressources/photos");
					System.out.println(path);
					File f = new File(path+File.separator+name);
					BufferedOutputStream stream =
							new BufferedOutputStream(new FileOutputStream(f));
					stream.write(bytes);
					stream.close();
					System.err.println( "You successfully uploaded " + name + "!");
					System.err.println( path+File.separator+name);
					liens.add(name);

				} catch (Exception e) {
					System.err.println("You failed to upload " + name + " => " + e.getMessage());
				}
				nb++;
			} else {
				System.err.println( "You failed to upload " + name + " because the file was empty.");
			}

		}
		annonce.setImages(liens);
		metierAnnonce.modifier(annonce);
		System.err.println(annonce.getId());
		System.err.println(annonce.getId());
		return "redirect:/annonce/consulter?ref=" + annonce.getId();
	}


/*	@RequestMapping(value = "/annonce", method = RequestMethod.GET)
	public String listerAnnonces(Locale locale,Model model,@RequestParam(defaultValue="") String categorie, @RequestParam(defaultValue="") String motCle, @RequestParam(defaultValue="") TypeAnnonce type){

		List<Annonce>lesAnnonces = new ArrayList<Annonce>();
		System.out.println(categorie);
		System.out.println(motCle);
		System.out.println(type);
		if ( ((motCle == null || (motCle.equals("")) && (categorie == null || categorie.equals("")) && type == null))) {
			lesAnnonces= metierAnnonce.listerAnnonces();
		} else if ( ((motCle == null || (motCle.equals("")) && (categorie == null || categorie.equals("")) && type != null))) {
			lesAnnonces= metierAnnonce.listerAnnoncesParType(type);
		} else if ( ((motCle == null || (motCle.equals("")) && (categorie != null && (!categorie.equals(""))) && type == null))) {
			lesAnnonces=metierAnnonce.listerAnnoncesParCategorie(categorie);
		} else if ( ((motCle == null || (motCle.equals("")) && (categorie != null && (!categorie.equals(""))) && type != null))) {
			lesAnnonces= metierAnnonce.chercherAnnonceParTypeCate(categorie, type);
		} else if ( ((motCle != null && (!(motCle.equals(""))) && (categorie == null || categorie.equals("")) && type == null))) {
			lesAnnonces= metierAnnonce.chercherAnnonceParMotCle(motCle);
		} else if ( ((motCle != null && (!(motCle.equals(""))) && (categorie == null || categorie.equals("")) && type != null))) {
			lesAnnonces= metierAnnonce.chercherAnnonceParTypeMotCle(type, motCle);
		} else if ( ((motCle != null && (!(motCle.equals(""))) && (categorie != null && (!categorie.equals(""))) && type == null))) {
			lesAnnonces=metierAnnonce.chercherAnnonceParMotCleCate(motCle, categorie);
		} else if ( ((motCle != null && (!(motCle.equals(""))) && (categorie != null && (!categorie.equals(""))) && type != null))) {
			lesAnnonces= metierAnnonce.chercherAnnoncesParTypeMotCleCate(type, categorie, motCle);
		}

		model.addAttribute("categories", metierCategorie.listerCategories());	
		model.addAttribute("annonces",lesAnnonces);
		model.addAttribute("types", TypeAnnonce.values()); 
		model.addAttribute("categorie",categorie);
		model.addAttribute("motCle",motCle);
		return "annonce/lister";
	}*/
	@RequestMapping(value = "/annonce/categorie/choisir", method = RequestMethod.GET)
	public String choixCategorie(Model model) {
		model.addAttribute("categories", metierCategorie.listerCategories());
		model.addAttribute("types", TypeAnnonce.values());
		return "annonce/categorie/choisir";
	}

	@RequestMapping(value = "/annonce/listerOffre", method = RequestMethod.GET)
	public String listerOffres(Locale locale,Model model,@RequestParam(defaultValue="") String categorie, @RequestParam(defaultValue="") String motCle){

		List<Annonce>lesAnnonces = new ArrayList<Annonce>();
		System.out.println(categorie);
		System.out.println(motCle);
		if ( ((motCle == null || (motCle.equals("")) && (categorie == null || categorie.equals(""))))) {
			lesAnnonces= metierAnnonce.listerAnnoncesParType(TypeAnnonce.offre);
		} 
		else if ( ((motCle == null || (motCle.equals("")) && (categorie != null && (!categorie.equals("")))))) {
			lesAnnonces=metierAnnonce.chercherAnnonceParTypeCate(categorie, TypeAnnonce.offre);
		}
		else if ( ((motCle != null && (!(motCle.equals(""))) && (categorie == null || categorie.equals(""))))) {
			lesAnnonces= metierAnnonce.chercherAnnonceParTypeMotCle(TypeAnnonce.offre, motCle);
		}
		else if ( ((motCle != null && (!(motCle.equals(""))) && (categorie != null && (!categorie.equals("")))))) {
			lesAnnonces=metierAnnonce.chercherAnnoncesParTypeMotCleCate(TypeAnnonce.offre, categorie, motCle);
		}

		model.addAttribute("categories", metierCategorie.listerCategories());	
		model.addAttribute("annonces",lesAnnonces);
		model.addAttribute("types", TypeAnnonce.offre); 
		model.addAttribute("categorie",categorie);
		model.addAttribute("motCle",motCle);
		return "annonce/listerOffre";
	}

	@RequestMapping(value = "/annonce/listerDemande", method = RequestMethod.GET)
	public String listerDemandes(Locale locale,Model model,@RequestParam(defaultValue="") String categorie, @RequestParam(defaultValue="") String motCle){

		List<Annonce>lesAnnonces = new ArrayList<Annonce>();
		System.out.println(categorie);
		System.out.println(motCle);
		if ( ((motCle == null || (motCle.equals("")) && (categorie == null || categorie.equals(""))))) {
			lesAnnonces= metierAnnonce.listerAnnoncesParType(TypeAnnonce.demande);
		} else if ( ((motCle == null || (motCle.equals("")) && (categorie != null && (!categorie.equals("")))))) {
			lesAnnonces=metierAnnonce.chercherAnnonceParTypeCate(categorie, TypeAnnonce.demande);
		} else if ( ((motCle != null && (!(motCle.equals(""))) && (categorie == null || categorie.equals(""))))) {
			lesAnnonces= metierAnnonce.chercherAnnonceParTypeMotCle(TypeAnnonce.demande, motCle);
		} else if ( ((motCle != null && (!(motCle.equals(""))) && (categorie != null && (!categorie.equals("")))))) {
			lesAnnonces=metierAnnonce.chercherAnnoncesParTypeMotCleCate(TypeAnnonce.demande, categorie, motCle);
		}

		model.addAttribute("categories", metierCategorie.listerCategories());	
		model.addAttribute("annonces",lesAnnonces);
		model.addAttribute("types", TypeAnnonce.demande); 
		model.addAttribute("categorie",categorie);
		model.addAttribute("motCle",motCle);
		return "annonce/listerDemande";
	}
	
	@RequestMapping(value = "/job/lister", method = RequestMethod.GET)
	public String listerJob(Model model, @RequestParam("ref") int ref) {
		return "redirect:/job/lister";
	}



	@RequestMapping(value = "/annonce/consulter", method = RequestMethod.GET)
	public String consulterAnnonce(Model model, @RequestParam("ref") int ref) {
		Annonce annonce = metierAnnonce.rechercher(ref);
		model.addAttribute("annonce", annonce);
		model.addAttribute("types", TypeAnnonce.values()); 
		model.addAttribute("ref",ref);
		return "annonce/consulter";
	}

	@RequestMapping(value = "/annonce/contacter")
	public String contactAnnonce(@RequestParam("ref")int ref, @ModelAttribute("formcontact") FormContact formcontact,HttpServletRequest request,
			BindingResult bindingResultOfContact,Model model)
	{
		Annonce annonce = metierAnnonce.rechercher(ref);
		Utilisateur utilisateur = metierUtilisateur.getUtilisateur((CasAuthenticationToken) request.getUserPrincipal());
		formcontact.setEmeteur(utilisateur.getEmail());


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

		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
		EmailManager mm = (EmailManager) context.getBean("mailMail");
		Annonce annonce = metierAnnonce.rechercher(ref);
		formcontact.setDestinataire(annonce.getUtilisateur().getEmail());


		mm.sendMail(formcontact.getEmeteur(), formcontact.getDestinataire(), formcontact.getObjet(), formcontact.getMessage());
		model.addAttribute("estUnSucces", true);
		model.addAttribute("a", annonce);
		return "annonce/contacter";
	}

	@RequestMapping(value = "/annonce/signaler", method = RequestMethod.GET)
	public String signalerAnnonce(Model model, @RequestParam("ref") int ref,HttpServletRequest request) {
		Utilisateur u = metierUtilisateur.getUtilisateur((CasAuthenticationToken) request.getUserPrincipal());
		Annonce annonce = metierAnnonce.rechercher(ref);
		boolean estSignale=false;
		model.addAttribute("annonce", annonce);
		model.addAttribute("ref",ref);
		if(metierAnnonce.signalerAnnonce(u,annonce)){
			System.out.println(annonce.getSignal());

			estSignale=true;
		}

		else {
			estSignale=false;
		}
		model.addAttribute("estSignale",estSignale);
		return "annonce/signaler";
	}
	@RequestMapping(value = "/annonce/modifier", method = RequestMethod.GET)
	public String modifierAnnonce(Model model, @RequestParam("ref") int ref) {
		Annonce annonce = metierAnnonce.rechercher(ref);
		
		model.addAttribute("types", TypeAnnonce.values()); 
		model.addAttribute("ref",ref);
		FormAnnonce formannonce  = new FormAnnonce();
		formannonce.setId(annonce.getId());
		formannonce.setCategorie(annonce.getCategorie().getNom());
		formannonce.setCategorieObject(annonce.getCategorie());
		formannonce.setTitre(annonce.getTitre());
		formannonce.setDescription(annonce.getDescription());
		
		for(Champ c : annonce.getCategorie().getChamps())
		{
			String nom = c.getLibelle();
			TypeChamp type = c.getTypeChamp();
			
			if(type == TypeChamp.NUMERIQUE)
				formannonce.getNumerique().put(nom, Double.parseDouble(annonce.getLesChamps().get(nom)));
			
			if(type == TypeChamp.TEXTE)
				formannonce.getTexte().put(nom,annonce.getLesChamps().get(nom));
			
			if(type == TypeChamp.TEXTEAREA)
				formannonce.getTextarea().put(nom,annonce.getLesChamps().get(nom));
			
			
				
		}
		model.addAttribute("images", annonce.getImages());
		model.addAttribute("annonce", formannonce);
		return "annonce/modifier";
	}
	
	@RequestMapping(value = "/annonce/modifier", method = RequestMethod.POST)
	public String modifAnnonce(Model model, @RequestParam("ref") int ref, HttpServletRequest request,@RequestParam Map parameters, @ModelAttribute("annonce") FormAnnonce formAnnonce,
			BindingResult bindingResultOfAnnonce,@RequestParam("photos")MultipartFile[] lesphotos) {
		model.addAttribute("estUnSucces", true);
		valideurAnnonce.validate(formAnnonce, bindingResultOfAnnonce);
		if (bindingResultOfAnnonce.hasErrors()) {
			model.addAttribute("estModifie", false);
			return "annonce/creer";
		}
		System.out.println("------------------------------------------------");
		Utilisateur utilisateur = metierUtilisateur.getUtilisateur((CasAuthenticationToken) request.getUserPrincipal());
		System.out.println("------------------------------------------------");
		Annonce annonce = metierAnnonce.rechercher(ref);
		annonce.setTitre(formAnnonce.getTitre());
		annonce.setDescription(formAnnonce.getDescription());
		for(Champ c : annonce.getCategorie().getChamps())
		{
			String nom = c.getLibelle();
			TypeChamp type = c.getTypeChamp();
			
			if(type == TypeChamp.NUMERIQUE)
				annonce.getLesChamps().put(c.getLibelle(), formAnnonce.getNumerique().get(c.getLibelle()).toString());
			
			if(type == TypeChamp.TEXTE)
				annonce.getLesChamps().put(c.getLibelle(), formAnnonce.getTexte().get(c.getLibelle()));
				formAnnonce.getTexte().put(nom,annonce.getLesChamps().get(nom));
			
			if(type == TypeChamp.TEXTEAREA)
				annonce.getLesChamps().put(c.getLibelle(), formAnnonce.getTextarea().get(c.getLibelle()));
				formAnnonce.getTextarea().put(nom,annonce.getLesChamps().get(nom));
			
			
				
		}
		
		
		System.out.println(annonce.getImages().size());

		int nb =annonce.getImages().size() +1;
		ArrayList<String> liens = new ArrayList<String>();
		for(MultipartFile image : lesphotos)
		{

			String name=annonce.getId()+"_"+nb+".jpg";

			if (!image.isEmpty()) {
				try {
					byte[] bytes = image.getBytes();
					String path = servletcontext.getRealPath("/ressources/photos");
					System.out.println(path);
					File f = new File(path+File.separator+name);
					BufferedOutputStream stream =
							new BufferedOutputStream(new FileOutputStream(f));
					stream.write(bytes);
					stream.close();
					System.err.println( "You successfully uploaded " + name + "!");
					System.err.println( path+File.separator+name);
					liens.add(name);

				} catch (Exception e) {
					System.err.println("You failed to upload " + name + " => " + e.getMessage());
				}
				nb++;
			} else {
				System.err.println( "You failed to upload " + name + " because the file was empty.");
			}

		}
		annonce.getImages().addAll(liens);
		metierAnnonce.modifier(annonce);
		System.err.println(annonce.getId());
		System.err.println(annonce.getId());
		return "redirect:/annonce/consulter?ref=" + annonce.getId();
	}
	
	@Override
	public void setServletContext(ServletContext sc) {
		this.servletcontext = sc;

	}

}