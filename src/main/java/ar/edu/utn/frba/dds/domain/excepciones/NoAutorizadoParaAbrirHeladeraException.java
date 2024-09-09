package ar.edu.utn.frba.dds.domain.excepciones;

public class NoAutorizadoParaAbrirHeladeraException extends RuntimeException {
    public NoAutorizadoParaAbrirHeladeraException(String message) {
        super(message);
    }
}
