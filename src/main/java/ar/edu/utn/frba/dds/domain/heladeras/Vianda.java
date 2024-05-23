package ar.edu.utn.frba.dds.domain.heladeras;

import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import java.time.LocalDate;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Vianda class permite representar una vianda.
 */
@Getter
@Setter
@AllArgsConstructor
public class Vianda {

  private String comida;

  private Date fechaCaducidad;

  private LocalDate fechaDonacion;

  private Colaborador colaborador;

  private Heladera heladera;

  private Integer calorias;

  private Integer peso;

  private boolean entregada;

}