package ar.edu.utn.frba.dds.exceptions;

import lombok.Getter;

@Getter
public class HeladeraVaciaException extends RuntimeException {

    Object formDto;

    public HeladeraVaciaException() {
    }

    public HeladeraVaciaException(String message) {
        super(message);
    }

    public HeladeraVaciaException(String message, Object formDto) {
        super(message);
        this.formDto = formDto;
    }
}
