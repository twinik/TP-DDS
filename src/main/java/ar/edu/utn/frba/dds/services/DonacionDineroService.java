package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.colaboraciones.DonacionDineroInputDto;
import ar.edu.utn.frba.dds.exceptions.FormIncompletoException;
import ar.edu.utn.frba.dds.helpers.DateHelper;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.DonacionDinero;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.calculadores.ICalculadorPuntos;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.utils.FrecuenciaDonacion;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeFechaInvalidaFactory;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeFormIncompletoFactory;
import ar.edu.utn.frba.dds.models.repositories.IDonacionDineroRepository;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@AllArgsConstructor
public class DonacionDineroService {
    private IDonacionDineroRepository donacionDineroRepository;
    private ColaboradoresService colaboradoresService;
    private ICalculadorPuntos calculadorPuntos;

    public void crearDonacionDinero(DonacionDineroInputDto dto) {
        Colaborador c = this.colaboradoresService.obtenerColaborador(dto.getIdColaborador());

        if (!dto.estanCamposLlenos()) throw new FormIncompletoException(MensajeFormIncompletoFactory.generarMensaje());

        DonacionDinero donacion = new DonacionDinero();
        donacion.setColaborador(c);

        if (dto.getFecha() != null){
            donacion.setFecha(DateHelper.fechaFromString(dto.getFecha(), "dd/MM/yyyy"));
            if(donacion.getFecha().isBefore(LocalDate.now()))
                throw new FormIncompletoException(MensajeFechaInvalidaFactory.generarMensaje());
        }
        donacion.setMonto(dto.getMonto());
        donacion.setFrecuencia(FrecuenciaDonacion.fromOrdinal(dto.getFrecuenciaDonacion()));
        this.calculadorPuntos.sumarPuntosPara(c, donacion);
        this.donacionDineroRepository.guardar(donacion);
    }
}
