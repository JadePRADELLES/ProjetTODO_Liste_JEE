package com.pradelles.todoliste.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.pradelles.todoliste.authentification.UtilisateurDetail;
import com.pradelles.todoliste.model.Tache;
import com.pradelles.todoliste.service.TacheService;

@RestController
public class TacheController {
	
	@Autowired
	private TacheService tS;
	
	/**
	 * affiche
	 * @return
	 */
	@GetMapping("/ajouterTache")
	public ModelAndView vueAjouterTache() {
		ModelAndView modelAndView = new ModelAndView("ajoutertache");
		modelAndView.getModelMap().addAttribute("tache", new Tache());
		return modelAndView;
	}
	
	@PostMapping("/ajouterTache")
	public RedirectView baseUpDateTache(Tache t, @AuthenticationPrincipal UtilisateurDetail u) {
		t.setUtilisateur(u.getU());
		t.setDate_derniere_modif(new Date());
		t.setEtat(false);
		tS.saveTache(t);
		return new RedirectView("/");
	}

}
