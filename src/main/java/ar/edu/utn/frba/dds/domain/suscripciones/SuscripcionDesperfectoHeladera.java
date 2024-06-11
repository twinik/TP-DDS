package ar.edu.utn.frba.dds.domain.suscripciones;

import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.heladeras.RecomendadorHeladeras;
import lombok.AllArgsConstructor;

/**
 * suscripcion que notifica si hubo un desperfecto en la heladera
 */
@AllArgsConstructor
public class SuscripcionDesperfectoHeladera implements ITipoSuscripcion {
    private RecomendadorHeladeras recomendadorHeladeras;

    public void notificar(Heladera heladera, Suscripcion suscripcion) {
        suscripcion.getNotificacionStrategy().notificar(suscripcion.getMedioDeContacto());
    }


}