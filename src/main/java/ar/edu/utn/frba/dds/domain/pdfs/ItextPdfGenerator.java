package ar.edu.utn.frba.dds.domain.pdfs;

import ar.edu.utn.frba.dds.domain.excepciones.GenerarPdfException;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.NoArgsConstructor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@NoArgsConstructor

public class ItextPdfGenerator implements IPDFGeneratorAdapter {

    public static void main(String[] args) {
        String dest = "example.pdf";
        String title = "Reporte cantidad de viandas por colaborador";
        String body = "Colaborador 1: dono 15\nColaborador 2: dono 20\nColaborador 3: dono 55";
        ItextPdfGenerator generador = new ItextPdfGenerator();

        try {
            generador.generarPdf(dest, title, body);
            System.out.println("PDF created successfully!");
        } catch (GenerarPdfException e) {
            e.printStackTrace();
        }
    }

    public void generarPdf(String rutaArchivo, String tituloPdf, String cuerpo) throws GenerarPdfException {
        // Initialize document
        Document document = new Document();


        try {
            PdfWriter.getInstance(document, new FileOutputStream(rutaArchivo));
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new GenerarPdfException(e);
        }


        document.open();


        Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
        Paragraph titulo = new Paragraph(tituloPdf, fuenteTitulo);
        titulo.setAlignment(Element.ALIGN_CENTER);
        try {
            document.add(titulo);
        } catch (DocumentException e) {
            throw new GenerarPdfException(e);
        }

        Font fuenteBody = FontFactory.getFont(FontFactory.HELVETICA, 12);
        Paragraph ParrafoCuerpo = new Paragraph(cuerpo, fuenteBody);
        ParrafoCuerpo.setAlignment(Element.ALIGN_LEFT);
        try {
            document.add(ParrafoCuerpo);
        } catch (DocumentException e) {
            throw new GenerarPdfException(e);
        }


        document.close();
    }
}
