package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.colaboraciones.DonacionDineroInputDto;
import ar.edu.utn.frba.dds.exceptions.FormIncompletoException;
import ar.edu.utn.frba.dds.exceptions.NoAutorizadoException;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.DonacionDinero;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.calculadores.ICalculadorPuntos;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.utils.FrecuenciaDonacion;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.repositories.IDonacionDineroRepository;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@AllArgsConstructor
public class DonacionDineroService {
  private IDonacionDineroRepository donacionDineroRepository;
  private ColaboradoresService colaboradoresService;
  private ICalculadorPuntos calculadorPuntos;

  public void crearDonacionDinero(DonacionDineroInputDto dto) {
    Optional<Colaborador> c = this.colaboradoresService.obtenerColaborador(dto.getIdColaborador());

    if (c.isEmpty()) throw new NoAutorizadoException("no hay colaborador asociado a este id");

    if(!dto.estanCamposLlenos()) throw new FormIncompletoException();

    DonacionDinero donacion = new DonacionDinero();
    donacion.setColaborador(c.get());

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    donacion.setFecha(LocalDate.parse(dto.getFecha(), formatter));
    donacion.setMonto(dto.getMonto());
    donacion.setFrecuencia(FrecuenciaDonacion.fromOrdinal(dto.getFrecuenciaDonacion()));
    this.calculadorPuntos.sumarPuntosPara(c.get(),donacion);
    this.donacionDineroRepository.guardar(donacion);
  }
}
