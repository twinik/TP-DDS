package ar.edu.utn.frba.dds.models.messageFactory;

import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;

public class MensajeViandasRestantesFactory {
    public static String generarMensaje(Heladera heladera) {
        return "Quedan " + heladera.getViandas() + " viandas en la heladera " + heladera.getNombre();
    }
}
