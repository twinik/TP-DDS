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

  public void crearRespuestaFormulario(RespuestaFormularioDto dto) {
    RespuestaFormulario res = new RespuestaFormulario();
    res.setFormulario(ServiceLocator.get(FormulariosService.class).obtenerFormulario(dto.getIdFormulario()));
    res.setColaborador(ServiceLocator.get(ColaboradoresService.class).obtenerColaborador(dto.getIdColaborador()));
    // yo se que esto esta horrible pero ya no lo soporto mas
    res.setRespuestas(dto.getRespuestas().stream().map(r -> {
      RespuestaACampo rta = new RespuestaACampo();
      rta.setCampo(ServiceLocator.get(ICampoRepository.class).buscar(r.getIdCampo()).get());

      if (rta.getCampo().getTipo() == TipoCampo.MULTIPLE_CHOICE || rta.getCampo().getTipo() == TipoCampo.CHOICE ) {
        rta.setOpcionesElegidas(r.getIdOpciones().stream().map(o -> {
          return ServiceLocator.get(IOpcionRepository.class).buscar(o).get();
        }).toList());
      } else {
        rta.setRespuesta(r.getRespuesta());
      }

      return rta;
    }).toList());

    repo.guardar(res);

    ServiceLocator.get(ColaboradoresService.class).marcarFormCompletado(dto.getIdColaborador());
  }

}
