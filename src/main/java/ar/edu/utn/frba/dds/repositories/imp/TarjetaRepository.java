package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.tarjetas.Tarjeta;
import ar.edu.utn.frba.dds.repositories.ITarjetasRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TarjetaRepository implements ITarjetasRepository {

  private List<Tarjeta> tarjetas;

  public TarjetaRepository() {
    this.tarjetas = new ArrayList<>();
  }

  @Override
  public Optional<Tarjeta> buscar(String codigo) {
    return this.tarjetas.stream().filter(t -> t.getCodigo().equals(codigo)).findFirst();
  }

  @Override
  public Optional<Tarjeta> buscar(long id) {
    return this.tarjetas.stream().filter(t -> t.getId() == id).findFirst();
  }

  @Override
  public List<Tarjeta> buscarTodos() {
    return this.tarjetas;
  }

  @Override
  public void guardar(Tarjeta tarjeta) {
    this.tarjetas.add(tarjeta);
  }

  @Override
  public void actualizar(Tarjeta tarjeta) {
    // nada
  }


  @Override
  public void eliminar(Tarjeta tarjeta) {

  }
}
