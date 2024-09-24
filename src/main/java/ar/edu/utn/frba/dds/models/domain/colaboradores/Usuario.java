package ar.edu.utn.frba.dds.models.domain.colaboradores;

import ar.edu.utn.frba.dds.models.db.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Usuario class permite representar un usuario.
 */
@Entity
@Table(name = "usuario")
@Getter
@Setter
public class Usuario extends EntidadPersistente {
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "clave")
    private String clave;

    public Usuario(String email, String clave) {
        this.email = email;
        this.clave = clave;
    }

    public Usuario() {

    }
}