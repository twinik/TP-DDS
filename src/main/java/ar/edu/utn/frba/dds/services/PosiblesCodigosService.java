package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.PosibleCodigoTarjetaDto;
import ar.edu.utn.frba.dds.models.domain.excepciones.CodigoInvalidoException;
import ar.edu.utn.frba.dds.models.domain.tarjetas.PosibleCodigoTarjeta;
import ar.edu.utn.frba.dds.models.repositories.IPosiblesCodigosTarjetaRepository;
import lombok.AllArgsConstructor;
import java.util.Optional;

@AllArgsConstructor
public class PosiblesCodigosService {
    private IPosiblesCodigosTarjetaRepository posiblesCodigosTarjetaRepository;
    public void crearPosibleCodigo(PosibleCodigoTarjetaDto dto){
        Optional<PosibleCodigoTarjeta> anterior = this.posiblesCodigosTarjetaRepository.buscarPorCodigo(dto.getCodigo());
        if(anterior.isPresent()) throw new CodigoInvalidoException("ya existe una tarjeta con este codigo");
        PosibleCodigoTarjeta p = new PosibleCodigoTarjeta(dto.getCodigo());
        this.posiblesCodigosTarjetaRepository.guardar(p);
    }
}
