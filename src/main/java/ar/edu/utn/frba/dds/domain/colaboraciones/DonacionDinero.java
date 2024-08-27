package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.db.EntidadPersistente;
import ar.edu.utn.frba.dds.domain.colaboraciones.utils.FrecuenciaDonacion;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import java.time.LocalDate;
import ar.edu.utn.frba.dds.domain.colaboradores.FormaColaboracion;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * DonacionDinero class representa una colaboracion de un colaborador.
 * Consiste en donar dinero.
 */
@Entity
@Table(name = "donacion_dinero")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DonacionDinero extends EntidadPersistente implements IPuntajeCalculable {
  private static final Float COEFICIENTE_DONACION_DINERO = .5f;

  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne
  @JoinColumn(name = "colaborador_id", referencedColumnName = "id", nullable = false)
  private Colaborador colaborador;

  @Column(name = "fecha_donacion_dinero", columnDefinition = "DATE",nullable = false)
  private LocalDate fecha;

  @Column(name = "monto_donacion",nullable = false)
  private Float monto;

  @Enumerated(EnumType.STRING)
  private FrecuenciaDonacion frecuencia;

  @Override
  public Float calcularPuntaje() {
    return COEFICIENTE_DONACION_DINERO * this.monto;
  }
}