package ar.edu.utn.frba.dds.models.repositories;

import ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion.Rol;
import java.util.List;
import java.util.Optional;

/**
 * IVisitasTecnicoRepository interface permite interactuar con las visitas de un tecnico.
 */
public interface IRolesRepository {
    Optional<Rol> buscar(String id);

    List<Rol> buscarTodos();

    void guardar(Rol rol);

    void actualizar(Rol rol);

    void eliminar(Rol rol);
}
