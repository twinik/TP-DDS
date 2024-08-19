package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.colaboradores.FormaColaboracion;
import ar.edu.utn.frba.dds.repositories.IFormasColaboracionRespository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IFormasColaboracionRespositoryImpl implements IFormasColaboracionRespository {

  private List<FormaColaboracion> formas;

  public IFormasColaboracionRespositoryImpl() {
    this.formas = new ArrayList<>();
  }

  @Override
  public Optional<FormaColaboracion> buscar(long id) {
    return this.formas.stream().filter(f -> f.getId() == id).findFirst();
  }

  @Override
  public Optional<FormaColaboracion> buscar(String nombre) {
    return this.formas.stream().filter(f -> f.getNombre().equals(nombre)).findFirst();
  }


  @Override
  public List<FormaColaboracion> buscarTodos() {
    return this.formas;
  }

  @Override
  public void guardar(FormaColaboracion forma) {
    this.formas.add(forma);
  }

  @Override
  public void actualizar(FormaColaboracion forma) {

  }

  @Override
  public void eliminar(FormaColaboracion forma) {

  }
}
