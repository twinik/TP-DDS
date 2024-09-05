package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.colaboraciones.RedistribucionViandas;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.helpers.DateHelper;
import ar.edu.utn.frba.dds.repositories.IRedistribucionesViandaRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class RedistribucionesViandaRepository implements IRedistribucionesViandaRepository, WithSimplePersistenceUnit {


    @Override
    public Optional<RedistribucionViandas> buscar(Long id) {
        return Optional.ofNullable(entityManager().find(RedistribucionViandas.class, id));
    }

    @Override
    public List<RedistribucionViandas> buscarTodos() {
        return entityManager().createQuery("from RedistribucionViandas where activo=:activo", RedistribucionViandas.class).
                setParameter("activo", true)
                .getResultList();
    }

    @Override
    public List<RedistribucionViandas> buscarTodosMismaSemana(LocalDate fecha) {
        LocalDate principioDeSemana = DateHelper.principioDeSemana(fecha);
        LocalDate finDeSemana = DateHelper.finDeSemana(fecha);
        return entityManager().createQuery("from RedistribucionViandas where activo=:activo and fecha between " +
                        ":principioSemana and :finSemana and heladeraDestino.activo=:activo", RedistribucionViandas.class).
                setParameter("activo", true)
                .setParameter("principioSemana", principioDeSemana)
                .setParameter("finSemana", finDeSemana)
                .getResultList();
    }

    @Override
    public Map<Heladera, Long> buscarViandasColocadasPorHeladera(LocalDate fecha) {
        LocalDate principioDeSemana = DateHelper.principioDeSemana(fecha);
        LocalDate finDeSemana = DateHelper.finDeSemana(fecha);
        List<Object[]> results = entityManager().createQuery(
                "select r.heladeraDestino, sum(r.cantidad) from RedistribucionViandas r where r.activo = :activo" +
                    " and r.fecha between :principioSemana and :finSemana group by r.heladeraDestino order by sum(r.cantidad) asc", Object[].class)
            .setParameter("activo", true)
            .setParameter("principioSemana", principioDeSemana)
            .setParameter("finSemana", finDeSemana)
            .getResultList();

        return results.stream().collect(Collectors.toMap(
            result -> (Heladera) result[0],
            result -> (Long) result[1]
        ));
    }

    @Override
    public Map<Heladera, Long> buscarViandasRetiradasPorHeladera(LocalDate fecha) {
        LocalDate principioDeSemana = DateHelper.principioDeSemana(fecha);
        LocalDate finDeSemana = DateHelper.finDeSemana(fecha);
        List<Object[]> results = entityManager().createQuery(
                "select r.heladeraOrigen, sum(r.cantidad) from RedistribucionViandas r where r.activo = :activo" +
                    " and r.fecha between :principioSemana and :finSemana group by r.heladeraOrigen order by sum(r.cantidad) asc", Object[].class)
            .setParameter("activo", true)
            .setParameter("principioSemana", principioDeSemana)
            .setParameter("finSemana", finDeSemana)
            .getResultList();

        return results.stream().collect(Collectors.toMap(
            result -> (Heladera) result[0],
            result -> (Long) result[1]
        ));
    }

    @Override
    public void guardar(RedistribucionViandas redistribucionViandas) {
        withTransaction(() -> entityManager().persist(redistribucionViandas));
    }

    public void guardar(RedistribucionViandas... redistribucionViandas) {

        withTransaction(() -> {
            for (RedistribucionViandas motivo : redistribucionViandas) {
                entityManager().persist(motivo);
            }
        });
    }

    @Override
    public void actualizar(RedistribucionViandas redistribucionViandas) {
        withTransaction(() -> entityManager().merge(redistribucionViandas));
    }

    @Override
    public void eliminar(RedistribucionViandas redistribucionViandas) {
        redistribucionViandas.borrarLogico();
        withTransaction(() -> entityManager().merge(redistribucionViandas));
    }

  /*public static void main(String[] args) {
        RedistribucionViandas m = new RedistribucionViandas("otro");
        RedistribucionViandas m1 = new RedistribucionViandas("uno");
        RedistribucionViandas m2 = new RedistribucionViandas("hola");
        IRedistribucionesViandaRepository repositorio = (IRedistribucionesViandaRepository) ServiceLocator.get("redistribucionesViandaRepository");
        repositorio.guardar(m);
        repositorio.guardar(m1);
        repositorio.guardar(m2);

        repositorio.eliminar(m1);
        m2.setMotivo("lo cambio");
        m2.setUpdated_at(LocalDateTime.of(2023,1,13,1,3));
      repositorio.actualizar(m2);

        Optional<RedistribucionViandas> redistribucionViandas1 = repositorio.buscar(1L);
        //System.out.println(hidratado.get().getMotivo());
        Optional<RedistribucionViandas> redistribucionViandas2 = repositorio.buscar(2L);

        List<RedistribucionViandas> lista = repositorio.buscarTodos();

    }*/
}
