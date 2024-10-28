package ar.edu.utn.frba.dds.dtos.personas;

import ar.edu.utn.frba.dds.exceptions.RegistroFailedException;
import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class FormaColaboracionDto {
    private String id;

    public static List<FormaColaboracionDto> of(Context context) {
        List<FormaColaboracionDto> result = new ArrayList<>();
        int formasDisponibles = Integer.parseInt(context.formParam("cant-formas"));
        for (int i = 0; i < formasDisponibles; i++) {
            String idForma = context.formParam("forma-" + i);
            if (idForma != null) result.add(new FormaColaboracionDto(idForma));
        }
        return result;
    }

    public static Boolean validarSeleccion(List<FormaColaboracionDto> formas, Object dto) {
        if (formas == null || formas.isEmpty())
            throw new RegistroFailedException("Debes seleccionar una forma de colaboracion", dto);
        return true;
    }
}
