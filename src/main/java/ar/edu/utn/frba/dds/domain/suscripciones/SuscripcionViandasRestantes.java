package ar.edu.utn.frba.dds.domain.suscripciones;

import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import lombok.NoArgsConstructor;

/**
 * Suscripcion quqe notifica cuando falten N viandas para que la heladera esta vacia
 */
@NoArgsConstructor
public class SuscripcionViandasRestantes implements ITipoSuscripcion {

    public void notificar(Heladera heladera, Suscripcion suscripcion) {
        if(heladera.getViandas().size() <= suscripcion.getNumero()){
            String message = "Quedan " + heladera.getViandas().size() + " viandas en la heladera " + heladera.getNombre();
            suscripcion.getNotificacionStrategy().notificar(suscripcion.getMedioDeContacto(), message);
        }
    }

}