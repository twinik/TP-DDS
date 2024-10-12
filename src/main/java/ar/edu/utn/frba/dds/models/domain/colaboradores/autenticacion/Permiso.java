package ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion;

import ar.edu.utn.frba.dds.models.db.EntidadPersistente;
import io.javalin.security.RouteRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "permiso")
public class Permiso extends EntidadPersistente implements RouteRole {
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "desc_interna", nullable = false)
    private String desc_interna;

    public boolean tieneIgualDescripcion(Permiso p) {
        return this.desc_interna.equals(p.getDesc_interna());
    }

}
