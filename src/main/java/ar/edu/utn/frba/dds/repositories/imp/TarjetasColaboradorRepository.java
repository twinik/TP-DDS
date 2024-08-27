package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.colaboraciones.utils.MotivoRedistribucionVianda;
import ar.edu.utn.frba.dds.domain.tarjetas.TarjetaColaborador;
import ar.edu.utn.frba.dds.repositories.ITarjetasColaboradorRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TarjetasColaboradorRepository implements ITarjetasColaboradorRepository, WithSimplePersistenceUnit {

  private List<TarjetaColaborador> tarjetas;

  public TarjetasColaboradorRepository() {
    this.tarjetas = new ArrayList<>();
  }

  @Override
  public Optional<TarjetaColaborador> buscar(String codigo) {
    return this.tarjetas.stream().filter(t -> t.getCodigo().equals(codigo)).findFirst();
  }

  @Override
  public Optional<TarjetaColaborador> buscar(long id) {
    return Optional.ofNullable(entityManager().find(TarjetaColaborador.class,id));
  }

  @Override
  public List<TarjetaColaborador> buscarTodos() {
    return entityManager().createQuery("from TarjetaColaborador where activo=:activo",TarjetaColaborador.class).
            setParameter("activo",true)
            .getResultList();
  }

  @Override
  public void guardar(TarjetaColaborador tarjeta) {
    withTransaction(() -> entityManager().persist(tarjeta));
  }
  public void guardar(TarjetaColaborador ...tarjeta) {

    withTransaction(() -> {
      for (TarjetaColaborador card : tarjeta){
        entityManager().persist(card);
      }
    });
  }


  @Override
  public void actualizar(TarjetaColaborador tarjeta) {
    withTransaction(() -> entityManager().merge(tarjeta));
  }

  @Override
  public void eliminar(TarjetaColaborador tarjeta) {
    tarjeta.setActivo(false);
    withTransaction(() -> entityManager().merge(tarjeta));
  }

  /*public static void main(String[] args) {
        TarjetaColaborador m = new TarjetaColaborador("otro");
        TarjetaColaborador m1 = new TarjetaColaborador("uno");
        TarjetaColaborador m2 = new TarjetaColaborador("hola");
        ITarjetasColaboradorRepository repositorio = (ITarjetasColaboradorRepository) ServiceLocator.get("tarjetasColaboradorRepository");
        repositorio.guardar(m);
        repositorio.guardar(m1);
        repositorio.guardar(m2);

        repositorio.eliminar(m1);
        m2.setMotivo("lo cambio");
        m2.setUpdated_at(LocalDateTime.of(2023,1,13,1,3));
      repositorio.actualizar(m2);

        Optional<TarjetaColaborador> tarjeta1 = repositorio.buscar(1L);
        //System.out.println(hidratado.get().getMotivo());
        Optional<TarjetaColaborador> tarjeta2 = repositorio.buscar(2L);

        List<TarjetaColaborador> lista = repositorio.buscarTodos();

    }*/
}
