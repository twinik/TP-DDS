package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.heladeras.Vianda;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IViandasRepository {
  Optional<Vianda> buscar(Long id);

  List<Vianda> buscarTodos();

  Map<String, Long> buscarViandasAgrupadasPorColaborador(LocalDate fecha);

  void guardar(Vianda vianda);

  void actualizar(Vianda vianda);

  void eliminar(Vianda vianda);
}
