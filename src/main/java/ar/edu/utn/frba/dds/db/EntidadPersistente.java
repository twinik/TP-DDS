package ar.edu.utn.frba.dds.db;

import ar.edu.utn.frba.dds.domain.colaboradores.Usuario;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public abstract class EntidadPersistente {
  @Id
  @GeneratedValue
  protected Long id;
  @Column(name = "created_at", columnDefinition = "TIMESTAMP")
  protected LocalDateTime crated_at;
  @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
  protected LocalDateTime updated_at;
  @Column(name = "activo")
  protected boolean activo = true;
//  @ManyToOne
//  @JoinColumn(name = "usuario_id",referencedColumnName = "id")
//  @Transient
//  protected Usuario usuario;

  public void borrarLogico(){
    this.activo = false;
  }

  @PreUpdate
  protected void onUpdate(){
    this.updated_at = LocalDateTime.now();
  }
  @PrePersist
  protected void onInsert(){
    this.updated_at = LocalDateTime.now();
    this.crated_at = LocalDateTime.now();
  }
}
