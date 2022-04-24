package com.pradelles.todoliste.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pradelles.todoliste.model.Utilisateur;
import com.pradelles.todoliste.repository.UtilisateurRepository;

@RestController
public class CreationCompteController {
	
	@Autowired
	private UtilisateurRepository uR;
	
	private BCryptPasswordEncoder bCP;
	
	@GetMapping("/creationCompte")
	public ModelAndView vueCreationCompte() {
		ModelAndView modelAndView = new ModelAndView("creationCompte");
		modelAndView.getModelMap().addAttribute("utilisateur", new Utilisateur());
		return modelAndView;
	}
	
	@PostMapping("/creationCompte")
	public String baseUpDate(Utilisateur u) {
		System.out.println(u);
		bCP = new BCryptPasswordEncoder();
		u.setMot_de_passe(bCP.encode(u.getMot_de_passe()));
		u = uR.save(u);
		System.out.println(u);
		return"";
	}
}