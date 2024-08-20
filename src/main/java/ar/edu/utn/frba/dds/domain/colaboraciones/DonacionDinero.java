package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.colaboraciones.utils.FrecuenciaDonacion;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import java.time.LocalDate;
import ar.edu.utn.frba.dds.domain.colaboradores.FormaColaboracion;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * DonacionDinero class representa una colaboracion de un colaborador.
 * Consiste en donar dinero.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DonacionDinero implements IPuntajeCalculable {
  private static final Float COEFICIENTE_DONACION_DINERO = .5f;
  private Long id;
  private Colaborador colaborador;
  private LocalDate fecha;
  private Float monto;
  private FrecuenciaDonacion frecuencia;

  @Override
  public Float calcularPuntaje() {
    return COEFICIENTE_DONACION_DINERO * this.monto;
  }
}