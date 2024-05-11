package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * oferta que hacen las personas juridicas.
 */
@Getter
@AllArgsConstructor
public class OfertaProducto extends Colaboracion {

  /**
   * Default constructor
   */
  public OfertaProducto() {
  }

  private Producto producto;


  private Float puntosNecesarios;


  private CategoriaOferta categoria;


  public void efectuar() {
  }

  public boolean puedeSerCanjeadoPor(Colaborador colaborador){
    return colaborador.getPuntosGanados() >= this.puntosNecesarios;
  }

}