package ar.edu.utn.frba.dds.models.repositories.imp;

import ar.edu.utn.frba.dds.models.domain.PersonaVulnerable;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.AltaPersonaVulnerable;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion.Usuario;
import ar.edu.utn.frba.dds.models.domain.tarjetas.FrecuenciaDiaria;
import ar.edu.utn.frba.dds.models.domain.tarjetas.Tarjeta;
import ar.edu.utn.frba.dds.models.repositories.IAltaPersonaVulnerableRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class AltaPersonaVulnerableRepository implements IAltaPersonaVulnerableRepository, WithSimplePersistenceUnit {
    public static void main(String[] args) {
        Colaborador c = new Colaborador();
        c.setUsuario(new Usuario("dkfnadkf", "dknfajksdnf"));
        c.setNombre("jaun");
        ColaboradoresRepository repo = new ColaboradoresRepository();
        repo.guardar(c);
        AltaPersonaVulnerable a = new AltaPersonaVulnerable();
        a.setColaborador(c);
        PersonaVulnerable n = new PersonaVulnerable();
        n.setNombre("dfdf");
        n.setApellido("dkfmkadf");
        n.setColaborador(c);
        a.setPersona(n);
        a.setTarjeta(Tarjeta.of("aaaaaaaaaaa", 0, new FrecuenciaDiaria(), n));
        a.setFecha(LocalDate.now());
        AltaPersonaVulnerableRepository r = new AltaPersonaVulnerableRepository();
        r.guardar(a);
    }

    @Override
    public Optional<AltaPersonaVulnerable> buscar(String id) {
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

    @Override
    public void actualizar(AltaPersonaVulnerable altaPersonaVulnerable) {
        withTransaction(() -> entityManager().merge(altaPersonaVulnerable));
    }

    @Override
    public void eliminar(AltaPersonaVulnerable altaPersonaVulnerable) {
        withTransaction(() -> {
            altaPersonaVulnerable.borrarLogico();
            entityManager().merge(altaPersonaVulnerable);
        });
    }

}
