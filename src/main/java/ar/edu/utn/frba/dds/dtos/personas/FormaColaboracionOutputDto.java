package ar.edu.utn.frba.dds.dtos.personas;

import ar.edu.utn.frba.dds.models.domain.colaboradores.FormaColaboracion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class FormaColaboracionOutputDto {
    private String id;
    private String desc;

    public static FormaColaboracionOutputDto fromForma(FormaColaboracion formaColaboracion) {
        return new FormaColaboracionOutputDto(formaColaboracion.getId(), formaColaboracion.getDescripcion());
    }

}
