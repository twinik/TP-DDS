package ar.edu.utn.frba.dds.domain.utils;

import ar.edu.utn.frba.dds.db.EntidadPersistente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

/**
 * medioDeContacto class permite representar un medio de contacto.
 */
@Entity
@Table(name = "medio_de_contacto")
@NoArgsConstructor
@Getter
public class MedioDeContacto extends EntidadPersistente {


  @Enumerated(EnumType.STRING)
  @Column(name = "canal")
  private CanalContacto canal;

  @Column(name = "contacto")
  private String contacto;

  public MedioDeContacto(CanalContacto canal, String contacto) {
    this.canal = canal;
    this.contacto = contacto;
  }
}