package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.heladeras.HeladeraMapa;
import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.models.repositories.IHeladerasRepository;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class HeladerasService {

  IHeladerasRepository repoHeladeras;

  public List<HeladeraMapa> getHeladerasParaMapa()
  {
    List<Heladera> heladeras = repoHeladeras.buscarTodos();
    List<HeladeraMapa> resultado = heladeras.stream().map(HeladeraMapa::fromHeladera).collect(Collectors.toList());
    return resultado;
  }
}
