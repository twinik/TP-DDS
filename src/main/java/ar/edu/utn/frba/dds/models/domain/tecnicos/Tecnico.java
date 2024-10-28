package ar.edu.utn.frba.dds.models.domain.tecnicos;

import ar.edu.utn.frba.dds.helpers.MedioContactoHelper;
import ar.edu.utn.frba.dds.models.db.EntidadPersistente;
import ar.edu.utn.frba.dds.models.domain.notifications.Contactable;
import ar.edu.utn.frba.dds.models.domain.utils.CanalContacto;
import ar.edu.utn.frba.dds.models.domain.utils.MedioDeContacto;
import ar.edu.utn.frba.dds.models.domain.utils.TipoDocumento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Tecnico class permite representar un tecnico.
 */
@Entity
@Table(name = "tecnico")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tecnico extends EntidadPersistente implements Contactable {

  @Column(name = "nombre", nullable = false)
  private String nombre;

  @Column(name = "apellido", nullable = false)
  private String apellido;

  @Column(name = "nro_documento", nullable = false)
  private String nroDocumento;

  @Enumerated(EnumType.STRING)
  @Column(name = "tipo_documento", nullable = false)
  private TipoDocumento tipoDocumento;

  @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
  @JoinColumn(name = "tecnico_id", referencedColumnName = "id")
  private List<MedioDeContacto> medioContacto = new ArrayList<>();

  @Embedded
  private AreaDeCobertura areaDeCobertura;


  public void agregarMedioContacto(List<MedioDeContacto> medioContacto) {
    this.medioContacto.addAll(medioContacto);
  }

  @Override
  public String email() {
    return MedioContactoHelper.getValorContacto(this.medioContacto, CanalContacto.EMAIL);
  }

  @Override
  public String telefonoCompleto() {
    return MedioContactoHelper.getValorContacto(this.medioContacto, CanalContacto.WHATSAPP);
  }

  @Override
  public String telegramId() {
    return MedioContactoHelper.getValorContacto(this.medioContacto, CanalContacto.TELEGRAM);
  }
}