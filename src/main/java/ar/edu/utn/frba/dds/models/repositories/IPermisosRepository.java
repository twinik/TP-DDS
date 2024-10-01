package ar.edu.utn.frba.dds.models.repositories;

import ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion.Permiso;
import java.util.List;
import java.util.Optional;

/**
 * IVisitasTecnicoRepository interface permite interactuar con las visitas de un tecnico.
 */
public interface IPermisosRepository {
    Optional<Permiso> buscar(String id);

    List<Permiso> buscarTodos();

    void guardar(Permiso permiso);

    void guardar(Permiso ...permisos);

    void actualizar(Permiso permiso);

    void eliminar(Permiso permiso);
}
