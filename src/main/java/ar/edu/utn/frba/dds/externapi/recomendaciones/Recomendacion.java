package ar.edu.utn.frba.dds.externapi.recomendaciones;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recomendacion {

  private String id;
  private String nombre;
  private String calle;
  private Integer altura;
  private String latitud;
  private String longitud;
  private Provincia provincia;
  private Localidad localidad;
}
