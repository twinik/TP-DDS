package ar.edu.utn.frba.dds.models.repositories;

import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;
import java.util.List;
import java.util.Optional;

public interface IHeladerasRepository {
  Optional<Heladera> buscar(String id);

  void refresh(List<Heladera> heladeras);

  void refresh(Heladera heladera);

  List<Heladera> buscarTodos();

  Heladera buscarPorNombre(String nombre);

  List<Heladera> buscarPorColaborador(String idColaborador);

  void guardar(Heladera heladera);

  void actualizar(Heladera heladera);

  void actualizar(List<Heladera> heladeras);

  void eliminar(Heladera heladera);

}
