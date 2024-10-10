package ar.edu.utn.frba.dds.models.domain.excepciones;


public class CodigoInvalidoException extends RuntimeException{

    public CodigoInvalidoException(String message) {
        super(message);
    }
}
