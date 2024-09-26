package ar.edu.utn.frba.dds.models.domain.colaboraciones.utils;

import ar.edu.utn.frba.dds.models.db.EntidadPersistente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * MotivoRedistribucionVianda class representa un motivo de redistribucion de vianda.
 */
@Getter
@Setter
@Entity
@Table(name = "motivo_redistribucion_vianda")
@NoArgsConstructor
public class MotivoRedistribucionVianda extends EntidadPersistente {
    @Column(name = "motivo_descripcion", columnDefinition = "TEXT")
    private String motivo;

    public MotivoRedistribucionVianda(String motivo) {
        this.motivo = motivo;
    }
}
