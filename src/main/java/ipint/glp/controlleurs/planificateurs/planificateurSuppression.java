package ipint.glp.controlleurs.planificateurs;

import ipint.glp.donnees.Annonce;
import ipint.glp.donnees.EmailManager;
import ipint.glp.metiers.MetierAnnonce;

import java.util.List;

import org.apache.xalan.xsltc.compiler.sym;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
public class planificateurSuppression {
	private static final Logger logger = LoggerFactory
			.getLogger(planificateurSuppression.class);
	private MetierAnnonce metierAnnonce = new MetierAnnonce();
	
    @Scheduled(fixedRate = 3600000)
    public void SuppressionAnnoncesAutomatique() {
    	try {
	    	List<Annonce> annonces = metierAnnonce.getAnnoncesPerimees();
	    	logger.info("suppressions de " + annonces.size() +" messages ayant atteint la date de fin de publication");
    	
	    	for (Annonce annonce : annonces){
	    		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
	    		EmailManager mm = (EmailManager) context.getBean("mailMail");
	    		mm.sendMail(annonce.getUtilisateur().getEmail(), annonce.getUtilisateur().getEmail(), "suppression de votre annonce", "Votre annonce a été supprimée, car elle a atteinte sa date de fin de publication");
	    		metierAnnonce.supprimerAnnonce(annonce);
	    	}
    	} catch(Exception e) {
    		e.printStackTrace();
    		logger.error("un envoi de mail lors de de la suppression automatique a échoué");
    	}
    }
}
