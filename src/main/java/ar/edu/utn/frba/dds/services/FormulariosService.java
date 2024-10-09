package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.formularios.AltaCampoDto;
import ar.edu.utn.frba.dds.dtos.formularios.AltaFormularioDto;
import ar.edu.utn.frba.dds.exceptions.RecursoInexistenteException;
import ar.edu.utn.frba.dds.models.domain.colaboradores.form.Campo;
import ar.edu.utn.frba.dds.models.domain.colaboradores.form.Formulario;
import ar.edu.utn.frba.dds.models.domain.colaboradores.form.Opcion;
import ar.edu.utn.frba.dds.models.repositories.IFormularioRepository;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FormulariosService {
  IFormularioRepository repo;

  public void crearFormulario(AltaFormularioDto dto) {
    Formulario form = new Formulario();
    form.setNombre("Formulario Colaboradores");
    form.setAutor(ServiceLocator.get(UsuarioService.class).obtenerUsuario(dto.getIdUsuario()));
    form.setCampos(dto.getCampos().stream().map(FormulariosService::campoFromDto).toList());
    repo.guardar(form);
  }

  private static Campo campoFromDto(AltaCampoDto c) {
    Campo campo = new Campo();
    campo.setTipo(AltaCampoDto.mapToTipo(c.getTipo()));
    campo.setPregunta(c.getPregunta());
    campo.setObligatorio(c.getObligatorio() != null);
    campo.setOpciones(c.getOpciones().stream().map(Opcion::new).toList());
    return campo;
  }

  public Formulario obtenerFormulario(String id) {
    return repo.buscar(id).orElseThrow(() -> new RecursoInexistenteException("El formulario no existe"));
  }
}
