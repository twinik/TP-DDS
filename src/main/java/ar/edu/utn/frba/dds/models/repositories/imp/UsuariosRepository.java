package ar.edu.utn.frba.dds.models.repositories.imp;


import ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion.Usuario;
import ar.edu.utn.frba.dds.models.repositories.IUsuariosRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import lombok.NoArgsConstructor;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

/**
 * IAlertasRepository interface permite interactuar con las alertas.
 */
@NoArgsConstructor
public class UsuariosRepository implements IUsuariosRepository, WithSimplePersistenceUnit {

    @Override
    public Optional<Usuario> buscar(String id) {
        return Optional.ofNullable(entityManager().find(Usuario.class, id));
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        try {
            return Optional.of(entityManager().createQuery("from Usuario where email=:email and activo=:activo", Usuario.class)
                    .setParameter("email", email)
                    .setParameter("activo", true)
                    .getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Usuario> buscarTodos() {
        return entityManager().createQuery("from Usuario where activo=:activo", Usuario.class).
                setParameter("activo", true)
                .getResultList();
    }

    @Override
    public void guardar(Usuario usuario) {
        withTransaction(() -> entityManager().persist(usuario));
    }

    @Override
    public void actualizar(Usuario usuario) {
        withTransaction(() -> entityManager().merge(usuario));
    }

    @Override
    public void eliminar(Usuario usuario) {
        withTransaction(() -> {
            usuario.borrarLogico();
            entityManager().merge(usuario);
        });
    }

    @Override
    public void refresh(Usuario usuario) {
        this.entityManager().refresh(usuario);
    }


}
