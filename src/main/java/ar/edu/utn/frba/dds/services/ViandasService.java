package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.colaboraciones.IngresoViandaDto;
import ar.edu.utn.frba.dds.dtos.colaboraciones.ViandaDto;
import ar.edu.utn.frba.dds.exceptions.FormIncompletoException;
import ar.edu.utn.frba.dds.exceptions.HeladeraLlenaException;
import ar.edu.utn.frba.dds.helpers.DateHelper;
import ar.edu.utn.frba.dds.models.domain.heladeras.IngresoVianda;
import ar.edu.utn.frba.dds.models.domain.heladeras.Vianda;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeFechaInvalidaFactory;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeHeladeraLLenaFactory;
import ar.edu.utn.frba.dds.models.repositories.IViandasRepository;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@AllArgsConstructor
public class ViandasService {
    private IViandasRepository viandasRepository;
    private HeladerasService heladerasService;
    private SolicitudAperturaHeladeraService solicitudAperturaHeladeraService;
    private ColaboradoresService colaboradoresService;

    public void crearIngresoViandas(IngresoViandaDto dto) {
        IngresoVianda ingreso = new IngresoVianda();

        ingreso.setFechaDonacion(DateHelper.fechaFromString(dto.getFechaDonacion(), "dd/MM/yyyy"));

        if (ingreso.getFechaDonacion().isBefore(LocalDate.now().minusDays(1)))
            throw new FormIncompletoException(MensajeFechaInvalidaFactory.generarMensaje(), dto);

        ingreso.setHeladera(heladerasService.obtenerHeladera(dto.getHeladeraDto().getId()));

        ingreso.setColaborador(this.colaboradoresService.obtenerColaborador(dto.getIdColaborador()));

        dto.getViandas().forEach(v -> this.crearVianda(ingreso, v));

        if (ingreso.getHeladera().getCuposLibresViandas() < ingreso.getViandas().size())
            throw new HeladeraLlenaException(MensajeHeladeraLLenaFactory.generarMensaje(ingreso.getHeladera().getNombre()));

        this.viandasRepository.guardar(ingreso.getViandas());

        this.solicitudAperturaHeladeraService.generarSolicitud(ingreso);

    }


    public void crearVianda(IngresoVianda ingreso, ViandaDto dto) {
        Vianda vianda = new Vianda();
        vianda.setComida(dto.getDesc());
        vianda.setFechaCaducidad(DateHelper.fechaFromString(dto.getFechaCaducidad(), "dd/MM/yyyy"));
        if (vianda.getFechaCaducidad().isBefore(LocalDate.now()))
            throw new FormIncompletoException(MensajeFechaInvalidaFactory.generarMensaje());
        vianda.setFechaDonacion(ingreso.getFechaDonacion());

        vianda.setHeladera(ingreso.getHeladera());

        vianda.setCalorias(dto.getCalorias());
        vianda.setPeso(dto.getPeso());
        vianda.setColaborador(ingreso.getColaborador());

        ingreso.agregarViandas(vianda);

    }
}
