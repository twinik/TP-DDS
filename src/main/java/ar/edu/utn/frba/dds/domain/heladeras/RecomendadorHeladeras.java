package ar.edu.utn.frba.dds.domain.heladeras;

import ar.edu.utn.frba.dds.repositories.IHeladerasRepository;
import lombok.AllArgsConstructor;
import java.util.*;

/**
 * Recomienda, a partir de una heladera, a que heldares se puede ir para redistriuir todas sus viandas
 */
@AllArgsConstructor
public class RecomendadorHeladeras {

    private IHeladerasRepository heladerasRepository;

    public List<Heladera> recomendarHeladeras(Heladera heladera) {
        // TODO implement here
        return null;
    }

}