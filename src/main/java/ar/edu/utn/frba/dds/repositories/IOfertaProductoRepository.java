package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.domain.colaboraciones.OfertaProducto;
import ar.edu.utn.frba.dds.domain.heladeras.ModeloHeladera;
import java.util.List;
import java.util.Optional;

public interface IOfertaProductoRepository {
    Optional<OfertaProducto> buscar(Long id);

    List<OfertaProducto> buscarTodos();

    void guardar(OfertaProducto ofertaProducto);

    void actualizar(OfertaProducto ofertaProducto);

    void eliminar(OfertaProducto ofertaProducto);
}
