package com.ibm.academia.apiruleta.services;

import com.ibm.academia.apiruleta.data.DummyData;
import com.ibm.academia.apiruleta.model.entities.Ruleta;
import com.ibm.academia.apiruleta.repositories.RuletaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RuletaDAOImplTest {
    RuletaDAO ruletaDAO;
    RuletaRepository ruletaRepository;

    @BeforeEach
    void setUp(){
        ruletaRepository = mock(RuletaRepository.class);
        ruletaDAO = new RuletaDAOImpl(ruletaRepository);
    }

    @Test
    void buscarPorId() {
        when(ruletaRepository.findById(1)).thenReturn(Optional.of(DummyData.ruleta01()));
        Ruleta ruleta = ruletaDAO.buscarPorId(1);
        assertThat(ruleta.getId()).isEqualTo(1);
        verify(ruletaRepository).findById(1);
    }
}
