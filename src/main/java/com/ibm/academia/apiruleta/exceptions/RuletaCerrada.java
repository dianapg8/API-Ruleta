package com.ibm.academia.apiruleta.exceptions;

public class RuletaCerrada extends RuntimeException {
    public RuletaCerrada(String message) {
        super(message);
    }
}