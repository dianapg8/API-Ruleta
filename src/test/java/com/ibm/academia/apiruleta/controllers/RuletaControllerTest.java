package com.ibm.academia.apiruleta.controllers;

import com.ibm.academia.apiruleta.data.DummyData;
import com.ibm.academia.apiruleta.enums.TipoApuesta;
import com.ibm.academia.apiruleta.model.entities.Apuesta;
import com.ibm.academia.apiruleta.services.RuletaDAO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RuletaController.class)
class RuletacontrollerTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    @Qualifier("ruletaDaoImpl")
    private RuletaDAO ruletaDAO;

    @Test
    @DisplayName("Retorna el id del nuevo objeto creado y un estado 'created'")
    void crearRuleta() throws Exception{
        when(ruletaDAO.crear()).thenReturn(DummyData.ruleta01().getId());
        mockMvc.perform(post("/api/v1/ruleta/crear").accept(MediaType.APPLICATION_JSON)
                .content(DummyData.ruletaIdJson01())).andExpect(status().isCreated());
        verify(ruletaDAO).crear();
    }

    @Test
    @DisplayName("Abre la ruleta y retorna un valor 'true' y un estado 'Accepted'")
    void abrirRuleta() throws Exception{
        when(ruletaDAO.abrir(1))
                .thenReturn(true);
        mockMvc.perform(put("/api/v1/ruleta/abrir/?id=1").accept(MediaType.APPLICATION_JSON)
                .content(DummyData.ruletaEstadoJson01())).andExpect(status().isAccepted());
        verify(ruletaDAO).abrir(1);
    }


    @Test
    @DisplayName("Retorna la apuesta creada y un estado 'Acepted'")
    void apostarRuleta() throws Exception{
        when(ruletaDAO.apostar(new Apuesta(1, "negro", TipoApuesta.COLOR, 1000d))).thenReturn(DummyData.apuesta01());
        mockMvc.perform(post("/api/v1/ruleta/apostar/?id=1&valorApuesta=negro&monto=8000")
                        .accept(MediaType.APPLICATION_JSON).content(DummyData.apuesta01Json()))
                .andExpect(status().isAccepted());

    }
    @Test
    @DisplayName("Retorna una lista de las ruletas creadas y un estado 'Accepted'")
    void listarRuletas() throws Exception {
        when(ruletaDAO.buscarTodos()).thenReturn(List.of(DummyData.ruleta01()));

        mockMvc.perform(get("/api/v1/ruleta/listar")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());

        verify(ruletaDAO).buscarTodos();
    }

    @Test
    @DisplayName("Retorna una lista de apuestas calculas y un estado 'Accepted'")
    void cierreApuestas() throws Exception{
        when(ruletaDAO.cerrar(2)).thenReturn(List.of(DummyData.apuesta04()));
        mockMvc.perform(get("/api/v1/ruleta/cierreapuestas/?id=2").accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isAccepted());
        verify(ruletaDAO).cerrar(2);

    }
}