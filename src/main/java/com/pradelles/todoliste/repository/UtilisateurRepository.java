package com.pradelles.todoliste.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pradelles.todoliste.model.Utilisateur;


public interface UtilisateurRepository extends CrudRepository<Utilisateur, Integer> {
	public Utilisateur findByPseudo (String pseudo) ;

}