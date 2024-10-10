package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.colaboraciones.AltaPersonaVulnerableDto;
import ar.edu.utn.frba.dds.dtos.colaboraciones.TarjetaInputDto;
import ar.edu.utn.frba.dds.models.domain.PersonaVulnerable;
import ar.edu.utn.frba.dds.models.domain.tarjetas.FrecuenciaDiaria;
import ar.edu.utn.frba.dds.models.domain.tarjetas.Tarjeta;
import ar.edu.utn.frba.dds.models.repositories.ITarjetasRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TarjetasService {
    private ITarjetasRepository tarjetasRepository;

    public void crearTarjeta(PersonaVulnerable vulnerable, TarjetaInputDto dto){
        Tarjeta t = Tarjeta.of(dto.getCodigo(),0,new FrecuenciaDiaria(),vulnerable);
        tarjetasRepository.guardar(t);
    }
}
