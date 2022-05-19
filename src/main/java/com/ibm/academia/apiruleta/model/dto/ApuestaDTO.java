package com.ibm.academia.apiruleta.model.dto;

import com.ibm.academia.apiruleta.enums.TipoApuesta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApuestaDTO
{
    private Integer id;
    private String valorApuesta;
    private TipoApuesta tipoApuesta;
    private Double monto;
    private Integer idRuletaApuesta;
}
