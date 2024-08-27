package ar.edu.utn.frba.dds.domain.colaboraciones.utils;

import ar.edu.utn.frba.dds.db.EntidadPersistente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Producto class representa un producto.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "producto")
public class Producto extends EntidadPersistente {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "nombre")
  private String nombre;
  @Column(name = "url_foto",columnDefinition = "TEXT")
  private String urlFoto;

  public Producto(String nombre, String urlFoto) {
    this.nombre = nombre;
    this.urlFoto = urlFoto;
  }
}