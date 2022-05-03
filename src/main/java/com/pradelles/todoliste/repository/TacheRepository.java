package com.pradelles.todoliste.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pradelles.todoliste.model.Tache;

@Repository
public interface TacheRepository extends CrudRepository<Tache, Integer> {

	public Tache findById (int id);


}
