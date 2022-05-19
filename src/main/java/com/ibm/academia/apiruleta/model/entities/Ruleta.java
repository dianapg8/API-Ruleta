package com.ibm.academia.apiruleta.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ibm.academia.apiruleta.enums.Color;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="ruletas", schema = "ruleta")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Ruleta
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Boolean estaAbierta=false;

    @Column(name = "color_ganador")
    @Enumerated(EnumType.STRING)
    private Color colorGanador;

    private Integer numeroGanador;
    private Date fechaCreacion;

    @OneToMany(mappedBy = "ruleta",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"ruleta"})
    private List<Apuesta> apuestasRealizadas;

    @PrePersist
    private void antesPersistir() {
        this.fechaCreacion= new Date();
    }

    public Ruleta(Integer id, Boolean estaAbierta) {
        this.id = id;
        this.estaAbierta = estaAbierta;
    }
}