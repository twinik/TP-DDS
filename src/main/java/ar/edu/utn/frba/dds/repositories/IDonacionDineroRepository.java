package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.domain.colaboraciones.DonacionDinero;
import java.util.List;
import java.util.Optional;

public interface IDonacionDineroRepository {
    Optional<DonacionDinero> buscar(Long id);

    List<DonacionDinero> buscarTodos();

    void guardar(DonacionDinero donacionDinero);

    void actualizar(DonacionDinero donacionDinero);

    void eliminar(DonacionDinero donacionDinero);
}
