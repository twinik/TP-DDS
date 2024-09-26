package ar.edu.utn.frba.dds.models.domain.excepciones;

/**
 * Clase: CrearMailException para cuando falla la creacion de un mail
 */
public class CrearMailException extends RuntimeException {
    public CrearMailException(Exception e) {
        super(e);
    }
}
