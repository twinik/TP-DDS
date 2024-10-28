package ar.edu.utn.frba.dds.models.repositories;

import ar.edu.utn.frba.dds.models.domain.colaboradores.form.Formulario;
import java.util.List;
import java.util.Optional;

public interface IFormularioRepository {
  Optional<Formulario> buscar(String id);

  List<Formulario> buscarTodos();

  void guardar(Formulario formulario);

  void actualizar(Formulario formulario);

  void eliminar(Formulario formulario);
}
