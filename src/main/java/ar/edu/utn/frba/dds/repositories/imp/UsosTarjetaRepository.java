package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.tarjetas.UsoTarjeta;
import ar.edu.utn.frba.dds.repositories.IUsosTarjetaRepository;
import java.util.List;
import java.util.Optional;

public class UsosTarjetaRepository implements IUsosTarjetaRepository {


  @Override
  public Optional<UsoTarjeta> buscar(long id) {
    return Optional.empty();
  }

  @Override
  public List<UsoTarjeta> buscarTodos() {
    return List.of();
  }

  @Override
  public void guardar(UsoTarjeta usoTarjeta) {

  }

  @Override
  public void actualizar(UsoTarjeta usoTarjeta) {

  }

  @Override
  public void eliminar(UsoTarjeta usoTarjeta) {

  }
}
