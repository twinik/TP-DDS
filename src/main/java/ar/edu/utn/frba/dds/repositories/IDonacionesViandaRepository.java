package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.domain.colaboraciones.DonacionVianda;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * IColaboradorRepository interface permite interactuar con los donacions de viandas
 */
public interface IDonacionesViandaRepository {
    Optional<DonacionVianda> buscar(Long id);

    List<DonacionVianda> buscarTodos();

    List<DonacionVianda> buscarPorColaborador(Colaborador c);

    Map<String, Long> buscarDonacionesAgrupadasPorHeladera(LocalDate fecha);

    void guardar(DonacionVianda donacionVianda);

    void actualizar(DonacionVianda donacionVianda);

    void eliminar(DonacionVianda donacionVianda);
}
