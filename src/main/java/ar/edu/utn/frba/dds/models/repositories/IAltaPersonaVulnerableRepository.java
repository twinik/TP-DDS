package ar.edu.utn.frba.dds.models.repositories;

import ar.edu.utn.frba.dds.models.domain.colaboraciones.AltaPersonaVulnerable;
import java.util.List;
import java.util.Optional;

public interface IAltaPersonaVulnerableRepository {
    Optional<AltaPersonaVulnerable> buscar(String id);

    List<AltaPersonaVulnerable> buscarTodos();

    void guardar(AltaPersonaVulnerable altaPersonaVulnerable);

    void actualizar(AltaPersonaVulnerable altaPersonaVulnerable);

    void eliminar(AltaPersonaVulnerable altaPersonaVulnerable);
}
