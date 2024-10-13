package ar.edu.utn.frba.dds.models.repositories;

import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;
import java.util.List;
import java.util.Optional;

public interface IHeladerasRepository {
    Optional<Heladera> buscar(String id);

    List<Heladera> buscarTodos();

    Heladera buscarPorNombre(String nombre);

    void guardar(Heladera heladera);

    void actualizar(Heladera heladera);

    void actualizar(List<Heladera> heladeras);

    void eliminar(Heladera heladera);

}
