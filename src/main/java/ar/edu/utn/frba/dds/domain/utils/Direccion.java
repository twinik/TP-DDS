package ar.edu.utn.frba.dds.domain.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Direccion class permite representar una direccion.
 */
@Getter
@Setter
@AllArgsConstructor
public class Direccion {
    private String calle;
    private Integer altura;
    private Integer piso;
    private Integer codigoPostal;

}
