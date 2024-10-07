package ar.edu.utn.frba.dds.dtos.personas;

import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ColaboradorPuntosDto {
  private Float puntos;
  public static ColaboradorPuntosDto fromColaborador(Colaborador c){
    return new ColaboradorPuntosDto(c.getPuntosGanados());
  }
}
