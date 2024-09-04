package ar.edu.utn.frba.dds.helpers.validaciones;

import ar.edu.utn.frba.dds.helpers.ConfigReader;
import ar.edu.utn.frba.dds.helpers.MotivoNoValido;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * ValidacionListaClavesNuevo class permite representar una validacion de lista de claves.
 */
public class ValidacionListaClavesNuevo extends Validacion {
    private List<String> peoresContrasenias = new ArrayList<>();
    private final ConfigReader config;

    /**
     * Constructor.
     */
    public ValidacionListaClavesNuevo() {
        this.setMotivo(new MotivoNoValido("La clave aparece en la lista de las 10.000 peores contrasenias"));
        this.config = new ConfigReader("config.properties");
        cargarPeoresContrasenias();
    }

    private void cargarPeoresContrasenias() {
        String archivo = null;
        try {
            archivo = this.config.getProperty("worstPasswordsFilePath");
        } catch (IOException e) {
            throw new RuntimeException("No se pudo leer la configuración", e);
        }

        try (Scanner scanner = new Scanner(new File(archivo))) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                peoresContrasenias.add(linea);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Archivo de contraseñas no encontrado", e);
        }

    }

    public boolean validar(String clave) {
        return !peoresContrasenias.contains(clave);
    }
}