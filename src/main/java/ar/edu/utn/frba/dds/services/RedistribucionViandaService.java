package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.colaboraciones.RedistribucionViandaDto;
import ar.edu.utn.frba.dds.exceptions.FormIncompletoException;
import ar.edu.utn.frba.dds.helpers.DateHelper;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.RedistribucionViandas;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.calculadores.ICalculadorPuntos;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.utils.MotivoRedistribucionVianda;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeFechaInvalidaFactory;
import ar.edu.utn.frba.dds.models.repositories.IRedistribucionesViandaRepository;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@AllArgsConstructor
public class RedistribucionViandaService {
  private IRedistribucionesViandaRepository redistribucionesViandaRepository;
  private ColaboradoresService colaboradoresService;
  private HeladerasService heladerasService;
  private MotivoRedistribucionService motivoRedistribucionService;
  private SolicitudAperturaHeladeraService solicitudAperturaHeladeraService;
  private ICalculadorPuntos calculadorPuntos;

  public void solicitarRedistribucion(RedistribucionViandaDto dto) {
    RedistribucionViandas redistribucionViandas = new RedistribucionViandas();
    redistribucionViandas.setColaborador(this.colaboradoresService.obtenerColaborador(dto.getIdColaborador()));
    redistribucionViandas.setHeladeraOrigen(this.heladerasService.obtenerHeladera(dto.getOrigen().getId()));
    redistribucionViandas.setHeladeraDestino(this.heladerasService.obtenerHeladera(dto.getDestino().getId()));
    redistribucionViandas.setFecha(DateHelper.fechaFromString(dto.getFecha(), "dd/MM/yyyy"));
    if (redistribucionViandas.getFecha().isBefore(LocalDate.now().minusDays(1)))
      throw new FormIncompletoException(MensajeFechaInvalidaFactory.generarMensaje(), dto);
    redistribucionViandas.setCantidad(dto.getCantViandas());
    if (dto.getIdMotivo() != null)
      redistribucionViandas.setMotivo(this.motivoRedistribucionService.obtenerMotivo(dto.getIdMotivo()));
    redistribucionViandas.validarCantidades(dto);
    this.redistribucionesViandaRepository.guardar(redistribucionViandas);
    this.solicitudAperturaHeladeraService.generarSolicitud(redistribucionViandas);

  }

  public void efectuarRedistribucionOrigen(RedistribucionViandas redistribucionViandas) {
    redistribucionViandas.redistribuirEnOrigen();
    this.heladerasService.actualizarHeladera(redistribucionViandas.getHeladeraOrigen());

  }

  public void efecutarRedistribucionDestino(RedistribucionViandas redistribucionViandas) {
    redistribucionViandas.redistribuirEnDestino();
    this.calculadorPuntos.sumarPuntosPara(redistribucionViandas.getColaborador(), redistribucionViandas);
    this.heladerasService.actualizarHeladera(redistribucionViandas.getHeladeraDestino());
    // no me importa la capacidad maxima, ya se arreglara alguien

  }

}
