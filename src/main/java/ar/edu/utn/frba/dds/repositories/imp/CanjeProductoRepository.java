package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.colaboraciones.utils.CanjeProducto;
import ar.edu.utn.frba.dds.domain.colaboradores.form.Campo;
import ar.edu.utn.frba.dds.repositories.ICanjeProductoRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;

public class CanjeProductoRepository implements ICanjeProductoRepository, WithSimplePersistenceUnit {
    @Override
    public Optional<CanjeProducto> buscar(Long id) {
        return Optional.ofNullable(entityManager().find(CanjeProducto.class, id));
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
        canje.borrarLogico();
        withTransaction(() -> entityManager().merge(canje));
    }
}
