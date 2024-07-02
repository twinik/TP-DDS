package ar.edu.utn.frba.dds.helpers;

import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import ar.edu.utn.frba.dds.domain.tecnicos.AreaDeCobertura;
import ar.edu.utn.frba.dds.domain.tecnicos.Tecnico;
import ar.edu.utn.frba.dds.domain.utils.Ubicacion;
import ar.edu.utn.frba.dds.repositories.ITecnicosRepository;
import java.util.List;

public class TecnicosHelper {

  public static Tecnico findTecnicoMasCercano(Ubicacion ubicacion) {
    ITecnicosRepository tecnicosRepository = (ITecnicosRepository) ServiceLocator.get("tecnicosRepository");
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