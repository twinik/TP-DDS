package ar.edu.utn.frba.dds.domain.tarjetas;

import ar.edu.utn.frba.dds.domain.tarjetas.FrecuenciaUso;
import ar.edu.utn.frba.dds.domain.tarjetas.Tarjeta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class FrecuenciaDiaria implements FrecuenciaUso {
    public FrecuenciaDiaria() {
    }

    private static Integer MAXIMOS_USOS_BASE;

//    private Integer usosEnElDia;
    public boolean permiteUsar(Tarjeta tarjeta) {
        Integer usosTotalesPermitidos = this.MAXIMOS_USOS_BASE + tarjeta.getDuenio().cantidadMenores() * 2;
        return this.usosEnElDia(tarjeta) < usosTotalesPermitidos;
    }

    private Integer usosEnElDia(Tarjeta tarjeta) {
        return Math.toIntExact(tarjeta.getUsos().stream().filter(u -> u.getFechaUso().toLocalDate().equals(LocalDate.now())).count());
    }

}