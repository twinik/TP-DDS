package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * oferta que hacen las personas juridicas.
 */
@Getter
@AllArgsConstructor
public class OfertaProducto extends Colaboracion{

  /**
   * Default constructor
   */
  public OfertaProducto() {
  }


  public void efectuar() {

  }

  /**
   *
   */

  /**
   *
   */
  private Producto producto;

  /**
   *
   */
  private Integer puntosNecesarios;

  /**
   *
   */
  private CategoriaOferta categoria;

}