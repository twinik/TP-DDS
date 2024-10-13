package ar.edu.utn.frba.dds.dtos.reportes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReporteDto {
  private String fecha;
  private String tipo;
  private String archivo;
}
