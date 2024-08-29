package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.colaboraciones.DonacionVianda;
import ar.edu.utn.frba.dds.domain.colaboraciones.utils.MotivoRedistribucionVianda;
import ar.edu.utn.frba.dds.repositories.IDonacionesViandaRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DonacionesVIandaRepository implements IDonacionesViandaRepository, WithSimplePersistenceUnit {

  private List<DonacionVianda> donaciones;

  public DonacionesVIandaRepository() {
    this.donaciones = new ArrayList<>();
  }

  @Override
  public Optional<DonacionVianda> buscar(Long id) {
    return Optional.ofNullable(entityManager().find(DonacionVianda.class,id));
  }

  @Override
  public List<DonacionVianda> buscarTodos() {
    return entityManager().createQuery("from DonacionVianda where activo=:activo",DonacionVianda.class).
            setParameter("activo",true)
            .getResultList();
  }

  @Override
  public void guardar(DonacionVianda donacionVianda) {
    withTransaction(() -> entityManager().persist(donacionVianda));
  }

  public void guardar(DonacionVianda ...donacionVianda) {

    withTransaction(() -> {
      for (DonacionVianda donacion : donacionVianda){
        entityManager().persist(donacion);
      }
    });
  }

  @Override
  public void actualizar(DonacionVianda donacionVianda) {
    withTransaction(() -> entityManager().merge(donacionVianda));
  }

  @Override
  public void eliminar(DonacionVianda donacionVianda) {
    donacionVianda.setActivo(false);
    withTransaction(() -> entityManager().merge(donacionVianda));
  }

  /*public static void main(String[] args) {
        DonacionVianda m = new DonacionVianda("otro");
        DonacionVianda m1 = new DonacionVianda("uno");
        DonacionVianda m2 = new DonacionVianda("hola");
        IDonacionesViandaRepository repositorio = (IDonacionesViandaRepository) ServiceLocator.get("donacionesViandaRepository");
        repositorio.guardar(m);
        repositorio.guardar(m1);
        repositorio.guardar(m2);

        repositorio.eliminar(m1);
        m2.setMotivo("lo cambio");
        m2.setUpdated_at(LocalDateTime.of(2023,1,13,1,3));
      repositorio.actualizar(m2);

        Optional<DonacionVianda> donacion1 = repositorio.buscar(1L);
        //System.out.println(hidratado.get().getMotivo());
        Optional<DonacionVianda> donacion2 = repositorio.buscar(2L);

        List<DonacionVianda> lista = repositorio.buscarTodos();

    }*/
}
