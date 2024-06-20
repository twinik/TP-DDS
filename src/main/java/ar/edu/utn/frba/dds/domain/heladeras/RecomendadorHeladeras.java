package ar.edu.utn.frba.dds.domain.heladeras;

import ar.edu.utn.frba.dds.repositories.IHeladerasRepository;
import java.util.ArrayList;
import java.util.List;

public class RecomendadorHeladeras {

  private IHeladerasRepository heladerasRepository;

  public RecomendadorHeladeras(IHeladerasRepository heladerasRepository) {
    this.heladerasRepository = heladerasRepository;
  }

  public List<Heladera> recomendarCombinacionHeladeras(Heladera heladera) {
    int totalViandas = heladera.getViandas().size();

    List<Heladera> heladerasCercanas = heladerasRepository.heladerasCercanas(heladera);

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