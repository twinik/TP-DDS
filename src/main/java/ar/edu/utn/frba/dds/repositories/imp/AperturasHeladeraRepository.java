package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.heladeras.AperturaHeladera;
import ar.edu.utn.frba.dds.repositories.IAperturasHeladeraRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AperturasHeladeraRepository implements IAperturasHeladeraRepository {

  private List<AperturaHeladera> aperturas;

  public AperturasHeladeraRepository() {
    this.aperturas = new ArrayList<>();
  }

  @Override
  public Optional<AperturaHeladera> buscar(long id) {
    return this.aperturas.stream().filter(a -> a.getId() == id).findFirst();
  }

  @Override
  public List<AperturaHeladera> buscarTodos() {
    return this.aperturas;
  }

  @Override
  public void guardar(AperturaHeladera aperturaHeladera) {
    this.aperturas.add(aperturaHeladera);
  }

  @Override
  public void actualizar(AperturaHeladera aperturaHeladera) {

  }

  @Override
  public void eliminar(AperturaHeladera aperturaHeladera) {

  }
}
