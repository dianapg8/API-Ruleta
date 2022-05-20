package com.ibm.academia.apiruleta.controllers;

import com.ibm.academia.apiruleta.mapper.MapperApuesta;
import com.ibm.academia.apiruleta.mapper.MapperRuleta;
import com.ibm.academia.apiruleta.model.dto.ApuestaDTO;
import com.ibm.academia.apiruleta.model.dto.RuletaDTO;
import com.ibm.academia.apiruleta.model.entities.Apuesta;
import com.ibm.academia.apiruleta.model.entities.Ruleta;
import com.ibm.academia.apiruleta.services.RuletaDAO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/ruleta")
public class RuletaController
{
    @Autowired
    RuletaDAO ruletaDAO;

    @Operation(summary = "Crear nueva ruleta", description = "Crea una nueva ruleta y retorna su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ruleta creada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Integer.class))}),
            @ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content)

    })

    @PostMapping("/crear")
    public ResponseEntity<Integer> crearRuleta() {
        return new ResponseEntity<>(ruletaDAO.crear(), HttpStatus.CREATED);
    }

    @Operation(summary = "Abrir ruleta", description = "Abre la ruleta con dicho id para permitir apuestas en ella")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "La ruleta esta abierta a apuestas",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Boolean.class))}),
            @ApiResponse(responseCode = "400", description = "Peticion invalida", content = @Content),
            @ApiResponse(responseCode = "404", description = "La ruleta no existe", content = @Content)
    })
    @Parameter(name = "ruletaId", required = true, description = "Id de ruleta")

    @PutMapping("/abrir")
    public ResponseEntity<Boolean> abrirRuleta(@RequestParam Integer ruletaId) {

        return new ResponseEntity<>(ruletaDAO.abrir(ruletaId), HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Realizar apuesta", description = "Realiza apuesta en la ruleta indicada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Apuesta creada", content =
                    {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApuestaDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Peticion Invalida", content = @Content)
    })
    @Parameters(value = {
            @Parameter(name = "idRuleta", description = "Id de ruleta", required = true),
            @Parameter(name = "valorApuesta", description = "Apuesta numero: 1-36 o Apuesta color: Rojo o Negro", required = true),
            @Parameter(name = "montoApuesta", description = "Maximo 10,000", required = true)
    })

    @PostMapping("/apostar")
    public ResponseEntity<?> apostarRuleta(@RequestParam Integer idRuleta, @RequestParam String valorApuesta, @RequestParam Double montoApuesta) {

        ApuestaDTO apuestaDTO = MapperApuesta.mapApuesta(ruletaDAO.apostar(idRuleta, valorApuesta, montoApuesta));
        return new ResponseEntity<>(apuestaDTO, HttpStatus.ACCEPTED);

    }

    @Operation(summary = "Cerrar ruleta", description = "Cierra la ruleta, no acepta apuestas y elige ganador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Ruleta Cerrada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Apuesta.class))}),
            @ApiResponse(responseCode = "400", description = "No se hicieron apuestas", content = @Content)
    })
    @Parameter(name = "ruletaId", description = "Id de ruleta")

    @GetMapping("/cerrar")
    public ResponseEntity<Iterable<Apuesta>> cierreApuestas(@RequestParam Integer ruletaId) {

        return new ResponseEntity<>(ruletaDAO.cerrar(ruletaId), HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Listar todas las ruletas", description = "Lista de ruletas existentes y su estado abierto o cerrado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Lista de ruletas", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = RuletaDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "No hay ruletas que mostrar", content = @Content)
    })

    @GetMapping("/listar")
    public ResponseEntity<?> listarRuletas() {
        List<Ruleta> ruletas = (List<Ruleta>) ruletaDAO.buscarTodos();
        List<RuletaDTO> ruletasDto = ruletas.
                stream()
                .map(MapperRuleta::mapRuleta)
                .collect(Collectors.toList());

        return new ResponseEntity<>(ruletasDto, HttpStatus.ACCEPTED);
    }
}