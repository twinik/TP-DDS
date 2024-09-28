package ar.edu.utn.frba.dds.services;

import io.javalin.http.UploadedFile;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUploadService {

    public String uploadFile(UploadedFile uploadedFile, String path) throws IOException {
        if (uploadedFile == null) {
            return "No se subió ningún archivo";
        }

        Path currentPath = Paths.get(System.getProperty("user.dir"));
        Path filePath = currentPath.resolve(path + uploadedFile.filename());

        try (FileOutputStream out = new FileOutputStream(filePath.toString())) {
            out.write(uploadedFile.content().readAllBytes());
        }

        return "El archivo: " + filePath + " se subio correctamente";
    }
}