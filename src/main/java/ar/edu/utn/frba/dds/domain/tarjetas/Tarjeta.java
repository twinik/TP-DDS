package ar.edu.utn.frba.dds.domain.tarjetas;

import ar.edu.utn.frba.dds.domain.PersonaVulnerable;
import java.util.*;
import ar.edu.utn.frba.dds.domain.helpers.GeneradorDeCodigosHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Tarjeta class permite representar una tarjeta.
 */
@Getter
@Setter
@AllArgsConstructor
public class Tarjeta {
  private String codigo;
  private Integer nroUsos;
  private boolean activa;
  private FrecuenciaUso frecuenciaPermitida;
  private List<UsoTarjeta> usos;
  private PersonaVulnerable duenio;
  private Date fechaAdjudicacion;
  private Integer cantidadUsosDia;
  private static final int TAMANIO_CODIGO = 11;
  // CRON JOB string (todos los dias a las 00:00hs): "0 0 0 1/1 * ? *"

  /**
   * Constructor de Tarjeta.
   */
  public Tarjeta(Integer nroUsos, FrecuenciaUso frecuenciaPermitida, PersonaVulnerable duenio, Date fechaAdjudicacion, Integer cantidadUsosDia) {
    this.codigo = GeneradorDeCodigosHelper.generarAlfanumericoUnico(TAMANIO_CODIGO);
    this.nroUsos = nroUsos;
    this.frecuenciaPermitida = frecuenciaPermitida;
    this.activa = true;
    this.usos = new ArrayList<UsoTarjeta>();
    this.duenio = duenio;
    this.fechaAdjudicacion = fechaAdjudicacion;
    this.cantidadUsosDia = 0;
  }

  public void agregarUsos() {
    this.cantidadUsosDia++;
    this.nroUsos++;
  }

  boolean permiteUsar() {
    return this.activa && frecuenciaPermitida.permiteUsar(this);
  }

}