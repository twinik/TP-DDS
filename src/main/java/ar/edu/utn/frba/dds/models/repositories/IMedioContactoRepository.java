package ar.edu.utn.frba.dds.models.repositories;

import ar.edu.utn.frba.dds.models.domain.utils.MedioDeContacto;
import java.util.List;
import java.util.Optional;

public interface IMedioContactoRepository {
    Optional<MedioDeContacto> buscar(String id);

    List<MedioDeContacto> buscarTodos();

    void guardar(MedioDeContacto medioDeContacto);

    void actualizar(MedioDeContacto medioDeContacto);

    void eliminar(MedioDeContacto medioDeContacto);
}
