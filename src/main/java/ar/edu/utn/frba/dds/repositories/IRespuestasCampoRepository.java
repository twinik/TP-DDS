package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.domain.colaboradores.form.RespuestaACampo;
import java.util.List;
import java.util.Optional;

/**
 * IRespuestasCampoRepository interface permite interactuar con las Respuestas de un Campo.
 */
public interface IRespuestasCampoRepository {
    Optional<RespuestaACampo> buscar(String id);

    List<RespuestaACampo> buscarTodos();

    void guardar(RespuestaACampo respuestaACampo);

    void actualizar(RespuestaACampo respuestaACampo);

    void eliminar(RespuestaACampo respuestaACampo);
}
