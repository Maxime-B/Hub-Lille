package ipint.glp.controlleurs;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.Cas20ProxyTicketValidator;
import org.jasig.cas.client.validation.TicketValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControlleurCas {
	private static final Logger logger = LoggerFactory
			.getLogger(ControlleurAccueil.class);
	
	@RequestMapping(value = "/cas/cas-logout", method = RequestMethod.GET)
	public String casLogout() {
		return "/cas/cas-logout";
	}
	
	@RequestMapping(value = "/cas/casfailed", method = RequestMethod.GET)
	public String casFailed() {
		return "/cas/casfailed";
	}

	@RequestMapping(value = "/cas/test", method = RequestMethod.GET)
	public ModelAndView test(Locale locale, @RequestParam("SAMLart") String ticket, HttpServletRequest request,Model model) {
	  	AttributePrincipal principal = null;
//        String casServerUrl = "https://sso-cas.univ-lille1.fr/";
	  	String casServerUrl = "http://localhost:8080/cas/";
        Cas20ProxyTicketValidator sv = new Cas20ProxyTicketValidator(casServerUrl);
        sv.setAcceptAnyProxy(true);
        logger.debug("ok");
        logger.debug("!!!!! "+ request.getServletPath());
        logger.debug("estNull :", principal == null);
        try {
            // there is no need, that the legacy application is accessible
            // through this URL. But for validation purpose, even a non-web-app
            // needs a valid looking URL as identifier.
            String legacyServerServiceUrl = request.getServletPath();
//            Assertion a = sv.validate(ticket, "http://b12p9.fil.univ-lille1.fr:8080/hublille1/test");
            Assertion a = sv.validate(ticket, "http://localhost:8080/hublille1/test");
            principal = a.getPrincipal();
            
           return new ModelAndView("test", "principal", principal);
        } catch (TicketValidationException e) {
            e.printStackTrace(); // bad style, but only for demonstration purpose.
        }
		return new ModelAndView("test");
	}
}
