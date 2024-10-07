package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.incidentes.FallaTecnicaDto;
import ar.edu.utn.frba.dds.models.domain.incidentes.FallaTecnica;
import ar.edu.utn.frba.dds.models.repositories.IFallasTecnicasRepository;
import lombok.AllArgsConstructor;
import java.util.List;

@AllArgsConstructor
public class FallasTecnicasService {
  private IFallasTecnicasRepository fallasTecnicasRepository;

  public List<FallaTecnicaDto> obtenerTodos(){
    return this.fallasTecnicasRepository.buscarTodos().stream().map(FallaTecnicaDto::fromFalla).toList();
  }

  public void crear(FallaTecnicaDto dto){
    // TODO
    FallaTecnica falla = new FallaTecnica();

    falla.reportar();

    this.fallasTecnicasRepository.guardar(falla);
  }


}
