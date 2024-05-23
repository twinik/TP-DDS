package ar.edu.utn.frba.dds.domain.tarjetas;

import ar.edu.utn.frba.dds.domain.PersonaVulnerable;
import java.util.*;
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

  private FrecuenciaUso frecuenciaPermitida;

  private List<UsoTarjeta> usos;

  private PersonaVulnerable duenio;

  private Date fechaAdjudicacion;

  private Integer cantidadUsosDia;
  // CRON JOB string (todos los dias a las 00:00hs): "0 0 0 1/1 * ? *"

  /**
   * Constructor de Tarjeta.
   */
  public Tarjeta(String codigo, Integer nroUsos, FrecuenciaUso frecuenciaPermitida, PersonaVulnerable duenio, Date fechaAdjudicacion, Integer cantidadUsosDia) {
    this.codigo = codigo;
    this.nroUsos = nroUsos;
    this.frecuenciaPermitida = frecuenciaPermitida;
    this.usos = new ArrayList<UsoTarjeta>();
    this.duenio = duenio;
    this.fechaAdjudicacion = fechaAdjudicacion;
    this.cantidadUsosDia = 0;

    try {
      Trigger trigger = TriggerBuilder.newTrigger()
          .withSchedule(CronScheduleBuilder.cronSchedule("0 0 0 1/1 * ? *"))
          .build();

      JobDetail job = JobBuilder.newJob(ResetarUsosDia.class)
          .build();

      Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

      scheduler.scheduleJob(job, trigger);

      scheduler.start();
    } catch (SchedulerException e) {
      System.console().printf("Error al crear cron job en Tarjeta: %s", e.getMessage());
    }
  }

  /**
   * Resetea la cantidad de usos del dia.
   */
  public class ResetarUsosDia implements Job {
    public void execute(JobExecutionContext context) {
      cantidadUsosDia = 0;
    }
  }
}