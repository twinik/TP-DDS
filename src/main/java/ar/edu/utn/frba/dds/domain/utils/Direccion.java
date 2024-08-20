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
    private Long id;
    private String calle;
    private Integer altura;
    private Integer piso;
    private Integer codigoPostal;

    public Direccion(String calle, Integer altura, Integer piso, Integer codigoPostal) {
        this.calle = calle;
        this.altura = altura;
        this.piso = piso;
        this.codigoPostal = codigoPostal;
    }
}
