package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.domain.suscripciones.Suscripcion;
import java.util.List;
import java.util.Optional;

/**
 * ISuscripcionesRepository interface permite interactuar con las suscripciones.
 */
public interface ISuscripcionesRepository {

    Optional<Suscripcion> buscar(String id);

    List<Suscripcion> buscarTodos();

    List<Suscripcion> buscarTodosPorColaborador(String colaborador_id);

    void guardar(Suscripcion suscripcion);

    void actualizar(Suscripcion suscripcion);

    void eliminar(Suscripcion suscripcion);
}
