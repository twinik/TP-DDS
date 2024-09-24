package ar.edu.utn.frba.dds.models.repositories;

import ar.edu.utn.frba.dds.models.domain.colaboraciones.utils.MotivoRedistribucionVianda;
import java.util.List;
import java.util.Optional;

public interface IMotivoRedistribucionRepository {
    Optional<MotivoRedistribucionVianda> buscar(String id);

    List<MotivoRedistribucionVianda> buscarTodos();

    void guardar(MotivoRedistribucionVianda motivo);

    void actualizar(MotivoRedistribucionVianda motivo);

    void eliminar(MotivoRedistribucionVianda motivo);
}
