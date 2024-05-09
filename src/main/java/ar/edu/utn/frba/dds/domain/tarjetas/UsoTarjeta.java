package ar.edu.utn.frba.dds.domain.tarjetas;

import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@AllArgsConstructor
public class UsoTarjeta {

  private LocalDateTime fechaUso;

  private Heladera heladera;

}