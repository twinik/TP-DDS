package ar.edu.utn.frba.dds.models.domain.colaboraciones;


import ar.edu.utn.frba.dds.models.db.EntidadPersistente;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.domain.heladeras.Vianda;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
  private static final Float PUNTAJE_POR_DONACION = 1.5f;
  @ManyToOne
  @JoinColumn(name = "colaborador_id", referencedColumnName = "id", nullable = false)
  private Colaborador colaborador;
  @Column(name = "fecha", columnDefinition = "DATE", nullable = false)
  private LocalDate fecha;
  @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
  @JoinColumn(name = "vianda_id", referencedColumnName = "id", nullable = false, unique = true)
  private Vianda vianda;

  public DonacionVianda(Colaborador colaborador, LocalDate fecha, Vianda vianda) {
    this.colaborador = colaborador;
    this.fecha = fecha;
    this.vianda = vianda;
  }

  @Override
  public Float calcularPuntaje() {
    return PUNTAJE_POR_DONACION;
  }
}