package ar.edu.utn.frba.dds.exceptions;

import lombok.Getter;

@Getter
public class RegistroFailedException extends RuntimeException {
    Object formDto;

    public RegistroFailedException(String message) {
        super(message);
    }

    public RegistroFailedException(String message, Object formDto) {
        super(message);
        this.formDto = formDto;
    }
}
