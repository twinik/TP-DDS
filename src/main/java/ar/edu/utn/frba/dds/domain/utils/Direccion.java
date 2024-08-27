package ar.edu.utn.frba.dds.domain.utils;

import ar.edu.utn.frba.dds.db.EntidadPersistente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Direccion class permite representar una direccion.
 */
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "direccion")
@NoArgsConstructor
public class Direccion extends EntidadPersistente {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "calle", nullable = false)
    private String calle;
    @Column(name = "altura", nullable = false)
    private Integer altura;
    @Column(name = "piso")
    private Integer piso;
    @Column(name = "codigoPostal")
    private Integer codigoPostal;

    public Direccion(String calle, Integer altura, Integer piso, Integer codigoPostal) {
        this.calle = calle;
        this.altura = altura;
        this.piso = piso;
        this.codigoPostal = codigoPostal;
    }
}
