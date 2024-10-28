package ar.edu.utn.frba.dds.models.domain.incidentes;

public enum TipoAlerta {
  TEMPERATURA,
  FRAUDE,
  FALLA_CONEXION;

  public static String mapearAString(TipoAlerta tipoAlerta) {
    return switch (tipoAlerta) {
      case FRAUDE -> "Fraude";
      case TEMPERATURA -> "Temperatura";
      case FALLA_CONEXION -> "Falla de conexión";
    };
  }
}