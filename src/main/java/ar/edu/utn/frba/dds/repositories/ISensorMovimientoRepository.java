package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.domain.heladeras.SensorMovimiento;
import java.util.List;
import java.util.Optional;

public interface ISensorMovimientoRepository {
    Optional<SensorMovimiento> buscar(Long id);

    List<SensorMovimiento> buscarTodos();

    void guardar(SensorMovimiento sensorMovimiento);

    void actualizar(SensorMovimiento sensorMovimiento);

    void eliminar(SensorMovimiento sensorMovimiento);
}
