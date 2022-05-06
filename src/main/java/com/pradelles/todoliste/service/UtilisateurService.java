package com.pradelles.todoliste.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pradelles.todoliste.model.Tache;
import com.pradelles.todoliste.model.Utilisateur;
import com.pradelles.todoliste.repository.UtilisateurRepository;

@Service
public class UtilisateurService {

	@Autowired
	private UtilisateurRepository uR;
	
	/**
	 * trouve un utilisateur avec son id
	 * @param id, id de la tache
	 * @return tache
	 */
	public Utilisateur getUtilisateurById(int id){
		return uR.findById(id);
		
	};
	/**
	 * trouve un utilisateur avec son pseudo
	 * @param pseudo
	 * @return
	 */
	public Utilisateur getUtilisateurByPseudo(String pseudo){
		return uR.findByPseudo(pseudo);
		
	};

	/**
	 * sauvegard l'utilisateur
	 * @param u
	 */
	public void saveUtilisateur(Utilisateur u) {
		uR.save(u);
	}
	
}