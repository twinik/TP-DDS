package ar.edu.utn.frba.dds.models.repositories;

import ar.edu.utn.frba.dds.models.domain.colaboradores.form.Opcion;
import java.util.List;
import java.util.Optional;

public interface IOpcionRepository {
    Optional<Opcion> buscar(String id);

    List<Opcion> buscarTodos();

    void guardar(Opcion opcion);

    void actualizar(Opcion opcion);

    void eliminar(Opcion opcion);
}
