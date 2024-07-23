package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.domain.heladeras.Vianda;
import java.util.List;
import java.util.Optional;

public interface IViandasRepository {
  Optional<Vianda> buscar(long id);

  List<Vianda> buscarTodos();

  void guardar(Vianda vianda);

  void actualizar(Vianda vianda);

  void eliminar(Vianda vianda);
}
