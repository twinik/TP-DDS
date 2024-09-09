package ar.edu.utn.frba.dds.domain.heladeras;

import ar.edu.utn.frba.dds.db.EntidadPersistente;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;

/**
 * Vianda class permite representar una vianda.
 */
@Entity
@Table(name = "vianda")
@Getter
@Setter
@NoArgsConstructor
public class Vianda extends EntidadPersistente {

  @Column(name = "comida")
  private String comida;

  @Column(name = "fecha_caducidad", columnDefinition = "DATE")
  private LocalDate fechaCaducidad;

  @Column(name = "fecha_donacion", columnDefinition = "DATE")
  private LocalDate fechaDonacion;

  @ManyToOne
  @JoinColumn(name = "colaborador_id", referencedColumnName = "id")
  private Colaborador colaborador;

  @ManyToOne
  @JoinColumn(name = "heladera_id", referencedColumnName = "id")
  private Heladera heladera;

  @Column(name = "calorias")
  private Integer calorias;

  @Column(name = "peso")
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