package ar.edu.utn.frba.dds.models.domain.recomendadorPuntosColocacion;

import ar.edu.utn.frba.dds.models.domain.utils.Direccion;
import ar.edu.utn.frba.dds.models.domain.utils.Ubicacion;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PuntoColocacion {
  private String nombre;
  private Ubicacion ubicacion;
  private Direccion direccion;
}
