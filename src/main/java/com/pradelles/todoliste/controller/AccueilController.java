package com.pradelles.todoliste.controller;

import org.hibernate.mapping.List;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pradelles.todoliste.authentification.UtilisateurDetail;
import com.pradelles.todoliste.model.Tache;
import com.pradelles.todoliste.service.UtilisateurService;

@Controller
public class AccueilController {
	private UtilisateurService uS;
	/**
	 * affiche la page d'accueil
	 * @return le model et la vue de la page
	 */
	@GetMapping("/")
	public ModelAndView vueAccueil(@AuthenticationPrincipal UtilisateurDetail u) {
		ModelAndView modelAndView = new ModelAndView("acceuil");
		modelAndView.getModelMap().addAttribute("tache", u.getU().getTaches());
		return modelAndView;
	}
}
