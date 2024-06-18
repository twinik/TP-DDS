package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.incidentes.FallaTecnica;
import ar.edu.utn.frba.dds.repositories.IFallasTecnicasRepository;
import java.util.List;
import java.util.Optional;

public class FallasTecnicasRepository implements IFallasTecnicasRepository {

  List<FallaTecnica> fallaTecnicas;

  @Override
  public Optional<FallaTecnica> buscar(int id) {
    return Optional.empty();
  }

  @Override
  public List<FallaTecnica> buscarTodos() {
    return this.fallaTecnicas;
  }

  @Override
  public void guardar(FallaTecnica fallaTecnica) {
    this.fallaTecnicas.add(fallaTecnica);
  }

  @Override
  public void actualizar(FallaTecnica fallaTecnica) {

  }

  @Override
  public void eliminar(FallaTecnica fallaTecnica) {

  }
}
