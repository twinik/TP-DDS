package ar.edu.utn.frba.dds.domain.helpers.validaciones;

import ar.edu.utn.frba.dds.domain.helpers.ConfigReader;
import ar.edu.utn.frba.dds.domain.helpers.MotivoNoValido;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ValidacionListaClavesNuevo implements Validacion{

    @Getter
    private MotivoNoValido motivo = new MotivoNoValido("La clave aparece en la lista de las 10.000 peores contrasenias");
    private List<String> peoresContrasenias = new ArrayList<>();
    private ConfigReader config;


    public ValidacionListaClavesNuevo() {
        this.config = new ConfigReader();
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