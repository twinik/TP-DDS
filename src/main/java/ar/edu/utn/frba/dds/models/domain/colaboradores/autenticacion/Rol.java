package ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion;

import ar.edu.utn.frba.dds.models.db.EntidadPersistente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "rol")
public class Rol extends EntidadPersistente {
    @Column(name = "nombre")
    private String nombre;
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "permisos_x_rol", joinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "permiso_id", referencedColumnName = "id"))
    private List<Permiso> permisos = new ArrayList<>();

    public static Rol of(Set<Permiso> p) {
        Rol r = new Rol();
        r.setNombre("Rol agregado");
        r.agregarPermisos(p.toArray(new Permiso[0]));
        return r;
    }

    public boolean tenesPermiso(String permiso) {
        return this.permisos.stream().anyMatch(p -> p.tieneIgualDescripcion(permiso));
    }

    public void agregarPermisos(Permiso... permisos) {
        this.permisos.addAll(Arrays.asList(permisos));
    }

}
