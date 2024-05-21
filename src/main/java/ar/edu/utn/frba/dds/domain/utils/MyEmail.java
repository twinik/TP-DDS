package ar.edu.utn.frba.dds.domain.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MyEmail {
  private String emisor;
  private String receptor;
  private String asunto;
  private String cuerpo;

}
