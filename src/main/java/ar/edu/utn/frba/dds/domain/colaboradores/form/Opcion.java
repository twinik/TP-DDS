package ar.edu.utn.frba.dds.domain.colaboradores.form;

import ar.edu.utn.frba.dds.db.EntidadPersistente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Opcion class permite representar una opcion de un campo de un formulario.
 */

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "opcion")
public class Opcion extends EntidadPersistente {

    @Column(name = "opcion", columnDefinition = "TEXT")
    private String opcion;
}