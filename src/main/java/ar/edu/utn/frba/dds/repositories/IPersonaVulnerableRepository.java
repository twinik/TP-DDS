package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.domain.PersonaVulnerable;
import java.util.List;
import java.util.Optional;

/**
 * IPersonaVulenerableRepository interface permite interactuar con las alertas.
 */
public interface IPersonaVulnerableRepository {
  Optional<PersonaVulnerable> buscar(Long id);

  List<PersonaVulnerable> buscarTodos();

  void guardar(PersonaVulnerable persona);

  void actualizar(PersonaVulnerable persona);

  void eliminar(PersonaVulnerable persona);
}
