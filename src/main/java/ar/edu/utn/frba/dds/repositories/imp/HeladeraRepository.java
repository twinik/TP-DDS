package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.colaboraciones.utils.MotivoRedistribucionVianda;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.repositories.IHeladerasRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HeladeraRepository implements IHeladerasRepository, WithSimplePersistenceUnit {

  private List<Heladera> heladeras;

  public HeladeraRepository() {
    this.heladeras = new ArrayList<>();
  }

  @Override
  public Optional<Heladera> buscar(long id) {
    return Optional.ofNullable(entityManager().find(Heladera.class,id));
  }

  @Override
  public List<Heladera> buscarTodos() {
    return entityManager().createQuery("from Heladera where activo=:activo",Heladera.class).
            setParameter("activo",true)
            .getResultList();
  }

  @Override
  public void guardar(Heladera heladera) {
    withTransaction(() -> entityManager().persist(heladera));
  }

  public void guardar(Heladera ...heladera) {

    withTransaction(() -> {
      for (Heladera hela : heladera){
        entityManager().persist(hela);
      }
    });
  }

  @Override
  public void actualizar(Heladera heladera) {
    withTransaction(() -> entityManager().merge(heladera));
  }

  @Override
  public void eliminar(Heladera heladera) {
    heladera.setActivo(false);
    withTransaction(() -> entityManager().merge(heladera));
  }

//  @Override
//  public List<Heladera> heladerasCercanas(Heladera heladera, int limite) {
//    return heladeras.stream()
//        .filter(Heladera::isActiva)
//        .sorted(Comparator.comparing(h -> h.getUbicacion().calcularDistanciaHasta(heladera.getUbicacion())))
//        .limit(limite)
//        .collect(Collectors.toList());
//  }

  /*public static void main(String[] args) {
        Heladera m = new Heladera("otro");
        Heladera m1 = new Heladera("uno");
        Heladera m2 = new Heladera("hola");
        IHeladerasRepository repositorio = (IHeladerasRepository) ServiceLocator.get("heladerasRepository");
        repositorio.guardar(m);
        repositorio.guardar(m1);
        repositorio.guardar(m2);

        repositorio.eliminar(m1);
        m2.setMotivo("lo cambio");
        m2.setUpdated_at(LocalDateTime.of(2023,1,13,1,3));
      repositorio.actualizar(m2);

        Optional<Heladera> heladera1 = repositorio.buscar(1L);
        //System.out.println(hidratado.get().getMotivo());
        Optional<Heladera> heladera2 = repositorio.buscar(2L);

        List<Heladera> lista = repositorio.buscarTodos();

    }*/
}
