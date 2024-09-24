package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.domain.colaboraciones.DonacionVianda;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * IColaboradorRepository interface permite interactuar con los donacions de viandas
 */
public interface IDonacionesViandaRepository {
    Optional<DonacionVianda> buscar(String id);

    List<DonacionVianda> buscarTodos();

    List<DonacionVianda> buscarPorColaborador(String colaborador_id);

    Map<String, Long> buscarDonacionesAgrupadasPorHeladera(LocalDate fecha);

    void guardar(DonacionVianda donacionVianda);

    void actualizar(DonacionVianda donacionVianda);

    void eliminar(DonacionVianda donacionVianda);
}
