package ar.edu.utn.frba.dds.models.repositories.imp;

import ar.edu.utn.frba.dds.models.domain.tecnicos.Tecnico;
import ar.edu.utn.frba.dds.models.domain.utils.TipoDocumento;
import ar.edu.utn.frba.dds.models.repositories.ITecnicosRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import lombok.NoArgsConstructor;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public class TecnicosRepository implements ITecnicosRepository, WithSimplePersistenceUnit {

  @Override
  public Optional<Tecnico> buscar(TipoDocumento tipoDocumento, String documento) {
    try {
      Tecnico t = entityManager().createQuery("from Tecnico where tipoDocumento=:tipoDocumento and nroDocumento=:documento and activo=:activo",Tecnico.class)
          .setParameter("tipoDocumento", tipoDocumento)
          .setParameter("documento", documento)
          .setParameter("activo",true)
          .getSingleResult();
      return Optional.ofNullable(t);
    } catch (NoResultException e) {
      return Optional.empty();
    }

  }

  public Optional<Tecnico> buscar(String id) {
    return Optional.ofNullable(entityManager().find(Tecnico.class, id));
  }

  @Override
  public List<Tecnico> buscarTodos() {
    return entityManager().createQuery("from Tecnico where activo=:activo", Tecnico.class).
        setParameter("activo", true)
        .getResultList();
  }

  @Override
  public void guardar(Tecnico tecnico) {
    withTransaction(() -> entityManager().persist(tecnico));
  }

  public void guardar(Tecnico... tecnico) {

    withTransaction(() -> {
      for (Tecnico tec : tecnico) {
        entityManager().persist(tec);
      }
    });
  }

  @Override
  public void actualizar(Tecnico tecnico) {
    withTransaction(() -> entityManager().merge(tecnico));
  }

  @Override
  public void eliminar(Tecnico tecnico) {

    withTransaction(() -> {
      tecnico.borrarLogico();
      entityManager().merge(tecnico);
    });
  }

  /*public static void main(String[] args) {
        Tecnico m = new Tecnico("otro");
        Tecnico m1 = new Tecnico("uno");
        Tecnico m2 = new Tecnico("hola");
        ITecnicosRepository repositorio = (ITecnicosRepository) ServiceLocator.get("tecnicosRepository");
        repositorio.guardar(m);
        repositorio.guardar(m1);
        repositorio.guardar(m2);

        repositorio.eliminar(m1);
        m2.setMotivo("lo cambio");
        m2.setUpdated_at(LocalDateTime.of(2023,1,13,1,3));
      repositorio.actualizar(m2);

        Optional<Tecnico> tecnico1 = repositorio.buscar(1L);
        //System.out.println(hidratado.get().getMotivo());
        Optional<Tecnico> tecnico2 = repositorio.buscar(2L);

        List<Tecnico> lista = repositorio.buscarTodos();

    }*/

}
