package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.heladeras.SensorMovimiento;
import ar.edu.utn.frba.dds.repositories.ISensorMovimientoRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SensoresMovimientoRepository implements ISensorMovimientoRepository {

  private List<SensorMovimiento> sensorMovimientos;

  public SensoresMovimientoRepository() {
    this.sensorMovimientos = new ArrayList<>();
  }

  @Override
  public Optional<SensorMovimiento> buscar(int id) {
    return Optional.empty();
  }

  @Override
  public List<SensorMovimiento> buscarTodos() {
    return this.sensorMovimientos;
  }

  @Override
  public void guardar(SensorMovimiento sensorMovimiento) {
    this.sensorMovimientos.add(sensorMovimiento);
  }

  @Override
  public void actualizar(SensorMovimiento sensorMovimiento) {

  }

  @Override
  public void eliminar(SensorMovimiento sensorMovimiento) {

  }
}
