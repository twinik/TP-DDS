package ar.edu.utn.frba.dds.models.domain.colaboradores.factories;

import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Usuario;

/**
 * ColaboradorFactory class se encarga de crear colaboradores.
 */

public class ColaboradorFactory {

    /**
     * Metodo createColaborador que se encarga de crear un colaborador.
     */
    public static Colaborador createColaborador(Usuario usuario) {
        Colaborador colaborador = new Colaborador();
        colaborador.setUsuario(usuario);
        return colaborador;
    }
}