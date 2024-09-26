package ar.edu.utn.frba.dds.models.repositories.imp;

import ar.edu.utn.frba.dds.models.domain.PersonaVulnerable;
import ar.edu.utn.frba.dds.models.domain.tarjetas.FrecuenciaDiaria;
import ar.edu.utn.frba.dds.models.domain.tarjetas.Tarjeta;
import ar.edu.utn.frba.dds.models.repositories.ITarjetasRepository;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import lombok.NoArgsConstructor;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public class TarjetaRepository implements ITarjetasRepository, WithSimplePersistenceUnit {


    public static void main(String[] args) {
        PersonaVulnerable p = new PersonaVulnerable();
        p.setNombre("juancito");
        Tarjeta m = new Tarjeta("uncodigo", 2, new FrecuenciaDiaria(), p, null, 3);
        ITarjetasRepository repositorio = ServiceLocator.get(ITarjetasRepository.class);
        repositorio.guardar(m);


        Optional<Tarjeta> tarjeta1 = repositorio.buscar(m.getId());
        Optional<Tarjeta> tarjeta2 = repositorio.buscar(m.getId());

        List<Tarjeta> lista = repositorio.buscarTodos();

    }

    @Override
    public Optional<Tarjeta> buscarPorCodigo(String codigo) {
        try {
            Tarjeta t = (Tarjeta) entityManager().createQuery("from Tarjeta where codigo=:codigo")
                    .setParameter("codigo", codigo)
                    .getSingleResult();
            return Optional.ofNullable(t);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Tarjeta> buscar(String id) {
        return Optional.ofNullable(entityManager().find(Tarjeta.class, id));
    }

    @Override
    public List<Tarjeta> buscarTodos() {
        return entityManager().createQuery("from Tarjeta where activo=:activo", Tarjeta.class).
                setParameter("activo", true)
                .getResultList();
    }

    @Override
    public void guardar(Tarjeta tarjeta) {
        withTransaction(() -> entityManager().persist(tarjeta));
    }

    public void guardar(Tarjeta... tarjeta) {

        withTransaction(() -> {
            for (Tarjeta card : tarjeta) {
                entityManager().persist(card);
            }
        });
    }

    @Override
    public void actualizar(Tarjeta tarjeta) {
        withTransaction(() -> entityManager().merge(tarjeta));
    }

    @Override
    public void eliminar(Tarjeta tarjeta) {

        withTransaction(() -> {
            tarjeta.borrarLogico();
            entityManager().merge(tarjeta);
        });
    }
}