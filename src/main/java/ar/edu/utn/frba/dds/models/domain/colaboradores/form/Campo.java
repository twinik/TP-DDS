package ar.edu.utn.frba.dds.models.domain.colaboradores.form;

import ar.edu.utn.frba.dds.models.db.EntidadPersistente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Campo class permite representar un campo de un formulario.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "campo")
public class Campo extends EntidadPersistente {

    @Enumerated(EnumType.STRING)
    private TipoCampo tipo;

    @Column(name = "pregunta", columnDefinition = "TEXT")
    private String pregunta;

    @Column(name = "obligatorio")
    private boolean obligatorio;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "campo_id", referencedColumnName = "id")
    private List<Opcion> opciones;

    /**
     * Campo class constructor.
     */
    public Campo(TipoCampo tipo, String pregunta, boolean obligatorio) {
        this.tipo = tipo;
        this.pregunta = pregunta;
        this.obligatorio = obligatorio;
        this.opciones = new ArrayList<Opcion>();
    }


    public void agregarOpciones(Opcion... opciones) {
        Collections.addAll(this.opciones, opciones);
    }

}