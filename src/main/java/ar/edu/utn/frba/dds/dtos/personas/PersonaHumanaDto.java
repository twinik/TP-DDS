package ar.edu.utn.frba.dds.dtos.personas;

import ar.edu.utn.frba.dds.models.domain.colaboradores.FormaColaboracion;
import ar.edu.utn.frba.dds.models.domain.utils.Direccion;
import ar.edu.utn.frba.dds.models.domain.utils.MedioDeContacto;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PersonaHumanaDto {
  private String nombre;
  private String apellido;
  private LocalDate fechaNacimiento;
  private Direccion direccion;
  private List<FormaColaboracion> formasColaboracion;
  private List<MedioDeContacto> mediosDeContacto;

}
