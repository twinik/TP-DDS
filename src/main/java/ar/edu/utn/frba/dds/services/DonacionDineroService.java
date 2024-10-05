package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.colaboraciones.DonacionDineroInputDto;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.DonacionDinero;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.utils.FrecuenciaDonacion;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.repositories.IDonacionDineroRepository;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
public class DonacionDineroService {
  private IDonacionDineroRepository donacionDineroRepository;
  private ColaboradoresService colaboradoresService;

  public void crearDonacionDinero(DonacionDineroInputDto dto, String idUsuario) {
    Colaborador c = this.colaboradoresService.colaboradorFromUsuario(idUsuario);


    DonacionDinero donacion = new DonacionDinero();
    donacion.setColaborador(c);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    donacion.setFecha(LocalDate.parse(dto.getFecha(),formatter));
    donacion.setMonto(dto.getMonto());
    donacion.setFrecuencia(FrecuenciaDonacion.fromOrdinal(dto.getFrecuenciaDonacion()));

    this.donacionDineroRepository.guardar(donacion);
  }
}
