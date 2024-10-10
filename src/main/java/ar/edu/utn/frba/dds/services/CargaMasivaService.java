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

  public String subirArchivo(UploadedFile uploadedFile) throws CargaArchivoFailedException {
    try {
      return this.service.uploadFile(uploadedFile);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  public void cargarColaboraciones(UploadedFile uploadedFile) throws CargaArchivoFailedException {
    String filePath = this.subirArchivo(uploadedFile);
    try {
      CargaColaboracionCsvReader csvReader = new CargaColaboracionCsvReader();
      SendGridMailSender mailSender = new SendGridMailSender();
      CargadorDeColaboraciones cargador = new CargadorDeColaboraciones(
          filePath.substring(1),
          csvReader,
          mailSender,
          this.colaboradoresRepository,
          this.formasColaboracionRespository,
          this.calculadorPuntos
      );

      cargador.cargarColaboraciones();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
