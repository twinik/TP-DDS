package ar.edu.utn.frba.dds.models.repositories.imp;

import ar.edu.utn.frba.dds.helpers.DateHelper;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.DonacionVianda;
import ar.edu.utn.frba.dds.models.repositories.IDonacionesViandaRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class DonacionesViandaRepository implements IDonacionesViandaRepository, WithSimplePersistenceUnit {

  @Override
  public Optional<DonacionVianda> buscar(String id) {
    return Optional.ofNullable(entityManager().find(DonacionVianda.class, id));
  }

  @Override
  public List<DonacionVianda> buscarTodos() {
    return entityManager().createQuery("from DonacionVianda where activo=:activo", DonacionVianda.class).
        setParameter("activo", true)
        .getResultList();
  }

  @Override
  public List<DonacionVianda> buscarPorColaborador(String colaborador_id) {
    return entityManager().createQuery("from DonacionVianda where activo=:activo and colaborador.id=:colaborador_id", DonacionVianda.class)
        .setParameter("activo", true)
        .setParameter("colaborador_id", colaborador_id)
        .getResultList();
  }

  @Override
  public Map<String, Long> buscarDonacionesAgrupadasPorHeladera(LocalDate fecha) {
    LocalDate principioDeSemana = DateHelper.principioDeSemana(fecha);
    LocalDate finDeSemana = DateHelper.finDeSemana(fecha);
    List<Object[]> results = entityManager().createQuery(
            "select d.vianda.heladera.nombre, count(d) from DonacionVianda d where d.activo = :activo" + " and d.fecha between :principioSemana and :finSemana" +
                " and d.vianda.heladera.activo=:activo group by d.vianda.heladera.nombre order by count(d)", Object[].class)
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
  public void guardar(DonacionVianda donacionVianda) {
    withTransaction(() -> entityManager().persist(donacionVianda));
  }

  @Override
  public void guardar(List<DonacionVianda> donacionesVianda) {

    withTransaction(() -> {
      donacionesVianda.forEach(d -> entityManager().persist(d));
    });
  }

  @Override
  public void actualizar(DonacionVianda donacionVianda) {
    withTransaction(() -> entityManager().merge(donacionVianda));
  }

  @Override
  public void eliminar(DonacionVianda donacionVianda) {

    withTransaction(() -> {
      donacionVianda.borrarLogico();
      entityManager().merge(donacionVianda);
    });
  }

}
