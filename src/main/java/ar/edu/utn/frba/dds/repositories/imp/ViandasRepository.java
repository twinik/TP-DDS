package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.colaboraciones.utils.MotivoRedistribucionVianda;
import ar.edu.utn.frba.dds.domain.heladeras.Vianda;
import ar.edu.utn.frba.dds.repositories.IViandasRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ViandasRepository implements IViandasRepository, WithSimplePersistenceUnit {

  @Override
  public Optional<Vianda> buscar(Long id) {
    return Optional.ofNullable(entityManager().find(Vianda.class,id));
  }

  @Override
  public List<Vianda> buscarTodos() {
    return entityManager().createQuery("from Vianda where activo=:activo",Vianda.class).
            setParameter("activo",true)
            .getResultList();
  }

  @Override
  public void guardar(Vianda vianda) {
    withTransaction(() -> entityManager().persist(vianda));
  }
  public void guardar(Vianda ...vianda) {

    withTransaction(() -> {
      for (Vianda via : vianda){
        entityManager().persist(via);
      }
    });
  }

  @Override
  public void actualizar(Vianda vianda) {
    withTransaction(() -> entityManager().merge(vianda));
  }

  @Override
  public void eliminar(Vianda vianda) {
    vianda.setActivo(false);
    withTransaction(() -> entityManager().merge(vianda));
  }

  /*public static void main(String[] args) {
        Vianda m = new Vianda("otro");
        Vianda m1 = new Vianda("uno");
        Vianda m2 = new Vianda("hola");
        IViandasRepository repositorio = (IViandasRepository) ServiceLocator.get("viandasRepository");
        repositorio.guardar(m);
        repositorio.guardar(m1);
        repositorio.guardar(m2);

        repositorio.eliminar(m1);
        m2.setMotivo("lo cambio");
        m2.setUpdated_at(LocalDateTime.of(2023,1,13,1,3));
      repositorio.actualizar(m2);

        Optional<Vianda> vianda1 = repositorio.buscar(1L);
        //System.out.println(hidratado.get().getMotivo());
        Optional<Vianda> vianda2 = repositorio.buscar(2L);

        List<Vianda> lista = repositorio.buscarTodos();

    }*/
}
