package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.repositories.IHeladerasRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HeladeraRepository implements IHeladerasRepository {

  private List<Heladera> heladeras;

  public HeladeraRepository() {
    this.heladeras = new ArrayList<>();
  }

  @Override
  public Optional<Heladera> buscar(int id) {
    // necesito la bd para esto
    return Optional.empty();
  }

  @Override
  public List<Heladera> buscarTodos() {
    return this.heladeras;
  }

  @Override
  public void guardar(Heladera heladera) {
    this.heladeras.add(heladera);
  }

  @Override
  public void actualizar(Heladera heladera) {

  }

  @Override
  public void eliminar(Heladera heladera) {

  }
}
