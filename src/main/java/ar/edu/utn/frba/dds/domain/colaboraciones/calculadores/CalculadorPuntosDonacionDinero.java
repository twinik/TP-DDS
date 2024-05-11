package ar.edu.utn.frba.dds.domain.colaboraciones.calculadores;

import ar.edu.utn.frba.dds.domain.colaboraciones.Colaboracion;
import ar.edu.utn.frba.dds.domain.colaboraciones.DonacionDinero;

public class CalculadorPuntosDonacionDinero implements CalculadorDePuntos {
  public Float calcularPuntos(Colaboracion colaboracion) {
    DonacionDinero donacion = (DonacionDinero) colaboracion;
    return (float) (donacion.getMonto() * 0.5);
  }
}
