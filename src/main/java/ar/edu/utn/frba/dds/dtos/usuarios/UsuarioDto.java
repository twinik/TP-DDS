package ar.edu.utn.frba.dds.dtos.usuarios;

import ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion.Usuario;
import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UsuarioDto {
    private String email;
    private String clave;

    public static UsuarioDto of(Context context) {
        return new UsuarioDto(context.formParam("email"), context.formParam("pass"));
    }

    public Usuario toEntity() {
        return new Usuario(this.getEmail(), this.getClave());
    }
}
