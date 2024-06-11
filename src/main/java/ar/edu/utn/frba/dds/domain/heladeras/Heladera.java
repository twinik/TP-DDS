package ar.edu.utn.frba.dds.domain.heladeras;

import ar.edu.utn.frba.dds.domain.utils.Direccion;
import ar.edu.utn.frba.dds.domain.utils.Ubicacion;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Heladera class permite representar una heladera.
 */
@Getter
@Setter
@AllArgsConstructor
public class Heladera {
  private Ubicacion ubicacion;
  private Direccion direccion;
  private boolean activa = true;
  private String nombre;
  private Integer capacidadViandas;
  private LocalDate fechaPuestaFuncionamiento;
  private List<Vianda> viandas;
  private ModeloHeladera modelo;
  private List<RegistroTemperatura> registroTemperaturas = new ArrayList<>();
  private List<SolicitudAperturaHeladera> solicitudesApertura = new ArrayList<>();

  public Heladera(LocalDate fecha) {
    this.fechaPuestaFuncionamiento = fecha;
  }

  public Heladera(Ubicacion ubicacion, Direccion direccion, String nombre, Integer capacidadViandas,
                  LocalDate fechaPuestaFuncionamiento, List<Vianda> viandas,
                  ModeloHeladera modelo) {
    this.ubicacion = ubicacion;
    this.direccion = direccion;
    this.nombre = nombre;
    this.capacidadViandas = capacidadViandas;
    this.fechaPuestaFuncionamiento = fechaPuestaFuncionamiento;
    this.viandas = viandas;
    this.modelo = modelo;
  }

  public void agregarVianda(Vianda vianda) {
    this.viandas.add(vianda);
  }

  /**
   * Metodo que verifica la temperatura de la heladera.
   */
  public void verificarTemperatura(float temperatura) {
    if (temperatura > this.modelo.getTempMax() || temperatura < this.modelo.getTempMin()) {
      inhabilitar();
    }
    registroTemperaturas.add(new RegistroTemperatura(LocalDateTime.now(), temperatura));
  }

  public float getUltimaTemperaturaRegistrada() {
    // asumo que van en orden la lista, si no hay que comparar las fechas
    return this.registroTemperaturas.get(this.registroTemperaturas.size() - 1).temperaturaRegistrada;
  }

  public void inhabilitar() {
    this.activa = false;
  }

}