package ar.edu.utn.frba.dds.models.repositories;

import ar.edu.utn.frba.dds.models.domain.colaboradores.Usuario;
import java.util.List;
import java.util.Optional;

public interface IUsuariosRepository {
    Optional<Usuario> buscar(String id);

    List<Usuario> buscarTodos();

    void guardar(Usuario usuario);

    void actualizar(Usuario usuario);

    void eliminar(Usuario usuario);
}
