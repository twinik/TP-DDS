package ar.edu.utn.frba.dds.dtos;

import ar.edu.utn.frba.dds.models.domain.utils.TipoDocumento;
import ar.edu.utn.frba.dds.models.domain.utils.TipoDocumentoMapper;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TipoDocumentoDto {
    String valor;
    String descripcion;

    public static TipoDocumentoDto fromTipoDocumento(TipoDocumento tipoDocumento) {
        String valor = ServiceLocator.get(TipoDocumentoMapper.class).mapearAstring(tipoDocumento);
        return switch (valor) {
            case "LC" -> new TipoDocumentoDto(valor, "Libreta Cívica");
            case "LE" -> new TipoDocumentoDto(valor, "Libreta de Enrolamiento");
            case "DNI" -> new TipoDocumentoDto(valor, "DNI");
            default -> throw new RuntimeException("valor invalido de tipo documento");
        };
    }
}
