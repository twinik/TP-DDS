package ar.edu.utn.frba.dds.domain.tarjetas;

import ar.edu.utn.frba.dds.domain.PersonaVulnerable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import java.util.*;

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

  public class ResetarUsosDia implements Job {
    public void execute(JobExecutionContext context) throws JobExecutionException {
      cantidadUsosDia = 0;
    }
  }

}