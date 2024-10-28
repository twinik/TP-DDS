package ar.edu.utn.frba.dds.exceptions;

import lombok.Getter;

@Getter
public class HeladeraLlenaException extends RuntimeException {

    Object formDto;

    public HeladeraLlenaException() {
    }

    public HeladeraLlenaException(String message) {
        super(message);
    }

    public HeladeraLlenaException(String message, Object formDto) {
        super(message);
        this.formDto = formDto;
    }
}
