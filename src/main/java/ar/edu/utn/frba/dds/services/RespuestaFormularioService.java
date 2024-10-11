package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.formularios.RespuestaFormularioDto;
import ar.edu.utn.frba.dds.models.domain.colaboradores.form.RespuestaACampo;
import ar.edu.utn.frba.dds.models.domain.colaboradores.form.RespuestaFormulario;
import ar.edu.utn.frba.dds.models.domain.colaboradores.form.TipoCampo;
import ar.edu.utn.frba.dds.models.repositories.ICampoRepository;
import ar.edu.utn.frba.dds.models.repositories.IOpcionRepository;
import ar.edu.utn.frba.dds.models.repositories.IRespuestasFormularioRepository;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RespuestaFormularioService {
  private IRespuestasFormularioRepository repo;
  private ColaboradoresService colaboradoresService;
  private FormulariosService formulariosService;
 private RespuestaCampoService respuestaCampoService;

  public void crearRespuestaFormulario(RespuestaFormularioDto dto) {
    RespuestaFormulario res = new RespuestaFormulario();
    res.setFormulario(this.formulariosService.obtenerFormulario(dto.getIdFormulario()));
    res.setColaborador(this.colaboradoresService.obtenerColaborador(dto.getIdColaborador()));
    dto.getRespuestas().forEach(  r -> res.agregarRespuestasACampo(this.respuestaCampoService.obtenerRespuesta(r)));
    repo.guardar(res);

    this.colaboradoresService.marcarFormCompletado(dto.getIdColaborador());
  }



}
