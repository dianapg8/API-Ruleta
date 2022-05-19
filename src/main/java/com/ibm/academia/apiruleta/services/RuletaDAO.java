package com.ibm.academia.apiruleta.services;

import com.ibm.academia.apiruleta.model.entities.Apuesta;
import com.ibm.academia.apiruleta.model.entities.Ruleta;

public interface RuletaDAO {
    void guardar (Ruleta ruleta);
    Ruleta buscarPorId(Integer id);
    Integer crear();
    Boolean abrir(Integer id);
    Apuesta apostar(Integer id, String valorApuesta, Double monto);
    void girar(Integer id);
    Iterable<Apuesta> cerrar(Integer id);
    Iterable<Ruleta> buscarTodos();

}
