package ar.edu.utn.frba.dds.domain.colaboraciones.calculadores;

import ar.edu.utn.frba.dds.domain.colaboraciones.Colaboracion;
import ar.edu.utn.frba.dds.domain.colaboraciones.DonacionVianda;

public class CalculadorPuntosDonacionVianda implements CalculadorDePuntos {
  public Float calcularPuntos(Colaboracion colaboracion) {
    // suma 1 por cada vianda distribuida
    return (float) 1;
  }
}
