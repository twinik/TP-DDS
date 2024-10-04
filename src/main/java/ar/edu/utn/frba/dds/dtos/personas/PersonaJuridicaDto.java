package ar.edu.utn.frba.dds.dtos.personas;

import ar.edu.utn.frba.dds.models.domain.colaboradores.FormaColaboracion;
import ar.edu.utn.frba.dds.models.domain.utils.Direccion;
import ar.edu.utn.frba.dds.models.domain.utils.MedioDeContacto;
import io.javalin.http.Context;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PersonaJuridicaDto {
  private String razonSocial;
  private String tipoOrganizacion;
  private String rubro;
  private Direccion direccion;
  private List<FormaColaboracion> formasColaboracion;
  private List<MedioDeContacto> mediosDeContacto;

}
