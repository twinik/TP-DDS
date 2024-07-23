package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.tecnicos.Tecnico;
import ar.edu.utn.frba.dds.repositories.ITecnicosRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TecnicosRepository implements ITecnicosRepository {

  private ArrayList<Tecnico> tecnicos;

  public TecnicosRepository() {
    tecnicos = new ArrayList<>();
  }

  @Override
  public Optional<Tecnico> buscar(String codigo) {
    // PONELE
    return this.tecnicos.stream().filter(t -> t.getNroDocumento().equals(codigo)).findFirst();
  }

  public Optional<Tecnico> buscar(long id) {
    return this.tecnicos.stream().filter(t -> t.getId() == id).findFirst();
  }

  @Override
  public List<Tecnico> buscarTodos() {
    return this.tecnicos;
  }

  @Override
  public void guardar(Tecnico tecnico) {
    this.tecnicos.add(tecnico);
  }

  @Override
  public void actualizar(Tecnico tecnico) {

  }

  @Override
  public void eliminar(Tecnico tecnico) {

  }
}
