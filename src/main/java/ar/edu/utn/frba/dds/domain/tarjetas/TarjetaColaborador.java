package ar.edu.utn.frba.dds.domain.tarjetas;

import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.excepciones.NoTieneDireccionException;
import ar.edu.utn.frba.dds.domain.heladeras.AperturaHeladera;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

/**
 * representa una tarjeta que usa el colaborador para acceder a las heladeras
 */
@Getter
@Setter
@AllArgsConstructor
public class TarjetaColaborador {
    private String codigo;
    private Colaborador colaborador;
    private boolean activa;
    private List<AperturaHeladera> usos;

    public static TarjetaColaborador of(Colaborador colaborador, String codigo) throws NoTieneDireccionException {
        if(colaborador.getDireccion() == null)
            throw new NoTieneDireccionException("Debe tener seteada una direccion para poder enviarle la tarjeta");
        return new TarjetaColaborador(codigo,colaborador,true,new ArrayList<>());
    }

}