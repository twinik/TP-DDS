package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.heladeras.ModeloHeladera;
import ar.edu.utn.frba.dds.domain.utils.MedioDeContacto;
import ar.edu.utn.frba.dds.repositories.IMedioContactoRepository;
import ar.edu.utn.frba.dds.repositories.IModeloHeladeraRepository;
import java.util.List;
import java.util.Optional;

public class ModeloHeladeraRepository implements IModeloHeladeraRepository {
  @Override
  public Optional<ModeloHeladera> buscar(Long id) {
    return Optional.empty();
  }

  @Override
  public List<ModeloHeladera> buscarTodos() {
    return List.of();
  }

  @Override
  public void guardar(ModeloHeladera modeloHeladera) {
  }

  @Override
  public void actualizar(ModeloHeladera modeloHeladera) {
  }

  @Override
  public void eliminar(ModeloHeladera modeloHeladera) {
  }
}
