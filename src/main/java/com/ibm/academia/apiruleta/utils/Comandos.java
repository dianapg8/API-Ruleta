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

		/*Integer id =ruletaDAO.crear();
		System.out.println("id ruleta= " + id);
		Boolean estado = ruletaDAO.estado(id);
		System.out.println("estado ruleta= " + estado);
	    ruletaDAO.apostar(12, ApostarColor.ROJO,null,7000d);
		ruletaDAO.apostar(12, ApostarColor.NEGRO,null,7000d);
		ruletaDAO.asignarApuestas(id);
		ruletaDAO.girar(id);
		System.out.println("Apuestas realizadas");
		Iterable<Apuesta> apuestas= ruletaDAO.cierre(12);
		for(Apuesta apuesta:apuestas){
			System.out.println("apuesta = " + apuesta);
		}
		System.out.println("Total de ruletas");
		Iterable<Ruleta> ruletas=ruletaDAO.buscarTodos();
		for (Ruleta ruleta :ruletas ) {
			System.out.println("ruleta = " + ruleta);
		}

		Ruleta ruleta = ruletaDAO.buscarPorId(1);
		ruleta.setEstaAbierta(false);
		ruletaDAO.guardar(ruleta);
		ruletaDAO.apostar(1, null,12,300d);*/
    }
}