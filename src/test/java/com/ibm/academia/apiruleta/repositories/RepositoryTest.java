package com.ibm.academia.apiruleta.repositories;

import com.ibm.academia.apiruleta.data.DummyData;
import com.ibm.academia.apiruleta.model.entities.Apuesta;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class RepositoryTest {
    @Autowired
    private ApuestaRepository repository;

    @Autowired
    private RuletaRepository ruletaRepository;

    @BeforeEach
    void setUp(){
        ruletaRepository.save(DummyData.ruleta01());
        repository.save(DummyData.apuesta01());
        repository.save(DummyData.apuesta02());
        repository.save(DummyData.apuesta03());
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void buscarPorRuletaId() {
        List<Apuesta> apuestas = (List<Apuesta>) repository.buscarApuestasPorRuletaId(1);
        assertThat(apuestas.size()).isGreaterThan(0);
    }
}
