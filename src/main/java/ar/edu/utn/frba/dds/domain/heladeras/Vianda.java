package ar.edu.utn.frba.dds.domain.heladeras;

import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import java.time.LocalDate;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Vianda class permite representar una vianda.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vianda {
  private Long id;

  private String comida;

  private LocalDate fechaCaducidad;

  private LocalDate fechaDonacion;

  private Colaborador colaborador;

  private Heladera heladera;

  private Integer calorias;

  private Float peso;

  private boolean entregada;
  public Vianda(String comida, LocalDate fechaCaducidad, LocalDate fechaDonacion, Colaborador colaborador, Heladera heladera, Integer calorias, Float peso, boolean entregada) {
    this.comida = comida;
    this.fechaCaducidad = fechaCaducidad;
    this.fechaDonacion = fechaDonacion;
    this.colaborador = colaborador;
    this.heladera = heladera;
    this.calorias = calorias;
    this.peso = peso;
    this.entregada = entregada;
  }


}