package ar.edu.utn.frba.dds.repositories;
import ar.edu.utn.frba.dds.domain.colaboradores.FormaColaboracion;
import java.util.List;
import java.util.Optional;

public interface IFormasColaboracionRespository {
  Optional<FormaColaboracion> buscar(Long id);

  Optional<FormaColaboracion> buscar(String nombre);

  List<FormaColaboracion> buscarTodos();

  void guardar(FormaColaboracion forma);

  void actualizar(FormaColaboracion forma);

  void eliminar(FormaColaboracion forma);
}
