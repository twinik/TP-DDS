package ar.edu.utn.frba.dds.dtos.formularios;

import ar.edu.utn.frba.dds.models.domain.colaboradores.form.Campo;
import ar.edu.utn.frba.dds.models.domain.colaboradores.form.TipoCampo;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class ShowCampoDto {
  private String id;
  private String pregunta;
  private String tipo;
  private boolean obligatorio;
  private List<ShowOpcionDto> opciones;

  public static ShowCampoDto fromCampo(Campo campo) {
    ShowCampoDto c = new ShowCampoDto();
    c.setId(campo.getId());
    c.setPregunta(campo.getPregunta());
    c.setTipo(mapTipoToString(campo.getTipo()));
    c.setObligatorio(campo.isObligatorio());
    c.setOpciones(campo.getOpciones().stream().map(o -> new ShowOpcionDto(o.getId(), o.getOpcion())).toList());
    return c;
  }

  public static String mapTipoToString(TipoCampo tipo) {
    return switch (tipo) {
      case LIBRE -> "texto-libre";
      case MULTIPLE_CHOICE -> "multiple-choice";
      case CHOICE -> "single-choice";
      case NUMERICO -> "numerico";
      case FECHA -> "fecha";
    };
  }
}
