package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.CalculadorPuntosOfertaProducto;
import ar.edu.utn.frba.dds.domain.colaboraciones.utils.CategoriaOferta;
import ar.edu.utn.frba.dds.domain.colaboraciones.utils.Producto;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.colaboradores.TipoPersona;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * OfertaProducto class representa una colaboracion de un colaborador.
 * Consiste en ofrecer un producto a cambio de puntos.
 */
@Getter
@AllArgsConstructor
public class OfertaProducto {
  private Colaborador colaborador;
  private LocalDate fechaCreacion;
  private Producto producto;
  private Float puntosNecesarios;
  private CategoriaOferta categoria;
  
  /**
   * Metodo puedeSerCanjeadoPor que se encarga de verificar.
   * si el colaborador puede canjear el producto.
   */
  public boolean puedeSerCanjeadoPor(Colaborador colaborador) {
    if (colaborador.getTipoColaborador().getTipo().equals(TipoPersona.PERSONA_JURIDICA)) {
      Float sumaMesesActivas = (float) colaborador.getHeladerasColocadas().stream().mapToDouble(ColocacionHeladeras::getMesesActiva).sum();
      Float puntosPorHeladerasColocadas = colaborador.getHeladerasColocadas().size() * sumaMesesActivas * 5;

      if (colaborador.getPuntosGanados() + puntosPorHeladerasColocadas >= this.puntosNecesarios) {
        colaborador.sumarPuntos(puntosPorHeladerasColocadas);
      }
    }
    return colaborador.getPuntosGanados() >= this.puntosNecesarios;
  }

}