package com.pradelles.todoliste.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import com.pradelles.todoliste.authentification.UtilisateurDetail;
import com.pradelles.todoliste.model.Tache;
import com.pradelles.todoliste.repository.TacheRepository;

@Service
public class TacheService {
	
	@Autowired
	private TacheRepository tR;
	
	/**
	 * trouve la tache avec son id
	 * @param id, id de la tache
	 * @return tache
	 */
	public Tache  getTacheById(int id){
		return tR.findById(id);
		
	};

	/**
	 * enregirte dans la bd une tache
	 * @param t
	 */
	public void updateTache(Tache t) {
		tR.save(t);
	};
	
	/**
	 * ajoute une tache et l'envoie à la bd
	 * @param titre
	 * @param description
	 * @param date_derniere_modif
	 * @param etat
	 * @param u
	 */

	public void ajouterTache(String titre, String description, Date date_derniere_modif, boolean etat, @AuthenticationPrincipal UtilisateurDetail u) {
		Tache t = new Tache();
		t.setTitre(titre);
		t.setDescription(description);
		t.setUtilisateur(u.getU());
		t.setDate_derniere_modif(new Date());
		t.setEtat(false);
		tR.save(t);
	};

	/**
	 * supprimme une tache avec l'id 
	 * @param id
	 */
	public void supprTache(int id) {
		Tache  t = tR.findById(id);
        if (t!=null) {
            tR.delete(t);
        }
	};

	/**
	 * sauvegarde une tache
	 * @param t
	 */
	public void saveTache(Tache t) {
		tR.save(t);
	}
	
	/**
	 * changement de l'etat de la tache dont l'id est passé en parametre
	 * @param id
	 */
	public void changementEtat(int id) {
		Tache t = tR.findById(id);
		t.setEtat(!t.getEtat());
		if (!t.getEtat()) {
			t.setDate_cloture(null);
		}
		else {
			
				t.setDate_cloture(new Date());
			}
		tR.save(t);
	}

}
