package com.ibm.academia.apiruleta.data;

import com.ibm.academia.apiruleta.enums.Color;
import com.ibm.academia.apiruleta.enums.TipoApuesta;
import com.ibm.academia.apiruleta.model.entities.Apuesta;
import com.ibm.academia.apiruleta.model.entities.Ruleta;

import java.util.Date;

public class DummyData {
    public static Ruleta ruleta01(){
        return new Ruleta(1,false, Color.NEGRO,10,new Date(),null);
    }

    public static String ruletaIdJson01() {
        return ruleta01().getId().toString();
    }
    public static String ruletaEstadoJson01() {

        return ruleta01().getEstaAbierta().toString();
    }

    public static Ruleta ruleta02(){
        return new Ruleta(2,false,Color.NEGRO,10,new Date(),null);
    }

    public static Apuesta apuesta01(){
        Apuesta apuesta = new Apuesta(1,"negro", TipoApuesta.COLOR,8000d);
        apuesta.setRuleta(ruleta01());
        return apuesta;
    }
    public static Apuesta apuesta02(){
        return new Apuesta(2,"rojo", TipoApuesta.COLOR,7000d);
    }
    public static Apuesta apuesta03(){
        Apuesta apuesta = new Apuesta(3,"rojo", TipoApuesta.COLOR,8000d);
        apuesta.setRuleta(ruleta01());
        return apuesta;
    }
    public static Apuesta apuesta04(){
        Apuesta apuesta = new Apuesta(1,"negro", TipoApuesta.COLOR,8000d);
        apuesta.setRuleta(ruleta02());
        apuesta.setEsGanadora(true);
        apuesta.setPremio(16000d);
        return apuesta;
    }
    public static String apuesta01Json() {
        return "{" + "\"id\": " + 1 + "," +
                "\"valorApuesta\": \"NEGRO\", " +
                "\"tipoApuesta\": \"COLOR\", " +
                "\"monto\": " + 8000.0 + "," +
                "\"idRuletaApuesta\": " + 1 + "}";
    }

}
