package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.domain.heladeras.SensorTemperatura;
import java.util.List;
import java.util.Optional;

public interface ISensorTemperaturaRepository {
    Optional<SensorTemperatura> buscar(long id);

    List<SensorTemperatura> buscarTodos();

    void guardar(SensorTemperatura sensorTemperatura);

    void actualizar(SensorTemperatura sensorTemperatura);

    void eliminar(SensorTemperatura sensorTemperatura);
}