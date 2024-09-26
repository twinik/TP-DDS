package ar.edu.utn.frba.dds.models.repositories;

import ar.edu.utn.frba.dds.models.domain.colaboraciones.utils.Producto;
import java.util.List;
import java.util.Optional;

public interface IProductoRepository {
    Optional<Producto> buscar(String id);

    List<Producto> buscarTodos();

    void guardar(Producto producto);

    void actualizar(Producto producto);

    void eliminar(Producto producto);
}
