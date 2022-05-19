package com.ibm.academia.apiruleta.services;

import com.ibm.academia.apiruleta.enums.Color;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CalculoGanador {
    public static Integer generarNumeroGanador(){
        Random random  = new Random();
        return random.nextInt(37);

    }
    public static Color generarColorGanador(Integer numero){
        Color color;
        List<Integer> numerosNegros = (Arrays.asList(2,4,6,8,10,11,13,15,17,20,22,24,26,28,29,31,33,35));
        if(numerosNegros.contains(numero)){
            color= Color.NEGRO;
        }
        else if(numero==0){
            color = Color.VERDE;
        }
        else {
            color= Color.ROJO;
        }
        return color;
    }
}
