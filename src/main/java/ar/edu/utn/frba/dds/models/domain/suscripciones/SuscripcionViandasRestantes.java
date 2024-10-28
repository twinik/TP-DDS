package ar.edu.utn.frba.dds.models.domain.suscripciones;

import ar.edu.utn.frba.dds.helpers.ConfigReader;
import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeViandasRestantesFactory;
import lombok.NoArgsConstructor;
import java.io.IOException;

/**
 * Suscripcion quqe notifica cuando falten N viandas para que la heladera esta vacia
 */
@NoArgsConstructor
public class SuscripcionViandasRestantes implements ITipoSuscripcion {

  public void notificar(Heladera heladera, Suscripcion suscripcion) {
    ConfigReader configReader = new ConfigReader("config.properties");
    try {
      if (heladera.getViandas() <= suscripcion.getNumero()) {
        String message = MensajeViandasRestantesFactory.generarMensaje(heladera);
        suscripcion.getNotificacionStrategy().notificar(suscripcion.getColaborador(), configReader.getProperty("ASUNTO_MAIL_SUSCRIPCION"), message);
      }
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

}