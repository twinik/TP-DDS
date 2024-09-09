package ar.edu.utn.frba.dds.domain.colaboraciones.cargaMasiva;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

/**
 * CargaColaboracion class representa una carga de colaboracion.
 */
@Getter
@Setter
public class CargaColaboracion {
    private String tipoDocumento;
    private String documento;
    private String nombre;
    private String apellido;
    private String mail;
    private LocalDate fechaColaboracion;
    private String formaColaboracion;
    private Integer cantidad;
    private String jsonColaboracion; // Tiene que tener las comillas escapadas
}
