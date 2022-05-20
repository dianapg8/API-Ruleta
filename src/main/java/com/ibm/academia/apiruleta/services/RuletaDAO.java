package com.ibm.academia.apiruleta.services;

import com.ibm.academia.apiruleta.model.entities.Apuesta;
import com.ibm.academia.apiruleta.model.entities.Ruleta;

import java.util.Optional;

public interface RuletaDAO {
    void guardar (Ruleta ruleta);
    Ruleta buscarPorId(Integer id);
    Integer crear();
    Boolean abrir(Integer id);
    Apuesta apostar(Apuesta apuesta);
    void girar(Integer id);
    Iterable<Apuesta> cerrar(Integer id);
    Iterable<Ruleta> buscarTodos();

}
