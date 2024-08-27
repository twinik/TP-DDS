package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.db.EntidadPersistente;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.helpers.DateHelper;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * ColocacionHeladeras class representa una colaboracion de un colaborador.
 * Consiste en colocar una heladera en un establecimiento.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "colocacion_heladera")
public class ColocacionHeladeras extends EntidadPersistente implements IPuntajeCalculable {
  public static final Float PUNTOS_POR_MES = 5.0f;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "colaoborador_id",referencedColumnName = "id",nullable = false)
  private Colaborador colaborador;
  @Column(name = "fecha",nullable = false)
  private LocalDate fecha;
  @OneToOne
  @JoinColumn(name = "heladera_id",referencedColumnName = "id",nullable = false)
  private Heladera heladera;

  public ColocacionHeladeras(Colaborador colaborador, LocalDate fecha, Heladera heladera) {
    this.colaborador = colaborador;
    this.fecha = fecha;
    this.heladera = heladera;
  }

  /**
   * Metodo getMesesActiva que devuelve la cantidad de meses que la heladera lleva activa.
   */
  public Integer getMesesActiva() {
    return DateHelper.mesesEntre(this.heladera.getFechaPuestaFuncionamiento(), LocalDate.now());
  }

  @Override
  public Float calcularPuntaje() {
    return PUNTOS_POR_MES * this.getMesesActiva();
  }
}
