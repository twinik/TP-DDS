package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.domain.tecnicos.AreaDeCobertura;
import ar.edu.utn.frba.dds.domain.utils.Direccion;

import java.util.List;
import java.util.Optional;

public interface IDireccionRepository {
    Optional<Direccion> buscar(long id);

    List<Direccion> buscarTodos();

    void guardar(Direccion direccion);

    void actualizar(Direccion direccion);

    void eliminar(Direccion direccion);
}
