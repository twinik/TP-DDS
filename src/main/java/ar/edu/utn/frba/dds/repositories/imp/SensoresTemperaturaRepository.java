package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.heladeras.SensorTemperatura;
import ar.edu.utn.frba.dds.repositories.ISensorTemperaturaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SensoresTemperaturaRepository implements ISensorTemperaturaRepository {
  private List<SensorTemperatura> sensorTemperatura;

  public SensoresTemperaturaRepository() {
    this.sensorTemperatura = new ArrayList<>();
  }

  @Override
  public Optional<SensorTemperatura> buscar(long id) {
    return this.sensorTemperatura.stream().filter(s -> s.getId() == id).findFirst();
  }

  @Override
  public List<SensorTemperatura> buscarTodos() {
    return this.sensorTemperatura;
  }

  @Override
  public void guardar(SensorTemperatura sensorTemperatura) {
    this.sensorTemperatura.add(sensorTemperatura);
  }

  @Override
  public void actualizar(SensorTemperatura redistribucionViandas) {

  }

  @Override
  public void eliminar(SensorTemperatura redistribucionViandas) {

  }
}
