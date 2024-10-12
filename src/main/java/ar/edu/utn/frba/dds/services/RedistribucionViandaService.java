package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.models.repositories.IRedistribucionesViandaRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RedistribucionViandaService {
    private IRedistribucionesViandaRepository redistribucionesViandaRepository;
    private ColaboradoresService colaboradoresService;
    private HeladerasService heladerasService;
}
