package ar.edu.utn.frba.dds.domain.colaboradores.factories;

import ar.edu.utn.frba.dds.domain.colaboradores.Usuario;
import ar.edu.utn.frba.dds.domain.utils.TipoDocumento;

/**
 * UsuarioFactory class se encarga de crear usuarios.
 */

public class UsuarioFactory {
  public static Usuario createUsuario(String mail, TipoDocumento tipoDoc, Integer documento, String clave) {
    return new Usuario(mail, tipoDoc, documento, clave);
  }
}