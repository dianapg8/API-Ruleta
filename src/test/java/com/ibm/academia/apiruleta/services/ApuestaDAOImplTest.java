package com.ibm.academia.apiruleta.services;

import com.ibm.academia.apiruleta.data.DummyData;
import com.ibm.academia.apiruleta.model.entities.Apuesta;
import com.ibm.academia.apiruleta.repositories.ApuestaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

public class ApuestaDAOImplTest {

    ApuestaDAO apuestaDAO;
    ApuestaRepository apuestaRepository;

    @BeforeEach
    void setUp(){
        apuestaRepository = mock(ApuestaRepository.class);
        apuestaDAO = new ApuestaDAOImpl(apuestaRepository);
    }

    @Test
    void buscarApuestasPorRuletaId() {
        when(apuestaRepository.buscarApuestasPorRuletaId(1))
                .thenReturn(List.of(DummyData.apuesta03()));
        List<Apuesta> apuestas = (List<Apuesta>) apuestaDAO.buscarApuestasPorRuletaId(1);

        assertThat(apuestas.size()).isGreaterThan(0);

        verify(apuestaRepository).buscarApuestasPorRuletaId(1);
    }
}
