package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.colaboraciones.OfertaProducto;
import ar.edu.utn.frba.dds.domain.colaboradores.form.Opcion;
import ar.edu.utn.frba.dds.repositories.IOfertaProductoRepository;
import ar.edu.utn.frba.dds.repositories.IOpcionRepository;
import java.util.List;
import java.util.Optional;

public class OpcionRepository implements IOpcionRepository {
  @Override
  public Optional<Opcion> buscar(Long id) {
    return Optional.empty();
  }

  @Override
  public List<Opcion> buscarTodos() {
    return List.of();
  }

  @Override
  public void guardar(Opcion opcion) {
  }

  @Override
  public void actualizar(Opcion opcion) {
  }

  @Override
  public void eliminar(Opcion opcion) {
  }
}
