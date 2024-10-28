package ar.edu.utn.frba.dds.models.repositories.imp;

import ar.edu.utn.frba.dds.models.domain.heladeras.SensorTemperatura;
import ar.edu.utn.frba.dds.models.repositories.ISensorTemperaturaRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;

public class SensoresTemperaturaRepository implements ISensorTemperaturaRepository, WithSimplePersistenceUnit {

  @Override
  public Optional<SensorTemperatura> buscar(String id) {
    return Optional.ofNullable(entityManager().find(SensorTemperatura.class, id));
  }

  @Override
  public List<SensorTemperatura> buscarTodos() {
    return entityManager().createQuery("from SensorTemperatura where activo=:activo", SensorTemperatura.class).
        setParameter("activo", true)
        .getResultList();
  }

  @Override
  public void guardar(SensorTemperatura sensorTemperatura) {
    withTransaction(() -> entityManager().persist(sensorTemperatura));
  }


  @Override
  public void actualizar(SensorTemperatura sensorTemperatura) {
    withTransaction(() -> entityManager().merge(sensorTemperatura));
  }

  @Override
  public void eliminar(SensorTemperatura sensorTemperatura) {

    withTransaction(() -> {
      sensorTemperatura.borrarLogico();
      entityManager().merge(sensorTemperatura);
    });
  }

}
