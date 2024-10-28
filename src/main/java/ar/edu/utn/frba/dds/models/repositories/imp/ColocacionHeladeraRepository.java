package ar.edu.utn.frba.dds.models.repositories.imp;

import ar.edu.utn.frba.dds.models.domain.colaboraciones.ColocacionHeladeras;
import ar.edu.utn.frba.dds.models.repositories.IColocacionHeladeraRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;

public class ColocacionHeladeraRepository implements IColocacionHeladeraRepository, WithSimplePersistenceUnit {
  @Override
  public Optional<ColocacionHeladeras> buscar(String id) {
    return Optional.ofNullable(entityManager().find(ColocacionHeladeras.class, id));
  }

  @Override
  public List<ColocacionHeladeras> buscarTodos() {
    return entityManager().createQuery("from ColocacionHeladeras where activo=:activo", ColocacionHeladeras.class).
        setParameter("activo", true)
        .getResultList();
  }

  @Override
  public void guardar(ColocacionHeladeras colocacionHeladera) {
    withTransaction(() -> entityManager().persist(colocacionHeladera));
  }

  @Override
  public void actualizar(ColocacionHeladeras colocacionHeladera) {
    withTransaction(() -> entityManager().merge(colocacionHeladera));
  }

  @Override
  public void eliminar(ColocacionHeladeras colocacionHeladera) {
    withTransaction(() -> {
      colocacionHeladera.borrarLogico();
      entityManager().merge(colocacionHeladera);
    });

  }

}
