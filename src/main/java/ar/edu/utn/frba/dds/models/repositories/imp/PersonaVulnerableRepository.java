package ar.edu.utn.frba.dds.models.repositories.imp;

import ar.edu.utn.frba.dds.models.domain.PersonaVulnerable;
import ar.edu.utn.frba.dds.models.domain.utils.TipoDocumento;
import ar.edu.utn.frba.dds.models.repositories.IPersonaVulnerableRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

public class PersonaVulnerableRepository implements IPersonaVulnerableRepository, WithSimplePersistenceUnit {
    @Override
    public Optional<PersonaVulnerable> buscar(String id) {
        return Optional.ofNullable(entityManager().find(PersonaVulnerable.class, id));
    }

    @Override
    public List<PersonaVulnerable> buscarTodos() {
        return entityManager().createQuery("from PersonaVulnerable where activo=:activo", PersonaVulnerable.class).
                setParameter("activo", true)
                .getResultList();
    }

    @Override
    public Optional<PersonaVulnerable> buscarPorDni(TipoDocumento tipoDocumento, String documento) {
        try {
            return Optional.of(entityManager().createQuery("from PersonaVulnerable where nroDocumento =:documento and tipoDocumento=:tipo and activo=:activo", PersonaVulnerable.class)
                    .setParameter("documento", documento)
                    .setParameter("activo", true)
                    .setParameter("tipo", tipoDocumento)
                    .getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public void guardar(PersonaVulnerable persona) {
        withTransaction(() -> entityManager().persist(persona));
    }

    @Override
    public void actualizar(PersonaVulnerable persona) {
        withTransaction(() -> entityManager().merge(persona));
    }

    @Override
    public void eliminar(PersonaVulnerable persona) {

        withTransaction(() -> {
            persona.borrarLogico();
            entityManager().merge(persona);
        });
    }

}
