package ar.edu.utn.frba.dds.domain.heladeras;

import ar.edu.utn.frba.dds.domain.utils.Direccion;
import ar.edu.utn.frba.dds.domain.utils.Ubicacion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
public class Heladera {

  private Ubicacion ubicacion;

  private Direccion direccion;

  private String nombre;

  private Integer capacidadViandas;

  private LocalDate fechaPuestaFuncionamiento;

  private List<Vianda> viandas;

  private Float tempMin;

  private Float tempMax;

  private SensorTemperatura sensorTemp;

  private SensorMovimiento sensorMov;

  public Heladera(LocalDate fecha) {
    this.fechaPuestaFuncionamiento = fecha;
  }

  public void agregarVianda(Vianda vianda) {
    this.viandas.add(vianda);
  }

  public void verificarTemperatura() {
    if (this.sensorTemp.getUtlimaTempRegistrada() > this.tempMax || this.sensorTemp.getUtlimaTempRegistrada() < this.tempMin) {
      System.console().printf("La temperatura esta fuera del rango aceptable!\n");
    }
  }


}