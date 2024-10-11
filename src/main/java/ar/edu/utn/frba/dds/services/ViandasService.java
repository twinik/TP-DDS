package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.models.repositories.IDonacionDineroRepository;
import ar.edu.utn.frba.dds.models.repositories.IDonacionesViandaRepository;
import ar.edu.utn.frba.dds.models.repositories.IViandasRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ViandasService {
  private IViandasRepository viandasRepository;
  private IDonacionesViandaRepository donacionesViandaRepository;
}
