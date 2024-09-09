package ar.edu.utn.frba.dds.domain.tecnicos;

import ar.edu.utn.frba.dds.domain.utils.Ubicacion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

/**
 * AreaDeCobertura class permite representar el area de cobertura de un tecnico.
 */
@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AreaDeCobertura {

    @Embedded
    private Ubicacion referencia;

    @Column(name = "radio_de_cobertura_en_km")
    private Float radioDeCoberturaEnKM;

    public boolean contieneUbicacion(Ubicacion ubicacion) {
        float distancia = referencia.calcularDistanciaHasta(ubicacion);
        return distancia <= radioDeCoberturaEnKM;
    }
}