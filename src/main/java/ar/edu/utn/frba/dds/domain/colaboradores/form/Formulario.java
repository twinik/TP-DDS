package ar.edu.utn.frba.dds.domain.colaboradores.form;

import ar.edu.utn.frba.dds.domain.colaboradores.form.Campo;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class Formulario {

    public Formulario() {
        this.campos = new ArrayList<Campo>();
    }

    private List<Campo> campos;

    public void agregarCampos(Campo ... campos) {
        Collections.addAll(this.campos, campos);
    }

}