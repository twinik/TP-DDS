package ar.edu.utn.frba.dds.models.repositories;

import ar.edu.utn.frba.dds.models.domain.heladeras.SensorMovimiento;
import java.util.List;
import java.util.Optional;

public interface ISensorMovimientoRepository {
    Optional<SensorMovimiento> buscar(String id);

    List<SensorMovimiento> buscarTodos();

    void guardar(SensorMovimiento sensorMovimiento);

    void actualizar(SensorMovimiento sensorMovimiento);

    void eliminar(SensorMovimiento sensorMovimiento);
}
