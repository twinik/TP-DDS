package ar.edu.utn.frba.dds.models.repositories.imp;

import ar.edu.utn.frba.dds.models.domain.heladeras.ModeloHeladera;
import ar.edu.utn.frba.dds.models.repositories.IModeloHeladeraRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;

public class ModeloHeladeraRepository implements IModeloHeladeraRepository, WithSimplePersistenceUnit {
  @Override
  public Optional<ModeloHeladera> buscar(String id) {
    return Optional.ofNullable(entityManager().find(ModeloHeladera.class, id));
  }

  @Override
  public List<ModeloHeladera> buscarTodos() {
    return entityManager().createQuery("from ModeloHeladera where activo=:activo", ModeloHeladera.class).
        setParameter("activo", true)
        .getResultList();
  }

  @Override
  public void guardar(ModeloHeladera modeloHeladera) {
    withTransaction(() -> entityManager().persist(modeloHeladera));
  }

  @Override
  public void actualizar(ModeloHeladera modeloHeladera) {
    withTransaction(() -> entityManager().merge(modeloHeladera));
  }

  @Override
  public void eliminar(ModeloHeladera modeloHeladera) {

    withTransaction(() -> {
      modeloHeladera.borrarLogico();
      entityManager().merge(modeloHeladera);
    });
  }

}
