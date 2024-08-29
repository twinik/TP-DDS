package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.domain.suscripciones.Suscripcion;
import java.util.List;
import java.util.Optional;

/**
 * ISuscripcionesRepository interface permite interactuar con las suscripciones.
 */
public interface ISuscripcionesRepository {

    Optional<Suscripcion> buscar(Long id);

    List<Suscripcion> buscarTodos();

    void guardar(Suscripcion suscripcion);

    void actualizar(Suscripcion suscripcion);

    void eliminar(Suscripcion suscripcion);
}
