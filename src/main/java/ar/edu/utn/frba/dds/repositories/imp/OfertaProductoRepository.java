package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.colaboraciones.OfertaProducto;
import ar.edu.utn.frba.dds.domain.heladeras.ModeloHeladera;
import ar.edu.utn.frba.dds.repositories.IModeloHeladeraRepository;
import ar.edu.utn.frba.dds.repositories.IOfertaProductoRepository;
import java.util.List;
import java.util.Optional;

public class OfertaProductoRepository implements IOfertaProductoRepository {
  @Override
  public Optional<OfertaProducto> buscar(Long id) {
    return Optional.empty();
  }

  @Override
  public List<OfertaProducto> buscarTodos() {
    return List.of();
  }

  @Override
  public void guardar(OfertaProducto ofertaProducto) {
  }

  @Override
  public void actualizar(OfertaProducto ofertaProducto) {
  }

  @Override
  public void eliminar(OfertaProducto ofertaProducto) {
  }
}
