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

  private String clave;

}