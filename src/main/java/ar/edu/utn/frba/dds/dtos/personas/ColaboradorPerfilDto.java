package ar.edu.utn.frba.dds.dtos.personas;

import ar.edu.utn.frba.dds.dtos.DireccionDto;
import java.util.List;

public class ColaboradorPerfilDto {
  // TODO
  private String nombre;
  private String apellido;

  private String fechaNacimiento;
  private String rubro;
  private String razonSocial;

  private String TipoPersonaJuridica;
  private String emailCuenta;
  private DireccionDto direccionDto;
  private List<MedioContactoDto> mediosContacto;
  private List<FormaColaboracionDto> formaColaboracionDtos;

  private Float puntosGanados;

}
