package ipint.glp.controlleurs.security;

import ipint.glp.donnees.Droit;
import ipint.glp.metiers.MetierUtilisateur;

import java.util.ArrayList;

import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AuthenticationUserDetailsServiceImpl implements AuthenticationUserDetailsService<CasAssertionAuthenticationToken> {
	private MetierUtilisateur metierUtilisateur = new MetierUtilisateur();
	
	@Override
	public UserDetails loadUserDetails(CasAssertionAuthenticationToken casAssertion) throws UsernameNotFoundException {
		ArrayList<SimpleGrantedAuthority> droits = new ArrayList<SimpleGrantedAuthority>();
		for (Droit droit: metierUtilisateur.getUtilisateur(casAssertion.getAssertion()).getDroits()) {
			droits.add(new SimpleGrantedAuthority(droit.name()));
		}
		return new User(casAssertion.getName(), "", droits);
	}

}
