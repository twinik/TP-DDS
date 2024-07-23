package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.domain.colaboraciones.DonacionVianda;
import java.util.List;
import java.util.Optional;

/**
 * IColaboradorRepository interface permite interactuar con los donacions de viandas
 */
public interface IDonacionesViandaRepository {
  Optional<DonacionVianda> buscar(long id);

  List<DonacionVianda> buscarTodos();

  void guardar(DonacionVianda donacionVianda);

  void actualizar(DonacionVianda donacionVianda);

  void eliminar(DonacionVianda donacionVianda);
}
