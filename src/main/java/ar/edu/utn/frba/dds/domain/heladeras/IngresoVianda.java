package ar.edu.utn.frba.dds.domain.heladeras;

import ar.edu.utn.frba.dds.domain.colaboraciones.DonacionVianda;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * IngresoVianda class permite representar un ingreso de vianda.
 */
@Getter
@AllArgsConstructor
public class IngresoVianda {

  private Date fechaDonacion;

  private Colaborador colaborador;

  private boolean entregada;

  private List<Vianda> viandas;

  private Heladera heladera;

  public List<DonacionVianda> donar() {
    return this.viandas.stream().map(DonacionVianda::new).toList();
  }

}