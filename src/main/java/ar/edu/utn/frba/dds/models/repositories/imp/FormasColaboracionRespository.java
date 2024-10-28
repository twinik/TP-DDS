package ar.edu.utn.frba.dds.models.repositories.imp;

import ar.edu.utn.frba.dds.models.domain.colaboradores.FormaColaboracion;
import ar.edu.utn.frba.dds.models.repositories.IFormasColaboracionRespository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

public class FormasColaboracionRespository implements IFormasColaboracionRespository, WithSimplePersistenceUnit {
  @Override
  public Optional<FormaColaboracion> buscarPorNombre(String nombre) {
    try {
      return Optional.of(entityManager().createQuery("from FormaColaboracion where nombreInterno=:nombre and activo=:activo", FormaColaboracion.class)
          .setParameter("nombre", nombre)
          .setParameter("activo", true)
          .getSingleResult());
    } catch (NoResultException e) {
      return Optional.empty();
    }
  }

  @Override
  public Optional<FormaColaboracion> buscar(String id) {
    return Optional.ofNullable(entityManager().find(FormaColaboracion.class, id));
  }


  @Override
  public List<FormaColaboracion> buscarTodos() {
    return entityManager().createQuery("from FormaColaboracion where activo=:activo", FormaColaboracion.class).
        setParameter("activo", true)
        .getResultList();
  }

  @Override
  public void guardar(FormaColaboracion forma) {
    withTransaction(() -> entityManager().persist(forma));
  }

  @Override
  public void guardar(FormaColaboracion... formas) {
    withTransaction(() -> {
      for (FormaColaboracion f : formas) {
        entityManager().persist(f);
      }
    });
  }

  @Override
  public void actualizar(FormaColaboracion forma) {
    withTransaction(() -> entityManager().merge(forma));
  }

  @Override
  public void eliminar(FormaColaboracion forma) {
    withTransaction(() -> {
      forma.borrarLogico();
      entityManager().merge(forma);
    });
  }

}
