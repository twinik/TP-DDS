package ar.edu.utn.frba.dds.models.repositories.imp;

import ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion.Rol;
import ar.edu.utn.frba.dds.models.repositories.IRolesRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;

public class RolesRepository implements IRolesRepository, WithSimplePersistenceUnit {
    @Override
    public Optional<Rol> buscar(String id) {
        return Optional.ofNullable(entityManager().find(Rol.class, id));
    }

    @Override
    public List<Rol> buscarTodos() {
        return entityManager().createQuery("from Rol where activo=:activo", Rol.class)
                .setParameter("activo", true)
                .getResultList();
    }

    @Override
    public void guardar(Rol rol) {
        withTransaction(() -> entityManager().persist(rol));
    }

    @Override
    public void guardar(Rol... roles) {
        withTransaction(() -> {
            for (Rol r : roles) {
                entityManager().persist(r);
            }
        });
    }

    @Override
    public void actualizar(Rol rol) {
        withTransaction(() -> entityManager().merge(rol));
    }

    @Override
    public void eliminar(Rol rol) {
        withTransaction(() -> {
            rol.borrarLogico();
            entityManager().merge(rol);
        });
    }
}
