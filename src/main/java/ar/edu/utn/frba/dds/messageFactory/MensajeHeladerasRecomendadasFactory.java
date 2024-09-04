package ar.edu.utn.frba.dds.messageFactory;

import static java.lang.String.format;

import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import java.util.List;

public class MensajeHeladerasRecomendadasFactory {
    public static String GenerarMensaje(List<Heladera> heladeras) {
        StringBuilder builder = new StringBuilder();
        heladeras.forEach(h -> builder.append(format("Ir a heladera %s que le sobran %d cupos de viandas\n", h.getNombre(), h.getCuposLibresViandas())));
        return builder.toString();
    }
}
