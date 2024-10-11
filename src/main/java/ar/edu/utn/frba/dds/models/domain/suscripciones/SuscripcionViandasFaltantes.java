package ar.edu.utn.frba.dds.models.domain.suscripciones;

import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeViandasFaltantesFactory;
import lombok.NoArgsConstructor;

/**
 * Suscripcion que notifica al haber N viandas faltantes para que la heledera este llena
 */
@NoArgsConstructor
public class SuscripcionViandasFaltantes implements ITipoSuscripcion {

    public void notificar(Heladera heladera, Suscripcion suscripcion) {
        int viandasFaltantes = heladera.getCapacidadViandas() - heladera.getViandas();
        if (viandasFaltantes <= suscripcion.getNumero()) {
            String message = MensajeViandasFaltantesFactory.generarMensaje(viandasFaltantes, heladera);
            suscripcion.getNotificacionStrategy().notificar(suscripcion.getColaborador(), message);
        }
    }

}