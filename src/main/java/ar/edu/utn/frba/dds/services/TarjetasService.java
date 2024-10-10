package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.models.domain.tarjetas.Tarjeta;
import ar.edu.utn.frba.dds.models.repositories.ITarjetasRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TarjetasService {
    private ITarjetasRepository tarjetasRepository;

    public Tarjeta crearTarjeta(String codigoTarjeta){
        return null;
    }
}
