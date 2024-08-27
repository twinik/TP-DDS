package ar.edu.utn.frba.dds.repositories.imp;


import ar.edu.utn.frba.dds.domain.colaboraciones.utils.MotivoRedistribucionVianda;
import ar.edu.utn.frba.dds.domain.colaboradores.Usuario;
import ar.edu.utn.frba.dds.domain.suscripciones.Suscripcion;
import ar.edu.utn.frba.dds.repositories.ISuscripcionesRepository;
import ar.edu.utn.frba.dds.repositories.IUsuariosRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.List;
import java.util.Optional;

/**
 * ISuscripcionesRepository interface permite interactuar con las suscripciones.
 */
public class SuscripcionesRepository implements ISuscripcionesRepository, WithSimplePersistenceUnit {

  @Override
  public Optional<Suscripcion> buscar(long id) {
    return Optional.ofNullable(entityManager().find(Suscripcion.class,id));
  }

  @Override
  public List<Suscripcion> buscarTodos() {
    return entityManager().createQuery("from Suscripcion where activo=:activo",Suscripcion.class).
            setParameter("activo",true)
            .getResultList();
  }

  @Override
  public void guardar(Suscripcion suscripcion) {
    withTransaction(() -> entityManager().persist(suscripcion));
  }
  public void guardar(Suscripcion ...suscripcion) {

    withTransaction(() -> {
      for (Suscripcion sus : suscripcion){
        entityManager().persist(sus);
      }
    });
  }

  @Override
  public void actualizar(Suscripcion suscripcion) {
    withTransaction(() -> entityManager().merge(suscripcion));
  }

  @Override
  public void eliminar(Suscripcion suscripcion) {
    suscripcion.setActivo(false);
    withTransaction(() -> entityManager().merge(suscripcion));
  }

  /*public static void main(String[] args) {
        Suscripcion m = new Suscripcion("otro");
        Suscripcion m1 = new Suscripcion("uno");
        Suscripcion m2 = new Suscripcion("hola");
        ISuscripcionesRepository repositorio = (ISuscripcionesRepository) ServiceLocator.get("suscripcionesRepository");
        repositorio.guardar(m);
        repositorio.guardar(m1);
        repositorio.guardar(m2);

        repositorio.eliminar(m1);
        m2.setMotivo("lo cambio");
        m2.setUpdated_at(LocalDateTime.of(2023,1,13,1,3));
      repositorio.actualizar(m2);

        Optional<Suscripcion> suscripcion1 = repositorio.buscar(1L);
        //System.out.println(hidratado.get().getMotivo());
        Optional<Suscripcion> suscripcion2 = repositorio.buscar(2L);

        List<Suscripcion> lista = repositorio.buscarTodos();

    }*/

}
