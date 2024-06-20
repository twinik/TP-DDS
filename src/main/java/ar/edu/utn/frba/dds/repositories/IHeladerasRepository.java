package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import java.util.List;
import java.util.Optional;

public interface IHeladerasRepository {
  Optional<Heladera> buscar(int id);

  List<Heladera> buscarTodos();

  void guardar(Heladera heladera);

  void actualizar(Heladera heladera);

  void eliminar(Heladera heladera);

  List<Heladera> heladerasCercanas(Heladera heladera);
}
