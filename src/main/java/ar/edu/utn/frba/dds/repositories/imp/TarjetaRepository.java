package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.PersonaVulnerable;
import ar.edu.utn.frba.dds.domain.colaboraciones.utils.MotivoRedistribucionVianda;
import ar.edu.utn.frba.dds.domain.tarjetas.FrecuenciaDiaria;
import ar.edu.utn.frba.dds.domain.tarjetas.Tarjeta;
import ar.edu.utn.frba.dds.repositories.ITarjetasRepository;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import lombok.NoArgsConstructor;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public class TarjetaRepository implements ITarjetasRepository, WithSimplePersistenceUnit {


    @Override
    public Optional<Tarjeta> buscar(String codigo) {
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
    public Optional<Tarjeta> buscar(Long id) {
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
        // nada
        //withTransaction(() -> entityManager().merge(tarjeta));
    }


    @Override
    public void eliminar(Tarjeta tarjeta) {
        tarjeta.borrarLogico();
        withTransaction(() -> entityManager().merge(tarjeta));
    }

    public static void main(String[] args) {
        PersonaVulnerable p = new PersonaVulnerable();
        p.setNombre("juancito");
        Tarjeta m = new Tarjeta("uncodigo", 2, new FrecuenciaDiaria(), p, null, 3);
        ITarjetasRepository repositorio = ServiceLocator.get("tarjetasRepository", ITarjetasRepository.class);
        repositorio.guardar(m);


        Optional<Tarjeta> tarjeta1 = repositorio.buscar(1L);
        Optional<Tarjeta> tarjeta2 = repositorio.buscar(2L);

        List<Tarjeta> lista = repositorio.buscarTodos();

    }
}
