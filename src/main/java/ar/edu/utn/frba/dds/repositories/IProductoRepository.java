package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.domain.colaboraciones.utils.Producto;
import ar.edu.utn.frba.dds.domain.colaboradores.form.Opcion;
import java.util.List;
import java.util.Optional;

public interface IProductoRepository {
  Optional<Producto> buscar(Long id);

  List<Producto> buscarTodos();

  void guardar(Producto producto);

  void actualizar(Producto producto);

  void eliminar(Producto producto);
}
