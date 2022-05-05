package com.pradelles.todoliste.controller;

import java.util.Date;
import java.util.Optional;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pradelles.todoliste.authentification.UtilisateurDetail;
import com.pradelles.todoliste.model.Tache;
import com.pradelles.todoliste.model.Utilisateur;
import com.pradelles.todoliste.service.TacheService;
import com.pradelles.todoliste.service.UtilisateurService;

@Controller
public class AccueilController {
	
	@Autowired
	private UtilisateurService uS;
	@Autowired
	private TacheService tS;

	/**
	 * affiche la page d'accueil
	 * @return le model et la vue de la page
	 */
	@GetMapping("/")
	public ModelAndView vueAccueil(
			@AuthenticationPrincipal UtilisateurDetail u,
			@RequestParam(required = false) String nom_recherche,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date1,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date2) {
		
		System.out.println(nom_recherche+"     "+date1+"   "+date2);
		
		ModelAndView modelAndView = new ModelAndView("acceuil");

		Utilisateur oU= uS.getUtilisateurById(u.getU().getId());
			modelAndView.getModelMap().addAttribute("taches",tS.filtre(tS.trieTache(oU), nom_recherche, date1, date2)) ;
			
		
		return modelAndView;
	}
}
