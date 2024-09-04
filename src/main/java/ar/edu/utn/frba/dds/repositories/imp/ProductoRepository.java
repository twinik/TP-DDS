package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.colaboraciones.utils.MotivoRedistribucionVianda;
import ar.edu.utn.frba.dds.domain.colaboraciones.utils.Producto;
import ar.edu.utn.frba.dds.domain.colaboradores.form.Opcion;
import ar.edu.utn.frba.dds.repositories.IOpcionRepository;
import ar.edu.utn.frba.dds.repositories.IProductoRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.List;
import java.util.Optional;

public class ProductoRepository implements IProductoRepository, WithSimplePersistenceUnit {
    @Override
    public Optional<Producto> buscar(Long id) {
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

    public void guardar(Producto... producto) {

        withTransaction(() -> {
            for (Producto prod : producto) {
                entityManager().persist(prod);
            }
        });
    }

    @Override
    public void actualizar(Producto producto) {
        withTransaction(() -> entityManager().merge(producto));
    }

    @Override
    public void eliminar(Producto producto) {
        producto.borrarLogico();
        withTransaction(() -> entityManager().merge(producto));
    }

  /*public static void main(String[] args) {
        Producto m = new Producto("otro");
        Producto m1 = new Producto("uno");
        Producto m2 = new Producto("hola");
        IProductoRepository repositorio = (IProductoRepository) ServiceLocator.get("productoRepository");
        repositorio.guardar(m);
        repositorio.guardar(m1);
        repositorio.guardar(m2);

        repositorio.eliminar(m1);
        m2.setMotivo("lo cambio");
        m2.setUpdated_at(LocalDateTime.of(2023,1,13,1,3));
      repositorio.actualizar(m2);

        Optional<Producto> producto1 = repositorio.buscar(1L);
        //System.out.println(hidratado.get().getMotivo());
        Optional<Producto> producto2 = repositorio.buscar(2L);

        List<Producto> lista = repositorio.buscarTodos();

    }

}*/
}
