package com.ibm.academia.apiruleta.services;

import com.ibm.academia.apiruleta.model.entities.Apuesta;
import com.ibm.academia.apiruleta.model.entities.Ruleta;

public interface ApuestaDAO {
    Apuesta guardar(Apuesta entidad);
    Iterable<Apuesta> buscarApuestasPorRuletaId(Integer id);
    Apuesta crearApuesta(String valorApuesta, Double monto, Ruleta ruleta);
    Iterable<Apuesta> calcularResultados(Ruleta ruleta);
}
