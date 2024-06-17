package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.domain.colaboraciones.DonacionVianda;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.utils.TipoDocumento;
import java.util.List;
import java.util.Optional;

/**
 * IColaboradorRepository interface permite interactuar con los donacions de viandas
 */
public interface IDonacionesVIandaRepository {
  Optional<DonacionVianda> buscar(int id);

  List<DonacionVianda> buscarTodos();

  void guardar(DonacionVianda donacionVianda);

  void actualizar(DonacionVianda donacionVianda);

  void eliminar(DonacionVianda donacionVianda);
}
