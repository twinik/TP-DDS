package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.domain.colaboraciones.DonacionVianda;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.incidentes.Alerta;
import ar.edu.utn.frba.dds.domain.incidentes.FallaTecnica;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * IAlertasRepository interface permite interactuar con las alertas.
 */
public interface IFallasTecnicasRepository {
  Optional<FallaTecnica> buscar(Long id);

  List<FallaTecnica> buscarTodos();

  List<FallaTecnica> buscarPorHeladera(Long heladera_id);

  Map<String, Long> buscarFallasAgrupadasPorHeladera(LocalDate fecha);

  void guardar(FallaTecnica fallaTecnica);

  void actualizar(FallaTecnica fallaTecnica);

  void eliminar(FallaTecnica fallaTecnica);
}
