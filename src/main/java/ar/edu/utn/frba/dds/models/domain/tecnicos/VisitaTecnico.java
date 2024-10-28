package ar.edu.utn.frba.dds.models.domain.tecnicos;

import ar.edu.utn.frba.dds.models.db.EntidadPersistente;
import ar.edu.utn.frba.dds.models.domain.incidentes.Incidente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * VisitaTecnico class representa una visita de un tecnico a una heladera
 */
@Entity
@Table(name = "visita_tecnico")
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class VisitaTecnico extends EntidadPersistente {

    @ManyToOne
    @JoinColumn(name = "tecnico_id", referencedColumnName = "id", nullable = false)
    private Tecnico tecnico;

    @Column(name = "fecha_visita", nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "url_foto", columnDefinition = "TEXT")
    private String urlFoto;

    @Column(name = "esta_solucionado")
    private boolean solucionado;

    @ManyToOne
    @JoinColumn(name = "incidente_id", referencedColumnName = "id", nullable = false)
    private Incidente incidente;

    public boolean estaSolucionado() {
        return this.solucionado;
    }

}