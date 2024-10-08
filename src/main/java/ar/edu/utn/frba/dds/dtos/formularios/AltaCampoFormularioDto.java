package ar.edu.utn.frba.dds.dtos.formularios;

import ar.edu.utn.frba.dds.models.domain.colaboradores.form.Opcion;
import ar.edu.utn.frba.dds.models.domain.colaboradores.form.TipoCampo;
import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class AltaCampoFormularioDto {
  private String tipo;
  private String pregunta;
  private String obligatorio; // va a ser "obligatorio" o null (creo)
  private List<String> opciones;

  public static AltaCampoFormularioDto fromContext(Integer nroCampo, Context ctx) {
    String tipo = ctx.formParam("tipo-campo-" + nroCampo.toString());
    String pregunta = ctx.formParam("pregunta-" + nroCampo.toString());
    String obligatorio = ctx.formParam("obligatorio-campo-" + nroCampo.toString());
    List<String> opciones = new ArrayList<>();
    if (tipo.equals("multiple-choice") || tipo.equals("single-choice")) {
      Integer cantidadOpciones = Integer.valueOf(ctx.formParam("cantidad-opciones-campo-" + nroCampo.toString()));
      for (int i = 1; i<=cantidadOpciones; i++) {
        opciones.add(ctx.formParam(String.format("opcion-%d-campo-%d-input", i, nroCampo)));
      }
    }
    return new AltaCampoFormularioDto(tipo, pregunta, obligatorio, opciones);
  }

  public static TipoCampo mapToTipo(String tipo) {
    switch (tipo.toLowerCase()) {
      case "texto-libre": return TipoCampo.LIBRE;
      case "multiple-choice": return TipoCampo.MULTIPLE_CHOICE;
      case "single-choice": return TipoCampo.CHOICE;
      case "numerico": return TipoCampo.NUMERICO;
      case "fecha": return TipoCampo.FECHA;
      default: throw new RuntimeException("Tipo de campo de formulario invalido");
    }
  }
}
