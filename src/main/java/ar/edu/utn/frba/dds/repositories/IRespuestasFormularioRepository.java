package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.domain.colaboradores.form.RespuestaFormulario;
import java.util.List;
import java.util.Optional;

/**
 * IRespuestasFormularioRepository interface permite interactuar con las Respuestas de un Formulario.
 */
public interface IRespuestasFormularioRepository {
  Optional<RespuestaFormulario> buscar(Long id);

  List<RespuestaFormulario> buscarTodos();

  void guardar(RespuestaFormulario respuestaFormulario);

  void actualizar(RespuestaFormulario respuestaFormulario);

  void eliminar(RespuestaFormulario respuestaFormulario);
}
