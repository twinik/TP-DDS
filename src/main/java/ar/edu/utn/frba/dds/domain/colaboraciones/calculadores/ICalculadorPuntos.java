package ar.edu.utn.frba.dds.domain.colaboraciones.calculadores;


import ar.edu.utn.frba.dds.domain.colaboraciones.IPuntajeCalculable;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import java.util.List;

/**
 * CalculadorDePuntos interface permite calcular los puntos de una colaboracion.
 */
public interface ICalculadorPuntos {
  void sumarPuntosPara(Colaborador colaborador, IPuntajeCalculable... colaboraciones);
}