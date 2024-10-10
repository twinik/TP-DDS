package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.usuarios.LoginDto;
import ar.edu.utn.frba.dds.dtos.usuarios.UsuarioDto;
import ar.edu.utn.frba.dds.dtos.usuarios.UsuarioNavbarDto;
import ar.edu.utn.frba.dds.exceptions.FormularioNoCompletadoException;
import ar.edu.utn.frba.dds.exceptions.LoginFailedException;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.domain.colaboradores.TipoPersona;
import ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion.Permiso;
import ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion.Usuario;
import ar.edu.utn.frba.dds.models.repositories.IUsuariosRepository;
import ar.edu.utn.frba.dds.utils.PasswordHasher;
import ar.edu.utn.frba.dds.utils.PermisosHelper;
import lombok.AllArgsConstructor;
import java.util.Optional;

@AllArgsConstructor
public class UsuarioService {

  private IUsuariosRepository usuariosRepository;
  private ColaboradoresService colaboradoresService;

  public void registrar(UsuarioDto dto) {
    usuariosRepository.guardar(dto.toEntity());
  }

  public LoginDto obtenerUsuario(UsuarioDto dto) {
    LoginDto resultado = new LoginDto();
    Optional<Usuario> user = usuariosRepository.buscarPorEmail(dto.getEmail());
    if (user.isEmpty()) throw new LoginFailedException("usuario o contraseña invalidos");
    if (!PasswordHasher.verifyPassword(dto.getClave(), user.get().getClave()))
      throw new LoginFailedException("usuario o contraseña invalidos");
    resultado.setIdUsuario(user.get().getId());
    Optional<Colaborador> colaborador = this.colaboradoresService.colaboradorFromUsuario(user.get().getId());
    colaborador.ifPresent(value -> {
      resultado.setFormCompletado(value.getFormCompletado());
      resultado.setIdColaborador(value.getId());
    });
    return resultado;
  }

  public Usuario obtenerUsuario(String idUsuario) {
    Optional<Usuario> user = usuariosRepository.buscar(idUsuario);
    return user.orElse(null);
  }

  public UsuarioNavbarDto getUsuarioNavbar(Usuario u, String idColaborador) {
    UsuarioNavbarDto dto = new UsuarioNavbarDto();
    dto.setEmail(u.getEmail());

    Colaborador colaborador = this.colaboradoresService.obtenerColaborador(idColaborador);
    if (colaborador.getTipoColaborador().getTipo() == TipoPersona.PERSONA_HUMANA) {
      dto.setNombre(colaborador.getNombreYapellido());
    } else {
      dto.setNombre(colaborador.getRazonSocial());
    }

    return dto;
  }

  public UsuarioNavbarDto getUsuarioNavbar(Usuario u) {
    UsuarioNavbarDto dto = new UsuarioNavbarDto();
    dto.setEmail(u.getEmail());
    dto.setNombre("Usuario");
    dto.setPermisoTecnico(u.tenesPermisos(PermisosHelper.getInstance().buscarPorNombres("alta-tecnico").toArray(new Permiso[0])));
    dto.setPermisoFormulario(u.tenesPermisos(PermisosHelper.getInstance().buscarPorNombres("alta-formulario").toArray(new Permiso[0])));
    dto.setPermisoModeloHeladera(u.tenesPermisos(PermisosHelper.getInstance().buscarPorNombres("alta-modelo-heladera").toArray(new Permiso[0])));
    dto.setPermisoCodTarjeta(u.tenesPermisos(PermisosHelper.getInstance().buscarPorNombres("alta-cod-tarjeta").toArray(new Permiso[0])));
    return dto;
  }

}