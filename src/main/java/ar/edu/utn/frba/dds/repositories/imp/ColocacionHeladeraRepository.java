package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.repositories.IColocacionHeladeraRepository;

import java.util.List;
import java.util.Optional;

public class ColocacionHeladeraRepository implements IColocacionHeladeraRepository {
    @Override
    public Optional<ColocacionHeladeraRepository> buscar(long id) {
        return Optional.empty();
    }

    @Override
    public List<ColocacionHeladeraRepository> buscarTodos() {
        return List.of();
    }

    @Override
    public void guardar(ColocacionHeladeraRepository colocacionHeladera) {
    }

    @Override
    public void actualizar(ColocacionHeladeraRepository colocacionHeladera) {
    }

    @Override
    public void eliminar(ColocacionHeladeraRepository colocacionHeladera) {
    }
}
