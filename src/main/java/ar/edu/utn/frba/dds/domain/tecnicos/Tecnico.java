package ar.edu.utn.frba.dds.domain.tecnicos;

import ar.edu.utn.frba.dds.domain.utils.TipoDocumento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Tecnico {

  private String nombre;

  private String apellido;

  private String nroDocumento;

  private TipoDocumento tipoDocumento;

  private List<medioDeContacto> medioContacto;

  private AreaDeCobertura areaDeCobertura;

}