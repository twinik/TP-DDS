package ar.edu.utn.frba.dds.models.repositories.imp;

import ar.edu.utn.frba.dds.models.domain.colaboraciones.ColocacionHeladeras;
import ar.edu.utn.frba.dds.models.repositories.IColocacionHeladeraRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;

public class ColocacionHeladeraRepository implements IColocacionHeladeraRepository, WithSimplePersistenceUnit {
    @Override
    public Optional<ColocacionHeladeras> buscar(String id) {
        return Optional.ofNullable(entityManager().find(ColocacionHeladeras.class, id));
    }

    @Override
    public List<ColocacionHeladeras> buscarTodos() {
        return entityManager().createQuery("from ColocacionHeladeras where activo=:activo", ColocacionHeladeras.class).
                setParameter("activo", true)
                .getResultList();
    }

    @Override
    public void guardar(ColocacionHeladeras colocacionHeladera) {
        withTransaction(() -> entityManager().persist(colocacionHeladera));
    }

    public void guardar(ColocacionHeladeras... colocacionHeladera) {

        withTransaction(() -> {
            for (ColocacionHeladeras colocacion : colocacionHeladera) {
                entityManager().persist(colocacion);
            }
        });
    }

    @Override
    public void actualizar(ColocacionHeladeras colocacionHeladera) {
        withTransaction(() -> entityManager().merge(colocacionHeladera));
    }

    @Override
    public void eliminar(ColocacionHeladeras colocacionHeladera) {
        withTransaction(() -> {
            colocacionHeladera.borrarLogico();
            entityManager().merge(colocacionHeladera);
        });

    }

    /* public static void main(String[] args) {
        ColocacionHeladeras m = new ColocacionHeladeras("otro");
        ColocacionHeladeras m1 = new ColocacionHeladeras("uno");
        ColocacionHeladeras m2 = new ColocacionHeladeras("hola");
        IColocacionHeladeraRepository repositorio = (IColocacionHeladeraRepository) ServiceLocator.get("colocacionHeladeraRepository");
        repositorio.guardar(m);
        repositorio.guardar(m1);
        repositorio.guardar(m2);

        repositorio.eliminar(m1);
        m2.setMotivo("lo cambio");
        m2.setUpdated_at(LocalDateTime.of(2023,1,13,1,3));
      repositorio.actualizar(m2);

        Optional<ColocacionHeladeras> heladera1 = repositorio.buscar(1L);
        //System.out.println(hidratado.get().getMotivo());
        Optional<ColocacionHeladeras> heladera2 = repositorio.buscar(2L);

        List<ColocacionHeladeras> lista = repositorio.buscarTodos();

    }*/
}
