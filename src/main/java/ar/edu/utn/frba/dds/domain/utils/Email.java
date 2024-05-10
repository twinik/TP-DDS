package ar.edu.utn.frba.dds.domain.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Email {
  private String emisor;
  private String receptor;
  private String asunto;
  private String cuerpo;

}
