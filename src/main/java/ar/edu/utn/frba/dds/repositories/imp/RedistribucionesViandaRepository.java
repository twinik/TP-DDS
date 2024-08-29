package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.colaboraciones.RedistribucionViandas;
import ar.edu.utn.frba.dds.domain.colaboraciones.utils.MotivoRedistribucionVianda;
import ar.edu.utn.frba.dds.repositories.IRedistribucionesViandaRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RedistribucionesViandaRepository implements IRedistribucionesViandaRepository, WithSimplePersistenceUnit {


  @Override
  public Optional<RedistribucionViandas> buscar(Long id) {
    return Optional.ofNullable(entityManager().find(RedistribucionViandas.class,id));
  }

  @Override
  public List<RedistribucionViandas> buscarTodos() {
    return entityManager().createQuery("from RedistribucionViandas where activo=:activo",RedistribucionViandas.class).
            setParameter("activo",true)
            .getResultList();
  }

  @Override
  public void guardar(RedistribucionViandas redistribucionViandas) {
    withTransaction(() -> entityManager().persist(redistribucionViandas));
  }
  public void guardar(RedistribucionViandas ...redistribucionViandas) {

    withTransaction(() -> {
      for (RedistribucionViandas motivo : redistribucionViandas){
        entityManager().persist(motivo);
      }
    });
  }

  @Override
  public void actualizar(RedistribucionViandas redistribucionViandas) {
    withTransaction(() -> entityManager().merge(redistribucionViandas));
  }

  @Override
  public void eliminar(RedistribucionViandas redistribucionViandas) {
    redistribucionViandas.borrarLogico();
    withTransaction(() -> entityManager().merge(redistribucionViandas));
  }

  /*public static void main(String[] args) {
        RedistribucionViandas m = new RedistribucionViandas("otro");
        RedistribucionViandas m1 = new RedistribucionViandas("uno");
        RedistribucionViandas m2 = new RedistribucionViandas("hola");
        IRedistribucionesViandaRepository repositorio = (IRedistribucionesViandaRepository) ServiceLocator.get("redistribucionesViandaRepository");
        repositorio.guardar(m);
        repositorio.guardar(m1);
        repositorio.guardar(m2);

        repositorio.eliminar(m1);
        m2.setMotivo("lo cambio");
        m2.setUpdated_at(LocalDateTime.of(2023,1,13,1,3));
      repositorio.actualizar(m2);

        Optional<RedistribucionViandas> redistribucionViandas1 = repositorio.buscar(1L);
        //System.out.println(hidratado.get().getMotivo());
        Optional<RedistribucionViandas> redistribucionViandas2 = repositorio.buscar(2L);

        List<RedistribucionViandas> lista = repositorio.buscarTodos();

    }*/
}
