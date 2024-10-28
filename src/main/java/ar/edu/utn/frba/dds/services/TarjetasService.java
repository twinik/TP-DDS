package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.colaboraciones.AltaPersonaVulnerableDto;
import ar.edu.utn.frba.dds.exceptions.DniDuplicadoException;
import ar.edu.utn.frba.dds.models.domain.PersonaVulnerable;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.domain.excepciones.CodigoInvalidoException;
import ar.edu.utn.frba.dds.models.domain.tarjetas.FrecuenciaDiaria;
import ar.edu.utn.frba.dds.models.domain.tarjetas.PosibleCodigoTarjeta;
import ar.edu.utn.frba.dds.models.domain.tarjetas.Tarjeta;
import ar.edu.utn.frba.dds.models.domain.tarjetas.TarjetaColaborador;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeCodigoDuplicadoFactory;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeCodigosNoDisponiblesFactory;
import ar.edu.utn.frba.dds.models.repositories.IPosiblesCodigosTarjetaRepository;
import ar.edu.utn.frba.dds.models.repositories.ITarjetasColaboradorRepository;
import ar.edu.utn.frba.dds.models.repositories.ITarjetasRepository;
import lombok.AllArgsConstructor;
import java.util.Optional;

@AllArgsConstructor
public class TarjetasService {
  private ITarjetasRepository tarjetasRepository;
  private ITarjetasColaboradorRepository tarjetasColaboradorRepository;
  private IPosiblesCodigosTarjetaRepository posiblesCodigosTarjetaRepository;

  public void crearTarjeta(PersonaVulnerable vulnerable, AltaPersonaVulnerableDto dto) {
    Optional<Tarjeta> posibleTarjeta = this.tarjetasRepository.buscarPorCodigo(dto.getTarjeta().getCodigo());
    if (posibleTarjeta.isPresent())
      throw new CodigoInvalidoException(MensajeCodigoDuplicadoFactory.generarMensaje(), dto);

    Tarjeta t = null;
    try {
      t = Tarjeta.of(dto.getTarjeta().getCodigo(), 0, new FrecuenciaDiaria(), vulnerable);
    } catch (CodigoInvalidoException e) {
      throw new CodigoInvalidoException(e.getMessage(), dto);
    }
    tarjetasRepository.guardar(t);
  }

  public void asignarTarjetaColaborador(Colaborador c) {
    Optional<PosibleCodigoTarjeta> codigo = this.posiblesCodigosTarjetaRepository.buscarPrimeroLibre();
    if (codigo.isEmpty()) throw new CodigoInvalidoException(MensajeCodigosNoDisponiblesFactory.generarMensaje());
    codigo.get().ocupar();
    TarjetaColaborador tarjetaColaborador = TarjetaColaborador.of(c, codigo.get().getCodigo());
    this.tarjetasColaboradorRepository.guardar(tarjetaColaborador);
  }
}
