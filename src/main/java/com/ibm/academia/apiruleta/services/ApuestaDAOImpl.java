package com.ibm.academia.apiruleta.services;

import com.ibm.academia.apiruleta.enums.TipoApuesta;
import com.ibm.academia.apiruleta.exceptions.ApuestaCancelada;
import com.ibm.academia.apiruleta.exceptions.DatosInvalidos;
import com.ibm.academia.apiruleta.model.entities.Apuesta;
import com.ibm.academia.apiruleta.model.entities.Ruleta;
import com.ibm.academia.apiruleta.repositories.ApuestaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Iterable<Apuesta> buscarApuestasPorRuletaId(Integer id) {
        return repository.buscarApuestasPorRuletaId(id);
    }


    @Override
    public Apuesta crearApuesta(String valorApuesta, Double monto, Ruleta ruleta) {
        Apuesta apuestaGuardada;
        if(validarColorApuesta(valorApuesta) && validarMontoApuesta(monto)){
            Apuesta nuevaApuesta = new Apuesta(valorApuesta.toUpperCase(), TipoApuesta.COLOR,monto,ruleta);
            apuestaGuardada =guardar(nuevaApuesta);
        }else {
            try{
                Integer numeroApuesta = Integer.parseInt(valorApuesta);
                if(validarNumeroApuesta(numeroApuesta) && validarMontoApuesta(monto)){
                    Apuesta nuevaApuesta = new Apuesta(valorApuesta,TipoApuesta.NUMERO,monto,ruleta);
                    apuestaGuardada = guardar(nuevaApuesta);
                }
                else
                    throw new DatosInvalidos("No es posible apostar a este valor");

            }catch (NumberFormatException e) {
                throw new DatosInvalidos("No es posible apostar a este valor");
            }
        }
        return apuestaGuardada;
    }

    @Override
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