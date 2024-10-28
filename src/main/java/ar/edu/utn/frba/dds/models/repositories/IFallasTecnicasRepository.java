package ar.edu.utn.frba.dds.models.repositories;

import ar.edu.utn.frba.dds.models.domain.incidentes.FallaTecnica;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * IAlertasRepository interface permite interactuar con las alertas.
 */
public interface IFallasTecnicasRepository {
  Optional<FallaTecnica> buscar(String id);

  List<FallaTecnica> buscarTodos();

  List<FallaTecnica> buscarPorHeladera(String heladera_id);

  Map<String, Long> buscarFallasAgrupadasPorHeladera(LocalDate fecha);

  void guardar(FallaTecnica fallaTecnica);

  void actualizar(FallaTecnica fallaTecnica);

  void eliminar(FallaTecnica fallaTecnica);

  void refresh(FallaTecnica fallaTecnica);

  void refresh(List<FallaTecnica> fallaTecnicas);
}
