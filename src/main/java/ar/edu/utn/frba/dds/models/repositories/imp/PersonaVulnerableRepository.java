package ar.edu.utn.frba.dds.models.repositories.imp;

import ar.edu.utn.frba.dds.models.domain.PersonaVulnerable;
import ar.edu.utn.frba.dds.models.repositories.IPersonaVulnerableRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
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
    public void guardar(PersonaVulnerable persona) {
        withTransaction(() -> entityManager().persist(persona));
    }

    public void guardar(PersonaVulnerable... persona) {

        withTransaction(() -> {
            for (PersonaVulnerable perVu : persona) {
                entityManager().persist(perVu);
            }
        });
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

  /* public static void main(String[] args) {
        PersonaVulnerable m = new PersonaVulnerable("otro");
        PersonaVulnerable m1 = new PersonaVulnerable("uno");
        PersonaVulnerable m2 = new PersonaVulnerable("hola");
        IPersonaVulnerableRepository repositorio = (IPersonaVulnerableRepository) ServiceLocator.get("personaVulnerableRepository");
        repositorio.guardar(m);
        repositorio.guardar(m1);
        repositorio.guardar(m2);

        repositorio.eliminar(m1);
        m2.setMotivo("lo cambio");
        m2.setUpdated_at(LocalDateTime.of(2023,1,13,1,3));
      repositorio.actualizar(m2);

        Optional<PersonaVulnerable> persona1 = repositorio.buscar(1L);
        //System.out.println(hidratado.get().getMotivo());
        Optional<PersonaVulnerable> persona2 = repositorio.buscar(2L);

        List<PersonaVulnerable> lista = repositorio.buscarTodos();

    }*/
}
