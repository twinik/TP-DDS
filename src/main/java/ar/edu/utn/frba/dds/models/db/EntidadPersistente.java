package ar.edu.utn.frba.dds.models.db;

import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public abstract class EntidadPersistente {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false, length = 36)
    protected String id;
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
//        this.id = UUID.randomUUID().toString();
        this.updated_at = LocalDateTime.now();
        this.crated_at = LocalDateTime.now();
    }
}
