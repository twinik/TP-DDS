package ar.edu.utn.frba.dds.domain.suscripciones;

import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.messageFactory.MensajeViandasRestantesFactory;
import lombok.NoArgsConstructor;

/**
 * Suscripcion quqe notifica cuando falten N viandas para que la heladera esta vacia
 */
@NoArgsConstructor
public class SuscripcionViandasRestantes implements ITipoSuscripcion {

    public void notificar(Heladera heladera, Suscripcion suscripcion) {
        if (heladera.getViandas().size() <= suscripcion.getNumero()) {
            String message = MensajeViandasRestantesFactory.generarMensaje(heladera);
            suscripcion.getNotificacionStrategy().notificar(suscripcion.getColaborador(), message);
        }
    }

}