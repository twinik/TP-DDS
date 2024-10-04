package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.UsuarioDto;
import ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion.Usuario;
import ar.edu.utn.frba.dds.models.repositories.IUsuariosRepository;
import lombok.AllArgsConstructor;
import java.util.Optional;

@AllArgsConstructor
public class UsuarioService {

  private IUsuariosRepository usuariosRepository;

  public String obtenerUsuario(UsuarioDto dto) {
    Optional<Usuario> user = usuariosRepository.buscar(dto.getEmail(), dto.getClave());
    if (user.isEmpty()) throw new LoginFailedException("usuario o contraseña invalidos");
    return user.get().getId();
  }


}
