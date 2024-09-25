package ar.edu.utn.frba.dds.models.domain.colaboradores.form;

import ar.edu.utn.frba.dds.models.db.EntidadPersistente;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * RespuestaFormulario class permite representar una respuesta a un formulario.
 */
@Entity
@Table(name = "respuesta_formulario")
@Getter
@Setter
@NoArgsConstructor
public class RespuestaFormulario extends EntidadPersistente {
    @ManyToOne
    @JoinColumn(name = "colaborador_id", referencedColumnName = "id", nullable = false)
    private Colaborador colaborador;

    @ManyToOne
    @JoinColumn(name = "formulario_id", referencedColumnName = "id", nullable = false)
    private Formulario formulario;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "respuesta_formulario_id", referencedColumnName = "id")
    private List<RespuestaACampo> respuestas = new ArrayList<>();

    public RespuestaFormulario(Formulario formulario) {
        this.formulario = formulario;
    }


    public void agregarRespuestasACampo(RespuestaACampo... respuestas) {
        Collections.addAll(this.respuestas, respuestas);
    }

}