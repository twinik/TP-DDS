package ar.edu.utn.frba.dds.models.domain.heladeras;

import ar.edu.utn.frba.dds.models.db.EntidadPersistente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Clase que representa la apertura de de una heladera
 */
@Entity
@Table(name = "apertura_heladera")
@Getter
@Setter
@NoArgsConstructor
public class AperturaHeladera extends EntidadPersistente {

    @OneToOne
    @JoinColumn(name = "solicitud_apertura_id", referencedColumnName = "id", unique = true)
    private SolicitudAperturaHeladera solicitud;

    @Column(name = "timestamp", columnDefinition = "TIMESTAMP")
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "heladera_id", referencedColumnName = "id")
    private Heladera heladera;

    public AperturaHeladera(SolicitudAperturaHeladera solicitud, LocalDateTime timestamp, Heladera heladera) {
        this.solicitud = solicitud;
        this.timestamp = timestamp;
        this.heladera = heladera;
    }

    public static AperturaHeladera of(SolicitudAperturaHeladera solicitud, LocalDateTime timestamp, Heladera heladera) {
        return new AperturaHeladera(solicitud, timestamp, heladera);
    }
}