package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.tecnicos.TecnicoDto;
import ar.edu.utn.frba.dds.exceptions.DniDuplicadoException;
import ar.edu.utn.frba.dds.helpers.DniHelper;
import ar.edu.utn.frba.dds.models.domain.tecnicos.AreaDeCobertura;
import ar.edu.utn.frba.dds.models.domain.tecnicos.Tecnico;
import ar.edu.utn.frba.dds.models.domain.utils.TipoDocumento;
import ar.edu.utn.frba.dds.models.domain.utils.TipoDocumentoMapper;
import ar.edu.utn.frba.dds.models.domain.utils.Ubicacion;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeDniDuplicadoFactory;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeDniInvalidoFactory;
import ar.edu.utn.frba.dds.models.repositories.ITecnicosRepository;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import lombok.AllArgsConstructor;
import java.util.Optional;

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
    this.validarDocumento(tecnico.getTipoDocumento(),tecnico.getNroDocumento());
    tecnico.setAreaDeCobertura(new AreaDeCobertura(new Ubicacion(dto.getAreaCoberturaDto().getLatitud(), dto.getAreaCoberturaDto().getLongitud()), dto.getAreaCoberturaDto().getRadio()));
    tecnico.agregarMedioContacto(this.medioContactoService.fromDtos(dto.getMedioContactoDtoList()));

    this.tecnicosRepository.guardar(tecnico);
  }

  private void validarDocumento(TipoDocumento tipoDocumento, String nroDocumento){
    if(!DniHelper.esValido(nroDocumento)) throw new DniDuplicadoException(MensajeDniInvalidoFactory.generarMensaje());
    Optional<Tecnico> t = tecnicosRepository.buscar(tipoDocumento,nroDocumento);
    if(t.isPresent()) throw new DniDuplicadoException(MensajeDniDuplicadoFactory.generarMensaje());
  }
}
