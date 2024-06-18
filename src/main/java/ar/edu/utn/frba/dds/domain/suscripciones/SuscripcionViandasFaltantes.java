package ar.edu.utn.frba.dds.domain.suscripciones;
import ar.edu.utn.frba.dds.domain.suscripciones.ITipoSuscripcion;
import ar.edu.utn.frba.dds.domain.suscripciones.Suscripcion;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import lombok.NoArgsConstructor;

/**
 * Suscripcion que notifica al haber N viandas faltantes para que la heledera este llena
 */
@NoArgsConstructor
public class SuscripcionViandasFaltantes implements ITipoSuscripcion {

    public void notificar(Heladera heladera, Suscripcion suscripcion) {
        int viandasFaltantes = heladera.getCapacidadViandas() - heladera.getViandas().size();
        if(viandasFaltantes <= suscripcion.getNumero()){
            String message = "Faltan " + viandasFaltantes + " viandas para que la heladera " + heladera.getNombre() + " alcance su capacidad maxima";
            suscripcion.getNotificacionStrategy().notificar(suscripcion.getMedioDeContacto(), message);
        }
    }

}