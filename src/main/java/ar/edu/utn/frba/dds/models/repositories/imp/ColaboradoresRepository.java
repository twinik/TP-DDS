package ar.edu.utn.frba.dds.models.repositories.imp;

import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.domain.utils.TipoDocumento;
import ar.edu.utn.frba.dds.models.repositories.IColaboradoresRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

/**
 * ColaboradorRepository class permite interactuar con los colaboradores.
 */
public class ColaboradoresRepository implements IColaboradoresRepository, WithSimplePersistenceUnit {

  @Override
  public Optional<Colaborador> buscar(TipoDocumento tipoDocumento, String documento) {
    try {
      Colaborador c = (Colaborador) entityManager().createQuery("from Colaborador where tipoDocumento=:tipoDocumento and documento=:documento")
          .setParameter("tipoDocumento", tipoDocumento)
          .setParameter("documento", documento).getSingleResult();
      return Optional.ofNullable(c);
    } catch (NoResultException e) {
      return Optional.empty();
    }

  }

  @Override
  public Optional<Colaborador> buscar(String id) {
    return Optional.ofNullable(entityManager().find(Colaborador.class, id));
  }

  @Override
  public Optional<Colaborador> buscarPorUsuario(String idUsuario) {
    try {
      return Optional.of(entityManager().createQuery("from Colaborador where usuario.id = :idUsuario and activo=:activo", Colaborador.class)
          .setParameter("idUsuario", idUsuario)
          .setParameter("activo", true)
          .getSingleResult());
    } catch (NoResultException e) {
      return Optional.empty();
    }
  }

  @Override
  public Optional<Colaborador> buscarPorDni(TipoDocumento tipoDocumento, String documento) {
    try {
      return Optional.of(entityManager().createQuery("from Colaborador where documento =:documento and tipoDocumento=:tipo and activo=:activo", Colaborador.class)
          .setParameter("documento", documento)
          .setParameter("activo", true)
          .setParameter("tipo", tipoDocumento)
          .getSingleResult());
    } catch (NoResultException e) {
      return Optional.empty();
    }
  }

  @Override
  public List<Colaborador> buscarTodos() {
    return entityManager().createQuery("from Colaborador where activo=:activo and formCompletado=:formCompletado", Colaborador.class).
        setParameter("activo", true)
        .setParameter("formCompletado", true)
        .getResultList();
  }

  @Override
  public void guardar(Colaborador colaborador) {
    withTransaction(() -> entityManager().persist(colaborador));
  }

  @Override
  public void actualizar(Colaborador colaborador) {
    withTransaction(() -> entityManager().merge(colaborador));
  }

  @Override
  public void actualizar(List<Colaborador> colaboradores) {
    withTransaction(() -> {
      colaboradores.forEach(c -> entityManager().merge(c));
    });
  }

  @Override
  public void eliminar(Colaborador colaborador) {

    withTransaction(() -> {
      colaborador.borrarLogico();
      entityManager().merge(colaborador);
    });
  }

  @Override
  public void refresh(Colaborador c) {
    entityManager().refresh(c);
  }
}
