package com.pradelles.todoliste.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.pradelles.todoliste.model.Utilisateur;
import com.pradelles.todoliste.repository.UtilisateurRepository;

@RestController
public class UtilisateurController {
	
	@Autowired
	private UtilisateurRepository uR;
	
	private BCryptPasswordEncoder bCP;
	
	/**
	 * affiche la page de création de compte
	 * @return
	 */
	@GetMapping("/creationCompte")
	public ModelAndView vueCreationCompte() {
		ModelAndView modelAndView = new ModelAndView("creationCompte");
		modelAndView.getModelMap().addAttribute("utilisateur", new Utilisateur());
		return modelAndView;
	}
	
	/**
	 * enregistre dans la base de donnée le nouvel utilisateur
	 * @param u
	 * @return
	 */
	@PostMapping("/creationCompte")
	public RedirectView baseUpDate(Utilisateur u) {
		System.out.println(u);
		bCP = new BCryptPasswordEncoder();
		u.setMot_de_passe(bCP.encode(u.getMot_de_passe()));
		u = uR.save(u);
		return new RedirectView("/",true);
	}
}
