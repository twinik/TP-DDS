package ar.edu.utn.frba.dds.models.repositories;

import ar.edu.utn.frba.dds.models.domain.incidentes.Alerta;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * IAlertasRepository interface permite interactuar con las alertas.
 */
public interface IAlertasRepository {
  Optional<Alerta> buscar(String id);

  List<Alerta> buscarTodos();

  List<Alerta> buscarAlertasHeladera(String heladera_id);

  Map<String, Long> buscarAlertasAgrupadasPorHeladera(LocalDate fecha);

  void guardar(Alerta alerta);

  void actualizar(Alerta alerta);

  void eliminar(Alerta alerta);
}
