package ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion;

import ar.edu.utn.frba.dds.models.db.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Usuario class permite representar un usuario.
 */
@Entity
@Table(name = "usuario")
@Getter
@Setter
public class Usuario extends EntidadPersistente {
  @Column(name = "email", unique = true)
  private String email;

  @Column(name = "clave")
  private String clave;

  @Transient
  private List<Rol> roles = new ArrayList<>();

  public Usuario(String email, String clave) {
    this.email = email;
    this.clave = clave;
  }

  public Usuario() {

  }

  public void agregarRoles(Rol... roles) {
    this.roles.addAll(Arrays.asList(roles));
  }

  public boolean tenesPermiso(Permiso permiso){
    return this.roles.stream().anyMatch(r -> r.tenesPermiso(permiso));
  }

}