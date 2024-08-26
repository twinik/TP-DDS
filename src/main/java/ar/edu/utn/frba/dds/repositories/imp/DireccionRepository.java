package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.tecnicos.AreaDeCobertura;
import ar.edu.utn.frba.dds.domain.utils.Direccion;
import ar.edu.utn.frba.dds.repositories.IDireccionRepository;

import java.util.List;
import java.util.Optional;

public class DireccionRepository implements IDireccionRepository {
    @Override
    public Optional<Direccion> buscar(long id) {
        return Optional.empty();
    }

    @Override
    public List<Direccion> buscarTodos() {
        return List.of();
    }

    @Override
    public void guardar(Direccion direccion) {
    }

    @Override
    public void actualizar(Direccion direccion) {
    }

    @Override
    public void eliminar(Direccion direccion) {
    }
}
