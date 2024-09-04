package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.colaboraciones.AltaPersonaVulnerable;
import ar.edu.utn.frba.dds.domain.colaboraciones.utils.MotivoRedistribucionVianda;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.tecnicos.AreaDeCobertura;
import ar.edu.utn.frba.dds.repositories.IAltaPersonaVulnerableRepository;
import ar.edu.utn.frba.dds.repositories.IMotivoRedistribucionRepository;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.List;
import java.util.Optional;

public class AltaPersonaVulnerableRepository implements IAltaPersonaVulnerableRepository, WithSimplePersistenceUnit {
    @Override
    public Optional<AltaPersonaVulnerable> buscar(Long id) {
        return Optional.ofNullable(entityManager().find(AltaPersonaVulnerable.class, id));
    }

    @Override
    public List<AltaPersonaVulnerable> buscarTodos() {
        return entityManager().createQuery("from AltaPersonaVulnerable where activo=:activo", AltaPersonaVulnerable.class).
                setParameter("activo", true)
                .getResultList();
    }

    @Override
    public void guardar(AltaPersonaVulnerable altaPersonaVulnerable) {
        withTransaction(() -> entityManager().persist(altaPersonaVulnerable));
    }

    public void guardar(AltaPersonaVulnerable... altaPersonaVulnerable) {

        withTransaction(() -> {
            for (AltaPersonaVulnerable persona : altaPersonaVulnerable) {
                entityManager().persist(persona);
            }
        });
    }

    @Override
    public void actualizar(AltaPersonaVulnerable altaPersonaVulnerable) {
        withTransaction(() -> entityManager().merge(altaPersonaVulnerable));
    }

    @Override
    public void eliminar(AltaPersonaVulnerable altaPersonaVulnerable) {
        altaPersonaVulnerable.borrarLogico();
        withTransaction(() -> entityManager().merge(altaPersonaVulnerable));
    }
/*
    public static void main(String[] args) {
        AltaPersonaVulnerable m = new AltaPersonaVulnerable(1);
        AltaPersonaVulnerable m1 = new AltaPersonaVulnerable("uno");
        AltaPersonaVulnerable m2 = new AltaPersonaVulnerable("hola");
        IAltaPersonaVulnerableRepository repositorio = (IAltaPersonaVulnerableRepository) ServiceLocator.get("motivosRedistribucionRepository");
        repositorio.guardar(m);
        repositorio.guardar(m1);
        repositorio.guardar(m2);

        repositorio.eliminar(m1);
        try {
            Thread.sleep(60 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        repositorio.actualizar(m2);

        Optional<AltaPersonaVulnerable> persona1 = repositorio.buscar(1L);
        //System.out.println(hidratado.get().getMotivo());
        Optional<AltaPersonaVulnerable> persona2 = repositorio.buscar(2L);

        List<AltaPersonaVulnerable> lista = repositorio.buscarTodos();

    }*/
}
