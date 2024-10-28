package ar.edu.utn.frba.dds.models.repositories.imp;

import ar.edu.utn.frba.dds.models.domain.colaboraciones.DonacionDinero;
import ar.edu.utn.frba.dds.models.repositories.IDonacionDineroRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;

public class DonacionDineroRepository implements IDonacionDineroRepository, WithSimplePersistenceUnit {
  @Override
  public Optional<DonacionDinero> buscar(String id) {
    return Optional.ofNullable(entityManager().find(DonacionDinero.class, id));
  }

  @Override
  public List<DonacionDinero> buscarTodos() {
    return entityManager().createQuery("from DonacionDinero where activo=:activo", DonacionDinero.class).
        setParameter("activo", true)
        .getResultList();
  }

  @Override
  public void guardar(DonacionDinero donacionDinero) {
    withTransaction(() -> entityManager().persist(donacionDinero));
  }

  @Override
  public void actualizar(DonacionDinero donacionDinero) {
    withTransaction(() -> entityManager().merge(donacionDinero));
  }

  @Override
  public void eliminar(DonacionDinero donacionDinero) {

    withTransaction(() -> {
      donacionDinero.borrarLogico();
      entityManager().merge(donacionDinero);
    });
  }

}
