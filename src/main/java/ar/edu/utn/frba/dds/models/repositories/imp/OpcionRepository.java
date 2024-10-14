package ar.edu.utn.frba.dds.models.repositories.imp;

import ar.edu.utn.frba.dds.models.domain.colaboradores.form.Opcion;
import ar.edu.utn.frba.dds.models.repositories.IOpcionRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;

public class OpcionRepository implements IOpcionRepository, WithSimplePersistenceUnit {
    @Override
    public Optional<Opcion> buscar(String id) {
        return Optional.ofNullable(entityManager().find(Opcion.class, id));
    }

    @Override
    public List<Opcion> buscarTodos() {
        return entityManager().createQuery("from Opcion where activo=:activo", Opcion.class).
                setParameter("activo", true)
                .getResultList();
    }

    @Override
    public void guardar(Opcion opcion) {
        withTransaction(() -> entityManager().persist(opcion));
    }

    @Override
    public void actualizar(Opcion opcion) {
        withTransaction(() -> entityManager().merge(opcion));
    }

    @Override
    public void eliminar(Opcion opcion) {

        withTransaction(() -> {
            opcion.borrarLogico();
            entityManager().merge(opcion);
        });
    }
}
