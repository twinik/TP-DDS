package ar.edu.utn.frba.dds.models.repositories.imp;

import ar.edu.utn.frba.dds.models.domain.colaboraciones.OfertaProducto;
import ar.edu.utn.frba.dds.models.repositories.IOfertaProductoRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;

public class OfertaProductoRepository implements IOfertaProductoRepository, WithSimplePersistenceUnit {
  @Override
  public Optional<OfertaProducto> buscar(String id) {
    return Optional.ofNullable(entityManager().find(OfertaProducto.class, id));
  }

  @Override
  public List<OfertaProducto> buscarTodos() {
    return entityManager().createQuery("from OfertaProducto where activo=:activo and colaborador.activo=:activo", OfertaProducto.class).
        setParameter("activo", true)
        .getResultList();
  }

  @Override
  public void guardar(OfertaProducto ofertaProducto) {
    withTransaction(() -> entityManager().persist(ofertaProducto));
  }

  @Override
  public void actualizar(OfertaProducto ofertaProducto) {
    withTransaction(() -> entityManager().merge(ofertaProducto));
  }

  @Override
  public void eliminar(OfertaProducto ofertaProducto) {

    withTransaction(() -> {
      ofertaProducto.borrarLogico();
      entityManager().merge(ofertaProducto)
      ;
    });
  }

}
