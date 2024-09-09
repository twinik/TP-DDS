package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.domain.colaboraciones.AltaPersonaVulnerable;
import java.util.List;
import java.util.Optional;

public interface IAltaPersonaVulnerableRepository {
    Optional<AltaPersonaVulnerable> buscar(Long id);

    List<AltaPersonaVulnerable> buscarTodos();

    void guardar(AltaPersonaVulnerable altaPersonaVulnerable);

    void actualizar(AltaPersonaVulnerable altaPersonaVulnerable);

    void eliminar(AltaPersonaVulnerable altaPersonaVulnerable);
}
