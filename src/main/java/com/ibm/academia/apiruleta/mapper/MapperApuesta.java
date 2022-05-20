package com.ibm.academia.apiruleta.mapper;

import com.ibm.academia.apiruleta.model.dto.ApuestaDTO;
import com.ibm.academia.apiruleta.model.entities.Apuesta;

public class MapperApuesta {
    public static ApuestaDTO mapApuesta(Apuesta apuesta){
        ApuestaDTO apuestaDTO = new ApuestaDTO();
        apuestaDTO.setId(apuesta.getId());
        apuestaDTO.setValorApuesta(apuesta.getValorApuesta());
        apuestaDTO.setTipoApuesta(apuesta.getTipoApuesta());
        apuestaDTO.setMonto(apuesta.getMonto());
        apuestaDTO.setIdRuletaApuesta(apuesta.getRuleta().getId());

        return apuestaDTO;
    }
}
