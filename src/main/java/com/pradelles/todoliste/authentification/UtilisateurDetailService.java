package com.pradelles.todoliste.authentification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.pradelles.todoliste.model.Utilisateur;
import com.pradelles.todoliste.repository.UtilisateurRepository;
import com.pradelles.todoliste.service.UtilisateurService;

public class UtilisateurDetailService implements UserDetailsService{

	@Autowired
	private UtilisateurService uS;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Utilisateur u;
		u = uS.getUtilisateurByPseudo(username);
		if (u==null) {
			throw new UsernameNotFoundException("pas trouver utilisateur");
		}
		
		return new UtilisateurDetail(u);
	}

}
