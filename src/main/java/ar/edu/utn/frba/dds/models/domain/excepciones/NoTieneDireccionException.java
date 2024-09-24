package ar.edu.utn.frba.dds.models.domain.excepciones;

public class NoTieneDireccionException extends RuntimeException {
    public NoTieneDireccionException(String messgae) {
        super(messgae);
    }
}
