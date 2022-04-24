package com.pradelles.todoliste.authentification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.pradelles.todoliste.model.Utilisateur;
import com.pradelles.todoliste.repository.UtilisateurRepository;

public class UtilisateurDetailService implements UserDetailsService{

	@Autowired
	private UtilisateurRepository uR;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Utilisateur u;
		u = uR.findByPseudo(username);
		if (u==null) {
			throw new UsernameNotFoundException("pas trouver utilisateur");
		}
		
		return new UtilisateurDetail(u);
	}

}
