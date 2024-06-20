package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.tarjetas.TarjetaColaborador;
import ar.edu.utn.frba.dds.repositories.ITarjetasColaboradorRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TarjetasColaboradorRepository implements ITarjetasColaboradorRepository {

  private List<TarjetaColaborador> tarjetas;

  public TarjetasColaboradorRepository() {
    this.tarjetas = new ArrayList<>();
  }

  @Override
  public Optional<TarjetaColaborador> buscar(String codigo) {
    return Optional.empty();
  }

  @Override
  public List<TarjetaColaborador> buscarTodos() {
    return this.tarjetas;
  }

  @Override
  public void guardar(TarjetaColaborador tarjeta) {
    this.tarjetas.add(tarjeta);
  }

  @Override
  public void actualizar(TarjetaColaborador tarjeta) {

  }

  @Override
  public void eliminar(TarjetaColaborador tarjeta) {

  }
}
