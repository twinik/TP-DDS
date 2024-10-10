package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.exceptions.CargaArchivoFailedException;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.calculadores.CalculadorPuntos;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.calculadores.ICalculadorPuntos;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.cargaMasiva.CargaColaboracionCsvReader;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.cargaMasiva.CargadorDeColaboraciones;
import ar.edu.utn.frba.dds.models.domain.emailSending.MailSenderAdapter;
import ar.edu.utn.frba.dds.models.domain.emailSending.SendGridMailSender;
import ar.edu.utn.frba.dds.models.repositories.IColaboradoresRepository;
import ar.edu.utn.frba.dds.models.repositories.IFormasColaboracionRespository;
import ar.edu.utn.frba.dds.models.repositories.imp.ColaboradoresRepository;
import ar.edu.utn.frba.dds.models.repositories.imp.FormasColaboracionRespository;
import io.javalin.http.UploadedFile;
import java.io.IOException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CargaMasivaService {
  private FileUploadService service;
  private IColaboradoresRepository colaboradoresRepository;
  private IFormasColaboracionRespository formasColaboracionRespository;
  private ICalculadorPuntos calculadorPuntos;

  public void subirArchivo(UploadedFile uploadedFile) throws CargaArchivoFailedException {
    try {
      this.service.uploadFile(uploadedFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void cargarColaboraciones(UploadedFile uploadedFile) throws CargaArchivoFailedException {
    try {
      CargaColaboracionCsvReader csvReader = new CargaColaboracionCsvReader();
      SendGridMailSender mailSender = new SendGridMailSender();
      CargadorDeColaboraciones cargador = new CargadorDeColaboraciones(
          "uploads/" + uploadedFile.filename(),
          csvReader,
          mailSender,
          colaboradoresRepository,
          formasColaboracionRespository,
          calculadorPuntos
      );

      cargador.cargarColaboraciones();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
