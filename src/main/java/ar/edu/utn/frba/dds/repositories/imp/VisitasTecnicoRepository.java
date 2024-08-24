package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.tecnicos.VisitaTecnico;
import ar.edu.utn.frba.dds.repositories.IVisitasTecnicoRepository;
import java.util.List;
import java.util.Optional;

public class VisitasTecnicoRepository implements IVisitasTecnicoRepository {
  @Override
  public Optional<VisitaTecnico> buscar(long id) {
    return Optional.empty();
  }

  @Override
  public List<VisitaTecnico> buscarTodos() {
    return List.of();
  }

  @Override
  public void guardar(VisitaTecnico visitaTecnico) {

  }

  @Override
  public void actualizar(VisitaTecnico visitaTecnico) {

  }

  @Override
  public void eliminar(VisitaTecnico visitaTecnico) {

  }
}
