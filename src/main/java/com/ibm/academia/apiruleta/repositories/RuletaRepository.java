package com.ibm.academia.apiruleta.repositories;

import com.ibm.academia.apiruleta.model.entities.Ruleta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuletaRepository  extends CrudRepository<Ruleta, Integer> {
}
