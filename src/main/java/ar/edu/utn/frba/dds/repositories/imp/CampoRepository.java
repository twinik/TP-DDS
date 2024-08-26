package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.colaboradores.form.Campo;
import ar.edu.utn.frba.dds.repositories.ICampoRepository;

import java.util.List;
import java.util.Optional;

public class CampoRepository implements ICampoRepository {
    @Override
    public Optional<Campo> buscar(long id) {
        return Optional.empty();
    }

    @Override
    public List<Campo> buscarTodos() {
        return List.of();
    }

    @Override
    public void guardar(Campo campo) {
    }

    @Override
    public void actualizar(Campo campo) {
    }

    @Override
    public void eliminar(Campo campo) {
    }
}
