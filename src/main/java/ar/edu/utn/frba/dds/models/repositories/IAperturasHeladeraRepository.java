package ar.edu.utn.frba.dds.models.repositories;

import ar.edu.utn.frba.dds.models.domain.heladeras.AperturaHeladera;
import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;
import java.util.List;
import java.util.Optional;

public interface IAperturasHeladeraRepository {
    Optional<AperturaHeladera> buscar(String id);

    List<AperturaHeladera> buscarTodos();

    List<AperturaHeladera> buscarPorHeladera(Heladera Heladera);

    void guardar(AperturaHeladera aperturaHeladera);

    void actualizar(AperturaHeladera aperturaHeladera);

    void eliminar(AperturaHeladera aperturaHeladera);
}
