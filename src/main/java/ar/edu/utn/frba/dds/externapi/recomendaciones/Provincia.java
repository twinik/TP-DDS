package ar.edu.utn.frba.dds.externapi.recomendaciones;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Provincia {

    private Integer id;
    private String nombre;
    private String etiqueta;
}