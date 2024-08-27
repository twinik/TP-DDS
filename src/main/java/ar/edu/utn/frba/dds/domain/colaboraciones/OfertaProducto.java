package ar.edu.utn.frba.dds.domain.colaboraciones;
import ar.edu.utn.frba.dds.db.EntidadPersistente;
import ar.edu.utn.frba.dds.domain.colaboraciones.utils.CategoriaOferta;
import ar.edu.utn.frba.dds.domain.colaboraciones.utils.Producto;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import java.time.LocalDate;
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
 * OfertaProducto class representa una colaboracion de un colaborador.
 * Consiste en ofrecer un producto a cambio de puntos.
 */
@Entity
@Table(name = "oferta_producto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OfertaProducto extends EntidadPersistente {
  @ManyToOne
  @JoinColumn(name = "colaborador_id", referencedColumnName = "id", nullable = false)
  private Colaborador colaborador;

  @Column(name = "fecha_creacion", columnDefinition = "DATE",nullable = false)
  private LocalDate fechaCreacion;

  @OneToOne
  @JoinColumn (name = "producto_id", referencedColumnName = "id", nullable = false)
  private Producto producto;

  @Column(name = "puntos_necesarios",  nullable = false)
  private Float puntosNecesarios;

  @Enumerated(EnumType.STRING)
  private CategoriaOferta categoria;
  
  /**
   * Metodo puedeSerCanjeadoPor que se encarga de verificar.
   * si el colaborador puede canjear el producto.
   */
  public boolean puedeSerCanjeadoPor(Colaborador colaborador) {
    return colaborador.getPuntosGanados() >= this.puntosNecesarios;
  }

}