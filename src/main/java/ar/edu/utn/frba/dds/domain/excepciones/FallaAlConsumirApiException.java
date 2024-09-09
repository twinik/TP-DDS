package ar.edu.utn.frba.dds.domain.excepciones;

/**
 * Clase: FallaAlConsumirApiException para cuando falla la conexion con la API
 */
public class FallaAlConsumirApiException extends RuntimeException {
    public FallaAlConsumirApiException(Exception e) {
        super(e);
    }
}
