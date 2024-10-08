package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.tecnicos.TecnicoDto;
import ar.edu.utn.frba.dds.models.domain.tecnicos.AreaDeCobertura;
import ar.edu.utn.frba.dds.models.domain.tecnicos.Tecnico;
import ar.edu.utn.frba.dds.models.domain.utils.TipoDocumentoMapper;
import ar.edu.utn.frba.dds.models.domain.utils.Ubicacion;
import ar.edu.utn.frba.dds.models.repositories.ITecnicosRepository;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TecnicosService {
  private ITecnicosRepository tecnicosRepository;
  private MedioContactoService medioContactoService;

  public void crearTecnico(TecnicoDto dto) {
    Tecnico tecnico = new Tecnico();
    tecnico.setNombre(dto.getNombre());
    tecnico.setApellido(dto.getApellido());
    tecnico.setTipoDocumento(ServiceLocator.get(TipoDocumentoMapper.class).obtenerTipoDeDocumento(dto.getTipoDocumento()));
    tecnico.setNroDocumento(dto.getNroDocumento());
    tecnico.setAreaDeCobertura(new AreaDeCobertura(new Ubicacion(dto.getAreaCoberturaDto().getLatitud(), dto.getAreaCoberturaDto().getLongitud()), dto.getAreaCoberturaDto().getRadio()));
    tecnico.setMedioContacto(this.medioContactoService.fromDtos(dto.getMedioContactoDtoList()));

    this.tecnicosRepository.guardar(tecnico);
  }
}
