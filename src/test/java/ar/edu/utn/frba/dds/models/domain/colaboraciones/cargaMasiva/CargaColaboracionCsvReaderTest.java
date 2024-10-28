package ar.edu.utn.frba.dds.models.domain.colaboraciones.cargaMasiva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import ar.edu.utn.frba.dds.helpers.ConfigReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;

class CargaColaboracionCsvReaderTest {

  private CargaColaboracionCsvReader cargaColaboracionCsvReader;
  private String path;
  private String separator;

  @BeforeEach
  void setUp() {
    try {
      cargaColaboracionCsvReader = new CargaColaboracionCsvReader();
      ConfigReader config = new ConfigReader("config.properties");
      path = config.getProperty("cargadorColaboracionesFilePath");
      separator = config.getProperty("separator");
    } catch (IOException e) {
      fail("No debería haber lanzado excepción");
    }
  }

  @Test
  @DisplayName("Lectura de archivo csv")
  void readCsv() {
    List<Object> registros = cargaColaboracionCsvReader.readCsv(path, separator);
    assertFalse(registros.isEmpty(), "La lista de registros no debería estar vacía");
    assertEquals(7, registros.size(), "La lista de registros debería tener 7 elementos");
  }

}