package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.heladeras.RegistroTemperatura;
import ar.edu.utn.frba.dds.repositories.IRegistrosTemperaturaRepository;
import java.util.List;
import java.util.Optional;

public class RegistrosTemperaturaRepository implements IRegistrosTemperaturaRepository {


  @Override
  public Optional<RegistroTemperatura> buscar(long id) {
    return Optional.empty();
  }

  @Override
  public List<RegistroTemperatura> buscarTodos() {
    return List.of();
  }

  @Override
  public void guardar(RegistroTemperatura registroTemperatura) {

  }

  @Override
  public void actualizar(RegistroTemperatura registroTemperatura) {

  }

  @Override
  public void eliminar(RegistroTemperatura registroTemperatura) {

  }
}
