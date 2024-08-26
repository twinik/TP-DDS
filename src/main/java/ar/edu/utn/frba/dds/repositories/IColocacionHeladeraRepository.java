package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.repositories.imp.ColocacionHeladeraRepository;

import java.util.List;
import java.util.Optional;

public interface IColocacionHeladeraRepository {
    Optional<ColocacionHeladeraRepository> buscar(long id);

    List<ColocacionHeladeraRepository> buscarTodos();

    void guardar(ColocacionHeladeraRepository colocacionHeladera);

    void actualizar(ColocacionHeladeraRepository colocacionHeladera);

    void eliminar(ColocacionHeladeraRepository colocacionHeladera);
}
