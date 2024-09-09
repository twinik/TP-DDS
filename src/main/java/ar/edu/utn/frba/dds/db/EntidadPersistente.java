package ar.edu.utn.frba.dds.db;

import lombok.Getter;
import javax.persistence.*;
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

    public void borrarLogico() {
        this.activo = false;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updated_at = LocalDateTime.now();
    }

    @PrePersist
    protected void onInsert() {
        this.updated_at = LocalDateTime.now();
        this.crated_at = LocalDateTime.now();
    }
}
