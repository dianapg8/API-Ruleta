package com.ibm.academia.apiruleta.services;

import com.ibm.academia.apiruleta.enums.TipoApuesta;
import com.ibm.academia.apiruleta.model.entities.Apuesta;
import com.ibm.academia.apiruleta.model.entities.Ruleta;

public class CalculoApuesta {
    public static Apuesta calculoApuesta(Ruleta ruleta, Apuesta apuesta){
        if (apuesta.getTipoApuesta()== TipoApuesta.COLOR)
        {
            if (apuesta.getValorApuesta().equalsIgnoreCase(ruleta.getColorGanador().toString()))
            {
                apuesta.setEsGanadora(true);
                apuesta.setPremio(apuesta.getMonto() * 2);
            }
        }
        else
        {
            if (Integer.parseInt(apuesta.getValorApuesta())== ruleta.getNumeroGanador())
            {
                apuesta.setEsGanadora(true);
                apuesta.setPremio(apuesta.getMonto() * 36);
            }
        }
        return apuesta;
    }
}
