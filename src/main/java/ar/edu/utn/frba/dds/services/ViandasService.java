package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.colaboraciones.ViandaDto;
import ar.edu.utn.frba.dds.exceptions.FormIncompletoException;
import ar.edu.utn.frba.dds.helpers.DateHelper;
import ar.edu.utn.frba.dds.models.domain.heladeras.Vianda;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeFechaInvalidaFactory;
import ar.edu.utn.frba.dds.models.repositories.IDonacionesViandaRepository;
import ar.edu.utn.frba.dds.models.repositories.IViandasRepository;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@AllArgsConstructor
public class ViandasService {
  private IViandasRepository viandasRepository;
  private IDonacionesViandaRepository donacionesViandaRepository;
  private HeladerasService heladerasService;
  private SolicitudAperturaHeladeraService solicitudAperturaHeladeraService;

  public void crearVianda(ViandaDto dto) {
    Vianda vianda = new Vianda();
    vianda.setComida(dto.getDesc());
    vianda.setFechaCaducidad(DateHelper.fechaFromString(dto.getFechaCaducidad(), "dd/MM/yyyy"));
    if (vianda.getFechaCaducidad().isBefore(LocalDate.now()))
      throw new FormIncompletoException(MensajeFechaInvalidaFactory.generarMensaje());
    vianda.setFechaDonacion(DateHelper.fechaFromString(dto.getFechaDonacion(), "dd/MM/yyyy"));
    if (vianda.getFechaDonacion().isBefore(LocalDate.now()))
      throw new FormIncompletoException(MensajeFechaInvalidaFactory.generarMensaje());
    vianda.setCalorias(dto.getCalorias());
    vianda.setPeso(dto.getPeso());
    vianda.setHeladera(heladerasService.obtenerHeladera(dto.getHeladeraDto().getId()));

    this.viandasRepository.guardar(vianda);

    this.solicitudAperturaHeladeraService.generarSolicitud(vianda,dto.getIdColaborador());

  }
}
