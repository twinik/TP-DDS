package ar.edu.utn.frba.dds.models.repositories;

import ar.edu.utn.frba.dds.models.domain.heladeras.ModeloHeladera;
import java.util.List;
import java.util.Optional;

public interface IModeloHeladeraRepository {
  Optional<ModeloHeladera> buscar(String id);

  List<ModeloHeladera> buscarTodos();

  void guardar(ModeloHeladera modeloHeladera);

  void actualizar(ModeloHeladera modeloHeladera);

  void eliminar(ModeloHeladera modeloHeladera);
}
