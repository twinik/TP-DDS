package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.domain.colaboradores.form.Campo;
import java.util.List;
import java.util.Optional;

public interface ICampoRepository {
    Optional<Campo> buscar(String id);

    List<Campo> buscarTodos();

    void guardar(Campo campo);

    void actualizar(Campo campo);

    void eliminar(Campo campo);
}
