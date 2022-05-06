package com.pradelles.todoliste.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	 * affiche la page ajouter tache
	 * @return
	 */
	@GetMapping("/ajouterTache")
	public ModelAndView vueAjouterTache() {
		ModelAndView modelAndView = new ModelAndView("ajoutertache");
		modelAndView.getModelMap().addAttribute("tache", new Tache());
		return modelAndView;
	}
	
	/**
	 * enregistre la nouvelle tache dans la base de donné
	 * @param t
	 * @param u
	 * @return
	 */
	@PostMapping("/ajouterTache")
	public RedirectView baseUpDateTache(Tache t, @AuthenticationPrincipal UtilisateurDetail u) {
		t.setUtilisateur(u.getU());
		t.setDate_derniere_modif(new Date());
		t.setEtat(false);
		t.setDate_creation(new Date());
		tS.saveTache(t);
		return new RedirectView("");
	}


	/**
	 * supprime la tache dans la base de donnée
	 * @param id
	 * @return
	 */
    @PostMapping("/suppTache")
    public RedirectView suppTache(@RequestParam int id) {
        tS.supprTache(id);
        // service.deleteTodo(id);
        return new RedirectView("");
    }
    
    /**
     * affiche la page de modification de la tache
     * @param id
     * @return
     */
    @GetMapping("/modifTache")
    public ModelAndView modifierTache(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView("modifierTache");
		modelAndView.getModelMap().addAttribute("tache",tS.getTacheById(id));
		return modelAndView;
	}
 
    /**
     * modifie la tache dans la base de donnée
     * @param t
     * @param u
     * @return
     */
	@PostMapping("/modifTache")
	public RedirectView baseUpDateModifTache(@ModelAttribute Tache t, @AuthenticationPrincipal UtilisateurDetail u) {
		t.setUtilisateur(u.getU());
		t.setDate_derniere_modif(new Date());
		tS.updateTache(t);
		return new RedirectView("");
	}

	/**
	 * enregistre dans la base de donnée le changement de l'etat de la tache
	 * @param id
	 * @return
	 */
	@PostMapping("/etatTache")
	public RedirectView etatTache (@RequestParam int id) {
        tS.changementEtat(id);
        return new RedirectView("");
	}


}
