package ar.edu.utn.frba.dds.models.repositories.imp;

import ar.edu.utn.frba.dds.helpers.DateHelper;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.RedistribucionViandas;
import ar.edu.utn.frba.dds.models.repositories.IRedistribucionesViandaRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class RedistribucionesViandaRepository implements IRedistribucionesViandaRepository, WithSimplePersistenceUnit {


  @Override
  public Optional<RedistribucionViandas> buscar(String id) {
    return Optional.ofNullable(entityManager().find(RedistribucionViandas.class, id));
  }

  @Override
  public List<RedistribucionViandas> buscarTodos() {
    return entityManager().createQuery("from RedistribucionViandas where activo=:activo", RedistribucionViandas.class).
        setParameter("activo", true)
        .getResultList();
  }

  @Override
  public Map<String, Long> buscarViandasColocadasPorHeladera(LocalDate fecha) {
    LocalDate principioDeSemana = DateHelper.principioDeSemana(fecha);
    LocalDate finDeSemana = DateHelper.finDeSemana(fecha);
    List<Object[]> results = entityManager().createQuery(
            "select r.heladeraDestino.nombre, sum(r.cantidad) from RedistribucionViandas r where r.activo = :activo" +
                " and r.fecha between :principioSemana and :finSemana group by r.heladeraDestino.nombre order by sum(r.cantidad) asc", Object[].class)
        .setParameter("activo", true)
        .setParameter("principioSemana", principioDeSemana)
        .setParameter("finSemana", finDeSemana)
        .getResultList();

    return results.stream().collect(Collectors.toMap(
        result -> (String) result[0],
        result -> (Long) result[1]
    ));
  }

  @Override
  public Map<String, Long> buscarViandasRetiradasPorHeladera(LocalDate fecha) {
    LocalDate principioDeSemana = DateHelper.principioDeSemana(fecha);
    LocalDate finDeSemana = DateHelper.finDeSemana(fecha);
    List<Object[]> results = entityManager().createQuery(
            "select r.heladeraOrigen.nombre, sum(r.cantidad) from RedistribucionViandas r where r.activo = :activo" +
                " and r.fecha between :principioSemana and :finSemana group by r.heladeraOrigen.nombre order by sum(r.cantidad) asc", Object[].class)
        .setParameter("activo", true)
        .setParameter("principioSemana", principioDeSemana)
        .setParameter("finSemana", finDeSemana)
        .getResultList();

    return results.stream().collect(Collectors.toMap(
        result -> (String) result[0],
        result -> (Long) result[1]
    ));
  }

  @Override
  public void guardar(RedistribucionViandas redistribucionViandas) {
    withTransaction(() -> entityManager().persist(redistribucionViandas));
  }

  @Override
  public void actualizar(RedistribucionViandas redistribucionViandas) {
    withTransaction(() -> entityManager().merge(redistribucionViandas));
  }

  @Override
  public void eliminar(RedistribucionViandas redistribucionViandas) {

    withTransaction(() -> {
      redistribucionViandas.borrarLogico();
      entityManager().merge(redistribucionViandas);
    });
  }
}
