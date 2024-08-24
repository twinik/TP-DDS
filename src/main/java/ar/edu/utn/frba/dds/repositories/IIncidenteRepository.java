package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.domain.incidentes.Incidente;
import java.util.List;
import java.util.Optional;

public interface IIncidenteRepository {
  Optional<Incidente> buscar(Long id);

  List<Incidente> buscarTodos();

  void guardar(Incidente incidente);

  void actualizar(Incidente incidente);

  void eliminar(Incidente incidente);
}
