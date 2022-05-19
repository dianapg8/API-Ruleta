package com.ibm.academia.apiruleta.repositories;

import com.ibm.academia.apiruleta.model.entities.Apuesta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApuestaRepository  extends CrudRepository<Apuesta, Integer> {
    @Query("select a from Apuesta a where a.ruleta.id=?1")
    Iterable<Apuesta> buscarApuestasPorRuletaId(Integer idRuleta);

}