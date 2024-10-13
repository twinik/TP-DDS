package ar.edu.utn.frba.dds.models.domain.suscripciones;

import ar.edu.utn.frba.dds.helpers.ConfigReader;
import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.models.domain.heladeras.RecomendadorHeladeras;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeHeladerasRecomendadasFactory;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.io.IOException;
import java.util.List;

/**
 * suscripcion que notifica si hubo un desperfecto en la heladera
 */
@AllArgsConstructor
@NoArgsConstructor
public class SuscripcionDesperfectoHeladera implements ITipoSuscripcion {
    private RecomendadorHeladeras recomendadorHeladeras;

    public void notificar(Heladera heladera, Suscripcion suscripcion) {
        ConfigReader configReader = new ConfigReader("config.properties");
        try {
            if (!heladera.isHeladeraActiva()) {
                suscripcion.getNotificacionStrategy().notificar(suscripcion.getColaborador(),
                    configReader.getProperty("ASUNTO_MAIL_SUSCRIPCION"),
                    GenerarMensajeHeladerasRecomendadas(heladera));
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private String GenerarMensajeHeladerasRecomendadas(Heladera heladera) {
        List<Heladera> heladeras = recomendadorHeladeras.recomendarCombinacionHeladeras(heladera);
        return MensajeHeladerasRecomendadasFactory.GenerarMensaje(heladeras, heladera);
    }

}