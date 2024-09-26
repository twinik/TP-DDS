package ar.edu.utn.frba.dds.models.domain.pdfs;

import ar.edu.utn.frba.dds.models.domain.excepciones.GenerarPdfException;

/**
 *
 */
public interface IPDFGeneratorAdapter {
    void generarPdf(String nombreArchivo, String titulo, String cuerpo) throws GenerarPdfException;
}