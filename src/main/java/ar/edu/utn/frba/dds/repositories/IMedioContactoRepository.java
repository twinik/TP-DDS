package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.domain.heladeras.Vianda;
import ar.edu.utn.frba.dds.domain.utils.MedioDeContacto;
import java.util.List;
import java.util.Optional;

public interface IMedioContactoRepository {
  Optional<MedioDeContacto> buscar(Long id);

  List<MedioDeContacto> buscarTodos();

  void guardar(MedioDeContacto medioDeContacto);

  void actualizar(MedioDeContacto medioDeContacto);

  void eliminar(MedioDeContacto medioDeContacto);
}
