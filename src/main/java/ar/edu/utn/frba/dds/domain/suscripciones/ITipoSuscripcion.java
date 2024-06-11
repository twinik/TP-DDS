package ar.edu.utn.frba.dds.domain.suscripciones;

import ar.edu.utn.frba.dds.domain.heladeras.Heladera;

/**
 * representa un tipo de suscripcion
 */
public interface ITipoSuscripcion {

    public void notificar(Heladera heladera, Suscripcion suscripcion);

}