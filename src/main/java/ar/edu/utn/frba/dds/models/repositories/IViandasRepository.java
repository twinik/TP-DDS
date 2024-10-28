package ar.edu.utn.frba.dds.models.repositories;

import ar.edu.utn.frba.dds.models.domain.heladeras.Vianda;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IViandasRepository {
  Optional<Vianda> buscar(String id);

  List<Vianda> buscarTodos();

  Map<String, Long> buscarViandasAgrupadasPorColaborador(LocalDate fecha);

  void guardar(Vianda vianda);

  void guardar(List<Vianda> viandas);

  void actualizar(Vianda vianda);

  void eliminar(Vianda vianda);
}
