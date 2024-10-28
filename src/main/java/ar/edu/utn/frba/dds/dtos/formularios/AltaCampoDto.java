package ar.edu.utn.frba.dds.dtos.formularios;

import ar.edu.utn.frba.dds.models.domain.colaboradores.form.TipoCampo;
import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class AltaCampoDto {
  private String tipo;
  private String pregunta;
  private String obligatorio; // va a ser "obligatorio" o null (creo)
  private List<String> opciones;

  public static AltaCampoDto fromContext(Integer nroCampo, Context ctx) {
    String tipo = ctx.formParam("tipo-campo-" + nroCampo.toString());
    String pregunta = ctx.formParam("pregunta-" + nroCampo.toString());
    String obligatorio = ctx.formParam("obligatorio-campo-" + nroCampo.toString());
    List<String> opciones = new ArrayList<>();
    if (tipo.equals("multiple-choice") || tipo.equals("single-choice")) {
      Integer cantidadOpciones = Integer.valueOf(ctx.formParam("cantidad-opciones-campo-" + nroCampo.toString()));
      for (int i = 1; i <= cantidadOpciones; i++) {
        opciones.add(ctx.formParam(String.format("opcion-%d-campo-%d-input", i, nroCampo)));
      }
    }
    return new AltaCampoDto(tipo, pregunta, obligatorio, opciones);
  }

  public static TipoCampo mapToTipo(String tipo) {
    return switch (tipo.toLowerCase()) {
      case "texto-libre" -> TipoCampo.LIBRE;
      case "multiple-choice" -> TipoCampo.MULTIPLE_CHOICE;
      case "single-choice" -> TipoCampo.CHOICE;
      case "numerico" -> TipoCampo.NUMERICO;
      case "fecha" -> TipoCampo.FECHA;
      default -> throw new RuntimeException("Tipo de campo de formulario invalido");
    };
  }
}
