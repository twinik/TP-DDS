package ar.edu.utn.frba.dds.models.repositories;

import ar.edu.utn.frba.dds.models.domain.incidentes.Incidente;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;

public class IncidentesRepository implements IIncidentesRepository, WithSimplePersistenceUnit {

  @Override
  public Optional<Incidente> buscar(String id) {
    return Optional.ofNullable(entityManager().find(Incidente.class, id));
  }

  @Override
  public List<Incidente> buscarTodos() {
    return entityManager().createQuery("from Incidente where solucionado=:solucionado and activo=:activo order by timestamp",Incidente.class)
        .setParameter("solucionado",false)
        .setParameter("activo",true)
        .getResultList();
  }
}
