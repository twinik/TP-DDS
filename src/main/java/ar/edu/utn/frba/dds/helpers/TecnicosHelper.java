package ar.edu.utn.frba.dds.helpers;

import ar.edu.utn.frba.dds.domain.serviceLocator.ServiceLocator;
import ar.edu.utn.frba.dds.domain.tecnicos.Tecnico;
import ar.edu.utn.frba.dds.domain.utils.Ubicacion;
import ar.edu.utn.frba.dds.repositories.ITecnicosRepository;

public class TecnicosHelper {

  public static Tecnico findTecnicoMasCercano(Ubicacion ubicacion) {
    // TODO implementar de verdad?
    ITecnicosRepository tecnicosRepository = (ITecnicosRepository) ServiceLocator.get("TecnicosRepository");
    return tecnicosRepository.buscarTodos().get(0);
  }
}
