package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.domain.incidentes.Alerta;
import ar.edu.utn.frba.dds.domain.incidentes.FallaTecnica;
import java.util.List;
import java.util.Optional;

/**
 * IAlertasRepository interface permite interactuar con las alertas.
 */
public interface IFallasTecnicasRepository {
  Optional<FallaTecnica> buscar(long id);

  List<FallaTecnica> buscarTodos();

  void guardar(FallaTecnica fallaTecnica);

  void actualizar(FallaTecnica fallaTecnica);

  void eliminar(FallaTecnica fallaTecnica);
}
