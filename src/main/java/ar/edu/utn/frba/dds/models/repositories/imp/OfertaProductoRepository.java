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

    public void guardar(OfertaProducto... ofertaProducto) {

        withTransaction(() -> {
            for (OfertaProducto oferta : ofertaProducto) {
                entityManager().persist(oferta);
            }
        });
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
        ;});
    }

  /*public static void main(String[] args) {
        OfertaProducto m = new OfertaProducto("otro");
        OfertaProducto m1 = new OfertaProducto("uno");
        OfertaProducto m2 = new OfertaProducto("hola");
        IOfertaProductoRepository repositorio = (IOfertaProductoRepository) ServiceLocator.get("ofertaProductoRepository");
        repositorio.guardar(m);
        repositorio.guardar(m1);
        repositorio.guardar(m2);

        repositorio.eliminar(m1);
        m2.setMotivo("lo cambio");
        m2.setUpdated_at(LocalDateTime.of(2023,1,13,1,3));
      repositorio.actualizar(m2);

        Optional<OfertaProducto> ofertaProducto1 = repositorio.buscar(1L);
        //System.out.println(hidratado.get().getMotivo());
        Optional<OfertaProducto> ofertaProducto2 = repositorio.buscar(2L);

        List<OfertaProducto> lista = repositorio.buscarTodos();

    }
*/
}
