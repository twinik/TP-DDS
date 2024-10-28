package ar.edu.utn.frba.dds.models.repositories.imp;

import ar.edu.utn.frba.dds.models.domain.tecnicos.VisitaTecnico;
import ar.edu.utn.frba.dds.models.repositories.IVisitasTecnicoRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public class VisitasTecnicoRepository implements IVisitasTecnicoRepository, WithSimplePersistenceUnit {
  @Override
  public Optional<VisitaTecnico> buscar(String id) {
    return Optional.ofNullable(entityManager().find(VisitaTecnico.class, id));
  }

  @Override
  public List<VisitaTecnico> buscarTodos() {
    return entityManager().createQuery("from VisitaTecnico where activo=:activo", VisitaTecnico.class).
        setParameter("activo", true)
        .getResultList();
  }

  @Override
  public void guardar(VisitaTecnico visitaTecnico) {
    withTransaction(() -> entityManager().persist(visitaTecnico));
  }

  @Override
  public void actualizar(VisitaTecnico visitaTecnico) {
    withTransaction(() -> entityManager().merge(visitaTecnico));
  }

  @Override
  public void eliminar(VisitaTecnico visitaTecnico) {

    withTransaction(() -> {
      visitaTecnico.borrarLogico();
      entityManager().merge(visitaTecnico);
    });
  }


}
