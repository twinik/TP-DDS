package ar.edu.utn.frba.dds.domain.colaboraciones.calculadores;

import ar.edu.utn.frba.dds.domain.colaboraciones.IPuntajeCalculable;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CalculadorPuntos implements ICalculadorPuntos {
  public void sumarPuntosPara(Colaborador colaborador, IPuntajeCalculable... colaboraciones){
    for(IPuntajeCalculable colaboracion: colaboraciones){
      colaborador.sumarPuntos(colaboracion.calcularPuntaje());
    }
  }
}
