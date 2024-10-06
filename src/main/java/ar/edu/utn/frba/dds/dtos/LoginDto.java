package ar.edu.utn.frba.dds.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LoginDto {
  private String idUsuario;
  private String idColaborador;
}
