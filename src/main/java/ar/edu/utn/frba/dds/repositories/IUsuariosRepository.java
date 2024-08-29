package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.domain.colaboradores.Usuario;
import java.util.List;
import java.util.Optional;

public interface IUsuariosRepository {
    Optional<Usuario> buscar(Long id);

    List<Usuario> buscarTodos();

    void guardar(Usuario usuario);

    void actualizar(Usuario usuario);

    void eliminar(Usuario usuario);
}
