package ar.edu.utn.frba.dds.dtos;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DIreccionDto {
    private String calle;
    private Integer numero;
    private Integer piso;
    private String codigoPostal;
}
