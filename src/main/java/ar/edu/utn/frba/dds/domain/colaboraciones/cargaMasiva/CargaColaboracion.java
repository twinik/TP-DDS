package ar.edu.utn.frba.dds.domain.colaboraciones.cargaMasiva;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/**
 * CargaColaboracion class representa una carga de colaboracion.
 */
@Getter
@Setter
public class CargaColaboracion {
  private String tipoDocumento;
  private Integer documento;
  private String nombre;
  private String apellido;
  private String mail;
  private LocalDate fechaColaboracion;
  private String formaColaboracion;
  private Integer cantidad;
  private String jsonColaboracion; // Tiene que tener las comillas escapadas
}
