package ar.edu.utn.frba.dds.exceptions;

public class ExceptionHelper {
  public static boolean isCausedBy(Throwable exception, Class<? extends Throwable> expectedException) {
    while (exception != null) {
      if (expectedException.isInstance(exception)) {
        return true;
      }
      exception = exception.getCause();
    }
    return false;
  }
}
