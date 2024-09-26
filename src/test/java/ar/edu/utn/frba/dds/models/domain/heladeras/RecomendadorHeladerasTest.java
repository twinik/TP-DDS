package ar.edu.utn.frba.dds.models.domain.heladeras;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

class RecomendadorHeladerasTest {

    private RecomendadorHeladeras recomendadorHeladeras;

    @BeforeEach
    void setUp() {
        recomendadorHeladeras = new RecomendadorHeladeras();
    }

    @Test
    void recomendarCombinacionHeladerasTest() {

        Heladera heladera1 = new Heladera(null, null, "Heladera 1", 2, LocalDate.now(), null, null);
        Heladera heladera2 = new Heladera(null, null, "Heladera 2", 1, LocalDate.now(), null, null);
        Heladera heladera3 = new Heladera(null, null, "Heladera 3", 4, LocalDate.now(), null, null);


        Vianda vianda1 = new Vianda("Comida 1", null, LocalDate.now(), null, null, null, null, false);
        Vianda vianda2 = new Vianda("Comida 2", null, LocalDate.now(), null, null, null, null, false);
        Vianda vianda3 = new Vianda("Comida 3", null, LocalDate.now(), null, null, null, null, false);
        Vianda vianda4 = new Vianda("Comida 4", null, LocalDate.now(), null, null, null, null, false);
        Vianda vianda5 = new Vianda("Comida 5", null, LocalDate.now(), null, null, null, null, false);
        Heladera heladeraObjetivo = new Heladera(null, null, "Heladera Objetivo", null, LocalDate.now(), Arrays.asList(vianda1, vianda2, vianda3, vianda4, vianda5), null);
        Heladera[] heladerasTop = new Heladera[3];
        heladerasTop[0] = heladera1;
        heladerasTop[1] = heladera2;
        heladerasTop[2] = heladera3;
        heladeraObjetivo.agregarHeladeraCercana(heladerasTop);

        List<Heladera> resultado = recomendadorHeladeras.recomendarCombinacionHeladeras(heladeraObjetivo);

        // Verificar el resultado
        assertEquals(3, resultado.size());
    }
}