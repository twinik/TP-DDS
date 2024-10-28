package ar.edu.utn.frba.dds.models.domain.incidentes;

import ar.edu.utn.frba.dds.helpers.ConfigReader;
import ar.edu.utn.frba.dds.helpers.TecnicosHelper;
import ar.edu.utn.frba.dds.models.db.EntidadPersistente;
import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.models.domain.notifications.NotificationStrategy;
import ar.edu.utn.frba.dds.models.domain.notifications.NotificationStrategyFactory;
import ar.edu.utn.frba.dds.models.domain.tecnicos.Tecnico;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeTecnicosIncidenteFactory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Representa un incidente
 */
@Getter
@NoArgsConstructor
@Entity
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "incidente")
@DiscriminatorColumn(name = "tipo_incidente")
public abstract class Incidente extends EntidadPersistente {
    @ManyToOne
    @JoinColumn(name = "heladera_id", referencedColumnName = "id")
    private Heladera heladera;

    @Column(name = "timestamp", columnDefinition = "TIMESTAMP")
    private LocalDateTime timestamp;

    // DESNORMALIZACION PARA PERFORMANCE
    @Column(name = "solucionado")
    private boolean solucionado = false;

    @Transient
    private TecnicosHelper tecnicosHelper;

    @Transient
    private NotificationStrategyFactory notificationStrategyFactory;

    public Incidente(Heladera heladera, LocalDateTime timestamp, TecnicosHelper tecnicosHelper, NotificationStrategyFactory notificationStrategyFactory) {
        this.heladera = heladera;
        this.timestamp = timestamp;
        this.tecnicosHelper = tecnicosHelper;
        this.notificationStrategyFactory = notificationStrategyFactory;
    }

    public void reportar() {
        heladera.inhabilitar();
        Tecnico tecnicoAContactar = tecnicosHelper.findTecnicoMasCercano(heladera.getUbicacion());
        String message = MensajeTecnicosIncidenteFactory.generarMensaje(tecnicoAContactar, heladera, timestamp);
        tecnicoAContactar.getMedioContacto().stream().parallel().forEach(medio -> {
            NotificationStrategy strategy = notificationStrategyFactory.create(medio.getCanal());
            try {
                strategy.notificar(tecnicoAContactar, new ConfigReader("config.properties").getProperty("ASUNTO_MENSAJE_TECNICO"), message);
                ;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void marcarSolucionado() {
        this.solucionado = true;
    }

    public abstract String getTipo();

}
