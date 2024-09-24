package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.domain.heladeras.RegistroTemperatura;
import java.util.List;
import java.util.Optional;

/**
 * IRegistrosTemperaturaRepository interface permite interactuar con las Registros de Temperatura.
 */
public interface IRegistrosTemperaturaRepository {
    Optional<RegistroTemperatura> buscar(String id);

    List<RegistroTemperatura> buscarTodos();

    void guardar(RegistroTemperatura registroTemperatura);

    void actualizar(RegistroTemperatura registroTemperatura);

    void eliminar(RegistroTemperatura registroTemperatura);
}
