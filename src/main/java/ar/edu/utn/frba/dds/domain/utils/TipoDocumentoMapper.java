package ar.edu.utn.frba.dds.domain.utils;

public class TipoDocumentoMapper {

  public TipoDocumento obtenerTipoDeDocumento(String tipoDocumento) {
    return switch (tipoDocumento) {
      case "LC" -> TipoDocumento.LIBRETA_CIVICA;
      case "LE" -> TipoDocumento.LIBRETA_DE_ENROLAMIENTO;
      case "DNI" -> TipoDocumento.DNI;
      default -> throw new RuntimeException("Tipo de Documento Invalido");
    };
  }
}
