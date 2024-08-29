package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.tecnicos.AreaDeCobertura;

import java.util.List;
import java.util.Optional;

public interface IAreaDeCoberturaRepository {
    Optional<AreaDeCobertura> buscar(Long id);

    List<AreaDeCobertura> buscarTodos();

    void guardar(AreaDeCobertura areaDeCobertura);

    void actualizar(AreaDeCobertura areaDeCobertura);

    void eliminar(AreaDeCobertura areaDeCobertura);
}
