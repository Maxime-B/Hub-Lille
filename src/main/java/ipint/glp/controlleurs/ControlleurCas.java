package ipint.glp.controlleurs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ControlleurCas {
	private static final Logger logger = LoggerFactory
			.getLogger(ControlleurCas.class);
	
	@RequestMapping(value = "/cas/cas-logout", method = RequestMethod.GET)
	public String casLogout() {
		return "/cas/cas-logout";
	}
	
	@RequestMapping(value = "/cas/casfailed", method = RequestMethod.GET)
	public String casFailed() {
		return "/cas/casfailed";
	}
}
