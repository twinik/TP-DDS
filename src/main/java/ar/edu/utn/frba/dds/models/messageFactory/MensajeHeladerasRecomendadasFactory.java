package ar.edu.utn.frba.dds.models.messageFactory;

import static java.lang.String.format;

import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;
import java.util.List;

public class MensajeHeladerasRecomendadasFactory {
    public static String GenerarMensaje(List<Heladera> heladeras, Heladera heladeraAfectada) {
        StringBuilder builder = new StringBuilder("La heladera " + heladeraAfectada.getNombre() + " ha sufrido un desperfecto");
        heladeras.forEach(h -> builder.append(format("\nIr a heladera %s que le sobran %d cupos de viandas\n", h.getNombre(), h.getCuposLibresViandas())));
        return builder.toString();
    }
}
