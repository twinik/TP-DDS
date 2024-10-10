package ar.edu.utn.frba.dds.models.repositories;

import ar.edu.utn.frba.dds.models.domain.tarjetas.TarjetaColaborador;
import java.util.List;
import java.util.Optional;

public interface ITarjetasColaboradorRepository {
  Optional<TarjetaColaborador> buscarPorCodigo(String codigo);

  Optional<TarjetaColaborador> buscar(String id);

  List<TarjetaColaborador> buscarTodos();

  void guardar(TarjetaColaborador tarjeta);

  void actualizar(TarjetaColaborador tarjeta);

  void eliminar(TarjetaColaborador tarjeta);
}
