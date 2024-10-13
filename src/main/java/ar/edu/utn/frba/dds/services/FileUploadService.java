package ar.edu.utn.frba.dds.services;

import io.javalin.http.UploadedFile;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class FileUploadService {

    public String uploadFile(UploadedFile uploadedFile) throws IOException {
        //Path currentPath = Paths.get(System.getProperty("user.dir"));
        String filePath = "uploads/" + UUID.randomUUID() + "-" + uploadedFile.filename();
        filePath = filePath.replace(' ', '_'); // a veces los espacios generan problemas
        try (FileOutputStream out = new FileOutputStream(filePath)) {
            out.write(uploadedFile.content().readAllBytes());
        }
        return '/' + filePath;
    }
}
