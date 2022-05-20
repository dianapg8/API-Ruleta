package com.ibm.academia.apiruleta.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ibm.academia.apiruleta.enums.TipoApuesta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="apuestas",schema="ruleta")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Apuesta
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "valor_apuesta")
    private String valorApuesta;

    @Column(name = "tipo_apuesta")
    @Enumerated(EnumType.STRING)
    private TipoApuesta tipoApuesta;

    private Double monto;
    private Boolean esGanadora=false;
    private Double premio =0d;
    private Date fechaCreacion;

    @ManyToOne
    @JsonIgnoreProperties({"apuestasRealizadas"})
    @JoinColumn(name = "ruleta_id",foreignKey = @ForeignKey(name = "FK_RULETA_ID"))
    private Ruleta ruleta;

    @PrePersist
    private void antesPersistir() {
        this.fechaCreacion= new Date();
    }

    public Apuesta(String valorApuesta, TipoApuesta tipoApuesta, Double monto, Ruleta ruleta) {
        this.valorApuesta = valorApuesta;
        this.tipoApuesta = tipoApuesta;
        this.monto = monto;
        this.ruleta = ruleta;
        this.fechaCreacion = new Date();
    }

    public Apuesta(Integer id, String valorApuesta, TipoApuesta tipoApuesta, Double monto) {
        this.id = id;
        this.valorApuesta = valorApuesta;
        this.tipoApuesta = tipoApuesta;
        this.monto = monto;
        this.fechaCreacion = new Date();
    }
}