package ar.edu.utn.frba.dds.models.repositories.imp;

import ar.edu.utn.frba.dds.models.domain.heladeras.SensorMovimiento;
import ar.edu.utn.frba.dds.models.repositories.ISensorMovimientoRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;

public class SensoresMovimientoRepository implements ISensorMovimientoRepository, WithSimplePersistenceUnit {

  @Override
  public Optional<SensorMovimiento> buscar(String id) {
    return Optional.ofNullable(entityManager().find(SensorMovimiento.class, id));
  }

  @Override
  public List<SensorMovimiento> buscarTodos() {
    return entityManager().createQuery("from SensorMovimiento where activo=:activo", SensorMovimiento.class).
        setParameter("activo", true)
        .getResultList();
  }

  @Override
  public void guardar(SensorMovimiento sensorMovimiento) {
    withTransaction(() -> entityManager().persist(sensorMovimiento));
  }

  @Override
  public void actualizar(SensorMovimiento sensorMovimiento) {
    withTransaction(() -> entityManager().merge(sensorMovimiento));
  }

  @Override
  public void eliminar(SensorMovimiento sensorMovimiento) {

    withTransaction(() -> {
      sensorMovimiento.borrarLogico();
      entityManager().merge(sensorMovimiento);
    });
  }

}
