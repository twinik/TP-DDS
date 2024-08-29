package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.db.EntidadPersistente;
import ar.edu.utn.frba.dds.domain.colaboraciones.utils.MotivoRedistribucionVianda;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * RedistribucionViandas class representa una colaboracion de un colaborador.
 * Representa el movimiento de una vianda entre heladeras.
 */

@Entity
@Table(name = "redistribucion_viandas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RedistribucionViandas extends EntidadPersistente implements IPuntajeCalculable {
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "colaborador_id", referencedColumnName = "id", nullable = false)
  private Colaborador colaborador;

  @Column(name = "fecha_redistribucion", columnDefinition = "DATE",nullable = false)
  private LocalDate fecha;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn (name = "heladera_origen", referencedColumnName = "id", nullable = false)
  private Heladera heladeraOrigen;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn (name = "heladera_destino", referencedColumnName = "id", nullable = false)
  private Heladera heladeraDestino;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "motivo_id",referencedColumnName = "id")
  private MotivoRedistribucionVianda motivo;

  @Column(name = "cantidad")
  private Integer cantidad;

  @Override
  public Float calcularPuntaje() {
    return (float) this.cantidad;
  }
}