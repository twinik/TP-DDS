package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.domain.colaboraciones.OfertaProducto;
import ar.edu.utn.frba.dds.domain.colaboradores.form.Opcion;
import java.util.List;
import java.util.Optional;

public interface IOpcionRepository {
  Optional<Opcion> buscar(Long id);

  List<Opcion> buscarTodos();

  void guardar(Opcion opcion);

  void actualizar(Opcion opcion);

  void eliminar(Opcion opcion);
}
