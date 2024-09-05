package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.domain.colaboraciones.RedistribucionViandas;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.heladeras.Vianda;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * IRedistribucionesRepository interface permite interactuar con las redistribuciones de viandas
 */
public interface IRedistribucionesViandaRepository {

    Optional<RedistribucionViandas> buscar(Long id);

    List<RedistribucionViandas> buscarTodos();

    List<RedistribucionViandas> buscarTodosMismaSemana(LocalDate fecha);

    Map<Heladera,Long> buscarViandasColocadasPorHeladera(LocalDate fecha);

    Map<Heladera, Long> buscarViandasRetiradasPorHeladera(LocalDate fecha);

    void guardar(RedistribucionViandas redistribucionViandas);

    void actualizar(RedistribucionViandas redistribucionViandas);

    void eliminar(RedistribucionViandas redistribucionViandas);
}
