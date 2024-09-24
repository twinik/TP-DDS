package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.domain.colaboraciones.ColocacionHeladeras;
import java.util.List;
import java.util.Optional;

public interface IColocacionHeladeraRepository {
    Optional<ColocacionHeladeras> buscar(String id);

    List<ColocacionHeladeras> buscarTodos();

    void guardar(ColocacionHeladeras colocacionHeladera);

    void actualizar(ColocacionHeladeras colocacionHeladera);

    void eliminar(ColocacionHeladeras colocacionHeladera);
}
