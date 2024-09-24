package ar.edu.utn.frba.dds.models.domain.suscripciones;

import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.models.domain.heladeras.RecomendadorHeladeras;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeHeladerasRecomendadasFactory;
import lombok.AllArgsConstructor;
import java.util.List;

/**
 * suscripcion que notifica si hubo un desperfecto en la heladera
 */
@AllArgsConstructor
public class SuscripcionDesperfectoHeladera implements ITipoSuscripcion {
    private RecomendadorHeladeras recomendadorHeladeras;

    public void notificar(Heladera heladera, Suscripcion suscripcion) {
        if (!heladera.isHeladeraActiva()) {
            suscripcion.getNotificacionStrategy().notificar(suscripcion.getColaborador(),
                    GenerarMensajeHeladerasRecomendadas(heladera));
        }
    }

    private String GenerarMensajeHeladerasRecomendadas(Heladera heladera) {
        List<Heladera> heladeras = recomendadorHeladeras.recomendarCombinacionHeladeras(heladera);
        return MensajeHeladerasRecomendadasFactory.GenerarMensaje(heladeras);
    }

}