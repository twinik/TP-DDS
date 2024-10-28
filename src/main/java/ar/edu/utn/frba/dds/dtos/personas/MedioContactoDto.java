package ar.edu.utn.frba.dds.dtos.personas;

import ar.edu.utn.frba.dds.exceptions.FormIncompletoException;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeContactoVacioFactory;
import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class MedioContactoDto {
  private String canal;
  private String valor;

  public static List<MedioContactoDto> of(Context context) {
    List<MedioContactoDto> lista = new ArrayList<>();
    int cantidadIngresada = Integer.parseInt(context.formParam("cantidad-contactos"));
    for (int i = 1; i <= cantidadIngresada; i++) {
      lista.add(new MedioContactoDto(context.formParam("canal-" + i), context.formParam("contacto-" + i)));
    }
    return lista;
  }

  public static boolean estanCamposLLenos(List<MedioContactoDto> lista, Object formDto) {
    if (lista.isEmpty()) throw new FormIncompletoException(MensajeContactoVacioFactory.generarMensaje(), formDto);
    return lista.stream().allMatch(m -> (m.canal != null && m.valor != null));
  }

}
