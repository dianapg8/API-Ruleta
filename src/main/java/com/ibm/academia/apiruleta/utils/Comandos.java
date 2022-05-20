package com.ibm.academia.apiruleta.utils;

import com.ibm.academia.apiruleta.services.ApuestaDAO;
import com.ibm.academia.apiruleta.services.RuletaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Comandos implements CommandLineRunner {

    @Autowired
    private RuletaDAO ruletaDAO;

    @Autowired
    private ApuestaDAO apuestaDAO;

    @Override
    public void run(String... args) throws Exception {
    }
}