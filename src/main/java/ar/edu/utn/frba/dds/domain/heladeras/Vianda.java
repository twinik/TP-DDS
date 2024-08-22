package ar.edu.utn.frba.dds.domain.heladeras;

import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import java.time.LocalDate;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

/**
 * Vianda class permite representar una vianda.
 */
@Entity
@Table(name = "vianda")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vianda {

  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "comida")
  private String comida;

  @Column(name = "fecha_caducidad", columnDefinition = "DATE")
  private LocalDate fechaCaducidad;

  @Column(name = "fecha_donacion", columnDefinition = "DATE")
  private LocalDate fechaDonacion;

  @OneToOne
  @JoinColumn(name = "colaborador_id", referencedColumnName = "id")
  private Colaborador colaborador;

  @OneToOne
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