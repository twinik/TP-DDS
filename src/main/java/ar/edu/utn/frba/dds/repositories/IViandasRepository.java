package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.domain.heladeras.Vianda;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IViandasRepository {
  Optional<Vianda> buscar(Long id);

  List<Vianda> buscarTodos();

  List<Vianda> buscarTodosMismaSemana(LocalDate fecha);

  void guardar(Vianda vianda);

  void actualizar(Vianda vianda);

  void eliminar(Vianda vianda);
}
