package com.ibm.academia.apiruleta.model.dto;

import com.ibm.academia.apiruleta.enums.TipoApuesta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApuestaDTO
{
    private Integer id;

    @NotNull(message = "No puede ser null")
    @NotEmpty(message = "No puede ser vacio")
    private String valorApuesta;

    @NotNull(message = "No puede ser null")
    @NotEmpty(message = "No puede ser vacio")
    private TipoApuesta tipoApuesta;

    @NotNull(message = "No puede ser null")
    @NotEmpty(message = "No puede ser vacio")
    private Double monto;

    private Integer idRuletaApuesta;
}
