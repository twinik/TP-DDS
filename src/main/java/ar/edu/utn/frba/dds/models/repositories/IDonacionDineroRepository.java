package ar.edu.utn.frba.dds.models.repositories;

import ar.edu.utn.frba.dds.models.domain.colaboraciones.DonacionDinero;
import java.util.List;
import java.util.Optional;

public interface IDonacionDineroRepository {
    Optional<DonacionDinero> buscar(String id);

    List<DonacionDinero> buscarTodos();

    void guardar(DonacionDinero donacionDinero);

    void actualizar(DonacionDinero donacionDinero);

    void eliminar(DonacionDinero donacionDinero);
}
