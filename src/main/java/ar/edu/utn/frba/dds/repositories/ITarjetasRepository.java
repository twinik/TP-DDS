package ar.edu.utn.frba.dds.repositories;


import ar.edu.utn.frba.dds.domain.tarjetas.Tarjeta;
import java.util.List;
import java.util.Optional;

/**
 * IColaboradorRepository interface permite interactuar con las tarjetas de las personas vulnerables.
 */
public interface ITarjetasRepository {
  Optional<Tarjeta> buscar(String codigo);

  List<Tarjeta> buscarTodos();

  void guardar(Tarjeta tarjeta);

  void actualizar(Tarjeta tarjeta);

  void eliminar(Tarjeta tarjeta);
}
