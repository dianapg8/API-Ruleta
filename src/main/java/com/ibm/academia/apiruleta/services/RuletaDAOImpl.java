package com.ibm.academia.apiruleta.services;

import com.ibm.academia.apiruleta.exceptions.NoExisteRuleta;
import com.ibm.academia.apiruleta.exceptions.RuletaCerrada;
import com.ibm.academia.apiruleta.model.entities.Apuesta;
import com.ibm.academia.apiruleta.model.entities.Ruleta;
import com.ibm.academia.apiruleta.repositories.RuletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.ibm.academia.apiruleta.services.CalculoGanador.generarColorGanador;
import static com.ibm.academia.apiruleta.services.CalculoGanador.generarNumeroGanador;

@Service
public class RuletaDAOImpl implements RuletaDAO {
    @Autowired
    ApuestaDAO apuestaDAO;

    private final RuletaRepository repository;

    @Autowired
    public RuletaDAOImpl(RuletaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void guardar(Ruleta ruleta) {
        repository.save(ruleta);
    }

    @Override
    public Ruleta buscarPorId(Integer id) {

        Optional<Ruleta> oRuleta = repository.findById(id);
        Ruleta ruletaEncontrada;

        if(oRuleta.isPresent())
            ruletaEncontrada=oRuleta.get();
        else
            throw new NoExisteRuleta("La ruleta con el ID " + id + " no existe.");

        return ruletaEncontrada;
    }

    @Override
    public Integer crear() {
        Ruleta ruleta = new Ruleta();
        guardar(ruleta);
        return ruleta.getId();
    }

    @Override
    public Boolean abrir(Integer id) {
        Ruleta ruleta = buscarPorId(id);
        ruleta.setEstaAbierta(true);
        guardar(ruleta);
        return ruleta.getEstaAbierta();
    }

    @Override
    public Apuesta apostar(Apuesta apuesta) {
        Ruleta ruleta = buscarPorId(apuesta.getId());
        Apuesta nuevaApuesta;

        if(ruleta.getEstaAbierta())
            nuevaApuesta=apuestaDAO.crearApuesta(apuesta.getValorApuesta(),apuesta.getMonto(),ruleta);
        else
            throw new RuletaCerrada("Esta ruleta no esta abierta a apuestas");

        return nuevaApuesta;
    }

    @Override
    public void girar(Integer id) {
        Ruleta ruleta = buscarPorId(id);
        Integer numero = generarNumeroGanador();
        ruleta.setNumeroGanador(numero);
        ruleta.setColorGanador(generarColorGanador(numero));
        guardar(ruleta);
    }

    @Override
    public Iterable<Apuesta> cerrar(Integer id) {
        Ruleta ruleta = buscarPorId(id);
        girar(id);
        Iterable<Apuesta> apuestasCalculadas = apuestaDAO.calcularResultados(ruleta);
        guardar(ruleta);
        return apuestasCalculadas;
    }

    @Override
    public Iterable<Ruleta> buscarTodos() {
        Iterable<Ruleta> ruletas = repository.findAll();
        if(((List<Ruleta>)ruletas).isEmpty()){
            throw new NoExisteRuleta("No existen ruletas que mostrar");
        }
        return ruletas;
    }


}