package com.ibm.academia.apiruleta.controllers;

import com.ibm.academia.apiruleta.mapper.MapperApuesta;
import com.ibm.academia.apiruleta.mapper.MapperRuleta;
import com.ibm.academia.apiruleta.model.dto.ApuestaDTO;
import com.ibm.academia.apiruleta.model.dto.RuletaDTO;
import com.ibm.academia.apiruleta.model.entities.Apuesta;
import com.ibm.academia.apiruleta.model.entities.Ruleta;
import com.ibm.academia.apiruleta.services.RuletaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/ruleta")
public class RuletaController
{
    @Autowired
    RuletaDAO ruletaDAO;

    @PostMapping("/crear")
    public ResponseEntity<?> crearRuleta() {
        Integer id = ruletaDAO.crear();
        return new ResponseEntity<String>("La ruleta con id " + id + " fue creada con exito", HttpStatus.CREATED);
    }

    @PutMapping("/abrir/{ruletaId}")
    public ResponseEntity<?> abrirRuleta(@PathVariable Integer ruletaId) {
        ruletaDAO.abrir(ruletaId);
        return new ResponseEntity<String>("La ruleta con id "+ ruletaId + " esta abierta a apuestas", HttpStatus.ACCEPTED);
    }

    @PutMapping("/apostar")
    public ResponseEntity<?> apostarRuleta(@RequestBody Apuesta apuesta) {
        ApuestaDTO apuestaDTO = MapperApuesta.mapApuesta(ruletaDAO.apostar(apuesta));
        return new ResponseEntity<>(apuestaDTO, HttpStatus.ACCEPTED);
    }

    @PostMapping("/cerrar/{ruletaId}")
    public ResponseEntity<?> cierreApuestas(@PathVariable Integer ruletaId) {
        ruletaDAO.cerrar(ruletaId);
        return new ResponseEntity<String>("La ruleta con id " + ruletaId + " fue cerrada con exito", HttpStatus.ACCEPTED);
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarRuletas() {
        List<Ruleta> ruletas = (List<Ruleta>) ruletaDAO.buscarTodos();
        List<RuletaDTO> ruletasDto = ruletas
                .stream()
                .map(MapperRuleta::mapRuleta)
                .collect(Collectors.toList());
        return new ResponseEntity<>(ruletasDto, HttpStatus.ACCEPTED);
    }
}