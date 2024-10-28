package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.formularios.AltaCampoDto;
import ar.edu.utn.frba.dds.dtos.formularios.AltaFormularioDto;
import ar.edu.utn.frba.dds.exceptions.RecursoInexistenteException;
import ar.edu.utn.frba.dds.models.domain.colaboradores.form.Campo;
import ar.edu.utn.frba.dds.models.domain.colaboradores.form.Formulario;
import ar.edu.utn.frba.dds.models.domain.colaboradores.form.Opcion;
import ar.edu.utn.frba.dds.models.repositories.IFormularioRepository;
import lombok.AllArgsConstructor;
import java.util.List;

@AllArgsConstructor
public class FormulariosService {
  private IFormularioRepository formularioRepository;
  private UsuarioService usuarioService;

  private static Campo campoFromDto(AltaCampoDto c) {
    Campo campo = new Campo();
    campo.setTipo(AltaCampoDto.mapToTipo(c.getTipo()));
    campo.setPregunta(c.getPregunta());
    campo.setObligatorio(c.getObligatorio() != null);
    campo.setOpciones(c.getOpciones().stream().map(Opcion::new).toList());
    return campo;
  }

  public void crearFormulario(AltaFormularioDto dto) {
    Formulario form = new Formulario();
    form.setNombre("Formulario Colaboradores");
    form.setAutor(this.usuarioService.obtenerUsuario(dto.getIdUsuario()));
    form.agregarCampos(dto.getCampos().stream().map(FormulariosService::campoFromDto).toList());
    this.formularioRepository.guardar(form);
  }

  public Formulario obtenerFormulario(String id) {
    return this.formularioRepository.buscar(id).orElseThrow(() -> new RecursoInexistenteException("El formulario no existe"));
  }

  public Formulario obtenerUltimo() {
    List<Formulario> formularios = this.formularioRepository.buscarTodos();
    if (formularios.isEmpty()) return null;
    return formularios.get(formularios.size() - 1);
  }
}
