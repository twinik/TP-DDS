package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.colaboraciones.utils.MotivoRedistribucionVianda;
import ar.edu.utn.frba.dds.domain.tecnicos.VisitaTecnico;
import ar.edu.utn.frba.dds.repositories.IVisitasTecnicoRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.List;
import java.util.Optional;

public class VisitasTecnicoRepository implements IVisitasTecnicoRepository, WithSimplePersistenceUnit {
  @Override
  public Optional<VisitaTecnico> buscar(Long id) {
    return Optional.ofNullable(entityManager().find(VisitaTecnico.class,id));
  }

  @Override
  public List<VisitaTecnico> buscarTodos() {
    return entityManager().createQuery("from VisitaTecnico where activo=:activo",VisitaTecnico.class).
            setParameter("activo",true)
            .getResultList();
  }

  @Override
  public void guardar(VisitaTecnico visitaTecnico) {
    withTransaction(() -> entityManager().persist(visitaTecnico));
  }
  public void guardar(VisitaTecnico ...visitaTecnico) {

    withTransaction(() -> {
      for (VisitaTecnico visita : visitaTecnico){
        entityManager().persist(visita);
      }
    });
  }

  @Override
  public void actualizar(VisitaTecnico visitaTecnico) {
    withTransaction(() -> entityManager().merge(visitaTecnico));
  }

  @Override
  public void eliminar(VisitaTecnico visitaTecnico) {
    visitaTecnico.setActivo(false);
    withTransaction(() -> entityManager().merge(visitaTecnico));
  }

  /*public static void main(String[] args) {
        VisitaTecnico m = new VisitaTecnico("otro");
        VisitaTecnico m1 = new VisitaTecnico("uno");
        VisitaTecnico m2 = new VisitaTecnico("hola");
        IVisitasTecnicoRepository repositorio = (IVisitasTecnicoRepository) ServiceLocator.get("visitasTecnicoRepository");
        repositorio.guardar(m);
        repositorio.guardar(m1);
        repositorio.guardar(m2);

        repositorio.eliminar(m1);
        m2.setMotivo("lo cambio");
        m2.setUpdated_at(LocalDateTime.of(2023,1,13,1,3));
      repositorio.actualizar(m2);

        Optional<VisitaTecnico> visitaTecnico1 = repositorio.buscar(1L);
        //System.out.println(hidratado.get().getMotivo());
        Optional<VisitaTecnico> visitaTecnico2 = repositorio.buscar(2L);

        List<VisitaTecnico> lista = repositorio.buscarTodos();

    }*/

}
