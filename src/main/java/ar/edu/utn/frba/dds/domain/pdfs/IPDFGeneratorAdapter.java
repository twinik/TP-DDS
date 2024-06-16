package ar.edu.utn.frba.dds.domain.pdfs;

import ar.edu.utn.frba.dds.domain.excepciones.GenerarPdfException;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;

/**
 * 
 */
public interface IPDFGeneratorAdapter {
    void generarPdf(String nombreArchivo, String titulo, String cuerpo) throws GenerarPdfException;
}