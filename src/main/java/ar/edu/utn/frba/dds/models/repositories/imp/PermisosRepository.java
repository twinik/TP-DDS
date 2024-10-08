package ar.edu.utn.frba.dds.models.repositories.imp;

import ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion.Permiso;
import ar.edu.utn.frba.dds.models.repositories.IPermisosRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;

public class PermisosRepository implements IPermisosRepository, WithSimplePersistenceUnit {
    @Override
    public Optional<Permiso> buscar(String id) {
        return Optional.ofNullable(entityManager().find(Permiso.class, id));
    }

    @Override
    public List<Permiso> buscarTodos() {
        return entityManager().createQuery("from Permiso where activo=:activo", Permiso.class)
                .setParameter("activo", true)
                .getResultList();
    }

    @Override
    public void guardar(Permiso permiso) {
        withTransaction(() -> entityManager().persist(permiso));
    }

    @Override
    public void guardar(Permiso... permisos) {
        withTransaction(() -> {
            for (Permiso p : permisos) {
                entityManager().persist(p);
            }
        });

    }

    @Override
    public void actualizar(Permiso permiso) {
        withTransaction(() -> entityManager().merge(permiso));
    }

    @Override
    public void eliminar(Permiso permiso) {
        withTransaction(() -> {
            permiso.borrarLogico();
            entityManager().merge(permiso);
        });
    }
}
