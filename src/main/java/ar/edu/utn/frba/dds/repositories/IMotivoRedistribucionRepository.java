package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.domain.colaboraciones.utils.MotivoRedistribucionVianda;
import ar.edu.utn.frba.dds.domain.heladeras.Vianda;
import java.util.List;
import java.util.Optional;

public interface IMotivoRedistribucionRepository {
    Optional<MotivoRedistribucionVianda> buscar(Long id);

    List<MotivoRedistribucionVianda> buscarTodos();

    void guardar(MotivoRedistribucionVianda motivo);

    void actualizar(MotivoRedistribucionVianda motivo);

    void eliminar(MotivoRedistribucionVianda motivo);
}
