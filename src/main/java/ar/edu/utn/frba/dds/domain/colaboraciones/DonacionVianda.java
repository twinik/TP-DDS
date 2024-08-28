package ar.edu.utn.frba.dds.domain.colaboraciones;


import ar.edu.utn.frba.dds.db.EntidadPersistente;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.heladeras.Vianda;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * DonacionVianda class representa una colaboracion de un colaborador.
 * Consiste en donar viandas.
 */
@Entity
@Table(name = "donacion_vianda")
@Getter
@Setter
@NoArgsConstructor
public class DonacionVianda extends EntidadPersistente implements IPuntajeCalculable {
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "colaborador_id", referencedColumnName = "id", nullable = false)
  private Colaborador colaborador;

  @Column(name = "fecha_donacion_dinero", columnDefinition = "DATE", nullable = false)
  private LocalDate fecha;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "vianda_id", referencedColumnName = "id", nullable = false, unique = true)
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