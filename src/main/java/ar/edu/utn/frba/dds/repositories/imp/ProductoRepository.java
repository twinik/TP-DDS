package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.colaboraciones.utils.Producto;
import ar.edu.utn.frba.dds.domain.colaboradores.form.Opcion;
import ar.edu.utn.frba.dds.repositories.IOpcionRepository;
import ar.edu.utn.frba.dds.repositories.IProductoRepository;
import java.util.List;
import java.util.Optional;

public class ProductoRepository implements IProductoRepository {
  @Override
  public Optional<Producto> buscar(Long id) {
    return Optional.empty();
  }

  @Override
  public List<Producto> buscarTodos() {
    return List.of();
  }

  @Override
  public void guardar(Producto producto) {
  }

  @Override
  public void actualizar(Producto producto) {
  }

  @Override
  public void eliminar(Producto producto) {
  }
}
