package ar.edu.utn.frba.dds.domain.heladeras;

import ar.edu.utn.frba.dds.db.EntidadPersistente;
import ar.edu.utn.frba.dds.domain.excepciones.NoAutorizadoParaAbrirHeladeraException;
import ar.edu.utn.frba.dds.helpers.ConfigReader;
import ar.edu.utn.frba.dds.helpers.DateHelper;
import ar.edu.utn.frba.dds.domain.tarjetas.TarjetaColaborador;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Optional;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Clase que representa la apertura de de una heladera
 */
@Entity
@Table(name = "apertura_heladera")
@Getter
@Setter
@NoArgsConstructor
public class AperturaHeladera extends EntidadPersistente {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "solicitud_apertura_id", referencedColumnName = "id",unique = true)
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

    public static AperturaHeladera of(SolicitudAperturaHeladera solicitud, LocalDateTime timestamp, Heladera heladera){
        return new AperturaHeladera(solicitud,timestamp,heladera);
    }
}