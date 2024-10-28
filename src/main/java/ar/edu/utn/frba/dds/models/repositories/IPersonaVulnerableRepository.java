package ar.edu.utn.frba.dds.models.repositories;

import ar.edu.utn.frba.dds.models.domain.PersonaVulnerable;
import ar.edu.utn.frba.dds.models.domain.utils.TipoDocumento;
import java.util.List;
import java.util.Optional;

/**
 * IPersonaVulenerableRepository interface permite interactuar con las alertas.
 */
public interface IPersonaVulnerableRepository {
  Optional<PersonaVulnerable> buscar(String id);

  Optional<PersonaVulnerable> buscarPorDni(TipoDocumento tipoDocumento, String documento);

  List<PersonaVulnerable> buscarTodos();

  void guardar(PersonaVulnerable persona);

  void actualizar(PersonaVulnerable persona);

  void eliminar(PersonaVulnerable persona);
}
