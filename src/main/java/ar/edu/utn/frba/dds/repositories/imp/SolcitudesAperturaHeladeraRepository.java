package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.colaboraciones.utils.MotivoRedistribucionVianda;
import ar.edu.utn.frba.dds.domain.heladeras.SolicitudAperturaHeladera;
import ar.edu.utn.frba.dds.repositories.ISolicitudesAperturaHeladeraRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SolcitudesAperturaHeladeraRepository implements ISolicitudesAperturaHeladeraRepository, WithSimplePersistenceUnit {


  @Override
  public Optional<SolicitudAperturaHeladera> buscar(Long id) {
    return Optional.ofNullable(entityManager().find(SolicitudAperturaHeladera.class,id));
  }

  @Override
  public List<SolicitudAperturaHeladera> buscarTodos() {
    return entityManager().createQuery("from SolicitudAperturaHeladera where activo=:activo",SolicitudAperturaHeladera.class).
            setParameter("activo",true)
            .getResultList();
  }

  @Override
  public void guardar(SolicitudAperturaHeladera solicitudAperturaHeladera) {
    withTransaction(() -> entityManager().persist(solicitudAperturaHeladera));
  }
  public void guardar(SolicitudAperturaHeladera ...solicitudAperturaHeladera) {

    withTransaction(() -> {
      for (SolicitudAperturaHeladera solicitud : solicitudAperturaHeladera){
        entityManager().persist(solicitud);
      }
    });
  }

  @Override
  public void actualizar(SolicitudAperturaHeladera solicitudAperturaHeladera) {
    withTransaction(() -> entityManager().merge(solicitudAperturaHeladera));
  }

  @Override
  public void eliminar(SolicitudAperturaHeladera solicitudAperturaHeladera) {
    solicitudAperturaHeladera.borrarLogico();
    withTransaction(() -> entityManager().merge(solicitudAperturaHeladera));
  }

  /*public static void main(String[] args) {
        SolicitudAperturaHeladera m = new SolicitudAperturaHeladera("otro");
        SolicitudAperturaHeladera m1 = new SolicitudAperturaHeladera("uno");
        SolicitudAperturaHeladera m2 = new SolicitudAperturaHeladera("hola");
        ISolicitudesAperturaHeladeraRepository repositorio = (ISolicitudesAperturaHeladeraRepository) ServiceLocator.get("solicitudesAperturaHeladeraRepository");
        repositorio.guardar(m);
        repositorio.guardar(m1);
        repositorio.guardar(m2);

        repositorio.eliminar(m1);
        m2.setMotivo("lo cambio");
        m2.setUpdated_at(LocalDateTime.of(2023,1,13,1,3));
      repositorio.actualizar(m2);

        Optional<SolicitudAperturaHeladera> solicitudAperturaHeladera1 = repositorio.buscar(1L);
        //System.out.println(hidratado.get().getMotivo());
        Optional<SolicitudAperturaHeladera> solicitudAperturaHeladera2 = repositorio.buscar(2L);

        List<SolicitudAperturaHeladera> lista = repositorio.buscarTodos();

    }*/

}
