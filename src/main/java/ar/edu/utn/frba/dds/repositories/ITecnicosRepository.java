package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.domain.tecnicos.Tecnico;
import java.util.List;
import java.util.Optional;

public interface ITecnicosRepository {
  Optional<Tecnico> buscar(String codigo);

  List<Tecnico> buscarTodos();

  void guardar(Tecnico tarjeta);

  void actualizar(Tecnico tarjeta);

  void eliminar(Tecnico tarjeta);
}
