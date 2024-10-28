package ar.edu.utn.frba.dds.models.repositories.imp;

import ar.edu.utn.frba.dds.models.domain.heladeras.SolicitudAperturaHeladera;
import ar.edu.utn.frba.dds.models.repositories.ISolicitudesAperturaHeladeraRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;

public class SolicitudesAperturaHeladeraRepository implements ISolicitudesAperturaHeladeraRepository, WithSimplePersistenceUnit {


  @Override
  public Optional<SolicitudAperturaHeladera> buscar(String id) {
    return Optional.ofNullable(entityManager().find(SolicitudAperturaHeladera.class, id));
  }

  @Override
  public List<SolicitudAperturaHeladera> buscarTodos() {
    return entityManager().createQuery("from SolicitudAperturaHeladera where activo=:activo", SolicitudAperturaHeladera.class).
        setParameter("activo", true)
        .getResultList();
  }

  @Override
  public void guardar(SolicitudAperturaHeladera solicitudAperturaHeladera) {
    withTransaction(() -> entityManager().persist(solicitudAperturaHeladera));
  }


  @Override
  public void actualizar(SolicitudAperturaHeladera solicitudAperturaHeladera) {
    withTransaction(() -> entityManager().merge(solicitudAperturaHeladera));
  }

  @Override
  public void eliminar(SolicitudAperturaHeladera solicitudAperturaHeladera) {

    withTransaction(() -> {
      solicitudAperturaHeladera.borrarLogico();
      entityManager().merge(solicitudAperturaHeladera);
    });
  }

}
