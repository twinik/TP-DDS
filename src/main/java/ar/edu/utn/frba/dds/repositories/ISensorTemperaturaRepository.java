package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.domain.heladeras.SensorTemperatura;
import java.util.List;
import java.util.Optional;

public interface ISensorTemperaturaRepository {
    Optional<SensorTemperatura> buscar(int id);

    List<SensorTemperatura> buscarTodos();

    void guardar(SensorTemperatura redistribucionViandas);

    void actualizar(SensorTemperatura redistribucionViandas);

    void eliminar(SensorTemperatura redistribucionViandas);
}