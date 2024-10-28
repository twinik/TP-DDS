package ar.edu.utn.frba.dds.models.messageFactory;

public class MensajeFilaCsvInvalidaFactory {
    public static String generarMensaje(int nroFila, String motivo) {
        return "Error en la fila número " + nroFila + " Motivo: " + motivo;
    }

    public static String generarMensaje(String motivo) {
        return "Motivo: " + motivo;
    }

}
