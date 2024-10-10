package ar.edu.utn.frba.dds.dtos.usuarios;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LoginDto {
    private String idUsuario;
    private String idColaborador;
    private Boolean formCompletado = null;
}
