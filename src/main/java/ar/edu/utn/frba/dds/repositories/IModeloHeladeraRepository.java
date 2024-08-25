package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.domain.heladeras.ModeloHeladera;
import ar.edu.utn.frba.dds.domain.utils.MedioDeContacto;
import java.util.List;
import java.util.Optional;

public interface IModeloHeladeraRepository {
  Optional<ModeloHeladera> buscar(Long id);

  List<ModeloHeladera> buscarTodos();

  void guardar(ModeloHeladera modeloHeladera);

  void actualizar(ModeloHeladera modeloHeladera);

  void eliminar(ModeloHeladera modeloHeladera);
}
