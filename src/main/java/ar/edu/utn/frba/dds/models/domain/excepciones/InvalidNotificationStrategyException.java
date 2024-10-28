package ar.edu.utn.frba.dds.models.domain.excepciones;

public class InvalidNotificationStrategyException extends RuntimeException {
  public InvalidNotificationStrategyException() {
    super("La NotificationStrategy que se intento crear es invalida");
  }
}
