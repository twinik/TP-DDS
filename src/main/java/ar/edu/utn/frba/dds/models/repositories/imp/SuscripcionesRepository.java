package ar.edu.utn.frba.dds.models.repositories.imp;


import ar.edu.utn.frba.dds.models.domain.suscripciones.Suscripcion;
import ar.edu.utn.frba.dds.models.repositories.ISuscripcionesRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;

/**
 * ISuscripcionesRepository interface permite interactuar con las suscripciones.
 */
public class SuscripcionesRepository implements ISuscripcionesRepository, WithSimplePersistenceUnit {

  @Override
  public Optional<Suscripcion> buscar(String id) {
    return Optional.ofNullable(entityManager().find(Suscripcion.class, id));
  }

  @Override
  public List<Suscripcion> buscarTodos() {
    return entityManager().createQuery("from Suscripcion where activo=:activo and colaborador.activo=:activo", Suscripcion.class).
        setParameter("activo", true)
        .getResultList();
  }

  @Override
  public List<Suscripcion> buscarTodosPorColaborador(String colaborador_id) {
    return entityManager().createQuery("from Suscripcion where activo=:activo and colaborador.id =:colaborador_id", Suscripcion.class).
        setParameter("activo", true)
        .setParameter("colaborador_id", colaborador_id)
        .getResultList();
  }


  @Override
  public void guardar(Suscripcion suscripcion) {
    withTransaction(() -> entityManager().persist(suscripcion));
  }


  @Override
  public void actualizar(Suscripcion suscripcion) {
    withTransaction(() -> entityManager().merge(suscripcion));
  }

  @Override
  public void eliminar(Suscripcion suscripcion) {

    withTransaction(() -> {
      suscripcion.borrarLogico();
      entityManager().merge(suscripcion);
    });
  }

}
