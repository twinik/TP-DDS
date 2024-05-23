package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.CalculadorPuntosOfertaProducto;
import ar.edu.utn.frba.dds.domain.colaboraciones.utils.CategoriaOferta;
import ar.edu.utn.frba.dds.domain.colaboraciones.utils.Producto;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.colaboradores.TipoPersona;
import java.time.LocalDate;
import lombok.Getter;

/**
 * OfertaProducto class representa una colaboracion de un colaborador.
 * Consiste en ofrecer un producto a cambio de puntos.
 */
@Getter
public class OfertaProducto extends Colaboracion {
  private Producto producto;
  private Float puntosNecesarios;
  private CategoriaOferta categoria;

  /**
   * Constructor por defecto con parametros.
   */
  public OfertaProducto(Colaborador colaborador,
                        Producto producto,
                        Float puntosNecesarios,
                        CategoriaOferta categoria,
                        LocalDate fecha) {
    super(colaborador, new CalculadorPuntosOfertaProducto(), fecha);
    this.producto = producto;
    this.puntosNecesarios = puntosNecesarios;
    this.categoria = categoria;
  }

  /**
   * Constructor por defecto con parametros.
   */
  public OfertaProducto(Producto producto, Float puntosNecesarios, CategoriaOferta categoria) {
    this.calculadorDePuntos = new CalculadorPuntosOfertaProducto();
    this.producto = producto;
    this.puntosNecesarios = puntosNecesarios;
    this.categoria = categoria;
  }

  /**
   * Metodo efectuar que se encarga de sumar puntos al colaborador.
   */
  @Override
  public void efectuar() {
    super.efectuar();
  }

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