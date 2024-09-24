package ar.edu.utn.frba.dds.helpers;

import ar.edu.utn.frba.dds.models.domain.tecnicos.AreaDeCobertura;
import ar.edu.utn.frba.dds.models.domain.tecnicos.Tecnico;
import ar.edu.utn.frba.dds.models.domain.utils.Ubicacion;
import ar.edu.utn.frba.dds.models.repositories.ITecnicosRepository;
import lombok.AllArgsConstructor;
import java.util.List;

@AllArgsConstructor
public class TecnicosHelper {
    ITecnicosRepository tecnicosRepository;

    public Tecnico findTecnicoMasCercano(Ubicacion ubicacion) {
        List<Tecnico> tecnicos = tecnicosRepository.buscarTodos();

        Tecnico tecnicoMasCercano = null;
        float distanciaMinima = Float.MAX_VALUE;    //necesito un valor muy grande para al empezar a comparar me tome el primer valor

        for (Tecnico tecnico : tecnicos) {
            AreaDeCobertura areaDeCobertura = tecnico.getAreaDeCobertura();
            if (areaDeCobertura.contieneUbicacion(ubicacion)) {
                float distancia = areaDeCobertura.getReferencia().calcularDistanciaHasta(ubicacion);
                if (distancia < distanciaMinima) {
                    tecnicoMasCercano = tecnico;
                    distanciaMinima = distancia;
                }
            }
        }

        return tecnicoMasCercano;
    }
}