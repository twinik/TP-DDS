package ar.edu.utn.frba.dds.domain.colaboraciones.calculadores;

import ar.edu.utn.frba.dds.domain.colaboraciones.AltaPersonaVulnerable;
import ar.edu.utn.frba.dds.domain.colaboraciones.Colaboracion;

public class CalculadorPuntosAltaPersona implements CalculadorDePuntos {
  public Float calcularPuntos(Colaboracion colaboracion) {
    return (float) 2;
  }
}
