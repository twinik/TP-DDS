package ar.edu.utn.frba.dds.domain.heladeras;

import ar.edu.utn.frba.dds.repositories.IHeladerasRepository;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RecomendadorHeladeras {

    private IHeladerasRepository heladerasRepository;

    public RecomendadorHeladeras(IHeladerasRepository heladerasRepository) {
        this.heladerasRepository = heladerasRepository;
    }

    public List<Heladera> recomendarHeladeras(Heladera heladera) {
        int totalViandas = heladera.getViandas().size();

        return heladerasRepository.buscarTodos().stream()
                .filter(Heladera::isActiva)
                .filter(h -> h.getCapacidadViandas() >= totalViandas)
                .sorted(Comparator.comparing(h -> h.getUbicacion().calcularDistanciaHasta(heladera.getUbicacion())))
                .collect(Collectors.toList());
    }
}

/*
OTRA IDEA

public class RecomendadorHeladeras {

    private IHeladerasRepository heladerasRepository;

    public RecomendadorHeladeras(IHeladerasRepository heladerasRepository) {
        this.heladerasRepository = heladerasRepository;
    }

    public List<Heladera> recomendarCombinacionHeladeras(Heladera heladera) {
        int totalViandas = heladera.getViandas().size();

        List<Heladera> heladerasOrdenadas = heladerasRepository.buscarTodos().stream()
                .filter(Heladera::isActiva)
                .filter(h -> h.getCapacidadViandas() > 0)
                .sorted(Comparator.comparing(h -> h.getUbicacion().calcularDistanciaHasta(heladera.getUbicacion())))
                .collect(Collectors.toList());

        List<Heladera> heladerasSeleccionadas = new ArrayList<>();
        int capacidadAcumulada = 0;

        for (Heladera h : heladerasOrdenadas) {
            heladerasSeleccionadas.add(h);
            capacidadAcumulada += h.getCapacidadViandas();
            if (capacidadAcumulada >= totalViandas) {
                break;
            }
        }

        return heladerasSeleccionadas;
    }
}
 */