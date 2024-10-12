package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.tecnicos.VisitaTecnicoDto;
import ar.edu.utn.frba.dds.models.repositories.IVisitasTecnicoRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class VisitasTecnicosService {
    private IVisitasTecnicoRepository visitasTecnicoRepository;

    public void crearVisitaTecnico(VisitaTecnicoDto dto) {
    }
}
