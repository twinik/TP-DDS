package ar.edu.utn.frba.dds.models.repositories;

import ar.edu.utn.frba.dds.models.domain.colaboraciones.utils.CanjeProducto;
import java.util.List;
import java.util.Optional;

public interface ICanjeProductoRepository {
  Optional<CanjeProducto> buscar(String id);

  List<CanjeProducto> buscarPorColaborador(String id);

  List<CanjeProducto> buscarTodos();

  void guardar(CanjeProducto canje);

  void actualizar(CanjeProducto canje);

  void eliminar(CanjeProducto canje);
}
