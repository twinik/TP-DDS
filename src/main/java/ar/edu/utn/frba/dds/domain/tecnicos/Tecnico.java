package ar.edu.utn.frba.dds.domain.tecnicos;

import ar.edu.utn.frba.dds.domain.notifications.Contactable;
import ar.edu.utn.frba.dds.domain.utils.CanalContacto;
import ar.edu.utn.frba.dds.domain.utils.MedioDeContacto;
import ar.edu.utn.frba.dds.domain.utils.TipoDocumento;
import java.util.List;
import ar.edu.utn.frba.dds.helpers.MedioContactoHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Tecnico class permite representar un tecnico.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tecnico implements Contactable {
  private Long id;
  private String nombre;
  private String apellido;
  private String nroDocumento;
  private TipoDocumento tipoDocumento;
  private List<MedioDeContacto> medioContacto;
  private AreaDeCobertura areaDeCobertura;
  @Override
  public String email() {
    return MedioContactoHelper.getValorContacto(this.medioContacto, CanalContacto.EMAIL);
  }

  @Override
  public String telefonoCompleto() {
    return MedioContactoHelper.getValorContacto(this.medioContacto,CanalContacto.WHATSAPP);
  }

  @Override
  public String telegramId() {
    return MedioContactoHelper.getValorContacto(this.medioContacto,CanalContacto.TELEGRAM);
  }
}