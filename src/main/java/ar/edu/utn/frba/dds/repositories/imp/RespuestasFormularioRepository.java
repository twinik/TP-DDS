package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.colaboradores.form.RespuestaFormulario;
import ar.edu.utn.frba.dds.repositories.IRespuestasFormularioRepository;
import java.util.List;
import java.util.Optional;

public class RespuestasFormularioRepository implements IRespuestasFormularioRepository{

  @Override
  public Optional<RespuestaFormulario> buscar(long id) {
    return Optional.empty();
  }

  @Override
  public List<RespuestaFormulario> buscarTodos() {
    return List.of();
  }

  @Override
  public void guardar(RespuestaFormulario respuestaFormulario) {

  }

  @Override
  public void actualizar(RespuestaFormulario respuestaFormulario) {

  }

  @Override
  public void eliminar(RespuestaFormulario respuestaFormulario) {

  }
}
