package com.pradelles.todoliste.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import com.pradelles.todoliste.authentification.UtilisateurDetail;
import com.pradelles.todoliste.model.Tache;
import com.pradelles.todoliste.model.Utilisateur;
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
	
	/**
	 * trie les taches par etat puis par date de cloture
	 * @param u
	 * @return
	 */
	public List<Tache> trieTache(Utilisateur u){
		List<Tache>lstT = tR.findAllByUtilisateur(u);
		Collections.sort(lstT,new Comparator<Tache>() {
			
			@Override
			public int compare(Tache o1, Tache o2) {
				int c = Boolean.compare(o1.getEtat(), o2.getEtat());
				if (c == 0) {
					if (o1.getDate_prevu_fin()!=null && o2.getDate_prevu_fin()!=null) {
						c = o1.getDate_prevu_fin().compareTo(o2.getDate_prevu_fin()); 
					}
					else if(o1.getDate_prevu_fin()!=null) {
						c = -1;
					}
					else if(o2.getDate_prevu_fin()!=null) {
						c = 1;
					}
				}	
				if (c==0) {
					c = o1.getTitre().compareTo(o2.getTitre());
				}
				return c;
			}
		});
		
		return lstT;
		
	}
	
	public List<Tache> filtre(List<Tache> lstT,String nom_recheche, Date date1, Date date2){
		List<Tache> lT = lstT;
		if (nom_recheche!=null) {
			lT.removeIf(t->!t.getTitre().contains(nom_recheche));	
		}
		if(date1!=null) {
			lT.removeIf(t->t.getDate_creation().before(date1));
		}
		if(date2!=null) {
			lT.removeIf(t->t.getDate_creation().after(date2));
		}
		
		
		return lT;
	}

}
