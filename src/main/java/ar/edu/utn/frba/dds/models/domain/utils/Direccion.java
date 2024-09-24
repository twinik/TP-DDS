package ar.edu.utn.frba.dds.models.domain.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Direccion class permite representar una direccion.
 */
@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class Direccion {
    @Column(name = "calle")
    private String calle;
    @Column(name = "altura")
    private Integer altura;
    @Column(name = "piso")
    private Integer piso;
    @Column(name = "codigoPostal")
    private String codigoPostal;

    public Direccion(String calle, Integer altura, Integer piso, String codigoPostal) {
        this.calle = calle;
        this.altura = altura;
        this.piso = piso;
        this.codigoPostal = codigoPostal;
    }
}
