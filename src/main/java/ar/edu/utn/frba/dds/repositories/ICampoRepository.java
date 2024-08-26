package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.domain.colaboradores.form.Campo;
import ar.edu.utn.frba.dds.domain.tecnicos.AreaDeCobertura;

import java.util.List;
import java.util.Optional;

public interface ICampoRepository {
    Optional<Campo> buscar(long id);

    List<Campo> buscarTodos();

    void guardar(Campo campo);

    void actualizar(Campo campo);

    void eliminar(Campo campo);
}
