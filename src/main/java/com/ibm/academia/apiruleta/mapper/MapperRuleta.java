package com.ibm.academia.apiruleta.mapper;

import com.ibm.academia.apiruleta.model.dto.RuletaDTO;
import com.ibm.academia.apiruleta.model.entities.Ruleta;

public class MapperRuleta {
    public static RuletaDTO mapRuleta(Ruleta ruleta){
        RuletaDTO ruletaDTO = new RuletaDTO();
        ruletaDTO.setId(ruleta.getId());
        ruletaDTO.setEstado(ruleta.getEstaAbierta());
        return ruletaDTO;
    }
}
