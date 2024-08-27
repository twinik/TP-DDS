package ar.edu.utn.frba.dds.domain.colaboradores;

import ar.edu.utn.frba.dds.db.EntidadPersistente;
import ar.edu.utn.frba.dds.domain.utils.TipoDocumento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Usuario class permite representar un usuario.
 */
@Entity
@Table(name = "usuario")
@Getter
@Setter
@AllArgsConstructor
public class Usuario extends EntidadPersistente {
  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "email")
  private String email;

  @Column(name = "clave")
  private String clave;

  public Usuario(String email, String clave) {
    this.email = email;
    this.clave = clave;
  }

  public Usuario() {

  }
}