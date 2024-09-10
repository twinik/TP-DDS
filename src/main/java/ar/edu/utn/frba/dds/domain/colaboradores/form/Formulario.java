package ar.edu.utn.frba.dds.domain.colaboradores.form;

import ar.edu.utn.frba.dds.db.EntidadPersistente;
import ar.edu.utn.frba.dds.domain.colaboradores.Usuario;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Formulario class permite representar un formulario.
 */

@Getter
@Setter
@Entity
@Table(name = "formulario")
public class Formulario extends EntidadPersistente {

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "formulario_id", referencedColumnName = "id")
    private List<Campo> campos;

    @Column(name = "nombre")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "usuario_id",referencedColumnName = "id")
    private Usuario autor;


    public Formulario() {
        this.campos = new ArrayList<Campo>();
    }


    public void agregarCampos(Campo... campos) {
        Collections.addAll(this.campos, campos);
    }

}