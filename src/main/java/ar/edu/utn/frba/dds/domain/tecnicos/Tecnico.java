package ar.edu.utn.frba.dds.domain.tecnicos;

import ar.edu.utn.frba.dds.domain.utils.MedioDeContacto;
import ar.edu.utn.frba.dds.domain.utils.TipoDocumento;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Tecnico class permite representar un tecnico.
 */
@Getter
@Setter
@AllArgsConstructor
public class Tecnico {
  private String nombre;
  private String apellido;
  private String nroDocumento;
  private TipoDocumento tipoDocumento;
  private List<MedioDeContacto> medioContacto;
  private AreaDeCobertura areaDeCobertura;
}