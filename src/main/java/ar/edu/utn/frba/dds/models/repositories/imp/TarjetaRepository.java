package ar.edu.utn.frba.dds.models.repositories.imp;

import ar.edu.utn.frba.dds.models.domain.tarjetas.Tarjeta;
import ar.edu.utn.frba.dds.models.repositories.ITarjetasRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import lombok.NoArgsConstructor;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public class TarjetaRepository implements ITarjetasRepository, WithSimplePersistenceUnit {

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
  public void actualizar(List<Tarjeta> tarjetas) {
    withTransaction(() -> {
      tarjetas.forEach(t -> entityManager().merge(t));
    });
  }

  @Override
  public void eliminar(Tarjeta tarjeta) {

    withTransaction(() -> {
      tarjeta.borrarLogico();
      entityManager().merge(tarjeta);
    });
  }
}
