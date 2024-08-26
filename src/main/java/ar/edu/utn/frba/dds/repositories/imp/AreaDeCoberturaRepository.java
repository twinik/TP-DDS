package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.tecnicos.AreaDeCobertura;
import ar.edu.utn.frba.dds.repositories.IAreaDeCoberturaRepository;

import java.util.List;
import java.util.Optional;

public class AreaDeCoberturaRepository implements IAreaDeCoberturaRepository {

    @Override
    public Optional<AreaDeCobertura> buscar(long id) {
        return Optional.empty();
    }

    @Override
    public List<AreaDeCobertura> buscarTodos() {
        return List.of();
    }

    @Override
    public void guardar(AreaDeCobertura areaDeCobertura) {
    }

    @Override
    public void actualizar(AreaDeCobertura areaDeCobertura) {
    }

    @Override
    public void eliminar(AreaDeCobertura areaDeCobertura) {
    }
}
