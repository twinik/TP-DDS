package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.domain.colaboraciones.DonacionVianda;
import ar.edu.utn.frba.dds.domain.colaboraciones.RedistribucionViandas;
import java.util.List;
import java.util.Optional;

/**
 * IColaboradorRepository interface permite interactuar con las redistribuciones de viandas
 */
public interface IRedistribucionesVIandaRepository {

  Optional<RedistribucionViandas> buscar(int id);

  List<RedistribucionViandas> buscarTodos();

  void guardar(RedistribucionViandas redistribucionViandas);

  void actualizar(RedistribucionViandas redistribucionViandas);

  void eliminar(RedistribucionViandas redistribucionViandas);
}
