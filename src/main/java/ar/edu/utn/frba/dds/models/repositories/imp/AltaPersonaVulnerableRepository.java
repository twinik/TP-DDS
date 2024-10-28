package ar.edu.utn.frba.dds.models.repositories.imp;

import ar.edu.utn.frba.dds.models.domain.colaboraciones.AltaPersonaVulnerable;
import ar.edu.utn.frba.dds.models.repositories.IAltaPersonaVulnerableRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;

public class AltaPersonaVulnerableRepository implements IAltaPersonaVulnerableRepository, WithSimplePersistenceUnit {
  @Override
  public Optional<AltaPersonaVulnerable> buscar(String id) {
    return Optional.ofNullable(entityManager().find(AltaPersonaVulnerable.class, id));
  }

  @Override
  public List<AltaPersonaVulnerable> buscarTodos() {
    return entityManager().createQuery("from AltaPersonaVulnerable where activo=:activo", AltaPersonaVulnerable.class).
        setParameter("activo", true)
        .getResultList();
  }

  @Override
  public void guardar(AltaPersonaVulnerable altaPersonaVulnerable) {
    withTransaction(() -> entityManager().persist(altaPersonaVulnerable));
  }

  @Override
  public void actualizar(AltaPersonaVulnerable altaPersonaVulnerable) {
    withTransaction(() -> entityManager().merge(altaPersonaVulnerable));
  }

  @Override
  public void eliminar(AltaPersonaVulnerable altaPersonaVulnerable) {
    withTransaction(() -> {
      altaPersonaVulnerable.borrarLogico();
      entityManager().merge(altaPersonaVulnerable);
    });
  }

}
