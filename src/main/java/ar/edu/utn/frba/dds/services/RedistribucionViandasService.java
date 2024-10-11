package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.models.repositories.IRedistribucionesViandaRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RedistribucionViandasService {
  private IRedistribucionesViandaRepository redistribucionesViandaRepository;

}
