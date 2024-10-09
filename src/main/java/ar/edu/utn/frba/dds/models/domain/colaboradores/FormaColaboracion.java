package ar.edu.utn.frba.dds.models.domain.colaboradores;

import ar.edu.utn.frba.dds.models.db.EntidadPersistente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * FormaColaboracion enum permite representar las formas de colaboracion que puede tener un colaborador.
 */

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "forma_colaboracion")
public class FormaColaboracion extends EntidadPersistente {

    @Column(name = "nombreInterno", unique = true)
    private String nombreInterno;

    @Column(name = "descripcion")
    private String descripcion;

}
