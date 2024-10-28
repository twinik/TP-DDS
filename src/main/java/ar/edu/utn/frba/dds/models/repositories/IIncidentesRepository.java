package ar.edu.utn.frba.dds.models.repositories;

import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.models.domain.incidentes.Incidente;
import java.util.List;
import java.util.Optional;

public interface IIncidentesRepository {

    Optional<Incidente> buscar(String id);

    List<Incidente> buscarTodos();

    List<Incidente> buscarPorHeladera(String id);

    Long cantidadNoSolucionadosPorHeladera(Heladera h);

    void actualizar(Incidente incidente);
}
