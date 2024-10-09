package ar.edu.utn.frba.dds.dtos.formularios;

import ar.edu.utn.frba.dds.models.domain.colaboradores.form.Formulario;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class ShowFormularioDto {
  private String nombre;
  private List<ShowCampoDto> campos;

  public static ShowFormularioDto fromFormulario(Formulario form) {
    ShowFormularioDto f = new ShowFormularioDto();
    f.setNombre(form.getNombre());
    f.setCampos(form.getCampos().stream().map(ShowCampoDto::fromCampo).toList());
    return f;
  }
}
