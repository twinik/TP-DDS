package ar.edu.utn.frba.dds.domain.tarjetas;

import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.excepciones.NoTieneDireccionException;
import ar.edu.utn.frba.dds.domain.heladeras.AperturaHeladera;
import ar.edu.utn.frba.dds.messageFactory.MensajeExcepcionDireccionTarjetaFactory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * representa una tarjeta que usa el colaborador para acceder a las heladeras
 */
@Getter
@Setter
@AllArgsConstructor
public class TarjetaColaborador {
  private String codigo;
  private Colaborador colaborador;
  private boolean activa;
  private LocalDate fechaAlta;
  private LocalDate fechaBaja;
  private List<AperturaHeladera> usos;


  public static TarjetaColaborador of(Colaborador colaborador, String codigo) throws NoTieneDireccionException {
    if (colaborador.getDireccion() == null)
      throw new NoTieneDireccionException(MensajeExcepcionDireccionTarjetaFactory.generarMensaje());
    return new TarjetaColaborador(codigo, colaborador, true, LocalDate.now(), null, new ArrayList<>());
  }

  public static TarjetaColaborador of(Colaborador colaborador, String codigo, LocalDate fechaAlta) throws NoTieneDireccionException {
    if (colaborador.getDireccion() == null)
      throw new NoTieneDireccionException(MensajeExcepcionDireccionTarjetaFactory.generarMensaje());
    return new TarjetaColaborador(codigo, colaborador, true, fechaAlta, null, new ArrayList<>());
  }

}