package ar.edu.utn.frba.dds.models.repositories.imp;

import ar.edu.utn.frba.dds.helpers.DateHelper;
import ar.edu.utn.frba.dds.models.domain.incidentes.FallaTecnica;
import ar.edu.utn.frba.dds.models.repositories.IFallasTecnicasRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor

public class FallasTecnicasRepository implements IFallasTecnicasRepository, WithSimplePersistenceUnit {


  @Override
  public Optional<FallaTecnica> buscar(String id) {
    return Optional.ofNullable(entityManager().find(FallaTecnica.class, id));
  }

  @Override
  public List<FallaTecnica> buscarTodos() {
    return entityManager().createQuery("from FallaTecnica where activo=:activo order by created_at desc", FallaTecnica.class)
        .setParameter("activo", true)
        .getResultList();
  }

  @Override
  public List<FallaTecnica> buscarPorHeladera(String heladera_id) {
    return entityManager().createQuery("from FallaTecnica where activo=:activo and heladera.id=:heladera_id order by created_at desc", FallaTecnica.class)
        .setParameter("activo", true)
        .setParameter("heladera_id", heladera_id)
        .getResultList();
  }

  @Override
  public Map<String, Long> buscarFallasAgrupadasPorHeladera(LocalDate fecha) {
    LocalDateTime principioDeSemana = DateHelper.principioDeSemana(fecha.atStartOfDay());
    LocalDateTime finDeSemana = DateHelper.finDeSemana(fecha).atStartOfDay();
    List<Object[]> results = entityManager().createQuery(
            "select f.heladera.nombre, count(f) from FallaTecnica f where f.activo = :activo" + " and f.timestamp between :principioSemana and :finSemana group by f.heladera.nombre order by count(f)", Object[].class)
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
  public void guardar(FallaTecnica fallaTecnica) {
    withTransaction(() -> entityManager().persist(fallaTecnica));
  }

  @Override
  public void actualizar(FallaTecnica fallaTecnica) {
    withTransaction(() -> entityManager().merge(fallaTecnica));
  }

  @Override
  public void eliminar(FallaTecnica fallaTecnica) {

    withTransaction(() -> {
      fallaTecnica.borrarLogico();
      entityManager().merge(fallaTecnica);
    });
  }

  @Override
  public void refresh(FallaTecnica fallaTecnica) {
    entityManager().refresh(fallaTecnica);
  }

  @Override
  public void refresh(List<FallaTecnica> fallaTecnicas) {
    fallaTecnicas.forEach(f -> {
      entityManager().refresh(f);
    });
  }
}
