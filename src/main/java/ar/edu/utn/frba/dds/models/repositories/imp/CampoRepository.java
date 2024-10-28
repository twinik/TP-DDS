package ar.edu.utn.frba.dds.models.repositories.imp;

import ar.edu.utn.frba.dds.models.domain.colaboradores.form.Campo;
import ar.edu.utn.frba.dds.models.repositories.ICampoRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;

public class CampoRepository implements ICampoRepository, WithSimplePersistenceUnit {
  @Override
  public Optional<Campo> buscar(String id) {
    return Optional.ofNullable(entityManager().find(Campo.class, id));
  }

  @Override
  public List<Campo> buscarTodos() {
    return entityManager().createQuery("from Campo where activo=:activo", Campo.class).
        setParameter("activo", true)
        .getResultList();
  }

  @Override
  public void guardar(Campo campo) {
    withTransaction(() -> entityManager().persist(campo));
  }

  public void guardar(Campo... campo) {

    withTransaction(() -> {
      for (Campo camp : campo) {
        entityManager().persist(camp);
      }
    });
  }

  @Override
  public void actualizar(Campo campo) {
    withTransaction(() -> entityManager().merge(campo));
  }

  @Override
  public void eliminar(Campo campo) {

    withTransaction(() -> {
      campo.borrarLogico();
      entityManager().merge(campo);
    });
  }
}
