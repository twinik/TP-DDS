package ar.edu.utn.frba.dds.domain.incidentes;

import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.notifications.NotificationStrategyFactory;
import ar.edu.utn.frba.dds.helpers.TecnicosHelper;
import ar.edu.utn.frba.dds.repositories.ITecnicosRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

/**
 * 
 */
@Getter
@Setter
@AllArgsConstructor
public class FallaTecnica extends Incidente {
    private Colaborador colaborador;
    private String descripcion;
    private String urlFoto;

    public FallaTecnica(Heladera heladera, LocalDateTime timestamp, TecnicosHelper tecnicosHelper,NotificationStrategyFactory notificationStrategyFactory, Colaborador colaborador, String descripcion, String urlFoto) {
        super(heladera, timestamp,tecnicosHelper,notificationStrategyFactory);
        this.colaborador = colaborador;
        this.descripcion = descripcion;
        this.urlFoto = urlFoto;
    }
}