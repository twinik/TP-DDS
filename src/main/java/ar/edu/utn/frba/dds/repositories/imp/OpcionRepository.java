package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.colaboraciones.OfertaProducto;
import ar.edu.utn.frba.dds.domain.colaboraciones.utils.MotivoRedistribucionVianda;
import ar.edu.utn.frba.dds.domain.colaboradores.form.Opcion;
import ar.edu.utn.frba.dds.repositories.IOfertaProductoRepository;
import ar.edu.utn.frba.dds.repositories.IOpcionRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.List;
import java.util.Optional;

public class OpcionRepository implements IOpcionRepository, WithSimplePersistenceUnit {
  @Override
  public Optional<Opcion> buscar(Long id) {
    return Optional.ofNullable(entityManager().find(Opcion.class,id));
  }

  @Override
  public List<Opcion> buscarTodos() {
    return entityManager().createQuery("from Opcion where activo=:activo",Opcion.class).
            setParameter("activo",true)
            .getResultList();
  }

  @Override
  public void guardar(Opcion opcion) {
    withTransaction(() -> entityManager().persist(opcion));
  }
  public void guardar(Opcion ...opcion) {

    withTransaction(() -> {
      for (Opcion op : opcion){
        entityManager().persist(op);
      }
    });
  }

  @Override
  public void actualizar(Opcion opcion) {
    withTransaction(() -> entityManager().merge(opcion));
  }

  @Override
  public void eliminar(Opcion opcion) {
    opcion.setActivo(false);
    withTransaction(() -> entityManager().merge(opcion));
  }

  /*public static void main(String[] args) {
        Opcion m = new Opcion("otro");
        Opcion m1 = new Opcion("uno");
        Opcion m2 = new Opcion("hola");
        IOpcionRepository repositorio = (IOpcionRepository) ServiceLocator.get("opcionRepository");
        repositorio.guardar(m);
        repositorio.guardar(m1);
        repositorio.guardar(m2);

        repositorio.eliminar(m1);
        m2.setMotivo("lo cambio");
        m2.setUpdated_at(LocalDateTime.of(2023,1,13,1,3));
      repositorio.actualizar(m2);

        Optional<Opcion> opcion1 = repositorio.buscar(1L);
        //System.out.println(hidratado.get().getMotivo());
        Optional<Opcion> opcion2 = repositorio.buscar(2L);

        List<Opcion> lista = repositorio.buscarTodos();

    }*/
}
