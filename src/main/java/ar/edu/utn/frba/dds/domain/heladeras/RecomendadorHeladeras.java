package ar.edu.utn.frba.dds.domain.heladeras;

import ar.edu.utn.frba.dds.repositories.IHeladerasRepository;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 */
@NoArgsConstructor
public class RecomendadorHeladeras {

  public List<Heladera> recomendarCombinacionHeladeras(Heladera heladera) {
    int totalViandas = heladera.getViandas().size();

    List<Heladera> heladerasCercanas = heladera.getHeladerasCercanas();

    List<Heladera> heladerasSeleccionadas = new ArrayList<>();
    int capacidadAcumulada = 0;

    for (Heladera h : heladerasCercanas) {
      if (h.getCapacidadViandas() > 0) {
        heladerasSeleccionadas.add(h);
        capacidadAcumulada += h.getCapacidadViandas();
        if (capacidadAcumulada >= totalViandas) {
          break;
        }
      }
    }
    return heladerasSeleccionadas;
  }

}