package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.colaboraciones.utils.MotivoRedistribucionVianda;
import ar.edu.utn.frba.dds.domain.tarjetas.Tarjeta;
import ar.edu.utn.frba.dds.repositories.ITarjetasRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TarjetaRepository implements ITarjetasRepository, WithSimplePersistenceUnit {

  private List<Tarjeta> tarjetas;

  public TarjetaRepository() {
    this.tarjetas = new ArrayList<>();
  }

  @Override
  public Optional<Tarjeta> buscar(String codigo) {
    return this.tarjetas.stream().filter(t -> t.getCodigo().equals(codigo)).findFirst(); //Este va o no?
  }

  @Override
  public Optional<Tarjeta> buscar(long id) {
    return Optional.ofNullable(entityManager().find(Tarjeta.class,id));
  }

  @Override
  public List<Tarjeta> buscarTodos() {
    return entityManager().createQuery("from Tarjeta where activo=:activo",Tarjeta.class).
            setParameter("activo",true)
            .getResultList();
  }

  @Override
  public void guardar(Tarjeta tarjeta) {
    withTransaction(() -> entityManager().persist(tarjeta));
  }

  public void guardar(Tarjeta ...tarjeta) {

    withTransaction(() -> {
      for (Tarjeta card : tarjeta){
        entityManager().persist(card);
      }
    });
  }

  @Override
  public void actualizar(Tarjeta tarjeta) {
    // nada
    //withTransaction(() -> entityManager().merge(tarjeta));
  }


  @Override
  public void eliminar(Tarjeta tarjeta) {
    tarjeta.setActivo(false);
    withTransaction(() -> entityManager().merge(tarjeta));
  }

  /*public static void main(String[] args) {
        Tarjeta m = new Tarjeta("otro");
        Tarjeta m1 = new Tarjeta("uno");
        Tarjeta m2 = new Tarjeta("hola");
        ITarjetasRepository repositorio = (ITarjetasRepository) ServiceLocator.get("tarjetasRepository");
        repositorio.guardar(m);
        repositorio.guardar(m1);
        repositorio.guardar(m2);

        repositorio.eliminar(m1);
        m2.setMotivo("lo cambio");
        m2.setUpdated_at(LocalDateTime.of(2023,1,13,1,3));
      repositorio.actualizar(m2);

        Optional<Tarjeta> tarjeta1 = repositorio.buscar(1L);
        //System.out.println(hidratado.get().getMotivo());
        Optional<Tarjeta> tarjeta2 = repositorio.buscar(2L);

        List<Tarjeta> lista = repositorio.buscarTodos();

    }*/
}
