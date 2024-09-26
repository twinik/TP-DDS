package ar.edu.utn.frba.dds.models.domain.utils;


import ar.edu.utn.frba.dds.models.messageFactory.MensajeExcepcionTipoDocumentoFactory;

/**
 * TipoDocumentoMapper class permite mapear un tipo de documento.
 */
public class TipoDocumentoMapper {

    /**
     * obtenerTipoDeDocumento permite obtener el tipo de documento.
     */
    public TipoDocumento obtenerTipoDeDocumento(String tipoDocumento) {
        return switch (tipoDocumento) {
            case "LC" -> TipoDocumento.LIBRETA_CIVICA;
            case "LE" -> TipoDocumento.LIBRETA_DE_ENROLAMIENTO;
            case "DNI" -> TipoDocumento.DNI;
            default -> throw new RuntimeException(MensajeExcepcionTipoDocumentoFactory.generarMensaje());
        };
    }
}
