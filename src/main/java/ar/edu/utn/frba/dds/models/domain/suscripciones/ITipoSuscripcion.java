package ar.edu.utn.frba.dds.models.domain.suscripciones;

import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;

/**
 * representa un tipo de suscripcion
 */
public interface ITipoSuscripcion {

  void notificar(Heladera heladera, Suscripcion suscripcion);

}