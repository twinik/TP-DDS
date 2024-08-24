package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.colaboradores.form.RespuestaACampo;
import ar.edu.utn.frba.dds.repositories.IRespuestasCampoRepository;
import java.util.List;
import java.util.Optional;

public class RespuestasCampoRepository implements IRespuestasCampoRepository {


  @Override
  public Optional<RespuestaACampo> buscar(long id) {
    return Optional.empty();
  }

  @Override
  public List<RespuestaACampo> buscarTodos() {
    return List.of();
  }

  @Override
  public void guardar(RespuestaACampo respuestaACampo) {

  }

  @Override
  public void actualizar(RespuestaACampo respuestaACampo) {

  }

  @Override
  public void eliminar(RespuestaACampo respuestaACampo) {

  }
}