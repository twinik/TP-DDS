package ar.edu.utn.frba.dds.models.repositories.imp;

import ar.edu.utn.frba.dds.models.domain.colaboraciones.utils.CanjeProducto;
import ar.edu.utn.frba.dds.models.repositories.ICanjeProductoRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;

public class CanjeProductoRepository implements ICanjeProductoRepository, WithSimplePersistenceUnit {
    @Override
    public Optional<CanjeProducto> buscar(String id) {
        return Optional.ofNullable(entityManager().find(CanjeProducto.class, id));
    }

    @Override
    public List<CanjeProducto> buscarPorColaborador(String id) {
        return entityManager().createQuery("from CanjeProducto where comprador.id =:id and activo=:activo", CanjeProducto.class).
                setParameter("id", id)
                .setParameter("activo", true)
                .getResultList();
    }

    @Override
    public List<CanjeProducto> buscarTodos() {
        return entityManager().createQuery("from CanjeProducto where activo=:activo", CanjeProducto.class).
                setParameter("activo", true)
                .getResultList();
    }

    @Override
    public void guardar(CanjeProducto canje) {
        withTransaction(() -> entityManager().persist(canje));
    }

    @Override
    public void actualizar(CanjeProducto canje) {
        withTransaction(() -> entityManager().merge(canje));
    }

    @Override
    public void eliminar(CanjeProducto canje) {
        withTransaction(() -> {
            canje.borrarLogico();
            entityManager().merge(canje);
        });
    }
}
