package com.ibm.academia.apiruleta.services;

import com.ibm.academia.apiruleta.enums.TipoApuesta;
import com.ibm.academia.apiruleta.exceptions.ApuestaCancelada;
import com.ibm.academia.apiruleta.exceptions.DatosInvalidos;
import com.ibm.academia.apiruleta.model.entities.Apuesta;
import com.ibm.academia.apiruleta.model.entities.Ruleta;
import com.ibm.academia.apiruleta.repositories.ApuestaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.ibm.academia.apiruleta.services.CalculoApuesta.calculoApuesta;
import static com.ibm.academia.apiruleta.validations.ValidarDatos.*;

@Service
public class ApuestaDAOImpl implements ApuestaDAO
{
    private final ApuestaRepository repository;

    @Autowired
    public ApuestaDAOImpl(ApuestaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Apuesta guardar(Apuesta entidad) {
        return repository.save(entidad);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Apuesta> buscarApuestasPorRuletaId(Integer id) {
        return repository.buscarApuestasPorRuletaId(id);
    }

    @Override
    @Transactional
    public Apuesta crearApuesta(String valorApuesta, Double monto, Ruleta ruleta) {
        Apuesta apuestaGuardada = null;
        if(validarMontoApuesta(monto)) {
            if (validarColorApuesta(valorApuesta)) {
                Apuesta nuevaApuesta = new Apuesta(valorApuesta.toUpperCase(), TipoApuesta.COLOR, monto, ruleta);
                apuestaGuardada = guardar(nuevaApuesta);
            } else {
                Integer numeroApuesta = Integer.parseInt(valorApuesta);
                if (validarNumeroApuesta(numeroApuesta) && validarMontoApuesta(monto)) {
                    Apuesta nuevaApuesta = new Apuesta(valorApuesta, TipoApuesta.NUMERO, monto, ruleta);
                    apuestaGuardada = guardar(nuevaApuesta);
                } else
                    throw new DatosInvalidos("El valor debe ser color negro o rojo o un entero entre 0 y 36");
            }
        }
        else
            throw new DatosInvalidos("El monto debe ser menor a 10,000");

        return apuestaGuardada;
    }

    @Override
    @Transactional
    public Iterable<Apuesta> calcularResultados(Ruleta ruleta) {

        List<Apuesta> apuestas = (List<Apuesta>) buscarApuestasPorRuletaId(ruleta.getId());

        if (apuestas.isEmpty())
            throw new ApuestaCancelada("No hay apuestas para calcular");
        else {
            ruleta.setEstaAbierta(false);
            apuestas.forEach(apuesta -> guardar(calculoApuesta(ruleta,apuesta)));
        }
        return apuestas;
    }
}