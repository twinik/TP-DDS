package ar.edu.utn.frba.dds.dtos.usuarios;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UsuarioNavbarDto {
    private String nombre;
    private String email;
}
