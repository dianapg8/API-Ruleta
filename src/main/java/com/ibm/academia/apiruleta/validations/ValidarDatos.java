package com.ibm.academia.apiruleta.validations;

public class ValidarDatos {

    public static Boolean validarNumeroApuesta(Integer numero){
        return numero >= 0 && numero <= 36 ;

    }
    public static Boolean validarColorApuesta(String color){
        return (color.equalsIgnoreCase("rojo" ) ||
                color.equalsIgnoreCase("negro") ) ;

    }
    public static Boolean validarMontoApuesta(Double monto){
        return monto>0 && monto <=10000;

    }


}