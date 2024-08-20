package ar.edu.utn.frba.dds.domain.colaboraciones;
import ar.edu.utn.frba.dds.domain.colaboraciones.utils.CategoriaOferta;
import ar.edu.utn.frba.dds.domain.colaboraciones.utils.Producto;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * OfertaProducto class representa una colaboracion de un colaborador.
 * Consiste en ofrecer un producto a cambio de puntos.
 */
@Getter
@AllArgsConstructor
@Setter
public class OfertaProducto {
  private Long id;
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
    return colaborador.getPuntosGanados() >= this.puntosNecesarios;
  }

}