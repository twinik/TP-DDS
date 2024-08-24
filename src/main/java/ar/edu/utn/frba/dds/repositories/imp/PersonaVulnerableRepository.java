package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.PersonaVulnerable;
import ar.edu.utn.frba.dds.repositories.IPersonaVulnerableRepository;
import java.util.List;
import java.util.Optional;

public class PersonaVulnerableRepository implements IPersonaVulnerableRepository {
  @Override
  public Optional<PersonaVulnerable> buscar(Long id) {
    return Optional.empty();
  }

  @Override
  public List<PersonaVulnerable> buscarTodos() {
    return null;
  }

  @Override
  public void guardar(PersonaVulnerable persona) {

  }

  @Override
  public void actualizar(PersonaVulnerable persona) {

  }

  @Override
  public void eliminar(PersonaVulnerable persona) {

  }
}
