package ar.edu.utn.frba.dds.domain.colaboraciones.utils;

import ar.edu.utn.frba.dds.domain.colaboraciones.OfertaProducto;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * CanjeProducto class representa una colaboracion de un colaborador.
 * Consiste en canjear un producto.
 */
@Entity
@Table(name = "canje_producto")
@Getter
@NoArgsConstructor
public class CanjeProducto {
  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne
  @JoinColumn(name = "colaborador_id")
  private Colaborador comprador;

  @ManyToOne
  @JoinColumn(name = "oferta_producto_id")
  private OfertaProducto ofertaCanjeada;

  @Column(name = "fecha_canje", columnDefinition = "DATETIME")
  private LocalDateTime fechaCanje;

  @Column(name = "puntos_gastados")
  private float puntosGastados;

  /**
   * Constructor con parametros.
   */
  public CanjeProducto(Colaborador comprador,
                       OfertaProducto ofertaCanjeada,
                       LocalDateTime fechaCanje, float puntosGastados) {
    this.comprador = comprador;
    this.ofertaCanjeada = ofertaCanjeada;
    this.fechaCanje = fechaCanje;
    this.puntosGastados = puntosGastados;
  }
}
