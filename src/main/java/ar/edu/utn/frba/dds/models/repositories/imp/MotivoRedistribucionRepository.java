package ar.edu.utn.frba.dds.models.repositories.imp;

import ar.edu.utn.frba.dds.models.domain.colaboraciones.utils.MotivoRedistribucionVianda;
import ar.edu.utn.frba.dds.models.repositories.IMotivoRedistribucionRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;

public class MotivoRedistribucionRepository implements IMotivoRedistribucionRepository, WithSimplePersistenceUnit {

  public MotivoRedistribucionRepository() {
  }

  @Override
  public Optional<MotivoRedistribucionVianda> buscar(String id) {
    return Optional.ofNullable(entityManager().find(MotivoRedistribucionVianda.class, id));
  }

  @Override
  public List<MotivoRedistribucionVianda> buscarTodos() {
    return entityManager().createQuery("from MotivoRedistribucionVianda where activo=:activo order by motivo desc", MotivoRedistribucionVianda.class).
        setParameter("activo", true)
        .getResultList();
  }

  @Override
  public void guardar(MotivoRedistribucionVianda motivosRedistribucion) {
    withTransaction(() -> entityManager().persist(motivosRedistribucion));
  }


  @Override
  public void actualizar(MotivoRedistribucionVianda motivo) {
    withTransaction(() -> entityManager().merge(motivo));
  }

  @Override
  public void eliminar(MotivoRedistribucionVianda motivo) {
    withTransaction(() -> {
      motivo.borrarLogico();
      entityManager().merge(motivo);
    });
  }


}
