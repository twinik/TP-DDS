package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.domain.colaboraciones.utils.CanjeProducto;
import java.util.List;
import java.util.Optional;

public interface ICanjeProductoRepository {
    Optional<CanjeProducto> buscar(Long id);

    List<CanjeProducto> buscarTodos();

    void guardar(CanjeProducto canje);

    void actualizar(CanjeProducto canje);

    void eliminar(CanjeProducto canje);
}
