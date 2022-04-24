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
	public Optional < Tache > getTacheById(int id){
		return tR.findById(id);
		
	};

	public void updateTache(Tache t) {
		tR.save(t);
	};

	public void ajouterTache(String titre, String description, Date date_derniere_modif, boolean etat, @AuthenticationPrincipal UtilisateurDetail u) {
		Tache t = new Tache();
		t.setTitre(titre);
		t.setDescription(description);
		t.setUtilisateur(u.getU());
		t.setDate_derniere_modif(new Date());
		t.setEtat(false);
		tR.save(t);
	};

	public void supprTache(int id) {
		Optional < Tache > t = tR.findById(id);
        if (t.isPresent()) {
            tR.delete(t.get());
        }
	};

	public void saveTache(Tache t) {
		tR.save(t);
	}
	

}
