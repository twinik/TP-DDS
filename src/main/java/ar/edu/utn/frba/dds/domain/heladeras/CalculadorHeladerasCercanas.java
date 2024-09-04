package ar.edu.utn.frba.dds.domain.heladeras;

import ar.edu.utn.frba.dds.repositories.IHeladerasRepository;
import lombok.AllArgsConstructor;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Perfectu. Me parece bien. O quizás cada vez que se agrega una nueva y se
 * calculan sus cercanas, habría que agregar esa misma heladera nueva a la
 * colección de la otra heladera (de su cercana), así ambas se conocen y listo. No?
 */
@AllArgsConstructor
public class CalculadorHeladerasCercanas {
  private IHeladerasRepository heladerasRepository;
  private int limite;

  public List<Heladera> getHeladerasCercanasA(Heladera heladera){
    return heladerasRepository.buscarTodos().stream()
        .filter(Heladera::isHeladeraActiva)
        .sorted(Comparator.comparing(h -> h.getUbicacion().calcularDistanciaHasta(heladera.getUbicacion())))
        .limit(this.limite)
        .collect(Collectors.toList());
  }

}
