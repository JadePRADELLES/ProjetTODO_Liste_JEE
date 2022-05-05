package com.pradelles.todoliste.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pradelles.todoliste.model.Tache;
import com.pradelles.todoliste.model.Utilisateur;

@Repository
public interface TacheRepository extends CrudRepository<Tache, Integer> {

	public Tache findById (int id);
	public List<Tache> findAllByUtilisateur (Utilisateur u);


}
