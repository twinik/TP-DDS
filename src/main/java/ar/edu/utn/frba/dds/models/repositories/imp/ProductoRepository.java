package ar.edu.utn.frba.dds.models.repositories.imp;

import ar.edu.utn.frba.dds.models.domain.colaboraciones.utils.Producto;
import ar.edu.utn.frba.dds.models.repositories.IProductoRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;

public class ProductoRepository implements IProductoRepository, WithSimplePersistenceUnit {
  @Override
  public Optional<Producto> buscar(String id) {
    return Optional.ofNullable(entityManager().find(Producto.class, id));
  }

  @Override
  public List<Producto> buscarTodos() {
    return entityManager().createQuery("from Producto where activo=:activo", Producto.class).
        setParameter("activo", true)
        .getResultList();
  }

  @Override
  public void guardar(Producto producto) {
    withTransaction(() -> entityManager().persist(producto));
  }

  @Override
  public void actualizar(Producto producto) {
    withTransaction(() -> entityManager().merge(producto));
  }

  @Override
  public void eliminar(Producto producto) {

    withTransaction(() -> {
      producto.borrarLogico();
      entityManager().merge(producto);
    });
  }

}
