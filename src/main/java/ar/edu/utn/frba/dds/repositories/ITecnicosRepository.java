package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.domain.tecnicos.Tecnico;
import java.util.List;
import java.util.Optional;

public interface ITecnicosRepository {
  Optional<Tecnico> buscar(String codigo);
  Optional<Tecnico> buscar(long id);

  List<Tecnico> buscarTodos();

  void guardar(Tecnico tecnico);

  void actualizar(Tecnico tecnico);

  void eliminar(Tecnico tecnico);
}
