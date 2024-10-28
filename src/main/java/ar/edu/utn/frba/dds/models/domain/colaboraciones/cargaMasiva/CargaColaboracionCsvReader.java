package ar.edu.utn.frba.dds.models.domain.colaboraciones.cargaMasiva;

import ar.edu.utn.frba.dds.helpers.DateHelper;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * CargaColaboracionCsvReader class lee un archivo csv de colaboraciones.
 */
public class CargaColaboracionCsvReader implements CSVReaderAdapter {

  @Override
  public List<Object> readCsv(String path, String separator) {
    List<Object> registros = new ArrayList<>();
    try (Scanner scanner = new Scanner(new File(path))) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] values = line.split(separator, 9);
        if (values.length < 9) {
          // Log error: Incorrect number of fields
          continue;
        }

        CargaColaboracion cargaColaboracion = new CargaColaboracion();
        try {
          cargaColaboracion.setTipoDocumento(values[0]);
          cargaColaboracion.setDocumento(values[1]);
          cargaColaboracion.setNombre(values[2]);
          cargaColaboracion.setApellido(values[3]);
          cargaColaboracion.setMail(values[4]);
          cargaColaboracion.setFechaColaboracion(DateHelper.fechaFromString(values[5], "dd/MM/yyyy"));
          cargaColaboracion.setFormaColaboracion(values[6]);
          cargaColaboracion.setCantidad(Integer.valueOf(values[7]));
          cargaColaboracion.setJsonColaboracion(values[8]);

          registros.add(cargaColaboracion);
        } catch (NumberFormatException e) {
          System.err.println("Error al parsear un número en la línea: " + line);
        }
      }
    } catch (FileNotFoundException e) {
      throw new RuntimeException("Archivo de colaboraciones no encontrado", e);
    }
    return registros;
  }
}