package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.personas.MedioContactoDto;
import ar.edu.utn.frba.dds.models.domain.utils.CanalContacto;
import ar.edu.utn.frba.dds.models.domain.utils.MedioDeContacto;
import java.util.List;

public class MedioContactoService {
  public MedioDeContacto fromDto(MedioContactoDto dto) {
    return new MedioDeContacto(CanalContacto.valueOf(dto.getCanal().toUpperCase()), dto.getValor());
  }

  public List<MedioDeContacto> fromDtos(List<MedioContactoDto> dtos) {
    return dtos.stream().map(this::fromDto).toList();
  }

}
