package ar.edu.utn.frba.dds.domain.colaboraciones;


import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.heladeras.Vianda;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

/**
 * DonacionVianda class representa una colaboracion de un colaborador.
 * Consiste en donar viandas.
 */
@NoArgsConstructor
@Getter
@Setter
public class DonacionVianda implements IPuntajeCalculable {
  private Long id;
  private Colaborador colaborador;
  private LocalDate fecha;
  private Vianda vianda;
  private static final Float PUNTATE_POR_DONACION = 1.5f;

  public DonacionVianda(Colaborador colaborador, LocalDate fecha, Vianda vianda) {
    this.colaborador = colaborador;
    this.fecha = fecha;
    this.vianda = vianda;
  }

  @Override
  public Float calcularPuntaje() {
    return PUNTATE_POR_DONACION;
  }
}