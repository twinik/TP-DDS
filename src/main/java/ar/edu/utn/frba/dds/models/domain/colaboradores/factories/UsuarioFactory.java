package ar.edu.utn.frba.dds.models.domain.colaboradores.factories;

import ar.edu.utn.frba.dds.models.domain.colaboradores.Usuario;

/**
 * UsuarioFactory class se encarga de crear usuarios.
 */

public class UsuarioFactory {
    public static Usuario createUsuario(String mail, String clave) {
        return new Usuario(mail, clave);
    }
}