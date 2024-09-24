package ar.edu.utn.frba.dds.models.domain.colaboraciones.utils;

import ar.edu.utn.frba.dds.models.db.EntidadPersistente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Producto class representa un producto.
 */
@Getter
@NoArgsConstructor
@Entity
@Table(name = "producto")
public class Producto extends EntidadPersistente {
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "url_foto", columnDefinition = "TEXT")
    private String urlFoto;

    public Producto(String nombre, String urlFoto) {
        this.nombre = nombre;
        this.urlFoto = urlFoto;
    }
}