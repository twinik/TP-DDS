package ar.edu.utn.frba.dds.domain.colaboradores;

import ar.edu.utn.frba.dds.domain.utils.TipoDocumento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Usuario class permite representar un usuario.
 */
@Getter
@Setter
@AllArgsConstructor
public class Usuario {

  private String email;

  private TipoDocumento tipoDocumento;

  private Integer documento;

  private String clave;

  public static Usuario create(String email, TipoDocumento tipoDocumento, Integer documento, String contrasenia) {
    return new Usuario(email, tipoDocumento, documento, contrasenia);
  }

}